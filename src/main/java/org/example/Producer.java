package org.example;

import static java.lang.Thread.sleep;

public class Producer implements Runnable{

    private Model model;
    private Enum<Thread.State> stateEnum;
    public Producer(Model model){
        this.model = model;
    }

    public void produce(){
        model.getResources().setQuantity(model.getResources().getQuantity() + 1);
    }
    @Override
    public void run() {

        for (int i=0; i<10000; i++){
            try {
                //produce();
                model.getResources().addResources();
                sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
