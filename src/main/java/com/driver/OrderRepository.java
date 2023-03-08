package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    HashMap<String,Order> orderHashMap=new HashMap<>();
    HashMap<String,DeliveryPartner>deliveryPartnerHashMap=new HashMap<>();

    HashMap<String,String> orderAndPartner=new HashMap<>();
    HashMap<String, List<String>> partnerIdAndOrder =new HashMap<>();




    public void addOrder(Order order){
        orderHashMap.put(order.getId(),order);
    }
    public void addPartnerId(String partnerId){
        deliveryPartnerHashMap.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId,String partnerId){
        if(orderHashMap.containsKey(orderId)&&deliveryPartnerHashMap.containsKey(partnerId)){
            List<String> orders=new ArrayList<>();
            if(partnerIdAndOrder.containsKey(partnerId)) orders=partnerIdAndOrder.get(partnerId);
            orders.add(orderId);
            partnerIdAndOrder.put(partnerId,orders);
            orderAndPartner.put(orderId,partnerId);
            DeliveryPartner deliveryPartner=deliveryPartnerHashMap.get(partnerId);
            deliveryPartner.setNumberOfOrders(orders.size());
        }
    }
    public Order getOrderById(String orderId){
        return orderHashMap.get(orderId);
    }
    public DeliveryPartner getPartnerById(String partnerId){
        return deliveryPartnerHashMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId){
        List<String> orders=partnerIdAndOrder.get(partnerId);
        return orders.size();
    }

    public List<String> getOrdersByPartnerId(String partnerId){
        List<String> orders=partnerIdAndOrder.get(partnerId);
        return orders;
    }

    public List<String> getAllOrders(){
        List<String> orders=new ArrayList<>();
        for(String x: orderHashMap.keySet()){
            orders.add(x);
        }
        return  orders;
    }
    public int getCountOfUnassignedOrders(){
        return orderHashMap.size()-orderAndPartner.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId){
        int count=0;
        List<String>  orders=partnerIdAndOrder.get(partnerId);
        for(String x: orders){
            int deliveryTime=orderHashMap.get(x).getDeliveryTime();
            if(deliveryTime>time) count++;
        }
        return  count;
    }
    public String  getLastDeliveryTimeByPartnerId(String partnerId){
        int maxTime=0;
        List<String > orders=partnerIdAndOrder.get(partnerId);
        for(String x: orders){
            int currTime=orderHashMap.get(x).getDeliveryTime();
            maxTime=Math.max(currTime,maxTime);
        }
        String HH=String.valueOf(maxTime/60);
        String MM=String.valueOf(maxTime%60);
        if(HH.length()<2) HH='0'+HH;
        if(MM.length()<2) MM='0'+MM;
        return HH+":"+MM;
    }

    public void deletePartnerById(String partnerId){
        deliveryPartnerHashMap.remove(partnerId);

        List<String> orders=partnerIdAndOrder.get(partnerId);
        partnerIdAndOrder.remove(partnerId);
        for(String x: orders){
            orderAndPartner.remove(x);

        }
    }
    public void deleteOrderById(String orderId){
        if(orderHashMap.containsKey(orderId)){
            orderHashMap.remove(orderId);
        }
        String partnerId=orderAndPartner.get(orderId);
        orderAndPartner.remove(orderId);
        partnerIdAndOrder.get(partnerId).remove(orderId);


        deliveryPartnerHashMap.get(partnerId).setNumberOfOrders(partnerIdAndOrder.get(partnerId).size());


    }

}