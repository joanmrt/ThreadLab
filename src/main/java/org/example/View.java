package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static java.awt.GridBagConstraints.*;

public class View extends JFrame implements Runnable {

    private Controller controller;
    private ControlPanel controlPanel;
    private Viewer viewer;
    private DataPanel dataPanel;
    private ConfigurationPanel configurationPanel;
    private ConsumerPanel consumerPanel;
    private ResourcePanel resourcePanel;
    private ProducerPanel producerPanel;


    public View(ControlPanel controlPanel, Viewer viewer, DataPanel dataPanel) {

        this.controlPanel = controlPanel;
        System.out.println("Control creado");
        this.viewer = viewer;
        System.out.println("Viewer creado");
        this.dataPanel = dataPanel;
        System.out.println("Data Viewer creado.");
        createWindow();
    }

    public View(Controller controller){
        this.controller = controller;
        this.viewer = new Viewer();
        this.controlPanel = new ControlPanel();
        this.dataPanel = new DataPanel(this);
        this.configurationPanel = new ConfigurationPanel();
        this.consumerPanel = new ConsumerPanel();
        this.resourcePanel = new ResourcePanel();
        this.producerPanel = new ProducerPanel();
        createWindow();
        Thread updateValuesThread = new Thread(this);
        updateValuesThread.start();
    }

    public void setController(Controller controller){
        this.controller = controller;
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
        c.fill = BOTH;
        this.add(new JScrollPane(this.dataPanel), c);

        //Conifg Panel

        c.weightx = 0.1; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 1;
        c.fill = BOTH;
        this.add(new JScrollPane(this.configurationPanel), c);

        // Consumer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 2;
        c.fill = BOTH;
        this.add(new JScrollPane(this.consumerPanel), c);

        // ResourcesPanel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 3;
        c.fill = BOTH;
        this.add(new JScrollPane(this.resourcePanel), c);

        // Producer Panel

        c.weightx = 0.2; // 0.2
        c.weighty = 1;
        c.gridy = 0;
        c.gridx = 4;
        c.fill = BOTH;
        this.add(new JScrollPane(this.producerPanel), c);

        // Panel de botones

        this.controlPanel.setLayout(new GridBagLayout());
        // Button Play
        JButton playButton = this.controlPanel.getPlay();
        playButton.addActionListener(e -> {
            System.out.println("Play button clicked!");
            this.viewer.setBackground(Color.GREEN);
            Thread thread = new Thread(controller.getModel().getProducer());
            Thread thread1 = new Thread(controller.getModel().getConsumer());
            thread.start();
            thread1.start();
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
            this.viewer.setBackground(Color.RED);
            System.out.println(controller.getModel().getResources().getQuantity());
        });
        c.gridy = 0;
        c.gridx = 1;
        this.controlPanel.add(stopButton,c);

        // Añadir Panel de botones al panel principal
        c.weightx = 1;
        c.weighty = 0;
        c.gridx = 0;
        c.gridy = 1;
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

    @Override
    public void run() {
        DefaultTableModel tableModel = (DefaultTableModel) this.dataPanel.getModel();

        while (true){

            try {
                int newValue = controller.getModel().getResources().getQuantity();
                tableModel.setValueAt(newValue, 0,1);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public Controller getController() {
        return controller;
    }
}