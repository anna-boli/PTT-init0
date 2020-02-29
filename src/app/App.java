package app;

import controllers.Controller;
import models.Model;
import models.UserSystem;
import models.database.Database;
import models.users.User;
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
        View view = new View(model, controller);
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