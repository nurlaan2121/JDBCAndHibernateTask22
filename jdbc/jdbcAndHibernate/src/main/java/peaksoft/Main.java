package peaksoft;

import peaksoft.service.UserServiceImpl;

import javax.script.ScriptEngine;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        LOOP1:
        while (true){
            System.out.println("""
                Choose command
                1.Create Users Table
                2.Drop Users Table
                3.Save User
                4.Remove User By Id
                5.Get All Users
                6.Clean Users Table
                0.Exit
                """);
            int action = new Scanner(System.in).nextInt();
            switch (action) {
                case 1 -> userService.createUsersTable();
                case 2 -> userService.dropUsersTable();
                case 3 -> {
                    System.out.println("Write name :  ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.println("Write last name: ");
                    String lastNAme = new Scanner(System.in).nextLine();
                    System.out.println("Write age: ");
                    userService.saveUser(name, lastNAme, new Scanner(System.in).nextByte());
                }
                case 4 -> {
                    System.out.println("Write id");
                    userService.removeUserById(new Scanner(System.in).nextLong());
                }
                case 5 -> System.out.println(userService.getAllUsers());
                case 6 -> {
                    userService.cleanUsersTable();
                }case 0->{break LOOP1;}
            }

        }
    }
}
