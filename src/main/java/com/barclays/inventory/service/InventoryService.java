package com.barclays.inventory.service;

import com.barclays.inventory.exception.ValidationException;

public interface InventoryService {

	void createItem(String[] input) throws ValidationException;

	void buyItem(String[] input) throws ValidationException;

	void sellItem(String[] input) throws ValidationException;

	void deleteItem(String[] input) throws ValidationException;

	void showReport();

	void updatePrice(String[] input) throws ValidationException;

}
