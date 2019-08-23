package Control;

import Dao.UserDao;
import Model.User;

import java.util.Scanner;

public class UsersAdmin {

    public static void main(String[] args) {

        while (true) {
            UserDao userDao = new UserDao();
            User[] users = userDao.findAll();
            System.out.println("User ID : User Name");
            for (User user : users) {
                System.out.println(user.getId() + " : " + user.getUsername());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Wybierz jedną z opcji:");
            System.out.println("add");
            System.out.println("edit");
            System.out.println("delete");
            System.out.println("quit");
            String choice = scanner.nextLine();
            if (choice.equals("add")) {
                addUser();
            } else  if (choice.equals("edit")){
                editUser();
            } else  if (choice.equals("delete")){
                deleteUser();
            } else  if (choice.equals("quit")){
                System.out.println("Zamykanie programu");
                System.exit(0);
            } else {System.out.println("wybrano add");
                System.out.println("wybranAdminUserso niepoprawny wariant");
            }
        }
    }

    private static void addUser() {
        User user = new User();
        user = collectDetails(user);
        UserDao userDao = new UserDao();
        userDao.create(user);
        System.out.println("Użytkownik dodany!");
    }

    private static void editUser() {
        User user = new User();
        user = collectId(user);
        user = collectDetails(user);
        UserDao userDao = new UserDao();
        userDao.update(user);
        System.out.println("Użytkownik zmodyfikowany!");
    }

    private static void deleteUser() {
        User user = new User();
        user = collectId(user);
        UserDao userDao = new UserDao();
        userDao.delete(user.getId());
        System.out.println("Użytkownik usunięty!");
    }

    private static User collectId(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Id użytkownika");
        user.setId(scanner.nextInt());
        return user;
    }

    private static User collectDetails(User user) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika");
        user.setUsername(scanner.nextLine());
        System.out.println("Podaj e-mail użytkownika");
        user.setEmail(scanner.nextLine());
        System.out.println("Podaj hasło użytkownika");
        user.setPassword(scanner.nextLine());
        return user;
    }


}
