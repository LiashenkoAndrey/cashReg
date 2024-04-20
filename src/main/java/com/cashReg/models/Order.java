package com.cashReg.models;

import com.cashReg.util.SQLList;
import java.util.List;
import java.util.Map;

public class Order extends Model {

    private long customerId;
    private float amount;
    private Map<OrderItem, Long> items;
    private boolean isClosed = false;

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Map<OrderItem, Long> getItems() {
        return items;
    }

    public void setItems(Map<OrderItem, Long> items) {
        this.items = items;
    }

    public void close(){
        isClosed=true;
    }

    public float getAmount() {
        float amount = 0.0f;
        for(OrderItem item : items.keySet()){
            amount += items.get(item) * item.getPrice();
        }
        this.amount = amount;
        return amount;
    }

    public void addProduct(Product product, long quantity){
        if(quantity > warehouse.getQuantity(product) || quantity <=0){
            throw new IllegalArgumentException("Invalid quantity");
        }
        items.put(new OrderItem(product, id), quantity);
    }

    public void addProduct(long productId, long quantity){
        Product product = warehouse.getProduct(productId);
        addProduct(product, quantity);
    }

    public void addProduct(String productName, long quantity){
        Product product = warehouse.getProduct(productName);
        addProduct(product, quantity);
    }

    public void setQuantity(long itemId, long quantity){
        for(OrderItem item : items.keySet()){
            if(item.getId() == itemId){
                items.put(item, quantity);
            }
        }
    }

    public boolean isClosed(){return isClosed;}

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        if(isClosed){throw new RuntimeException("Order is closed");}
        this.customerId = customerId;
    }

    public void cancel(){
        items.clear();
        items = null;
        amount = 0.0f;
        customerId = -1;
    }

    public void setClosed(){
        isClosed=true;
    }

    public Product remove(long productId){
       Product result = null;
        for(OrderItem item : items.keySet()){
            if(item.getProductId()==productId){
                result = item;
                items.remove(items);
            }
        }
        return result;
    }
}
