package edu.ncsu.csc.AUTOR;


import edu.ncsu.csc.ROW.Insert;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Manager {

    private static int currentUserId;

    public Manager(int loginedUserId, Scanner scanner) {
        System.out.println("Access to Manager Landing page.");
        currentUserId = loginedUserId;
        landing(scanner);
    }

    private static void landing(Scanner console) {
        //final Scanner console = new Scanner( System.in );
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("1. Setup Store");
            System.out.println("2. Add new Employee");
            System.out.println("3. Logout\n");
            final String input = console.next();
            int choose = 0;
            try {
                choose = Integer.parseInt(input);
            } catch (final NumberFormatException e) {
                System.out.println("Your choice is invalid.\n");
                continue;
            }
            if (choose == 1) {
                StepUpMenu(console);
            } else if (choose == 2) {
                AddEmployee(console);
            } else if (choose == 3) {
                break;
            } else {
                System.out.println("Your choice is invalid.\\n");
                continue;
            }
        }
        //console.close();

    }

    private static void StepUpMenu(Scanner scanner) {
        //Scanner scanner = new Scanner(System.in);
        int action = 0;
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("1. Add New Employee");
            System.out.println("2. Set Operational Hour");
            System.out.println("3. Set up service prices");
            System.out.println("4. Go back\n");
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
                        System.out.println("Action: Add New Employee");
                        console.close();
                        AddEmployee(scanner);
                    }
                    if (action == 2) {
                        System.out.println("Action: Set Operational Hour");
                        console.close();
                        SetupOperationalHours(scanner);
                    } else if (action == 3) {
                        System.out.println("Action: Set up service prices.");
                        console.close();
                        SetupServicePrices( scanner);

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

    private static void SetupServicePrices(Scanner scanner) {
        int action = 0;
        while (true) {
            System.out.println("1.Setup maintenance service prices");
            System.out.println("2.Setup repair service prices");
            System.out.println("3.Go back\n");
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
                        System.out.println("Action: Setup maintenance service prices");
                        console.close();
                        SetupMaintenanceServicePrices(scanner);
                    }
                    if (action == 2) {
                        System.out.println("Action:Setup repair service prices");
                        console.close();
                        SetupRepairServicePrices(scanner);
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

    private static void SetupMaintenanceServicePrices(Scanner scanner) {
        while (true) {
            System.out.println("A. Schedule A price");
            System.out.println("B. Schedule B price");
            System.out.println("C. Schedule C price");
            String s = scanner.next();
            Scanner console = new Scanner(s);
            String action = "";
            if (console.hasNext()) {
                action = console.next();
                if (String.valueOf(action).equals(s)) { // the only input is int
                    if (console.hasNext()) {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }
                    if ("A".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Maintenance Service A",scanner);
                        break;
                    }
                    else if ("B".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();
                        AddPrice(p1, p2,p3,"Maintenance Service B",scanner);
                        break;
                    } else if ("C".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Maintenance Service C",scanner);
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
        System.out.println("All information update!");
        System.out.println("1. GO BACK");
        while (scanner.hasNextLine()) {
            final String input = scanner.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch (final NumberFormatException e) {
                continue;
            }

            if (count == 1) {
                landing(scanner);
                break;
            }
        }
        //scan.close();
    }
    private static void  SetupRepairServicePrices(Scanner scanner) {
        while (true) {
            System.out.println("A. Belt Replacement");
            System.out.println("B. Engine Repair");
            System.out.println("C. Catalytic Converter Repair");
            System.out.println("D. Muffler Repair");
            System.out.println("E. Alternator Repair");
            System.out.println("F. Power Lock Repair");
            System.out.println("G. Axle Repair");
            System.out.println("H. Transmission Flush");
            System.out.println("I. Tire Balancing");
            System.out.println("J. Wheel Alignment");
            System.out.println("K. Compressor Repair");
            String s = scanner.next();
            Scanner console = new Scanner(s);
            String action = "";
            if (console.hasNext()) {
                action = console.next();
                if (String.valueOf(action).equals(s)) { // the only input is int
                    if (console.hasNext()) {
                        System.out.println("Please input a valid index for actions.");
                        continue;
                    }
                    if ("A".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Belt Replacement",scanner);
                        break;
                    }
                    else if ("B".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Engine Repair",scanner);
                        break;
                    } else if ("C".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Catalytic Converter Repair",scanner);
                        break;

                    }
                    else if ("D".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Muffler Repair",scanner);
                        break;

                    }
                    else if ("E".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Alternator Repair",scanner);
                        break;

                    }
                    else if ("F".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Power Lock Repair",scanner);
                        break;

                    }
                    else if ("G".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Axle Repair",scanner);
                        break;

                    }
                    else if ("H".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Transmission Flush",scanner);
                        break;

                    }
                    else if ("I".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Tire Balancing",scanner);
                        break;
                    }
                    else if ("J".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Wheel Alignment",scanner);
                        break;
                    }
                    else if ("K".equals(action)) {
                        console.close();
                        System.out.println("Honda price");
                        int p1 = scanner.nextInt();
                        System.out.println("Nissan price");
                        int p2 = scanner.nextInt();
                        System.out.println("Toyota price");
                        int p3 = scanner.nextInt();

                        AddPrice(p1, p2,p3,"Compressor Repair",scanner);
                        break;
                    }else {
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
        System.out.println("All information update!");
        System.out.println("1. GO BACK");
        while (scanner.hasNextLine()) {
            final String input = scanner.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch (final NumberFormatException e) {
                continue;
            }

            if (count == 1) {
                landing(scanner);
                break;
            }
        }
        //scan.close();
    }
    private static void AddPrice(int p1, int p2, int p3, String service,Scanner scanner) {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123"
                )) {
            stmt = conn.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM service WHERE s_name=" +"'"+ service+"'");

            long sPriceTier = 0;
            while (resultSet.next()) {
                sPriceTier = resultSet.getLong("s_priceTier");
            }

            ResultSet resultSet1 = stmt.executeQuery("SELECT * FROM emp WHERE emp_id=" + currentUserId);
            long empId = 0;
            long centerId = 0;
            while (resultSet1.next()) {
                empId = resultSet1.getLong("emp_id");
                centerId = resultSet1.getLong("emp_center");
            }

            ResultSet resultSet2 = stmt.executeQuery("SELECT * FROM prices WHERE p_centerId= "+centerId+" and p_model=" +"'"+ "Honda"+"' and p_tier = "+sPriceTier);

            long p_centerId = 0;
            String p_model = "";
            long p_tier = 0;
            while (resultSet2.next()) {
                p_centerId = resultSet2.getLong("p_centerId");
                p_model = resultSet2.getString("p_model");
                p_tier = resultSet2.getLong("p_tier");
            }
            ResultSet resultSet3 = stmt.executeQuery("SELECT * FROM prices WHERE p_centerId= "+centerId+" and p_model=" +"'"+ "Toyota"+"' and p_tier = "+sPriceTier);

            long p_centerId3 = 0;
            String p_model3 = "";
            long p_tier3 = 0;
            while (resultSet3.next()) {
                p_centerId3 = resultSet3.getLong("p_centerId");
                p_model3 = resultSet3.getString("p_model");
                p_tier3 = resultSet3.getLong("p_tier");
            }
            ResultSet resultSet4 = stmt.executeQuery("SELECT * FROM prices WHERE p_centerId= "+centerId+" and p_model=" +"'"+ "Toyota"+"' and p_tier = "+sPriceTier);

            long p_centerId4 = 0;
            String p_model4 = "";
            long p_tier4 = 0;
            while (resultSet4.next()) {
                p_centerId4 = resultSet4.getLong("p_centerId");
                p_model4 = resultSet4.getString("p_model");
                p_tier4 = resultSet4.getLong("p_tier");
            }
            if(p_centerId!=0){
                System.out.println("Honda has price already.1 Update Price 2 Go Back");
                while (true) {
                    int action = 0;
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
                                final PreparedStatement ps = conn
                                        .prepareStatement("update prices  set p_dollar =? where p_centerId=? and p_model = ? and p_tier= ?");


                                ps.setLong(1, p1);
                                ps.setLong(2, centerId);
                                ps.setString(3, "Honda");
                                ps.setLong(4, sPriceTier);
                                ps.executeUpdate();
                                ps.close();
                                break;
                            } else if (action == 2) {
                                break;
                            }  else {
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
            }else{
                final PreparedStatement ps = conn
                        .prepareStatement("insert into prices  (p_centerId,p_model,p_tier,p_dollar) values(?,?,?,?)");

                ps.setLong(1, centerId);
                ps.setString(2, "Honda");
                ps.setLong(3, sPriceTier);
                ps.setLong(4, p1);
                ps.executeUpdate();
                ps.close();
            }
            if(p_centerId3!=0){
                System.out.println("Nissan has price already.1 Update Price 2 Go Back");
                while (true) {
                    int action = 0;
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
                                final PreparedStatement ps = conn
                                        .prepareStatement("update prices  set p_dollar =? where p_centerId=? and p_model = ? and p_tier= ?");


                                ps.setLong(1, p1);
                                ps.setLong(2, centerId);
                                ps.setString(3, "Nissan");
                                ps.setLong(4, sPriceTier);
                                ps.executeUpdate();
                                ps.close();
                                break;
                            } else if (action == 2) {
                                break;
                            }  else {
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
            }else{
                final PreparedStatement ps1 = conn
                        .prepareStatement("insert into prices  (p_centerId,p_model,p_tier,p_dollar) values(?,?,?,?)");

                ps1.setLong(1, centerId);
                ps1.setString(2, "Nissan");
                ps1.setLong(3, sPriceTier);
                ps1.setLong(4, p2);
                ps1.executeUpdate();
                ps1.close();

            }
            if(p_centerId3!=0){
                System.out.println("Toyota has price already.1 Update Price 2 Go Back");
                while (true) {
                    int action = 0;
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
                                final PreparedStatement ps = conn
                                        .prepareStatement("update prices  set p_dollar =? where p_centerId=? and p_model = ? and p_tier= ?");


                                ps.setLong(1, p1);
                                ps.setLong(2, centerId);
                                ps.setString(3, "Toyota");
                                ps.setLong(4, sPriceTier);
                                ps.executeUpdate();
                                ps.close();
                                break;
                            } else if (action == 2) {
                                break;
                            }  else {
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
            }else{
                final PreparedStatement ps2 = conn
                        .prepareStatement("insert into prices  (p_centerId,p_model,p_tier,p_dollar) values(?,?,?,?)");
                ps2.setLong(1, centerId);
                ps2.setString(2, "Toyota");
                ps2.setLong(3, sPriceTier);
                ps2.setLong(4, p3);
                ps2.executeUpdate();
                ps2.close();

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
    // 加店里的员工，相当于create了一个员工的新instance，需要吧员工信息同步进入员工表包括账号密码
    //1，一步一步获取员工信息，参考（员工表）
    // 2，将获取的信息插入员工表,同时将账号密码部分插入用户有一个只能智能前台constraint ，前台只能有一个。。

    private static void AddEmployee(Scanner scan) {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123"
                )) {
            stmt = conn.createStatement();
            scan.nextLine();
            while (true) {
                System.out.println("Please enter emp Name");
                final String empName = scan.nextLine();
                System.out.println("Please enter emp Address");
                //scan.nextLine();
                final String empAddress = scan.nextLine();
                System.out.println("Please enter emp Email");
                final String empEmail = scan.next();
                System.out.println("Please enter emp Phone");
                final String empPhone = scan.next();
                System.out.println("Please enter emp empRole:4.Receptionist 5.Mechanic");
                final int empRole = scan.nextInt();
                if (empRole != 4 && empRole != 5) {
                    System.out.println("Invalid role.\n");
                    continue;
                }
                System.out.println("Please enterCompensation");
                final int salary = scan.nextInt();
//                //System.out.println(empPhone);
//                System.out.println("Please input your loginId.");
//                long loginId = 0;
//                if (scan.hasNext()) {
//                    loginId = Long.parseLong(scan.next());
//                }
                //System.out.println(loginId);
                System.out.println("Please input Start Day.");
                //scan.nextLine();
                String startDay = scan.next();
                System.out.println("What's your next action? 1.Add 2.GO BACK");
                final String action = scan.next();

                // select query for emp
                final String queryCenterForEmp = "SELECT EMP_ID FROM EMP";
                final ResultSet rsForEmp = stmt.executeQuery(queryCenterForEmp);
                List<Integer> listForEmp = new ArrayList<>();
                while (rsForEmp.next()) {
                    final int a = rsForEmp.getInt("EMP_ID");
                    listForEmp.add(a);
                }
                Integer loginId = 0;
                if (listForEmp == null || listForEmp.size() == 0) {
                    loginId = 0;
                } else {
                    loginId = Collections.max(listForEmp) + 1;
                }

                if (action.equals("1")) {
                    ResultSet resultSet = stmt.executeQuery("SELECT * FROM emp WHERE emp_id=" + currentUserId);

                    long empId = 0;
                    long centerId = 0;
                    while (resultSet.next()) {
                        empId = resultSet.getLong("emp_id");
                        centerId = resultSet.getLong("emp_center");
                    }

                    if (empRole == 4) {
                        ResultSet resultSetForRe = stmt.executeQuery("SELECT receptionist_id FROM emp e join receptionist re on e.emp_id = re.receptionist_id WHERE e.emp_center =" + centerId);
                        Long receptionistId = 0l;
                        List<Long> receptionistIds = new ArrayList<>();
                        while (resultSetForRe.next()) {
                            receptionistId = resultSetForRe.getLong("receptionist_id");
                            receptionistIds.add(receptionistId);
                        }
                        if (receptionistIds.size() > 0) {
                            System.out.println("only one reptionist");
                            break;
                        }
                        //System.out.println("HERE1");
                        final PreparedStatement psForEmp = conn
                                .prepareStatement("INSERT INTO EMP (emp_id,emp_center "
                                        + ") VALUES(?,?)");
                        //System.out.println("HERE2");
                        psForEmp.setLong(1, loginId);
                        psForEmp.setLong(2, centerId);
                        psForEmp.executeUpdate();
                        psForEmp.close();

                        final PreparedStatement ps = conn
                                .prepareStatement("INSERT INTO receptionist (receptionist_salary,receptionist_name, receptionist_address, receptionist_email,receptionist_phone,receptionist_id "
                                        + ") VALUES(?,?,?,?,?,?)");
                        //System.out.println("HERE2");
                        ps.setInt(1, salary);
                        ps.setString(2, empName);
                        ps.setString(3, empAddress);
                        ps.setString(4, empEmail);
                        ps.setString(5, empPhone);
                        ps.setLong(6, loginId);
                        ps.executeUpdate();
                        ps.close();
                    } else if (empRole == 5) {
                        //System.out.println("HERE1");
                        final PreparedStatement psForEmp = conn
                                .prepareStatement("INSERT INTO EMP (emp_id,emp_center "
                                        + ") VALUES(?,?)");
                        //System.out.println("HERE2");
                        psForEmp.setLong(1, loginId);
                        psForEmp.setLong(2, centerId);
                        psForEmp.executeUpdate();
                        psForEmp.close();
                        final PreparedStatement ps = conn
                                .prepareStatement("INSERT INTO mechanic (mechanic_compensation,mechanic_name, mechanic_address, mechanic_email,mechanic_phone,mechanic_id,mechanic_startDate "
                                        + ") VALUES(?,?,?,?,?,?,?)");
                        //System.out.println("HERE2");
                        ps.setInt(1, salary);
                        ps.setString(2, empName);
                        ps.setString(3, empAddress);
                        ps.setString(4, empEmail);
                        ps.setString(5, empPhone);
                        ps.setLong(6, loginId);
                        ps.setString(7, startDay);
                        ps.executeUpdate();
                        ps.close();
                    }
                    Insert.user_pw_Insert(loginId, "pwd", empRole);
                    System.out.println("Insertion completed.");
                    System.out.println("New Employee." + loginId + ":" + "pwd");
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

        }
    }

    private static void SetupOperationalHours(Scanner scan) {
        System.out.println("Please enter Operational on Saturdays? t/f");
        final String state = scan.next();

        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123"
                )) {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM emp WHERE emp_id=" + currentUserId);

            long empId = 0;
            long centerId = 0;
            while (resultSet.next()) {
                empId = resultSet.getLong("emp_id");
                centerId = resultSet.getLong("emp_center");
            }
            // select query
            final String queryCenterForEmp = "SELECT EMP_ID FROM Emp where emp_center = " + centerId;
            final ResultSet rsForEmp = stmt.executeQuery(queryCenterForEmp);
            List<Integer> listForEmp = new ArrayList<>();
            while (rsForEmp.next()) {
                final int a = rsForEmp.getInt("EMP_ID");
                listForEmp.add(a);
            }

            if (listForEmp.size() < 3) {

                System.out.println("Store needs to have 3 and more employees");

            } else {
                final PreparedStatement ps = conn
                        .prepareStatement("UPDATE CENTER set open_sat= ?  where CENTER_ID= ?");

                ps.setString(1, state);
                ps.setLong(2, centerId);
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
                StepUpMenu(scan);
                break;
            }
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

    /**
     * Close the result
     *
     * @param rs the result
     */
    static void close(final ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (final Throwable whatever) {

            }
        }
    }
}
