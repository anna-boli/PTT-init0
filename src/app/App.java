package app;

import controllers.Controller;
import models.Model;
import models.UserSystem;
import models.database.Database;
import models.users.User;
import views.UserSystemView;
import views.View;

public class App {
    public static void main(String[] args) {
        /*
         * Initialize MVC pattern
         */
        Model model = new Model();
        if (Database.isDataExist()) {
            model = Database.load();
        }
        Controller controller = new Controller(model);
        View view = new View();
        controller.addView(view);

        // test create
        // model.getUserSystem().register("a", "a", "a");
        // model.getUserSystem().register("t", "t", "t");
        // model.getUserSystem().register("cd", "cd", "cd");
        // model.getUserSystem().register("pt", "pt", "pt");

        // Course course = new Course("math");
        // RequirementList requirementList = new RequirementList(2020, 1);
        // requirementList.updateCourse(course);
        // model.getData().addToData(requirementList);

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
            UserSystem.setCurrentUser(null);
        }

        // controller.courseDirectorLogin();
        // controller.pttDirectorLogin();
        // controller.administratorLogin();
        // controller.teacherLogin();

        // /*
        // * System input loop
        // */
        // UserSystem.importUsers(new User("admin", "admin"));
        // view.StartScreen();
        // System.out.println("You have logged in successfully.");
        // view.endScreen();
    }
}