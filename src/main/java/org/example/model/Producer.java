package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Model;
import org.example.model.ResourceType;

import java.util.Random;

import static java.lang.Thread.sleep;
@Getter
@Setter
public class Producer implements Runnable{

    private Model model;
    private int id;
    private String state;
    private long startDelay;
    private long produceDelay;
    private int timesProduced = 0;
    private int lifeCycles = 0;
    private long startTime;
    private long endTime;
    private ResourceType boundResource;
    public Producer(Model model, int id, ResourceType resourceType, long startDelay){
        this.boundResource = resourceType;
        this.model = model;
        this.id = id;
        this.startDelay =startDelay;

    }

    private void produce() {
        boolean isSync = this.model.getConfigurationPropertiesDTO().isGuardedRegion();
        try {
            boolean produced;
            if (isSync){
                produced = this.boundResource.produceSync();
            }
            else {
                produced = this.boundResource.produce();
            }
            if (produced){
                timesProduced++;
            }

            sleep(randomProducerDelay());
            lifeCycles++;
        } catch (InterruptedException e) {
            state = "INTERRUPTED";
            throw new RuntimeException(e);
        }
    }
    private int randomProducerDelay(){
        int min = this.model.getConfigurationPropertiesDTO().getProducerDelayMin();
        int max = this.model.getConfigurationPropertiesDTO().getProducerDelayMax();

        if (max >= min){
            Random random = new Random();
            int result = random.nextInt(max - min + 1) + min;
            produceDelay = result;
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

        //Incrementar numero de productores asociados al recurso
        //this.boundResource.setProducerNum(this.boundResource.getProducerNum() + 1);

        state = "RUNNING";
        if (this.model.getConfigurationPropertiesDTO().isLifeCyclesEnabled()){
            for (int i=0; i<this.model.getConfigurationPropertiesDTO().getCycles(); i++){
                produce();
            }
        }

        else{
            state = "RUNNING";
            while (this.model.isRunning()){
                produce();
            }
        }

    }

}