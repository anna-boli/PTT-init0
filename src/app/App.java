package app;

import controllers.Controller;
import models.Model;
import views.View;

public class App {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);
        controller.addView(view);
    }
}