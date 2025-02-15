package org.example;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

public class Controller {

    private View view;
    private Model model;


    public Controller(View view, Model model) {
        this.view = view;
        System.out.println("myView creado");
        this.model = model;
        System.out.println("myModel creado");
    }

    public Controller(){
        this.model = new Model(this);
        this.view = new View(this);

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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


}
