package org.example;

public class Model {

    private Controller controller;
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
        this.consumer = new Consumer(this);
        this.producer = new Producer(this);
        this.resourceType = new ResourceType();
        this.configurationPropertiesDTO = new ConfigurationPropertiesDTO();
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
}
