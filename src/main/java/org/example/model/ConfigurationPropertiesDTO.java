package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationPropertiesDTO {

    private int resourceTypes;
    private int maxQuantity;
    private int minQuantity;
    private int producerNumber;
    private int consumerNumber;

    // En milisegundos
    private int startDelayMax;
    private int startDelayMin;
    private int ProducerDelayMax;
    private int ProducerDelayMin;
    private int consumerDelayMax;
    private int ConsumerDelayMin;
    private boolean lifeCyclesEnabled;

    private int cycles;
    private boolean guardedRegion;
    private boolean protectNegativeStock;

    @Override
    public String toString() {
        return "ConfigurationPropertiesDTO{" +
                "resourceTypes=" + resourceTypes +
                ", maxQuantity=" + maxQuantity +
                ", minQuantity=" + minQuantity +
                ", producerNumber=" + producerNumber +
                ", consumerNumber=" + consumerNumber +
                ", startDelayMax=" + startDelayMax +
                ", startDelayMin=" + startDelayMin +
                ", ProducerDelayMax=" + ProducerDelayMax +
                ", ProducerDelayMin=" + ProducerDelayMin +
                ", consumerDelayMax=" + consumerDelayMax +
                ", ConsumerDelayMin=" + ConsumerDelayMin +
                ", lifeCyclesEnabled=" + lifeCyclesEnabled +
                ", cycles=" + cycles +
                ", guardedRegion=" + guardedRegion +
                ", protectNegativeStock=" + protectNegativeStock +
                '}';
    }
}
