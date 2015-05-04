   				       <!--  sampleproperty of samplecom -->
					   <tr>
							<td><label class="desc"><bean:message key="label.samplecom.sampleproperty" /></label></td>
							<td>								
								<html:select property="samplecom.sampleproperty.componentId" >
									<html:option value="0">---Select---</html:option>
									<html:options collection="<%=SESSION_KEYS.COMBO_DATA_LIST%>" property="componentId" labelProperty="uniqueCode"/>
								</html:select>
							</td>
						</tr> 