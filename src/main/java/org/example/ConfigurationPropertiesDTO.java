package org.example;

public class ConfigurationPropertiesDTO {

    int resourceTypes;
    int maxQuantity;
    int minQuantity;
    int producerNumber;
    int consumerNumber;


    // En milisegundos
    int startDelayMax;
    int startDelayMin;
    int ProducerDelayMax;
    int ProducerDelayMin;
    int consumerDelayMax;
    int ConsumerDelayMin;
    boolean lifeCyclesEnabled;

    int cycles;
    boolean guardedRegion;
    boolean protectNegativeStock;


    public int getResourceTypes() {
        return resourceTypes;
    }

    public void setResourceTypes(int resourceTypes) {
        this.resourceTypes = resourceTypes;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public int getProducerNumber() {
        return producerNumber;
    }

    public void setProducerNumber(int producerNumber) {
        this.producerNumber = producerNumber;
    }

    public int getConsumerNumber() {
        return consumerNumber;
    }

    public void setConsumerNumber(int consumerNumber) {
        this.consumerNumber = consumerNumber;
    }

    public int getStartDelayMax() {
        return startDelayMax;
    }

    public void setStartDelayMax(int startDelayMax) {
        this.startDelayMax = startDelayMax;
    }

    public int getStartDelayMin() {
        return startDelayMin;
    }

    public void setStartDelayMin(int startDelayMin) {
        this.startDelayMin = startDelayMin;
    }

    public int getProducerDelayMax() {
        return ProducerDelayMax;
    }

    public void setProducerDelayMax(int producerDelayMax) {
        ProducerDelayMax = producerDelayMax;
    }

    public int getProducerDelayMin() {
        return ProducerDelayMin;
    }

    public void setProducerDelayMin(int prodcerDelayMin) {
        ProducerDelayMin = prodcerDelayMin;
    }

    public int getConsumerDelayMax() {
        return consumerDelayMax;
    }

    public void setConsumerDelayMax(int consumerDelayMax) {
        this.consumerDelayMax = consumerDelayMax;
    }

    public int getConsumerDelayMin() {
        return ConsumerDelayMin;
    }

    public void setConsumerDelayMin(int consumerDelayMin) {
        ConsumerDelayMin = consumerDelayMin;
    }

    public boolean isLifeCyclesEnabled() {
        return lifeCyclesEnabled;
    }

    public void setLifeCyclesEnabled(boolean lifeCyclesEnabled) {
        this.lifeCyclesEnabled = lifeCyclesEnabled;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public boolean isGuardedRegion() {
        return guardedRegion;
    }

    public void setGuardedRegion(boolean guardedRegion) {
        this.guardedRegion = guardedRegion;
    }

    public boolean isProtectNegativeStock() {
        return protectNegativeStock;
    }

    public void setProtectNegativeStock(boolean protectNegativeStock) {
        this.protectNegativeStock = protectNegativeStock;
    }

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
