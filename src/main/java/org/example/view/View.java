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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private Timer timer;
    private int timeCounter;

    public View(Controller controller){
        this.controller = controller;
        this.controlPanel = new ControlPanel();
        this.dataPanel = new DataPanel();
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
        c.gridheight=2;
        c.fill = BOTH;
        JScrollPane scrollPane = new JScrollPane(this.dataPanel);
        scrollPane.getViewport().setBackground(new Color(255, 219, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(154, 66, 35),1));
        this.add(scrollPane, c);

        //Conifg Panel

        c.weightx = 0.1; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 1;
        c.gridheight=3;
        c.fill = BOTH;
        scrollPane = new JScrollPane(this.configurationPanel);
        scrollPane.getViewport().setBackground(new Color(255, 219, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(154, 66, 35),1));
        this.add(scrollPane, c);

        // ResourcesPanel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        scrollPane = new JScrollPane(this.resourcePanel);
        scrollPane.getViewport().setBackground(new Color(255, 219, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(154, 66, 35),1));
        this.add(scrollPane, c);

        // Consumer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 1;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        scrollPane = new JScrollPane(this.consumerPanel);
        scrollPane.getViewport().setBackground(new Color(255, 219, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(154, 66, 35),1));
        this.add(scrollPane, c);

        // Producer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 2;
        c.gridx = 2;
        c.gridheight=1;
        c.fill = BOTH;
        scrollPane = new JScrollPane(this.producerPanel);
        scrollPane.getViewport().setBackground(new Color(255, 219, 100));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(154, 66, 35),1));
        this.add(scrollPane, c);

        // Panel de botones

        this.controlPanel.setLayout(new GridBagLayout());

        // Button Play
        JButton playButton = this.controlPanel.getPlay();
        playButton.addActionListener(e -> {
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
                startTimer();
            }

            else {
                System.out.println("Already running!");
            }

        });

        c.weightx = 1;
        c.weighty = 0;
        c.gridy = 0;
        c.gridx = 0;
        c.ipady = 20;
        c.fill = HORIZONTAL;
        c.anchor = CENTER;
        this.controlPanel.add(playButton, c);

        // Button Stop
        JButton stopButton = this.controlPanel.getStop();
        stopButton.addActionListener(e -> {
            this.controller.stop();
            stopTimer();
        });
        c.weightx = 1;
        c.weighty = 0;
        c.gridy = 1;
        c.gridx = 0;
        c.fill = HORIZONTAL;
        c.anchor = CENTER;
        this.controlPanel.add(stopButton,c);

        // Añadir Panel de botones al panel principal
        c.weightx = 0.1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.ipady = 0;
        c.fill = BOTH;
        c.anchor = CENTER;
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

        //Add DataPanel

        DefaultTableModel tableModelData = (DefaultTableModel) this.dataPanel.getModel();
        tableModelData.setValueAt(arrayListResources.size(),0,1);
        tableModelData.setValueAt(arrayListProducer.size(),1,1);
        tableModelData.setValueAt(arrayListConsumer.size(),2,1);
        tableModelData.setValueAt(arrayListConsumer.size()+arrayListProducer.size(),3,1);

    }

    private void updateTables(ModelDTO modelDTO){
        updateResources(modelDTO);
        updateConsumers(modelDTO);
        updateProducers(modelDTO);
        updateDataPanel();
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

    private void updateDataPanel(){
        DefaultTableModel tableModelData = (DefaultTableModel) this.dataPanel.getModel();
        String timerString = formatTime(timeCounter);
        tableModelData.setValueAt(timerString,4,1);
    }

    private void clearTables(){
        DefaultTableModel tableModelResource = (DefaultTableModel) this.resourcePanel.getModel();
        tableModelResource.setRowCount(0);

        DefaultTableModel tableModelConsumer = (DefaultTableModel) this.consumerPanel.getModel();
        tableModelConsumer.setRowCount(0);

        DefaultTableModel tableModelProducer = (DefaultTableModel) this.producerPanel.getModel();
        tableModelProducer.setRowCount(0);

        DefaultTableModel tableModelData = (DefaultTableModel) this.dataPanel.getModel();
        tableModelData.setValueAt(0,0,1);
        tableModelData.setValueAt(0,1,1);
        tableModelData.setValueAt(0,2,1);
        tableModelData.setValueAt(0,3,1);
    }

    private void startTimer(){
        if (timer != null && timer.isRunning()) {
            return;
        }
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeCounter++;
            }
        });
        timer.start();
    }

    private void stopTimer(){
        if (timer != null) {
            timer.stop();
            timeCounter = 0;
        }
    }

    private static String formatTime(int milliseconds) {
        int minutes = (milliseconds % 3600000) / 60000;  // 60000 ms in a minute
        int seconds = (milliseconds % 60000) / 1000;  // 1000 ms in a second
        int ms = milliseconds % 1000;  // Remaining milliseconds

        // Format the time as mm:ss.SSS
        return String.format("%02d:%02d.%03d", minutes, seconds, ms);
    }

    @Override
    public void run() {
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
}