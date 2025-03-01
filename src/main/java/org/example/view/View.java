package org.example.view;

import lombok.Getter;
import lombok.Setter;
import org.example.*;
import org.example.model.Consumer;
import org.example.model.Producer;
import org.example.model.ResourceType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static java.awt.GridBagConstraints.*;
import static java.lang.Thread.sleep;

@Getter
@Setter
public class View extends JFrame implements Runnable {

    private Controller controller;
    private ControlPanel controlPanel;
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private ConsumerPanel consumerPanel;
    private ResourcePanel resourcePanel;
    private ProducerPanel producerPanel;

    public View(Controller controller){
        this.controller = controller;
        this.controlPanel = new ControlPanel();
        this.dataPanel = new DataPanel(this);
        this.configurationPanel = new ConfigurationPanel();
        this.consumerPanel = new ConsumerPanel();
        this.resourcePanel = new ResourcePanel();
        this.producerPanel = new ProducerPanel();
        createWindow();

    }
    private void createWindow(){
        this.setLayout(new GridBagLayout());
        this.setTitle("ThreadLab");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints c = new GridBagConstraints();


        // Data Viewer
        c.weightx = 0.1; //0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 0;
        c.gridheight=3;
        c.fill = BOTH;
        this.add(new JScrollPane(this.dataPanel), c);

        //Conifg Panel

        c.weightx = 0.1; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 1;
        c.gridheight=3;
        c.fill = BOTH;
        this.add(new JScrollPane(this.configurationPanel), c);

        // ResourcesPanel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        this.add(new JScrollPane(this.resourcePanel), c);

        // Consumer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 1;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        this.add(new JScrollPane(this.consumerPanel), c);

        // Producer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 2;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        this.add(new JScrollPane(this.producerPanel), c);

        // Panel de botones

        this.controlPanel.setLayout(new GridBagLayout());

        // Button Play
        JButton playButton = this.controlPanel.getPlay();
        playButton.addActionListener(e -> {
            System.out.println("Play button clicked!");
            ModelDTO modelDTO = this.controller.getModelDTO();

            if (!modelDTO.isRunning()){
                try {
                    this.controller.setDTOParams();
                }
                catch (Exception exceptionPlay){
                    System.out.println("Error trying to start model "+ exceptionPlay);
                    System.out.println("Using default settings");
                }

                this.controller.play();
                addTables();
                Thread updateValuesThread = new Thread(this);
                updateValuesThread.start();
            }

            else {
                System.out.println("Already running!");
            }

        });

        c.weightx = 0;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.ipady = 20;
        c.fill = NONE;
        c.anchor = LAST_LINE_START;
        this.controlPanel.add(playButton, c);

        // Button Stop
        JButton stopButton = this.controlPanel.getStop();
        stopButton.addActionListener(e -> {
            System.out.println("Stop button clicked!");

            System.out.println(Integer.parseInt(this.getConfigurationPanel().getValueAt(0,1).toString()));
            this.controller.stop();
            //clearTables();
        });
        c.gridy = 0;
        c.gridx = 1;
        this.controlPanel.add(stopButton,c);



        // Añadir Panel de botones al panel principal
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 5;
        c.ipady = 0;
        c.fill = NONE;
        c.anchor = LAST_LINE_START;
        this.add(this.controlPanel, c);

        // Actualizar maquetacion
        this.pack();


        // Definir tamaño final de ventana y hacerlo visible
        this.setBounds(500,200, 1000,800);
        this.setVisible(true);

    }

    private void addTables() {
        //Add Resources
        ModelDTO modelDTO = this.controller.getModelDTO();

        ArrayList<ResourceType> arrayListResources = modelDTO.getResourceTypesList();
        DefaultTableModel tableModelResource = (DefaultTableModel) this.resourcePanel.getModel();

        for (ResourceType resourceType : arrayListResources){
            Object[] rowData = {resourceType.getId(),
                    resourceType.getQuantity(),
                    resourceType.getMinQuantity(),
                    resourceType.getMaxQuantity(),
                    resourceType.getConsumerNum(),
                    resourceType.getProducerNum(),
                    resourceType.getUnderflow(),
                    resourceType.getOverflow(),
                    resourceType.getState()};
            tableModelResource.addRow(rowData);
        }

        // Add Producers
        ArrayList<Producer> arrayListProducer = modelDTO.getProducersList();
        DefaultTableModel tableModelProducer = (DefaultTableModel) this.producerPanel.getModel();

        for (Producer producer : arrayListProducer){
            Object[] rowData = {producer.getId(),
                    "Resource " + producer.getBoundResource().getId(),
                    producer.getState(),
                    producer.getStartDelay(),
                    producer.getProduceDelay(),
                    producer.getTimesProduced(),
                    producer.getLifeCycles(),
                    producer.getStartTime(),
                    producer.getEndTime()};
            tableModelProducer.addRow(rowData);
        }

        //Add Consumer

        ArrayList<Consumer> arrayListConsumer = modelDTO.getConsumerList();
        DefaultTableModel tableModelConsumer = (DefaultTableModel) this.consumerPanel.getModel();

        for (Consumer consumer : arrayListConsumer){
            Object[] rowData = {consumer.getId(),
                    "Resource " + consumer.getBoundResource().getId(),
                    consumer.getState(),
                    consumer.getStartDelay(),
                    consumer.getConsumeDelay(),
                    consumer.getTimesConsumed(),
                    consumer.getLifeCycles(),
                    consumer.getStartTime(),
                    consumer.getEndTime()};
            tableModelConsumer.addRow(rowData);
        }

    }

    private void updateTables(ModelDTO modelDTO){
        updateResources(modelDTO);
        updateConsumers(modelDTO);
        updateProducers(modelDTO);
    }

    private void updateProducers(ModelDTO modelDTO) {
        ArrayList<Producer> arrayListProducer = modelDTO.getProducersList();
        DefaultTableModel tableModelProducer = (DefaultTableModel) this.producerPanel.getModel();

        for (Producer producer : arrayListProducer){
            tableModelProducer.setValueAt(producer.getState(),producer.getId()-1,2);
            tableModelProducer.setValueAt(producer.getProduceDelay(),producer.getId()-1,4);
            tableModelProducer.setValueAt(producer.getTimesProduced(),producer.getId()-1,5);
            tableModelProducer.setValueAt(producer.getLifeCycles(),producer.getId()-1,6);
            tableModelProducer.setValueAt(producer.getStartTime(),producer.getId()-1,7);
            tableModelProducer.setValueAt(producer.getEndTime(),producer.getId()-1,8);


        }
    }

    private void updateConsumers(ModelDTO modelDTO) {
        ArrayList<Consumer> arrayListConsumer = modelDTO.getConsumerList();
        DefaultTableModel tableModelConsumer = (DefaultTableModel) this.consumerPanel.getModel();

        for (Consumer consumer : arrayListConsumer){
            tableModelConsumer.setValueAt(consumer.getState(),consumer.getId()-1,2);
            tableModelConsumer.setValueAt(consumer.getConsumeDelay(),consumer.getId()-1,4);
            tableModelConsumer.setValueAt(consumer.getTimesConsumed(),consumer.getId()-1,5);
            tableModelConsumer.setValueAt(consumer.getLifeCycles(),consumer.getId()-1,6);
            tableModelConsumer.setValueAt(consumer.getStartTime(),consumer.getId()-1,7);
            tableModelConsumer.setValueAt(consumer.getEndTime(),consumer.getId()-1,8);


        }
    }

    private void updateResources(ModelDTO modelDTO){
        ArrayList<ResourceType> arrayListResources = modelDTO.getResourceTypesList();
        DefaultTableModel tableModelResource = (DefaultTableModel) this.resourcePanel.getModel();


        for (ResourceType resourceType : arrayListResources){
            tableModelResource.setValueAt(resourceType.getQuantity(),resourceType.getId()-1,1);
            tableModelResource.setValueAt(resourceType.getConsumerNum(),resourceType.getId()-1,4);
            tableModelResource.setValueAt(resourceType.getProducerNum(),resourceType.getId()-1,5);
            tableModelResource.setValueAt(resourceType.getUnderflow(), resourceType.getId()-1, 6);
            tableModelResource.setValueAt(resourceType.getOverflow(), resourceType.getId()-1, 7);
            tableModelResource.setValueAt(resourceType.getState(), resourceType.getId()-1, 8);
        }
    }

    private void clearTables(){
        DefaultTableModel tableModelResource = (DefaultTableModel) this.resourcePanel.getModel();
        tableModelResource.setRowCount(0);

        DefaultTableModel tableModelConsumer = (DefaultTableModel) this.consumerPanel.getModel();
        tableModelConsumer.setRowCount(0);

        DefaultTableModel tableModelProducer = (DefaultTableModel) this.producerPanel.getModel();
        tableModelProducer.setRowCount(0);
    }

    @Override
    public void run() {
        DefaultTableModel tableModelData = (DefaultTableModel) this.dataPanel.getModel();
        ModelDTO modelDTO = this.controller.getModelDTO();

        while (modelDTO.isRunning()){
            modelDTO = this.controller.getModelDTO();
            try {
                updateTables(modelDTO);
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        clearTables();

    }

    public Controller getController() {
        return controller;
    }

    public ConfigurationPanel getConfigurationPanel() {
        return configurationPanel;
    }
}