package Control;

import Dao.ExerciseDao;
import Model.Exercise;
import Model.User;

import java.util.Scanner;

public class ExerciseAdmin {

    public static void main(String[] args) {

        while (true) {
            ExerciseDao exerciseDao = new ExerciseDao();
            Exercise[] exercises = exerciseDao.findAll();
            System.out.println("Exercise ID : Exercise Title");
            for (Exercise exercise : exercises) {
                System.out.println(exercise.getId() + " : " + exercise.getTitle());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Wybierz jedną z opcji:");
            System.out.println("add");
            System.out.println("edit");
            System.out.println("delete");
            System.out.println("quit");
            String choice = scanner.nextLine();
            if (choice.equals("add")) {
                addExercise();
            } else  if (choice.equals("edit")){
                editExercise();
            } else  if (choice.equals("delete")){
                deleteExercise();
            } else  if (choice.equals("quit")){
                System.out.println("Zamykanie programu");
                System.exit(0);
            } else {
                System.out.println("wybrano niepoprawny wariant");
            }
        }
    }

    private static void addExercise() {
        Exercise exercise = new Exercise();
        exercise = collectDetails(exercise);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.create(exercise);
        System.out.println("Zadanie dodane!");
    }

    private static void editExercise() {
        Exercise exercise = new Exercise();
        exercise = collectId(exercise);
        exercise = collectDetails(exercise);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.update(exercise);
        System.out.println("Zadanie zmodyfikowane!");
    }

    private static void deleteExercise() {
        Exercise exercise = new Exercise();
        exercise = collectId(exercise);
        ExerciseDao exerciseDao = new ExerciseDao();
        exerciseDao.delete(exercise.getId());
        System.out.println("Zadanie usunięte!");
    }

    private static Exercise collectId(Exercise exercise) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Id zadania");
        exercise.setId(scanner.nextInt());
        return exercise;
    }

    private static Exercise collectDetails(Exercise exercise) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj tytuł zadania:");
        exercise.setTitle(scanner.nextLine());
        System.out.println("Podaj opis zadania:");
        exercise.setDescription(scanner.nextLine());
        return exercise;
    }


}
