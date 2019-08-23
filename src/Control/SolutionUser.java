package Control;

import Dao.ExerciseDao;
import Dao.SolutionDao;
import Dao.UserDao;
import Model.Exercise;
import Model.Solution;
import Model.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class SolutionUser {

    public static void main(String[] args) {

        int userId = chooseUser();

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Wybierz jedną z opcji:");
            System.out.println("add");
            System.out.println("view");
            System.out.println("quit");
            String choice = scanner.nextLine();
            if (choice.equals("add")) {
                addSolution(userId);
            } else  if (choice.equals("view")){
                viewSolutions(userId);
            } else  if (choice.equals("quit")){
                System.out.println("Zamykanie programu");
                System.exit(0);
            } else {
                System.out.println("wybrano niepoprawny wariant");
            }
        }
    }

    private static int chooseUser() {
        System.out.println("Podaj swój identyfikator:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int chooseExercise() {
        System.out.println("Podaj Id zadania:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String resolveExercise() {
        System.out.println("Podaj rozwiązanie zadania:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void addSolution(int userId) {
        SolutionDao solutionDao = new SolutionDao();
        Solution[] usersSolutions = solutionDao.findAllByUserId(userId);
        System.out.println("Nierozwiązane zadania:");
        for (Solution solution : usersSolutions) {
            if (solution.getDescription() == null) {
                System.out.println(solution.getExercise_id());
            }
        }

        int exerciseId = chooseExercise();
        for (Solution solution : usersSolutions) {
            if (solution.getExercise_id() == exerciseId) {
                if (solution.getDescription() != null) {
                    System.out.println("To zadanie zostało już rozwiązane!");
                } else {
                    solution.setDescription(resolveExercise());
                    solution.setCreated(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
                    solutionDao.update(solution);
                    System.out.println("Rozwiązanie zostało dodane!");
                }
            }
        }

    }

    private static void viewSolutions(int userId) {
        SolutionDao solutionDao = new SolutionDao();
        Solution[] usersSolutions = solutionDao.findAllByUserId(userId);
        System.out.println("ID zadania : data rozwiązania : rozwiązanie");
        for (Solution solution : usersSolutions) {
            if (solution.getDescription() != null) {
                System.out.println(solution.getExercise_id() + " : " + solution.getCreated() + " : " + solution.getDescription());
            }
        }
    }
}
