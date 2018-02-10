package com.barclays.inventory.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.barclays.inventory.model.Item;

public class Inventory {

	private Map<String, Item> items = new TreeMap<>();

	private List<Item> deletedItems = new ArrayList<>();

	public Map<String, Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.put(item.getItemName(), item);
	}

	public List<Item> getDeletedItems() {
		return deletedItems;
	}

	public void addDeletedItem(Item item) {
		this.deletedItems.add(item);
	}

	@Override
	public String toString() {
		return "Inventory [items=" + items + "]";
	}
}