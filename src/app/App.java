package app;

import controllers.Controller;
import models.Model;
import models.UserSystem;
import models.users.User;
import views.View;

public class App {
    public static void main(String[] args) {
        /*
         * Initialize MVC pattern
         */
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);
        controller.addView(view);

        /*
         * System input loop
         */
        UserSystem.importUsers(new User("admin", "admin"));
        view.StartScreen();
        System.out.println("You have logged in successfully.");
        view.endScreen();
    }
}