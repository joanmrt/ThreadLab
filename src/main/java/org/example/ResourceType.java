package org.example;

public class ResourceType {
    private Model model;
    private int id;

    private int quantity = 0;
    private int maxQuantity;
    private int minQuantity;

    private int consumerNum = 0;

    private int producerNum = 0;

    private int underflow = 0;

    private int overflow = 0;
    private String state;

    public ResourceType(Model model, int id){
        this.model = model;
        this.id = id;
        this.maxQuantity = this.model.getConfigurationPropertiesDTO().getMaxQuantity();
        this.minQuantity = this.model.getConfigurationPropertiesDTO().getMinQuantity();
        this.state = "Normal";

    }

    public boolean consume() {
        System.out.println("quantity consume: " + quantity);
        if (quantity > minQuantity){
            //sleep(20);
            quantity = quantity - 1;
            checkUnderflow();
            updateState();
            return true;
        }

        return false;
    }

    public boolean produce() {
        System.out.println("quantity produce: " + quantity);
        if (quantity < maxQuantity){
            //sleep(20);

            quantity = quantity + 1;
            checkOverflow();
            updateState();
            return true;
        }
        return false;
    }

    public synchronized boolean consumeSync() {

        checkUnderflow();
        if (quantity > minQuantity){
            //sleep(20);
            quantity = quantity - 1;
            checkUnderflow();
            updateState();
            return true;
        }

        else if (quantity <= minQuantity){
            try {
                wait();
                return false;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        return false;
    }

    public synchronized boolean produceSync() {

        checkOverflow();
        if (quantity < maxQuantity){
            //sleep(20);

            quantity = quantity + 1;
            checkOverflow();
            updateState();
            notify();
            return true;
        }
        return false;
    }


    private void checkOverflow(){
        System.out.println("quantity overflow: " + quantity);
        if (quantity > maxQuantity){

            overflow ++;
        }
    }

    private void checkUnderflow(){
        System.out.println("quantity underflow: " + quantity);
        if (quantity < minQuantity){

            underflow++;
        }
    }

    private void updateState() {
        if (quantity > maxQuantity) {
            state = "Overflow";
        } else if (quantity < minQuantity) {
            state = "Underflow";
        } else if (quantity >= 0) {
            state = "Normal";
        }
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


}