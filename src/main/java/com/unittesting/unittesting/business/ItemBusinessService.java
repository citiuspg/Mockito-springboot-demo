package com.unittesting.unittesting.business;

import java.security.cert.CollectionCertStoreParameters;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.unittesting.unittesting.data.ItemRepository;
import com.unittesting.unittesting.model.Item;

@Component
public class ItemBusinessService {

	@Autowired
	private ItemRepository repository;

	public Item retreiveHardcodedItem() {
		return new Item(1, "Ball", 10, 100);
	}

	public List<Item> retrieveAllItems() {
		List<Item> items = repository.findAll();
		List<Item> threadSafeList = new CopyOnWriteArrayList<Item>(items);
		for (Item item : threadSafeList) {
			if (item.getPrice() == 0)
				threadSafeList.remove(item);
			item.setValue(item.getPrice() * item.getQuantity());
		}

		return threadSafeList;
	}

}
