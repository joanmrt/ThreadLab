package org.example;

import java.util.ArrayList;

public class Model {

    private Controller controller;

    private ArrayList<Consumer> consumerList;
    private ArrayList<Producer> producersList;
    private ArrayList<ResourceType> resourceTypesList;
    private Consumer consumer;
    private Producer producer;
    private ResourceType resourceType;
    private ConfigurationPropertiesDTO configurationPropertiesDTO;

    public Model(Consumer consumer, Producer producer) {
        this.consumer = consumer;
        this.producer = producer;

    }

    public Model(Controller controller){
        this.controller = controller;
        this.configurationPropertiesDTO = new ConfigurationPropertiesDTO();
        this.consumer = new Consumer(this);
        this.producer = new Producer(this);
        this.resourceType = new ResourceType(this, 1);

    }

    public void play(){
        // Crear Resources establecidos en el DTO y añadirlos a la tabla
        createResources();
        // Crear Producers establecidos en el DTO ''

        // Crear Consumers ''
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

    public void stop(){

    }

    public ArrayList<Consumer> getConsumerList() {
        return consumerList;
    }

    public ArrayList<Producer> getProducersList() {
        return producersList;
    }

    public ArrayList<ResourceType> getResourceTypesList() {
        return resourceTypesList;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public ResourceType getResources() {
        return resourceType;
    }

    public void setResources(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ConfigurationPropertiesDTO getConfigurationPropertiesDTO() {
        return configurationPropertiesDTO;
    }

    public void setConfigurationPropertiesDTO(ConfigurationPropertiesDTO configurationPropertiesDTO) {
        this.configurationPropertiesDTO = configurationPropertiesDTO;
    }
}
