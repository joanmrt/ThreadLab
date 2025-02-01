package org.example;

public class ResourceType {

    private int quantity = 0;
    private int maxQuantity;
    private int minQuantity;

    private String name;

    public ResourceType(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public void addResources(){
        quantity = quantity + 1;
    }

    public void removeResources(){
        quantity = quantity - 1;
    }
}