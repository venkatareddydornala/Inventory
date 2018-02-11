package com.barclays.inventory.model;

import static com.barclays.inventory.util.InventoryUtility.formatAmount;

import java.math.BigDecimal;

public class Item {
	private String itemName;
	private BigDecimal boughtAt;
	private BigDecimal soldAt;
	private int buy;
	private int sell;
	private int availableQty;
	private BigDecimal profit = BigDecimal.ZERO;

	public Item(String itemName, double boughtAt, double soldAt) {
		this.itemName = itemName;
		this.boughtAt = new BigDecimal(boughtAt);
		this.soldAt = new BigDecimal(soldAt);
	}

	public void addAvailableQty(int quantity) {
		this.buy += quantity;
		this.availableQty += quantity;
	}

	public void removeAvailableQty(int soldQty) {
		this.sell = soldQty;
		this.availableQty -= soldQty;
		calculateProfit();
	}

	private void calculateProfit() {
		profit = profit.add(soldAt.subtract(boughtAt).multiply(new BigDecimal(sell)));
		// System.out.println("-->" + InventoryUtility.formatAmount(profit));
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getBoughtAt() {
		return boughtAt;
	}

	public void setBoughtAt(BigDecimal boughtAt) {
		this.boughtAt = boughtAt;
	}

	public BigDecimal getSoldAt() {
		return soldAt;
	}

	public void setSoldAt(BigDecimal soldAt) {
		this.soldAt = soldAt;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public BigDecimal getValue() {
		return this.boughtAt.multiply(new BigDecimal(this.availableQty));
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	@Override
	public String toString() {
		return "Item [itemName=" + itemName + ", boughtAt=" + formatAmount(boughtAt) + ", soldAt="
				+ formatAmount(soldAt) + ", availableQty=" + availableQty + ", value=" + formatAmount(getValue()) + ""
				+ ", buy=" + buy + ", sell=" + sell + "]";
	}
}