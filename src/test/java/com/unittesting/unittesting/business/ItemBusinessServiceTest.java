package com.unittesting.unittesting.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.unittesting.unittesting.business.ItemBusinessService;
import com.unittesting.unittesting.data.ItemRepository;
import com.unittesting.unittesting.model.Item;

@ExtendWith(MockitoExtension.class)
public class ItemBusinessServiceTest {

	@InjectMocks
	private ItemBusinessService business;

	@Mock
	private ItemRepository repository;

	@Test
	public void retrieveAllItems_basic() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Item(2,"Item2",10,10),
				new Item(3,"Item3",20,20)));
		List<Item> items = business.retrieveAllItems();
		
		assertEquals(100, items.get(0).getValue());
		assertEquals(400, items.get(1).getValue());
	}
	@Test
	public void retrieveAllItems_NoRecords() {
		when(repository.findAll()).thenReturn(Arrays.asList());
		List<Item> items = business.retrieveAllItems();
		assertEquals(0, items.size());
	}
	
	@Test
	public void retrieveAllItems_ignoreIfPriceIsZero() {
		when(repository.findAll()).thenReturn(Arrays.asList(new Item(2,"Item2",10,10),
				new Item(3,"Item3",0,20)));
		List<Item> items = business.retrieveAllItems();
		assertEquals(1, items.size());
	}
}
