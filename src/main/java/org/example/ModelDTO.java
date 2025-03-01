package org.example;

import org.example.model.Consumer;
import org.example.model.Producer;
import org.example.model.ResourceType;

import java.util.ArrayList;

public class ModelDTO {
    private ArrayList<Consumer> consumerList;
    private ArrayList<Producer> producersList;
    private ArrayList<ResourceType> resourceTypesList;
    private boolean isRunning;

    public ModelDTO(ArrayList<Consumer> consumerList, ArrayList<Producer> producersList, ArrayList<ResourceType> resourceTypesList, boolean isRunning) {
        this.consumerList = consumerList;
        this.producersList = producersList;
        this.resourceTypesList = resourceTypesList;
        this.isRunning = isRunning;
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

    public boolean isRunning() {
        return isRunning;
    }
}
