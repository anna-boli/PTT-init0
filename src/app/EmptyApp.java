package app;

import controllers.Controller;
import models.Model;
import models.UserSystem;
import models.users.User;
import views.UserSystemView;
import views.View;

public class EmptyApp {
    public static void main(String[] args) {
        /*
         * Initialize MVC pattern
         */
        Model model = new Model();
        Controller controller = new Controller(model);
        View view = new View();
        controller.addView(view);

        // ---- test controller cd method
        while (true) {
            UserSystemView.startScreen();
            User currentUser = UserSystem.getCurrentUser();
            if (currentUser == null) {
                continue;
            }
            String role = currentUser.getRole();
            switch (role) {
                case "a":
                    controller.administratorLogin();
                    break;
                case "pd":
                    controller.pttDirectorLogin();
                    break;
                case "cd":
                    controller.courseDirectorLogin();
                    break;
                case "t":
                    controller.teacherLogin();
                    break;
            }
            currentUser = null;
        }
    }
}