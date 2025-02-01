package org.example;

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
}
