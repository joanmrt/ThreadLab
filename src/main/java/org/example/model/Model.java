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
    private Consumer consumer;
    private Producer producer;
    private ResourceType resourceType;
    private ConfigurationPropertiesDTO configurationPropertiesDTO;

    private boolean running = false;

    public Model(Controller controller){
        this.controller = controller;
        this.configurationPropertiesDTO = new ConfigurationPropertiesDTO();
        this.resourceType = new ResourceType(this, 1);

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
        int id = 1;


        for (int i = 0; i < configurationPropertiesDTO.getConsumerNumber(); i++){
            long startDelay = pickRandomStartDelay();
            ResourceType resourceType1 = pickRandomResource();
            Consumer consumer = new Consumer(this, id, resourceType1, startDelay);
            consumerList.add(consumer);

            //Incrementar numero de consumidores asociados al recurso
            resourceType1.setConsumerNum(resourceType1.getConsumerNum() + 1);
            id++;
            Thread consumerThread = new Thread(consumer);

            consumerThread.start();
        }
    }

    private void createProducers() {
        producersList = new ArrayList<>();
        int id = 1;


        for (int i = 0; i < configurationPropertiesDTO.getProducerNumber(); i++){
            long startDelay = pickRandomStartDelay();
            ResourceType resourceType1 = pickRandomResource();
            Producer producer1 = new Producer(this, id, resourceType1, startDelay);
            producersList.add(producer1);

            //Incrementar numero de productores asociados al recurso
            resourceType1.setProducerNum(resourceType1.getProducerNum() + 1);

            id++;
            Thread producerThread = new Thread(producer1);
            producerThread.start();

        }
    }

    private void createResources(){
        resourceTypesList = new ArrayList<>();
        int id = 1;

        for (int i = 0; i < configurationPropertiesDTO.getResourceTypes(); i++){
            ResourceType resourceType1 = new ResourceType(this, id);
            resourceTypesList.add(resourceType1);
            id++;
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
        ModelDTO modelDTO = new ModelDTO(this.consumerList,this.producersList,this.resourceTypesList,this.running);
        return modelDTO;
    }
}
