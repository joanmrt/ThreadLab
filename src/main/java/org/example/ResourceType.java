package org.example;

public class ResourceType {
    private Model model;
    private int id;

    private int quantity = 0;
    private int maxQuantity;
    private int minQuantity;

    private int consumerNum = 0;

    private int producerNum = 0;

    private int underflow;

    private int overflow;
    private String state;

    public ResourceType(Model model, int id){
        this.model = model;
        this.id = id;
        this.maxQuantity = this.model.getConfigurationPropertiesDTO().getMaxQuantity();
        this.minQuantity = this.model.getConfigurationPropertiesDTO().getMinQuantity();
        this.state = "Normal";

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

    public Model getModel() {
        return model;
    }

    public int getId() {
        return id;
    }

    public int getUnderflow() {
        return underflow;
    }

    public int getOverflow() {
        return overflow;
    }

    public String getState() {
        return state;
    }

    public int getConsumerNum() {
        return consumerNum;
    }

    public void setConsumerNum(int consumerNum) {
        this.consumerNum = consumerNum;
    }

    public int getProducerNum() {
        return producerNum;
    }

    public void setProducerNum(int producerNum) {
        this.producerNum = producerNum;
    }

    public void addResources(){
        quantity = quantity + 1;
    }

    public void removeResources(){
        quantity = quantity - 1;
    }
}