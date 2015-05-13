package com.swiftcorp.portal.item.dto;

import java.util.Calendar;
import com.swiftcorp.portal.common.dto.PersistentCapableDTO;

public class ItemDTO extends PersistentCapableDTO {

	private String itemId = null;
	private String itemName = null;
	private double purchasePrice = 0.00;
	private double sellPrice = 0.00;
	private long parent = 0;

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getItemId() {
		return this.itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public long getParent() {
		return this.parent;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setParent(long parent) {
		this.parent = parent;
	}

}
