package edu.ncsu.csc.AUTOR;

import edu.ncsu.csc.ROW.Insert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Manager {

    public Manager(long loginedUserId, Scanner scanner) {
        System.out.println("Access to Manager Landing page.");
        landing(scanner);
    }
    // landing()
    //        private static void landing(){
//            Scanner scanner = new Scanner(System.in);
//            int action = 0;
//            while (true) {
//                System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
//                System.out.println("1. Setup Store");
//                System.out.println("2. Add New Employee");
//                System.out.println("3. Logout\n");
//                action = 0;
//                String s = scanner.next();
//                Scanner console = new Scanner(s);
//                if (console.hasNextInt()) {
//                    action = console.nextInt();
//                    if (String.valueOf(action).equals(s)) { // the only input is int
//                        if (console.hasNext()) {
//                            System.out.println("Please input a valid index for actions.");
//                            continue;
//                        }
//                        if (action == 1) {
//                            System.out.println("Action: Setup Store.");
//                            console.close();
//                            SetupStore(scanner);
//                            break;
//                        } else if (action == 2) {
//                            System.out.println("Action: Add New Employee.");
//                            console.close();
//                            AddEmployee(scanner);
//                            break;
//                        }else if (action == 3) {
//                            console.close();
//                            System.out.println("Action: Exit.");
//                            break;
//                        } else {
//                            System.out.println("Please input a valid index for actions.");
//                            continue;
//                        }
//                    } else {
//                        System.out.println("Please input a valid index for actions.");
//                        continue;
//                    }
//                } else {
//                    System.out.println("Please input a valid index for actions.");
//                }
//            }
//            scanner.close();
//    }
    private static void landing(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int action = 0;
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("1. Setup Store");
            System.out.println("2. Add New Employee");
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
                        System.out.println("Action: Setup Store.");
                        console.close();
                        StepUpMenu(scanner);
                        break;
                    } else if (action == 2) {
                        System.out.println("Action: Add New Employee.");
                        console.close();
                        AddEmployee(scanner);
                        break;
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
        //scanner.close();
    }

    private static void StepUpMenu(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int action = 0;
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("1. Update Store Info");
            System.out.println("2. Update Store State");
            System.out.println("3. Add New Employee");
            System.out.println("4. Exit\n");
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
                        System.out.println("Action: Update Store Info.");
                        console.close();
                        SetupStore(scanner);
                    }if (action == 2) {
                        System.out.println("Action: Update Store State.");
                        console.close();
                        SetupStoreState(scanner);
                    } else if (action == 3) {
                        System.out.println("Action: Add New Employee.");
                        console.close();
                        AddEmployee(scanner);
                        System.out.println("啊实打实大苏打实打实的");
                    } else if (action == 4) {
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
        //scanner.close();
    }
    // 列出用户可选项1. Setup Store
    //2. Add New Employee
    //3. Logout // return
    //scanner获取用户input

    //

    // 加店里的员工，相当于create了一个员工的新instance，需要吧员工信息同步进入员工表包括账号密码
    //1，一步一步获取员工信息，参考（员工表）
    // 2，将获取的信息插入员工表,同时将账号密码部分插入用户有一个只能智能前台constraint ，前台只能有一个。。

    private static void AddEmployee(Scanner scan) {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@47.105.124.136:1621:helowin", "system",
                        "System123")) {
            stmt = conn.createStatement();
//            stmt.executeUpdate( "DROP TABLE EMP" );
//
//            // EMP
//            stmt.executeUpdate( "CREATE TABLE EMP " + "(EMP_ID INTEGER PRIMARY KEY , EMP_NAME VARCHAR(20), "
//                    + "EMP_ADDRESS VARCHAR(20), EMP_EMAIL VARCHAR(20), EMP_PHONE INTEGER, CENTER_ID VARCHAR(20), EMP_ROLE varchar(20))" );
//            // Add a primary key
//            stmt.executeUpdate( "ALTER TABLE EMP " + "ADD CONSTRAINT CENTER_FK FOREIGN KEY (CENTER_ID)REFERENCES CENTER(CENTER_ID)" );
            scan.nextLine();
            while (true) {
                System.out.println("Please enter emp empName");
                final String empName = scan.nextLine();
                System.out.println("Please enter emp empAddress");
                //scan.nextLine();
                final String empAddress = scan.nextLine();
                System.out.println("Please enter emp empEmail");
                final String empEmail = scan.next();
                System.out.println("Please enter emp empPhone");
                final String empPhone = scan.next();
                //System.out.println(empPhone);
                System.out.println("Please input your loginId.");
                long loginId = 0;
                if (scan.hasNext()) {
                    loginId = Long.parseLong(scan.next());
                }
                //System.out.println(loginId);
                System.out.println("Please input your loginPwd.");
                //scan.nextLine();
                String pwd = scan.next();
                System.out.println("Please enter emp empRole:4.Receptionist 5.Mechanic");
                final int empRole = scan.nextInt();
                if (empRole != 4 && empRole !=5) {
                    System.out.println("Invalid role.\n");
                    continue;
                }

                System.out.println("What's your next action? 1.Add 2.GO BACK");
                final String action = scan.next();
                if (action.equals("1")) {
                    // select query
                    final String queryCenter = "SELECT CENTER_ID FROM CENTER";
                    final ResultSet rs = stmt.executeQuery(queryCenter);
                    List<Integer> list = new ArrayList<>();
                    while (rs.next()) {
                        final int a = rs.getInt("CENTER_ID");
                        list.add(a);
                    }
                    // select query for emp
                    final String queryCenterForEmp = "SELECT EMP_ID FROM EMP";
                    final ResultSet rsForEmp = stmt.executeQuery(queryCenterForEmp);
                    List<Integer> listForEmp = new ArrayList<>();
                    while (rsForEmp.next()) {
                        final int a = rsForEmp.getInt("EMP_ID");
                        listForEmp.add(a);
                    }
                    Integer id = 0;
                    if (listForEmp == null || listForEmp.size() == 0) {
                        id = 0;
                    } else {
                        id = Collections.max(listForEmp) + 1;
                    }
                    //System.out.println("HERE1");
                    final PreparedStatement ps = conn
                            .prepareStatement("INSERT INTO EMP (CENTER_ID,EMP_NAME, EMP_ADDRESS, EMP_EMAIL,EMP_PHONE,EMP_ROLE,EMP_ID "
                                    + ") VALUES(?,?,?,?,?,?,?)");
                    //System.out.println("HERE2");
                    ps.setInt(1, list.get(0));
                    ps.setString(2, empName);
                    ps.setString(3, empAddress);
                    ps.setString(4, empEmail);
                    ps.setString(5, empPhone);
                    ps.setInt(6, empRole);
                    ps.setInt(7, id);
                    ps.executeUpdate();
                    ps.close();
                    //user_pw_Insert
                    //System.out.println("HERE3");
                    Insert.user_pw_Insert(loginId, pwd, empRole);
                    System.out.println("Insertion completed.");
                    System.out.println("Go back now.");
                    StepUpMenu(scan);
                    break;
                } else if (action.equals("2")) {
                    break;
                } else {
                    System.out.println("Invalid input, press enter to retry.");
                    scan.nextLine();
                }
            }

        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            // close(conn);
        }
        //System.out.println("All information insert!");
        //System.out.println("1. GO BACK");

        /*while (scan.hasNextLine()) {
            final String input = scan.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch (final NumberFormatException e) {
                continue;
            }

            if (count == 1) {
                landing(scan);
                break;
            }
        }*/
        //scan.close();
    }
    //启动店，相当于setup了一家店铺实体，在admin添加了这个店之后对于店铺的更新操作
    // 1,参考店铺表获取输入店铺的所有attributes
    // 2， 需要把添加员method） emp add（） 参考（以及同步他们的信息进入员用户表以及同时表）户更新，
    //3，询问运营时间，询问以及各项服务的价格， 同时更新各项相关联的表，

    private static void SetupStore(Scanner scan) {

        System.out.println("Please enter center address");

        final String address = scan.next();

        System.out.println("Please enter center tele");
        final String tele = scan.next();
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@47.105.124.136:1621:helowin", "system",
                        "System123")) {
            stmt = conn.createStatement();

            // select query
            final String queryCenter = "SELECT CENTER_ID FROM CENTER";
            final ResultSet rs = stmt.executeQuery(queryCenter);
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                final int a = rs.getInt("CENTER_ID");
                list.add(a);
            }

            final PreparedStatement ps = conn
                    .prepareStatement("UPDATE CENTER set CENTER_ADD= ? , CENTER_TELE = ? where CENTER_ID= ?");

            ps.setString(1, address);
            ps.setString(2, tele);
            ps.setInt(3, list.get(0));
            ps.executeUpdate();
            ps.close();
        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            // close(conn);
        }
        System.out.println("All information update!");
        System.out.println("1. GO BACK");

        while (scan.hasNextLine()) {
            final String input = scan.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch (final NumberFormatException e) {
                continue;
            }

            if (count == 1) {
                landing(scan);
                break;
            }
        }
        //scan.close();
    }
    private static void SetupStoreState(Scanner scan) {
        System.out.println("Please enter center state");
        final String state = scan.next();

        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@47.105.124.136:1621:helowin", "system",
                        "System123")) {
            stmt = conn.createStatement();

            // select query
            final String queryCenter = "SELECT CENTER_ID FROM CENTER";
            final ResultSet rs = stmt.executeQuery(queryCenter);
            List<Integer> list = new ArrayList<>();
            while (rs.next()) {
                final int a = rs.getInt("CENTER_ID");
                list.add(a);
            }
            // select query
            final String queryCenterForEmp = "SELECT EMP_ID FROM Emp";
            final ResultSet rsForEmp = stmt.executeQuery(queryCenterForEmp);
            List<Integer> listForEmp = new ArrayList<>();
            while (rsForEmp.next()) {
                final int a = rsForEmp.getInt("EMP_ID");
                listForEmp.add(a);
            }

            if(listForEmp.size()<3){
                System.out.println("店铺必须有三个及以上职员");
            }else {
                final PreparedStatement ps = conn
                        .prepareStatement("UPDATE CENTER set STATE= ?  where CENTER_ID= ?");

                ps.setString(1, state);
                ps.setInt(2, list.get(0));
                ps.executeUpdate();
                ps.close();
                System.out.println("All information update!");
            }
            System.out.println("1. GO BACK");
        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
            // close(conn);
        }
        while (scan.hasNextLine()) {
            final String input = scan.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch (final NumberFormatException e) {
                continue;
            }

            if (count == 1) {
                landing(scan);
                break;
            }
        }
        //scan.close();
    }

    static void close(final Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (final Throwable whatever) {
            }
        }
    }
}
