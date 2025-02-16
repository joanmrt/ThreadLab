package org.example;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Consumer implements Runnable{

    private Model model;
    private int id;
    private String state;
    private long startDelay;
    private long consumeDelay;
    private int timesConsumed = 0;
    private int lifeCycles = 0;
    private long startTime;
    private long endTime;
    private ResourceType boundResource;

    public Consumer(Model model, int id, ResourceType resourceType, long startDelay){
        this.model = model;
        this.boundResource = resourceType;
        this.id = id;
        this.startDelay = startDelay;
    }

    public Model getModel() {
        return model;
    }

    public int getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public long getStartDelay() {
        return startDelay;
    }

    public long getConsumeDelay() {
        return consumeDelay;
    }

    public int getTimesConsumed() {
        return timesConsumed;
    }

    public int getLifeCycles() {
        return lifeCycles;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public ResourceType getBoundResource() {
        return boundResource;
    }

    public void consume(){
        this.boundResource.setQuantity(boundResource.getQuantity() - 1);
        this.timesConsumed++;
    }

    private int randomConsumerDelay(){
        int min = this.model.getConfigurationPropertiesDTO().getConsumerDelayMin();
        int max = this.model.getConfigurationPropertiesDTO().getConsumerDelayMax();

        if (max >= min){
            Random random = new Random();
            int result = random.nextInt(max - min + 1) + min;
            consumeDelay = result;
            return result;
        }
        // Valor por defecto por si el maximo es menor al minimo
        return 10;
    }
    @Override
    public void run() {

        // Comprobar el delay inicial y hacerlo
        try {
            sleep(startDelay);
        } catch (InterruptedException e) {
            state = "INTERRUPTED";
            throw new RuntimeException(e);
        }


        if (this.model.getConfigurationPropertiesDTO().lifeCyclesEnabled){
            state = "RUNNING";
            for (int i=0; i<this.model.getConfigurationPropertiesDTO().getCycles(); i++){

                try {
                    boolean consumed = this.boundResource.consume();
                    if (consumed){
                        timesConsumed++;
                    }

                    sleep(randomConsumerDelay());
                    lifeCycles++;
                } catch (InterruptedException e) {
                    state = "INTERRUPTED";
                    throw new RuntimeException(e);
                }
            }
        }

        else{
            state = "RUNNING";
            while (this.model.isRunning()){
                try {
                    boolean consumed = this.boundResource.consume();
                    if (consumed){
                        timesConsumed++;
                    }


                    sleep(randomConsumerDelay());
                    lifeCycles++;
                } catch (InterruptedException e) {
                    state = "INTERRUPTED";
                    throw new RuntimeException(e);
                }
            }
        }


    }
}
