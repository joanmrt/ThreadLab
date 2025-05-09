package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

import static java.lang.Thread.sleep;

@Getter
@Setter
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

    private void consume() {
        boolean isSync = this.model.getConfigurationPropertiesDTO().isGuardedRegion();
        boolean isProtected = this.model.getConfigurationPropertiesDTO().isProtectNegativeStock();
        try {
            boolean consumed;
            if (isSync && isProtected){
                consumed = this.boundResource.consumeSyncProtected();
            }
            else if (!isSync && isProtected){
                consumed = this.boundResource.consumeUnsyncProtected();
            } else if (isSync && !isProtected) {
                consumed = this.boundResource.consumeSyncUnprotected();
            } else {
                consumed = this.boundResource.consumeUnsyncUnprotected();
            }
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


        if (this.model.getConfigurationPropertiesDTO().isLifeCyclesEnabled()){
            state = "RUNNING";
            for (int i=0; i<this.model.getConfigurationPropertiesDTO().getCycles(); i++){

                consume();
            }
        }

        else{
            state = "RUNNING";
            while (this.model.isRunning()){
                consume();
            }
        }


    }


}
