package com.barclays.inventory;

import java.util.Scanner;

import com.barclays.inventory.service.InventoryService;
import com.barclays.inventory.util.Command;
import com.barclays.inventory.util.InventoryUtility;

public class App {
	public static void main(String[] args) {
		InventoryService service = new InventoryService();

		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		while (true) {
			if (line.equals("#")) {
				System.out.println("Thank You!");
				break;
			}
			String[] splits = line.split(" ");
			try {
				if (InventoryUtility.isCommand(splits[0])) {
					String command = splits[0];
					if (command.equals(Command.CREATE.getCommand())) {
						service.createItem(splits);
					} else if (command.equals(Command.UPDATE_BUY.getCommand())) {
						service.buyItem(splits);
					} else if (command.equals(Command.UPDATE_SELL.getCommand())) {
						service.sellItem(splits);
					} else if (command.equals(Command.DELETE.getCommand())) {
						service.deleteItem(splits);
					} else if (command.equals(Command.REPORT.getCommand())) {
						service.showReport();
					} else if(command.equals(Command.UPDATE_PRICE.getCommand())) {
						service.updatePrice(splits);
					}
				} else {
					System.out.println("Not a valid command");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			line = sc.nextLine();
		}		
		sc.close();
	}
}