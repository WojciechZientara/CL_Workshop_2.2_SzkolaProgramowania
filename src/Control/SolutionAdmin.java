package Control;

import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;
import Model.Exercise;
import Model.Solution;
import Model.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class SolutionAdmin {

    public static void main(String[] args) {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Wybierz jedną z opcji:");
            System.out.println("add");
            System.out.println("view");
            System.out.println("quit");
            String choice = scanner.nextLine();
            if (choice.equals("add")) {
                addSolution();
            } else  if (choice.equals("view")){
                viewSolutions();
            } else  if (choice.equals("quit")){
                System.out.println("Zamykanie programu");
                System.exit(0);
            } else {
                System.out.println("wybrano niepoprawny wariant");
            }
        }
    }

    private static void addSolution() {
        Solution solution = new Solution();
        getUsers();
        solution.setUsers_id(chooseUser());
        getExercises();
        solution.setExercise_id(chooseExercise());
        solution.setCreated(new java.sql.Date(Calendar.getInstance().getTime().getTime()));

        SolutionDao solutionDao = new SolutionDao();
        solutionDao.create(solution);
        System.out.println("Zadanie przypisane!");
    }

    private static void viewSolutions() {
        int userId = chooseUser();
        SolutionDao solutionDao = new SolutionDao();
        Solution[] usersSolutions = solutionDao.findAllByUserId(userId);
        System.out.println("Id zadania : data utworzenia");
        for (Solution solution : usersSolutions) {
            System.out.println(solution.getExercise_id() + " : " + solution.getCreated());
        }
    }

    private static void getUsers() {
        UserDao userDao = new UserDao();
        User[] users = userDao.findAll();
        System.out.println("User ID : User Name");
        for (User user : users) {
            System.out.println(user.getId() + " : " + user.getUsername());
        }
        System.out.println();
    }

    private static int chooseUser() {
        System.out.println("Podaj Id użytkownika:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();        
    }

    private static void getExercises() {
        ExerciseDao exerciseDao = new ExerciseDao();
        Exercise[] exercises = exerciseDao.findAll();
        System.out.println("Exercise ID : Exercise Title");
        for (Exercise exercise : exercises) {
            System.out.println(exercise.getId() + " : " + exercise.getTitle());
        }
        System.out.println();
    }

    private static int chooseExercise() {
        System.out.println("Podaj Id zadania:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

}
