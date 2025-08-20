package com.example.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.dto.ItemsDto;
import com.example.order.model.Items;
import com.example.order.model.Orders;
import com.example.order.repo.ItemsRepo;

@Service
public class ItemsService {
	
	@Autowired
	private ItemsRepo itemsRepo;

	public List<ItemsDto> getItems() {
		List<Items> all = itemsRepo.findAll();
		List<ItemsDto> collect = all.stream().map(
				
				item->{
					 ItemsDto idto=new ItemsDto();
					 idto.setItemId(item.getItemId());
					 idto.setCategory(item.getCategory());
					 idto.setCurrency(item.getCurrency());
					 idto.setDescription(item.getDescription());
					 idto.setDiscount(item.getDiscount());
					 idto.setImageUrl(item.getImageUrl());
					 idto.setName(item.getName());
					 idto.setProductCode(item.getProductCode());
					 idto.setQuantity(item.getQuantity());
					 idto.setUnitPrice(item.getUnitPrice());
					 return idto;
				}
				
				
				).collect(Collectors.toList());
		return collect;
	}

	public ItemsDto getItem(int id) {
		Optional<Items> byId = itemsRepo.findById(id);
		 ItemsDto idto=new ItemsDto();
		byId.ifPresentOrElse(
				item->{
					
					 idto.setItemId(item.getItemId());
					 idto.setCategory(item.getCategory());
					 idto.setCurrency(item.getCurrency());
					 idto.setDescription(item.getDescription());
					 idto.setDiscount(item.getDiscount());
					 idto.setImageUrl(item.getImageUrl());
					 idto.setName(item.getName());
					 idto.setProductCode(item.getProductCode());
					 idto.setQuantity(item.getQuantity());
					 idto.setUnitPrice(item.getUnitPrice());
				}
				
				, null);
		return idto;
	}

	public String addItem(ItemsDto itemDto) {
		Items item=new Items();
		item.setItemId(itemDto.getItemId());
		item.setCategory(itemDto.getCategory());
		item.setCurrency(itemDto.getCurrency());
		item.setDescription(itemDto.getDescription());
		item.setDiscount(itemDto.getDiscount());
		item.setImageUrl(itemDto.getImageUrl());
		item.setName(itemDto.getName());
		item.setProductCode(itemDto.getProductCode());
		item.setQuantity(itemDto.getQuantity());
		item.setUnitPrice(itemDto.getUnitPrice());
		 
		 itemsRepo.save(item);
		return "Item Added Succesfully";
	}

	public String updateItem(ItemsDto itemDto) {
		Optional<Items> byId = itemsRepo.findById(itemDto.getItemId());
		byId.ifPresentOrElse(
				item->{
					
					item.setCategory(itemDto.getCategory());
					item.setCurrency(itemDto.getCurrency());
					item.setDescription(itemDto.getDescription());
					item.setDiscount(itemDto.getDiscount());
					item.setImageUrl(itemDto.getImageUrl());
					item.setName(itemDto.getName());
					item.setProductCode(itemDto.getProductCode());
					item.setQuantity(itemDto.getQuantity());
					item.setUnitPrice(itemDto.getUnitPrice());
					
				}
				
				
				, null);
		 itemsRepo.save(byId.get());
		return "Update Succefully";
	}

	public void deleteItem(int id) {
		itemsRepo.deleteById(id);
	}

}
