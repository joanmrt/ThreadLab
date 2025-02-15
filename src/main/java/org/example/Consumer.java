package org.example;

import static java.lang.Thread.sleep;

public class Consumer implements Runnable{

    private Model model;
    private ResourceType resourceType;

    public Consumer(Model model){
        this.model = model;
    }

    public void consume(){
        model.getResources().setQuantity(model.getResources().getQuantity()-1);
    }
    @Override
    public void run() {
        for (int i=0; i<10000; i++){

            try {
                model.getResources().removeResources();
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
