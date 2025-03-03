package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.*;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;
@Getter
@Setter
public class Model {

    private Controller controller;
    private ArrayList<Consumer> consumerList;
    private ArrayList<Producer> producersList;
    private ArrayList<ResourceType> resourceTypesList;
    private ConfigurationPropertiesDTO configurationPropertiesDTO;

    private boolean running = false;

    public Model(Controller controller){
        this.controller = controller;
        this.configurationPropertiesDTO = new ConfigurationPropertiesDTO();
    }

    public void play(){
        running = true;
        // Crear Resources establecidos en el DTO y añadirlos a la tabla
        createResources();
        // Crear Producers establecidos en el DTO ''
        createProducers();
        // Crear Consumers ''
        createConsumers();
    }

    private void createConsumers() {
        consumerList = new ArrayList<>();

        for (int i = 0; i < configurationPropertiesDTO.getConsumerNumber(); i++){
            long startDelay = pickRandomStartDelay();
            ResourceType resourceType = pickRandomResource();
            Consumer consumer = new Consumer(this, i + 1, resourceType, startDelay);
            consumerList.add(consumer);

            //Incrementar numero de consumidores asociados al recurso
            resourceType.increaseConsumerNum();
            Thread consumerThread = new Thread(consumer);

            consumerThread.start();
        }
    }

    private void createProducers() {
        producersList = new ArrayList<>();

        for (int i = 0; i < configurationPropertiesDTO.getProducerNumber(); i++){
            long startDelay = pickRandomStartDelay();
            ResourceType resourceType = pickRandomResource();
            Producer producer1 = new Producer(this, i + 1, resourceType, startDelay);
            producersList.add(producer1);

            //Incrementar numero de productores asociados al recurso
            resourceType.increaseProducerNum();

            Thread producerThread = new Thread(producer1);
            producerThread.start();
        }
    }

    private void createResources(){
        resourceTypesList = new ArrayList<>();

        for (int i = 0; i < configurationPropertiesDTO.getResourceTypes(); i++){
            ResourceType resourceType1 = new ResourceType(this, i + 1);
            resourceTypesList.add(resourceType1);
        }

    }

    private ResourceType pickRandomResource(){
        Random random = new Random();
        int randomResourceIndex = random.nextInt(resourceTypesList.size());
        return resourceTypesList.get(randomResourceIndex);
    }

    private long pickRandomStartDelay(){
        int min = this.getConfigurationPropertiesDTO().getStartDelayMin();
        int max = this.getConfigurationPropertiesDTO().getStartDelayMax();
        if (max >= min){
            Random random = new Random();
            long result = random.nextInt(max - min + 1) + min;
            return result;
        }

        return 300;
    }

    public void stop(){
        this.running = false;
    }
    public ModelDTO sendDTO(){
        return new ModelDTO(this.consumerList, this.producersList, this.resourceTypesList, this.running);
    }
}
