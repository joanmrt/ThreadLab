package org.example;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Model;
import org.example.view.View;

import javax.swing.*;

@Getter
@Setter
public class Controller {

    private View view;
    private Model model;

    public Controller(){
        this.model = new Model(this);
        this.view = new View(this);

    }

    public void setDTOParams() {

        JTable configTable = this.view.getConfigurationPanel();

        model.getConfigurationPropertiesDTO().setResourceTypes(Integer.parseInt(configTable.getValueAt(0,1).toString()));
        model.getConfigurationPropertiesDTO().setMaxQuantity(Integer.parseInt(configTable.getValueAt(1,1).toString()));
        model.getConfigurationPropertiesDTO().setMinQuantity(Integer.parseInt(configTable.getValueAt(2,1).toString()));
        model.getConfigurationPropertiesDTO().setProducerNumber(Integer.parseInt(configTable.getValueAt(3,1).toString()));
        model.getConfigurationPropertiesDTO().setConsumerNumber(Integer.parseInt(configTable.getValueAt(4,1).toString()));
        model.getConfigurationPropertiesDTO().setStartDelayMax(Integer.parseInt(configTable.getValueAt(5,1).toString()));
        model.getConfigurationPropertiesDTO().setStartDelayMin(Integer.parseInt(configTable.getValueAt(6,1).toString()));
        model.getConfigurationPropertiesDTO().setProducerDelayMax(Integer.parseInt(configTable.getValueAt(7,1).toString()));
        model.getConfigurationPropertiesDTO().setProducerDelayMin(Integer.parseInt(configTable.getValueAt(8,1).toString()));
        model.getConfigurationPropertiesDTO().setConsumerDelayMax(Integer.parseInt(configTable.getValueAt(9,1).toString()));
        model.getConfigurationPropertiesDTO().setConsumerDelayMin(Integer.parseInt(configTable.getValueAt(10,1).toString()));
        model.getConfigurationPropertiesDTO().setLifeCyclesEnabled(Boolean.parseBoolean(configTable.getValueAt(11,1).toString()));
        model.getConfigurationPropertiesDTO().setCycles(Integer.parseInt(configTable.getValueAt(12,1).toString()));
        model.getConfigurationPropertiesDTO().setGuardedRegion(Boolean.parseBoolean(configTable.getValueAt(13,1).toString()));
        model.getConfigurationPropertiesDTO().setProtectNegativeStock(Boolean.parseBoolean(configTable.getValueAt(14,1).toString()));

        System.out.println(model.getConfigurationPropertiesDTO().toString());
    }

    public ModelDTO getModelDTO(){
        ModelDTO modelDTO = this.model.sendDTO();
        return modelDTO;
    }

    public void play(){
        this.getModel().play();
    }

    public void stop(){
        this.model.stop();
    }

}
