package com.barclays.inventory.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * Utility methods used by Inventory Management System
 *
 */
public class InventoryUtility {
	/**
	 * Round the price value to 2 decimal places and return as String
	 * 
	 * @param value
	 * @return
	 */
	public static String formatAmount(final BigDecimal value) {
		if (value == null) {
			return null;
		}
		return getDecimalFormat(2, 2).format(value);
	}

	/**
	 * Round the price value to 2 decimal places
	 * 
	 * @param minFraction
	 * @param maxFraction
	 * @return
	 */
	private static DecimalFormat getDecimalFormat(final int minFraction, final int maxFraction) {
		final DecimalFormat df = new DecimalFormat();
		df.setMinimumFractionDigits(minFraction);
		df.setMaximumFractionDigits(maxFraction);
		df.setGroupingUsed(false);

		return df;
	}

	/**
	 * Check if the given input is valid command
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isCommand(String input) {
		Command[] commands = Command.values();
		for (Command command : commands) {
			if (input.equals(command.getCommand())) {
				return true;
			}
		}
		return false;
	}
}