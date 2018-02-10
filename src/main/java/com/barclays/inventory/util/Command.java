package com.barclays.inventory.util;

/**
 * 
 * Supported commands by Inventory Management System
 *
 */
public enum Command {
	CREATE("create"), UPDATE_BUY("updateBuy"), UPDATE_SELL("updateSell"), DELETE("delete"), REPORT(
			"report"), UPDATE_PRICE("updatePrice");

	private String command;

	Command(String command) {
		this.command = command;
	}

	public String getCommand() {
		return this.command;
	}
}