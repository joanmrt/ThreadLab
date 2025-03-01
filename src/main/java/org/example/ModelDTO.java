package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Consumer;
import org.example.model.Producer;
import org.example.model.ResourceType;

import java.util.ArrayList;
@Getter
@Setter
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
}
