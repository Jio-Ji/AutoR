package edu.ncsu.csc.AUTOR;


import edu.ncsu.csc.ROW.Insert;

import java.sql.*;
import java.util.Scanner;


public class AUTOR {

    public static void main(String[] args) {
        //InitializeTables temp = new InitializeTables();// 初始化所有TABLE，记得在init里边启用constructor

        Scanner scanner = new Scanner(System.in);
        home(scanner);
        scanner.close();
        System.out.println("Goodbye!");
    }

    // the home page
    public static void home(Scanner scanner) {

        int action = 0;
        while (true) {
            System.out.println();
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("You may Login, Sign-Up, or Exit.");
            System.out.println();
            System.out.println("Please input the index at first for actions.");
            System.out.println("1. Login");
            System.out.println("2. Sign-Up");
            System.out.println("3. Exit\n");
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
                        login(scanner);
                    } else if (action == 2) {
                        System.out.println("Action: Sign up.");
                        System.out.println("Please input your id.");
                        long num = 0;
                        if (scanner.hasNext()) {
                            num = Long.parseLong(scanner.next());
                        }
                        System.out.println("Please input your password.");
                        String pwd = scanner.next();
                        System.out.println("Please input the index to indicate your role: \n" +
                                "1.Admin 2.Manager 3.Customer 4.Receptionist 5.Mechanic \n");
                        if (scanner.hasNextInt()) {
                            Insert.user_pw_Insert(num, pwd, scanner.nextInt());
                        } else {
                            System.out.println("Invalid Role.");
                        }
                        console.close();
                        //home(scanner);
                        //break;
                    } else if (action == 3) {
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
    }

    // ready to login
    public static void login(Scanner scanner) {
        //Scanner sc = new Scanner(System.in);
        //Scanner scanner = new Scanner(System.in);

        int action = 0;
        while (true) {
            System.out.println("Please input your user ID (If you don't know or don't have, just input something).");
            String id = scanner.next();
            System.out.println("Please input your password (If you don't know or don't have, just input something).");
            String password = scanner.next();
//            System.out.println("What's your next action?");
            System.out.println("Please input the index at first for actions.");
            System.out.println("1. Sign-In");
            System.out.println("2. Go Back\n");

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
                        System.out.println("Action: Sign-In.\n");
                        console.close();
                        checkUserPW(id, password, scanner);
                        break;
                    } else if (action == 2) {
                        console.close();
                        System.out.println("Action: Go back.");
                        //home();
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
        //sc.close();
        //scanner.close();
    }

    /**
     * check if the user and pwd match one row in USERPW TABLE
     */
    public static void checkUserPW(String id, String password, Scanner scanner) {
        /*// 等会删掉
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE USERPW (" +
                                                "u_id NUMBER(20) PRIMARY KEY, " +
                                                "PWD VARCHAR(20)," +
                                                "ROLE INTEGER)");
            //stmt.executeUpdate( "ALTER TABLE USERPW " + "ADD CONSTRAINT userpw_pk PRIMARY KEY (U_ID)");
        }catch ( final SQLException e ) {
            System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
        }
        catch ( final Exception e ) {
            e.printStackTrace();
        }
        finally {
            close( stmt );
            // close(conn);
        }
        // -------*/
        // Connection conn = null;
        Statement stmt = null;
        //stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123")) {

            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM user_pw WHERE u_id=" + id);

            long USERPW_id = 0;
            String USERPW_pwd = "";
            int USERPW_role = 0;
            while (resultSet.next()) {
                USERPW_id = resultSet.getLong("u_id");
                USERPW_pwd = resultSet.getString("u_pwd");
                USERPW_role = resultSet.getInt("u_role");

                if (resultSet.next()) {
                    System.out.println("Multi user match!");
                    System.out.println("Something goes wrong!");
                    System.out.println("In AUTOR, checkUserPW() method");
                }
            }

            System.out.println(id + ":" + String.valueOf(USERPW_id));
            System.out.println(password + ":" + String.valueOf(USERPW_pwd));

            if (id.equals(String.valueOf(USERPW_id)) && password.equals(String.valueOf(USERPW_pwd))) {
                System.out.println("Logging in...\n");
                switch (USERPW_role) {
                    case 1:
                        Admin admin = new Admin((int) USERPW_id);
                        break;
                    case 2:
                        Manager manager = new Manager((int) USERPW_id, scanner);
                        break;
                    case 3:
                        Customer customer = new Customer((long) USERPW_id, scanner);
                        break;
                    case 4:
                        Receptionist receptionist = new Receptionist((int) USERPW_id, scanner);
                        break;
                    case 5:
                        Mechanic mechanic = new Mechanic((int) USERPW_id, scanner);
                        break;
                    default:
                        System.out.println("In AUTOR, checkUserPW() method");
                        break;
                }
            } else {
                System.out.println("Your id or password is invalid.");
                System.out.println("Please input valid id and password, or sign-up at the previous page.\n");
            }


        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            // close(conn);
        }
    }

    /**
     * Close the statement.
     *
     * @param st the statement
     */
    static void close(final Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (final Throwable whatever) {
            }
        }
    }
}