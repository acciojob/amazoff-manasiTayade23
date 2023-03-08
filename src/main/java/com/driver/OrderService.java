package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
//    @Autowired
//    private OrderRepository orderRepository;

    OrderRepository orderRepository=new OrderRepository();
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }

    public void addPartnerId(String partnerId){
        orderRepository.addPartnerId(partnerId);
    }

    public void addOrderPartnerPair(String orderId,String partnerId){
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId){
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
        return orderRepository.getPartnerById(partnerId);
    }

    public int  getOrderCountByPartnerId(String partnerId){
        int ans=orderRepository. getOrderCountByPartnerId(partnerId);
        return ans;
    }


    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orders=orderRepository.getOrdersByPartnerId(partnerId);
        return orders;
    }


    public List<String> getAllOrders(){
        List<String> ans=orderRepository.getAllOrders();
        return  ans;
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId){
        String [] arr=time.split(":");
        int hour=Integer.parseInt(arr[0]);
        int minute=Integer.parseInt(arr[1]);

        hour=hour*60;
        return  orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(hour+minute,partnerId);

    }

    public String  getLastDeliveryTimeByPartnerId(String partnerId){
        return  orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }



    public void deletePartnerById(String partnerId){
        orderRepository.deletePartnerById(partnerId);
    }



    public void deleteOrderById(String orderId){
        orderRepository.deleteOrderById(orderId);
    }
}
