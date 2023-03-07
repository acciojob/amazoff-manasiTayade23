package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order order){
         orderRepository.addOrder(order);
    }
    public void addPartner(String id){
        orderRepository.addPartner(id);
    }
    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.assignOrderToPartner(orderId,partnerId);
    }
    public Order getOrderById(String id){
        return orderRepository.getOrder(id);
    }
    public DeliveryPartner getPartner(String id){
        return orderRepository.getPartner(id);
    }
    public int getNoOfOrderAssignedToPartner(String id){
        return orderRepository.getNoOfOrderAssignedToPartner(id);
    }
    public int getOrderWhichAreNotAssigned(){
        return orderRepository.getOrderWhichAreNotAssigned();
    }
    public List<String> getListOfAllOrder(){
        return orderRepository. getListOfAllOrder();
    }
    public String getLastDeliveryTime(String partnerId){
        return orderRepository.getLastDeliveryTime(partnerId);
    }
    public void deleteDeliveryPartner(String partnerId){
         orderRepository.deleteDeliveryPartner(partnerId);
    }
    public void deleteOrder(String id){
        orderRepository.deleteOrder(id);
    }
    public List<String> getListOfOrder(String partnerId){
        return orderRepository.getListOfOrder(partnerId);
    }
    public int orderLeftUndelivered(String time,String partnerId){
        return orderRepository.orderLeftUndelivered(time,partnerId);
    }
}
