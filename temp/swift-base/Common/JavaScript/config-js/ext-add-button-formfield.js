    /**
     * @class Ext.ux.form.field.AddButton
     *
     * Plugin for text components that shows a "Add" button over the text field.
     * When the button is clicked the text field is set empty.
     * Icon image and positioning can be controlled using CSS.
     * Works with Ext.form.field.Text, Ext.form.field.TextArea, Ext.form.field.ComboBox and Ext.form.field.Date.
     *
     * Plugin alias is 'Addbutton' (use "plugins: 'Addbutton'" in GridPanel config).
     *
     * @author <a href="mailto:stephen.friedrich@fortis-it.de">Stephen Friedrich</a>
     * @author <a href="mailto:fabian.urban@fortis-it.de">Fabian Urban</a>
     *
     * @copyright (c) 2011 Fortis IT Services GmbH
     * @license Ext.ux.form.field.AddButton is released under the
     * <a target="_blank" href="http://www.apache.org/licenses/LICENSE-2.0">Apache License, Version 2.0</a>.
     *
     */
Ext.form.Field.AddButton = function(config){
	//alert('constructor called');
	Ext.form.Field.AddButton.superclass.constructor.call(this, config);
	this.animateAddButton = true;
};
Ext.extend(Ext.form.Field.AddButton, Ext.form.Field,  {
        alias: 'plugin.addbutton',

        /**
         * @cfg {Boolean} Hide the Add button when the field is empty (default: true).
         */
        hideAddButtonWhenEmpty: true,

        /**
         * @cfg {Boolean} Hide the Add button until the mouse is over the field (default: true).
         */
        hideAddButtonWhenMouseOut: true,

        /**
         * @cfg {Boolean} When the Add buttons is hidden/shown, this will animate the button to its new state (using opacity) (default: true).
         */
        animateAddButton: true,

        /**
         * @cfg {Boolean} Empty the text field when ESC is pressed while the text field is focused.
         */
        addOnEscape: true,

        /**
         * @cfg {String} CSS class used for the button div.
         * Also used as a prefix for other classes (suffixes: '-mouse-over-input', '-mouse-over-button', '-mouse-down', '-on', '-off')
         */
        addButtonCls: 'ext-ux-addbutton',

        /**
         * The text field (or text area, combo box, date field) that we are attached to
         */
        textField: null,

        /**
         * Will be set to true if animateAddButton is true and the browser supports CSS 3 transitions
         * @private
         */
        animateWithCss3: false,

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Set up and tear down
        //
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        constructor: function(cfg) {
        	alert('cfg::'+cfg);
            Ext.apply(this, cfg);

            this.callParent(arguments);
        },

        /**
         * After the field has been rendered sets up the plugin (create the Element for the Add button, attach listeners).
         * @private
         */
        handleAfterRender: function(textField) {
        	alert('handleAfterRender');
            this.isTextArea = false;//(this.textField.inputEl.dom.type.toLowerCase() == 'textarea');

            this.createAddButtonEl();
            this.addListeners();

            this.repositionAddButton();
            this.updateAddButtonVisibility();

            this.addEscListener();
        },

        /**
         * Creates the Element and DOM for the Add button
         */
        createAddButtonEl: function() {
        	alert('increate');
            var animateWithClass = this.animateAddButton && this.animateWithCss3;
            this.addButtonEl = this.textField.getEl().createChild({
                tag: 'div',
                cls: this.addButtonCls
            });
            if(this.animateAddButton) {
                this.animateWithCss3 = this.supportsCssTransition(this.addButtonEl);
            }
            if(this.animateWithCss3) {
                this.addButtonEl.addClass(this.addButtonCls + '-off');
            }
            else {
                this.addButtonEl.setStyle('visibility', 'hidden');
            }
        },

        /**
         * Returns true iff the browser supports CSS 3 transitions
         * @param el an element that is checked for support of the "transition" CSS property (considering any
         *           vendor prefixes)
         */
        supportsCssTransition: function(el) {
            var styles = ['transitionProperty', 'WebkitTransitionProperty', 'MozTransitionProperty',
                          'OTransitionProperty', 'msTransitionProperty', 'KhtmlTransitionProperty'];

            var style = el.dom.style;
            for(var i = 0, length = styles.length; i < length; ++i) {
                if(style[styles[i]] !== 'undefined') {
                    // Supported property will result in empty string
                    return true;
                }
            }
            return false;
        },

        /**
         * If config option "AddOnEscape" is true, then add a key listener that will Add this field
         */
        addEscListener: function() {
            if (!this.addOnEscape) {
                return;
            }

            // Using a KeyMap did not work: ESC is swallowed by combo box and date field before it reaches our own KeyMap
            this.textField.inputEl.on('keydown',
                function(e) {
                    if (e.getKey() == Ext.EventObject.ESC) {
                        if (this.textField.isExpanded) {
                            // Let combo box or date field first remove the popup
                            return;
                        }
                        // No idea why the defer is necessary, but otherwise the call to setValue('') is ignored
                        Ext.Function.defer(this.textField.setValue, 1, this.textField, ['']);
                        e.stopEvent();
                    }
                },
                this);
        },

        /**
         * Adds listeners to the field, its input element and the Add button to handle resizing, mouse over/out events, click events etc.
         */
        addListeners: function() {
            // listeners on input element (DOM/El level)
            var textField = this.textField;
            var bodyEl = textField.getEl();
            bodyEl.on('mouseover', this.handleMouseOverInputField, this);
            bodyEl.on('mouseout', this.handleMouseOutOfInputField, this);

            // listeners on text field (component level)
            textField.on('destroy', this.handleDestroy, this);
            textField.on('resize', this.repositionAddButton, this);
            textField.on('change', function() {
                this.repositionAddButton();
                this.updateAddButtonVisibility();
            }, this);

            // listeners on Add button (DOM/El level)
            var addButtonEl = this.addButtonEl;
            addButtonEl.on('mouseover', this.handleMouseOverAddButton, this);
            addButtonEl.on('mouseout', this.handleMouseOutOfAddButton, this);
            addButtonEl.on('mousedown', this.handleMouseDownOnAddButton, this);
            addButtonEl.on('mouseup', this.handleMouseUpOnClearButton, this);
            addButtonEl.on('click', this.handleMouseClickOnAddButton, this);
        },

        /**
         * When the field is destroyed, we also need to destroy the Add button Element to prevent memory leaks.
         */
        handleDestroy: function() {
            this.addButtonEl.destroy();
        },

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Mouse event handlers
        //
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Tada - the real action: If user left clicked on the Add button, then empty the field
         */
        handleMouseClickOnAddButton: function(event, htmlElement, object) {
            if (!this.isLeftButton(event)) {
                return;
            }
            this.textField.setValue('');
            this.textField.focus();
        },

        handleMouseOverInputField: function(event, htmlElement, object) {
            this.addButtonEl.addCls(this.addButtonCls + '-mouse-over-input');
            if (event.getRelatedTarget() == this.addButtonEl.dom) {
                // Moused moved to Add button and will generate another mouse event there.
                // Handle it here to avoid duplicate updates (else animation will break)
                this.addButtonEl.removeCls(this.addButtonCls + '-mouse-over-button');
                this.addButtonEl.removeCls(this.addButtonCls + '-mouse-down');
            }
            this.updateAddButtonVisibility();
        },

        handleMouseOutOfInputField: function(event, htmlElement, object) {
            this.addButtonEl.removeCls(this.addButtonCls + '-mouse-over-input');
            if (event.getRelatedTarget() == this.addButtonEl.dom) {
                // Moused moved from Add button and will generate another mouse event there.
                // Handle it here to avoid duplicate updates (else animation will break)
                this.addButtonEl.addCls(this.addButtonCls + '-mouse-over-button');
            }
            this.updateAddButtonVisibility();
        },

        handleMouseOverAddButton: function(event, htmlElement, object) {
            event.stopEvent();
            if (this.textField.bodyEl.contains(event.getRelatedTarget())) {
                // has been handled in handleMouseOutOfInputField() to prevent double update
                return;
            }
            this.addButtonEl.addCls(this.addButtonCls + '-mouse-over-button');
            this.updateAddButtonVisibility();
        },

        handleMouseOutOfAddButton: function(event, htmlElement, object) {
            event.stopEvent();
            if (this.textField.bodyEl.contains(event.getRelatedTarget())) {
                // will be handled in handleMouseOverInputField() to prevent double update
                return;
            }
            this.addButtonEl.removeCls(this.addButtonCls + '-mouse-over-button');
            this.addButtonEl.removeCls(this.addButtonCls + '-mouse-down');
            this.updateAddButtonVisibility();
        },

        handleMouseDownOnAddButton: function(event, htmlElement, object) {
            if (!this.isLeftButton(event)) {
                return;
            }
            this.addButtonEl.addCls(this.addButtonCls + '-mouse-down');
        },

        handleMouseUpOnAddButton: function(event, htmlElement, object) {
            if (!this.isLeftButton(event)) {
                return;
            }
            this.addButtonEl.removeCls(this.addButtonCls + '-mouse-down');
        },

        /////////////////////////////////////////////////////////////////////////////////////////////////////
        //
        // Utility methods
        //
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        /**
         * Repositions the Add button element based on the textfield.inputEl element
         * @private
         */
        repositionAddButton: function() {
            var addButtonEl = this.addButtonEl;
            if (!addButtonEl) {
                return;
            }
            var addButtonPosition = this.calculateAddButtonPosition(this.textField);
            addButtonEl.dom.style.right = addButtonPosition.right + 'px';
            addButtonEl.dom.style.top = addButtonPosition.top + 'px';
        },

        /**
         * Calculates the position of the clear button based on the textfield.inputEl element
         * @private
         */
        calculateAddButtonPosition: function(textField) {
            var positions = textField.inputEl.getBox(true, true);
            var top = positions.y;
            var right = positions.x;
            if (this.fieldHasScrollBar()) {
                right += Ext.getScrollBarWidth();
            }
            if (this.textField.triggerWrap) {
                right += this.textField.getTriggerWidth();
            }
            return {
                right: right,
                top: top
            };
        },

        /**
         * Checks if the field we are attached to currently has a scrollbar
         */
        fieldHasScrollBar: function() {
            if (!this.isTextArea) {
                return false;
            }

            var inputEl = this.textField.inputEl;
            var overflowY = inputEl.getStyle('overflow-y');
            if (overflowY == 'hidden' || overflowY == 'visible') {
                return false;
            }
            if (overflowY == 'scroll') {
                return true;
            }
            //noinspection RedundantIfStatementJS
            if (inputEl.dom.scrollHeight <= inputEl.dom.clientHeight) {
                return false;
            }
            return true;
        },


        /**
         * Small wrapper around AddButtonEl.isVisible() to handle setVisible animation that may still be in progress.
         */
        isButtonCurrentlyVisible: function() {
            if (this.animateAddButton && this.animateWithCss3) {
                return this.addButtonEl.hasCls(this.addButtonCls + '-on');
            }

            // This should not be necessary (see Element.setVisible/isVisible), but else there is confusion about visibility
            // when moving the mouse out and _quickly_ over then input again.
            var cachedVisible = Ext.core.Element.data(this.addButtonEl.dom, 'isVisible');
            if (typeof(cachedVisible) == 'boolean') {
                return cachedVisible;
            }
            return this.addButtonEl.isVisible();
        },

        /**
         * Checks config options and current mouse status to determine if the Add button should be visible.
         */
        shouldButtonBeVisible: function() {
            if (this.hideAddButtonWhenEmpty && Ext.isEmpty(this.textField.getValue())) {
                return false;
            }

            var addButtonEl = this.addButtonEl;
            //noinspection RedundantIfStatementJS
            if (this.hideAddButtonWhenMouseOut
                && !addButtonEl.hasCls(this.addButtonCls + '-mouse-over-button')
                && !addButtonEl.hasCls(this.addButtonCls + '-mouse-over-input')) {
                return false;
            }

            return true;
        },

        /**
         * Called after any event that may influence the Add button visibility.
         */
        updateAddButtonVisibility: function() {
            var oldVisible = this.isButtonCurrentlyVisible();
            var newVisible = this.shouldButtonBeVisible();

            var addButtonEl = this.addButtonEl;
            if (oldVisible != newVisible) {
                if(this.animateAddButton && this.animateWithCss3) {
                    this.addButtonEl.removeCls(this.addButtonCls + (oldVisible ? '-on' : '-off'));
                    addButtonEl.addCls(this.addButtonCls + (newVisible ? '-on' : '-off'));
                }
                else {
                	addButtonEl.stopAnimation();
                	addButtonEl.setVisible(newVisible, this.animateAddButton);
                }

                // Set background-color of AddButton to same as field's background-color (for those browsers/cases
                // where the padding-right (see below) does not work)
                addButtonEl.setStyle('background-color', this.textField.inputEl.getStyle('background-color'));

                // Adjust padding-right of the input tag to make room for the button
                // IE (up to v9) just ignores this and Gecko handles padding incorrectly with  textarea scrollbars
                if (!(this.isTextArea && Ext.isGecko) && !Ext.isIE) {
                    // See https://bugzilla.mozilla.org/show_bug.cgi?id=157846
                    var deltaPaddingRight = addButtonEl.getWidth() - this.addButtonEl.getMargin('l');
                    var currentPaddingRight = this.textField.inputEl.getPadding('r');
                    var factor = (newVisible ? +1 : -1);
                    this.textField.inputEl.dom.style.paddingRight = (currentPaddingRight + factor * deltaPaddingRight) + 'px';
                }
            }
        },

        isLeftButton: function(event) {
            return event.button === 0;
        },
        /**
         * Called by plug-in system to initialize the plugin for a specific text field (or text area, combo box, date field).
         * Most all the setup is delayed until the component is rendered.
         */
        init: function(textField) {
        	alert('init called'+textField);
            this.textField = textField;
            if (!textField.rendered) {
            	alert(23);
                textField.on('render', this.handleAfterRender, this);
            }
            else {
            	alert('in else')
                // probably an existing input element transformed to extjs field
                this.handleAfterRender();
            }
        }        

    });
