package app;

import java.util.ArrayList;

import controllers.Controller;
import models.Model;
import models.RequirementList;
import models.UserSystem;
import models.users.CourseDirector;
import models.users.Teacher;
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
    

        // ---- test controller cd method
        controller.cd_login();
        controller.ptt_login();
        controller.ad_login();
        // controller.t_login();

        // /*
        // * System input loop
        // */
        // UserSystem.importUsers(new User("admin", "admin"));
        // view.StartScreen();
        // System.out.println("You have logged in successfully.");
        // view.endScreen();
    }
}