package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
   HashMap<String,Order> orderHM=new HashMap<>();
   HashMap<String,DeliveryPartner> deliveryPartnerHM=new HashMap<>();
   HashMap<String,String> pairHm=new HashMap<>();
   HashMap<String, List<String>> AssignedHm=new HashMap<>();

   public void addOrder(Order order){
      String id = order.getId();
      orderHM.put(id,order);
   }
   public void addPartner(String partnerId){
      DeliveryPartner dp=new DeliveryPartner(partnerId);
      deliveryPartnerHM.put(partnerId,dp);
   }
   public void assignOrderToPartner(String orderId,String partnerId) {
      pairHm.put(orderId,partnerId);
   }
      public Order getOrder(String orderId){
         return orderHM.get(orderId);
      }
      public DeliveryPartner getPartner(String partnerId){
         return deliveryPartnerHM.get(partnerId);
      }
      public int getNoOfOrderAssignedToPartner(String partnerId){
        return AssignedHm.get(partnerId).size();
      }
      public List<String> getListOfOrder(String partnerId){
         return AssignedHm.get(partnerId);
      }
      public List<String> getListOfAllOrder(){
        List<String>order=new ArrayList<>();
       for(Map.Entry<String,Order> entry:orderHM.entrySet()){
          order.add(entry.getKey());
       }
        return order;
      }
      public int getOrderWhichAreNotAssigned(){
        return orderHM.size()-AssignedHm.size();
      }
      public int orderLeftUndelivered(String time,String partnerId){
      int count=0;
      List<String> al=AssignedHm.get(partnerId);
      int deliveryTime=Integer.parseInt(time.substring(0,2))*60+Integer.parseInt(time.substring(3));
      for(String s:al){
         Order order=orderHM.get(s);
         if(order.getDeliveryTime() > deliveryTime){
            count++;
         }
      }
      return count;
      }
      public String getLastDeliveryTime(String partnerId){
        String time="";

        List<String> list = AssignedHm.get(partnerId);
        int deliveryTime=0;
        for(String o:list){
           Order order=orderHM.get(o);
           deliveryTime=Math.max(deliveryTime,order.getDeliveryTime());
        }
        int hour=deliveryTime/60;
        String Hour="";
        if(hour < 10){
           Hour= '0'+String.valueOf(hour);
        }else{
           Hour=String.valueOf(hour);
        }
        int min=deliveryTime%60;
        String Min="";
         if (min < 10 ) {
            
            Min='0'+String.valueOf(min);
         }else{
            Min=String.valueOf(min);
         }
         time=Hour+":"+Min;
         return time;

      }
      public void deleteDeliveryPartner(String partnerId){
         deliveryPartnerHM.remove(partnerId);
         List<String> al = AssignedHm.getOrDefault(partnerId,new ArrayList());
         ListIterator<String> itr=al.listIterator();
         while(itr.hasNext()){
            String s=itr.next();
            AssignedHm.remove(s);
         }
      deliveryPartnerHM.remove(partnerId);
   }
   public void deleteOrder(String orderId){
     orderHM.remove(orderId);
     String partnerId=pairHm.get(orderId);
     pairHm.remove(orderId);
     List<String>al=AssignedHm.get(partnerId);
     ListIterator<String> itr=al.listIterator();
     while (itr.hasNext()){
        String s=itr.next();
        if(s.equals(orderId)){
           itr.remove();
        }
     }
      AssignedHm.put(partnerId,al);
   }


}
