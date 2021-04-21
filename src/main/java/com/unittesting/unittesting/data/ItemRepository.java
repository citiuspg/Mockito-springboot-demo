package com.unittesting.unittesting.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unittesting.unittesting.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

}
