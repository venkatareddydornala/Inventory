package com.barclays.inventory.service;

import static com.barclays.inventory.util.InventoryUtility.formatAmount;

import java.math.BigDecimal;

import com.barclays.inventory.dao.Inventory;
import com.barclays.inventory.exception.ValidationException;
import com.barclays.inventory.model.Item;

/**
 * 
 * Service class of Inventory Management System
 *
 */
public class InventoryService {
	Inventory inventory = new Inventory();

	/**
	 * Creates new item
	 * 
	 * @param splits
	 * @throws ValidationException
	 */
	public void createItem(String[] splits) throws ValidationException {
		if (splits.length != 4) {
			throw new ValidationException("Command requires 3 parameters");
		}
		try {
			String itemName = splits[1];
			double boughtAt = Double.valueOf(splits[2]);
			double soldAt = Double.valueOf(splits[3]);
			Item item = new Item(itemName, boughtAt, soldAt);
			inventory.addItem(item);
		} catch (NumberFormatException e) {
			throw new ValidationException(e.getMessage() + " - Not a valid number");
		}
	}

	/**
	 * Buy an item
	 * 
	 * @param splits
	 * @throws ValidationException
	 */
	public void buyItem(String[] splits) throws ValidationException {
		if (splits.length != 3) {
			throw new ValidationException("Command requires 3 parameters");
		}
		try {
			String itemName = splits[1];
			int availableQty = Integer.valueOf(splits[2]);
			Item item = inventory.getItems().get(itemName);
			if (item == null) {
				throw new ValidationException("Item not found");
			}
			item.addAvailableQty(availableQty);
		} catch (NumberFormatException e) {
			throw new ValidationException(e.getMessage() + " - Not a valid number");
		}
	}

	/**
	 * Sell an item
	 * 
	 * @param splits
	 * @throws ValidationException
	 */
	public void sellItem(String[] splits) throws ValidationException {
		if (splits.length != 3) {
			throw new ValidationException("Command requires 3 parameters");
		}
		try {
			String itemName = splits[1];
			int soldQty = Integer.valueOf(splits[2]);
			Item item = inventory.getItems().get(itemName);
			if (item == null) {
				throw new ValidationException("Item not found");
			}
			item.removeAvailableQty(soldQty);
		} catch (NumberFormatException e) {
			throw new ValidationException(e.getMessage() + " - Not a valid number");
		}

	}

	/**
	 * Delete an item
	 * 
	 * @param splits
	 * @throws ValidationException
	 */
	public void deleteItem(String[] splits) throws ValidationException {
		if (splits.length != 2) {
			throw new ValidationException("Command requires 3 parameters");
		}
		try {
			String itemName = splits[1];
			Item item = inventory.getItems().remove(itemName);
			if (item == null) {
				throw new ValidationException("Item not found");
			}
			inventory.addDeletedItem(item);
		} catch (NumberFormatException e) {
			throw new ValidationException(e.getMessage() + " - Not a valid number");
		}
	}

	/**
	 * Display report
	 */
	public void showReport() {
		BigDecimal value = BigDecimal.ZERO;
		BigDecimal profit = BigDecimal.ZERO;
		for (Item item : inventory.getItems().values()) {
			value = value.add(item.getValue());
			profit = profit.add(item.getProfit());
		}
		for (Item item : inventory.getDeletedItems()) {
			profit = profit.subtract(item.getBoughtAt().multiply(new BigDecimal(item.getAvailableQty())));
		}

		System.out.printf("%40s\n", "INVENTORY REPORT");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "Item Name", "Bought At", "Sold At", "AvailableQty",
				"Value");
		System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", "---------", "---------", "-------", "------------",
				"-----");
		for (Item item : inventory.getItems().values()) {
			System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", item.getItemName(), formatAmount(item.getBoughtAt()),
					formatAmount(item.getSoldAt()), item.getAvailableQty(), formatAmount(item.getValue()));
		}
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < 100; i++) {
			sb.append("-");
		}
		System.out.println(sb);
		System.out.printf("%-80s %s\n", "Total value", formatAmount(value));
		System.out.printf("%-80s %s\n", "Profit since previous report ", formatAmount(profit));

	}

	/**
	 * Update with new price
	 * 
	 * @param splits
	 * @throws ValidationException
	 */
	public void updatePrice(String[] splits) throws ValidationException {
		if (splits.length != 3) {
			throw new ValidationException("Command requires 3 parameters");
		}
		try {
			String itemName = splits[1];
			int newPrice = Integer.valueOf(splits[2]);
			Item item = inventory.getItems().get(itemName);
			if (item == null) {
				throw new ValidationException("Item not found");
			}
			item.setSoldAt(new BigDecimal(newPrice));
		} catch (NumberFormatException e) {
			throw new ValidationException(e.getMessage() + " - Not a valid number");
		}

	}
}