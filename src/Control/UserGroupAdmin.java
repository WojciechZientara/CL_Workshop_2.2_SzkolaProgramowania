package Control;

import Dao.UserGroupDao;
import Model.User;
import Model.UserGroup;

import java.util.Scanner;

public class UserGroupAdmin {

    public static void main(String[] args) {

        while (true) {
            UserGroupDao userGroupDao = new UserGroupDao();
            UserGroup[] userGroups = userGroupDao.findAll();
            System.out.println("UserGroup ID : UserGroup Name");
            for (UserGroup userGroup : userGroups) {
                System.out.println(userGroup.getId() + " : " + userGroup.getName());
            }

            Scanner scanner = new Scanner(System.in);
            System.out.println("\n" + "Wybierz jedną z opcji:");
            System.out.println("add");
            System.out.println("edit");
            System.out.println("delete");
            System.out.println("quit");
            String choice = scanner.nextLine();
            if (choice.equals("add")) {
                addUserGroup();
            } else  if (choice.equals("edit")){
                editUserGroup();
            } else  if (choice.equals("delete")){
                deleteUserGroup();
            } else  if (choice.equals("quit")){
                System.out.println("Zamykanie programu");
                System.exit(0);
            } else {
                System.out.println("wybrano niepoprawny wariant");
            }
        }
    }

    private static void addUserGroup() {
        UserGroup userGroup = new UserGroup() ;
        userGroup = collectDetails(userGroup);
        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.create(userGroup);
        System.out.println("Grupa dodana!");
    }

    private static void editUserGroup() {
        UserGroup userGroup = new UserGroup() ;
        userGroup = collectId(userGroup);
        userGroup = collectDetails(userGroup);
        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.update(userGroup);
        System.out.println("Grupa zmodyfikowana!");
    }

    private static void deleteUserGroup() {
        UserGroup userGroup = new UserGroup() ;
        userGroup = collectId(userGroup);
        UserGroupDao userGroupDao = new UserGroupDao();
        userGroupDao.delete(userGroup.getId());
        System.out.println("Grupa usunięta!");
    }

    private static UserGroup collectId(UserGroup userGroup) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj Id grupy");
        userGroup.setId(scanner.nextInt());
        return userGroup;
    }

    private static UserGroup collectDetails(UserGroup userGroup) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę grupy");
        userGroup.setName(scanner.nextLine());
        return userGroup;
    }


}
