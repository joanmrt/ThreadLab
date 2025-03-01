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

    public boolean produce() throws InterruptedException {
        System.out.println("quantity produce: " + quantity);
        quantity = quantity + 1;
        checkOverflow();
        updateState();
        return true;
    }

    public synchronized boolean consumeSyncProtected(){

        while (quantity <= minQuantity){
            try{
                wait();
                return false;
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

        while (quantity <= minQuantity){
            try{
                sleep(5);
                //return false;
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


}