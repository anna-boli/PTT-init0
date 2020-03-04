package app;

import controllers.Controller;
import models.Model;
import models.UserSystem;
import models.users.User;
import views.View;

public class EmptyApp {
    public static void main(String[] args) {
        /*
         * Initialize MVC pattern
         */
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View(model, controller);
        controller.addView(view);

        // ---- test controller cd method
        while (true) {
            view.getUserSystemView().startScreen();
            User currentUser = UserSystem.getCurrentUser();
            if (currentUser == null) {
                continue;
            }
            String role = currentUser.getRole();
            switch (role) {
                case "a":
                    controller.ad_login();
                    break;
                case "pd":
                    controller.ptt_login();
                    break;
                case "cd":
                    controller.courseDirectorLogin();
                    break;
                case "t":
                    controller.t_login();
                    break;
            }
            currentUser = null;
        }
    }
}