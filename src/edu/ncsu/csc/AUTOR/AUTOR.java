package edu.ncsu.csc.AUTOR;

import edu.ncsu.csc.TABLE.InitializeTables;

import java.sql.*;
import java.util.Scanner;


public class AUTOR {

    public static void main(String[] args) {
        InitializeTables temp = new InitializeTables();// 初始化所有TABLE，记得在init里边启用constructor
        home();












        System.out.println("Goodbye!");
    }

    // the home page
    public static void home() {
        System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
        System.out.println("You may Login, Sign-Up, or Exit.");
        System.out.println("Please input the index at first for actions.");
        System.out.println("1. Login");
        System.out.println("Sign-Up还没实现");
        System.out.println("2. Exit\n");

        Scanner scanner = new Scanner(System.in);

        int action = 0;
        while (scanner.hasNext()) {
            action = 0;
            String s = scanner.next();
            Scanner console = new Scanner(s);
            if (console.hasNextInt()) {
                action = console.nextInt();
                if (String.valueOf(action).equals(s)) { // the only input is int
                    if (console.hasNext()) {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }

                    if (action == 1) {
                        System.out.println("Action: go to Login page.");
                        console.close();
                        login();
                    } else if (action == 2) {
                        console.close();
                        System.out.println("Action: Exit.");
                        break;
                    } else {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }
                } else {
                    System.out.println("Please input a valid index for actions.");
                    continue;
                }
            } else {
                System.out.println("Please input a valid index for actions.");
            }
        }
        scanner.close();
    }

    // ready to login
    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your user ID (If you don't know or don't have, just input something).");
        String id = sc.next();
        System.out.println("Please input your password (If you don't know or don't have, just input something).");
        String password = sc.next();



        System.out.println("What's your next action?");
        System.out.println("Please input the index at first for actions.");
        System.out.println("1. Sign-In");
        System.out.println("2. Go Back\n");

        Scanner scanner = new Scanner(System.in);

        int action = 0;
        while (scanner.hasNext()) {
            action = 0;
            String s = scanner.next();
            Scanner console = new Scanner(s);
            if (console.hasNextInt()) {
                action = console.nextInt();
                if (String.valueOf(action).equals(s)) { // the only input is int
                    if (console.hasNext()) {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }
                    if (action == 1) {
                        System.out.println("Action: Sign-In.");
                        console.close();
                        System.out.println("SELECT id, password FROM ?  还没实现");
                        //break;
                    } else if (action == 2) {
                        console.close();
                        System.out.println("Action: Go back.");
                        home();
                        break;
                    } else {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }
                } else {
                    System.out.println("Please input a valid index for actions.");
                    continue;
                }
            } else {
                System.out.println("Please input a valid index for actions.");
            }
        }
        sc.close();
        scanner.close();
    }
}