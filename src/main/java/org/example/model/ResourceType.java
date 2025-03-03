package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Model;

import static java.lang.Thread.sleep;
@Getter
@Setter
public class ResourceType {
    private Model model;
    private int id;
    private volatile int quantity = 0;
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

    public synchronized boolean consumeSyncProtected(){

        while (quantity <= minQuantity){
            try{
                wait();
            }
            catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }

        quantity--;
        checkUnderflow();
        updateState();
        return true;
    }

    public synchronized boolean consumeSyncUnprotected(){
        quantity--;
        checkUnderflow();
        updateState();
        return true;

    }

    public boolean consumeUnsyncUnprotected() {
        quantity--;
        checkUnderflow();
        updateState();
        return true;

    }

    public boolean consumeUnsyncProtected() {

        if (quantity <= minQuantity){
            return false;
        }

        quantity--;
        checkUnderflow();
        updateState();
        return true;

    }
    public boolean produce() {
        quantity = quantity + 1;
        checkOverflow();
        updateState();
        return true;
    }

    public synchronized boolean produceSync() {

        if (quantity < maxQuantity){
            quantity = quantity + 1;
            checkOverflow();
            checkOverflow();
            updateState();
            notify();
            return true;
        }
        return false;
    }


    private void checkOverflow(){
        if (quantity > maxQuantity){
            System.out.println("quantity overflow: " + quantity);
            overflow ++;
        }
    }

    private void checkUnderflow(){

        if (quantity < minQuantity){
            System.out.println("quantity underflow: " + quantity);
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

    public void increaseConsumerNum(){
        consumerNum++;
    }

    public void increaseProducerNum(){
        producerNum++;
    }
}