package com.example.order.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order.dto.ItemsDto;
import com.example.order.dto.NotificationDto;
import com.example.order.dto.OrderDto;
import com.example.order.dto.UserDto;
import com.example.order.model.Items;
import com.example.order.model.Orders;
import com.example.order.repo.OrderRepo;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    
    @Autowired
    private UserClient userClient;
    
    @Autowired
    private NotificationClient notificationClient;

    public List<OrderDto> getOrders() {
    	
    	 List<Orders> all = orderRepo.findAll();
    	 	List<OrderDto> list= all.stream().map(
    			 od->{
    				 OrderDto odto=new OrderDto();
    				 
    				 
    				 odto.setId(od.getId());
    				 odto.setStatus(od.getStatus());
    				 
    				 List<Items> items = od.getItems();
   				 List<ItemsDto> collect = items.stream().map(
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
   				odto.setItems(collect);
    				 return odto;
    			 }).collect(Collectors.toList());
    	 	
    	 
        return list;
    }

    public OrderDto getOrder(int id) {
    	Optional<Orders> byId = orderRepo.findById(id);
    	OrderDto odto=new OrderDto();
    	byId.ifPresent(od->{
    		odto.setId(od.getId());
    		
    		odto.setStatus(od.getStatus());
    		List<Items> items = od.getItems();
			 List<ItemsDto> collect = items.stream().map(
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
			 odto.setItems(collect);
    	});
        return odto ;
    }

    public Orders addOrder(OrderDto orderDto) {
    	Orders orders=new Orders();
    	orders.setId(orderDto.getId());
    	orders.setUserId(orderDto.getUserId());
    	orders.setStatus(orderDto.getStatus());
    	
    	orders.setItems(orderDto.getItems().stream().map(it-> {
    		Items its=new Items();
    		its.setItemId(it.getItemId());
    		its.setName(it.getName());   		
    		its.setProductCode(it.getProductCode());    		
    		its.setQuantity(it.getQuantity());   
 		
    		return its;
    	}).collect(Collectors.toList()));
    	UserDto userById = userClient.getUserById(orderDto.getUserId());
    	NotificationDto dto =new NotificationDto();
    	dto.setTo(userById.getEmail());
    	dto.setMessage("Hi "+userById.getUsername() + " "+"Your order has been placed");
    	
    	notificationClient.sendNotification(dto);
    	
    	
        return orderRepo.save(orders);
    }

    public String updateOrder(OrderDto orderDto) {
    	
    	Optional<Orders> byId = orderRepo.findById(orderDto.getId());
    	byId.ifPresent(od->{
    		od.setStatus(orderDto.getStatus());
    		List<ItemsDto> items = orderDto.getItems();
    		List<Items> collect = items.stream().map(
    				
    				itemDtos -> {
    					Items it= new Items();
    					it.setItemId(itemDtos.getItemId());
    					it.setCategory(itemDtos.getCategory());
    					it.setCurrency(itemDtos.getCurrency());
    					it.setDescription(itemDtos.getDescription());
    					it.setDiscount(itemDtos.getDiscount());
    					it.setImageUrl(itemDtos.getImageUrl());
    					it.setName(itemDtos.getName());
    					it.setProductCode(itemDtos.getProductCode());
    					it.setQuantity(itemDtos.getQuantity());
    					it.setUnitPrice(itemDtos.getUnitPrice());
    					return it;
    				}
    				
    				).collect(Collectors.toList());
    		od.setItems(collect);
    		orderRepo.save(od);
    	});
    	
        return "Update Succesfully";
    }

    public void deleteOrder(Integer id) {

        orderRepo.deleteById(id);
    }
}
