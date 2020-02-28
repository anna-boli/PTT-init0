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

        // test create
        User u1 = new User("t1", "t1", "teacher");
        User u2 = new User("t2", "t2", "teacher");
        // ---- test controller cd method

        view.startScreen();
        User currentUser = UserSystem.getCurrentUser();
        switch (currentUser.getRole()) {
            case "a":
                controller.ad_login();
                break;
            case "pd":
                controller.ptt_login();
                break;
            case "cd":
                controller.cd_login();
                break;
            case "t":
                controller.t_login();
                break;
        }
        // controller.cd_login();
        // controller.ptt_login();
        // controller.ad_login();
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