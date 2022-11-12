package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.Scanner;

public class Mechanic {

    private static int currentUserId;

    public Mechanic(int loginedUserId, Scanner scanner) {
        System.out.println("Access to Mechanic Landing page.");

        currentUserId = loginedUserId;
        //center();
        home(scanner);
    }

    // landing()
    // 列出用户可选项1. View Schedule
    // 2. Request TimeOff
    //3. Request Swap
    //4. Accept/Reject Swap
    //5. Logout ==> return;
    // 用scanner 获取用户input
    // 根据input index跳转

    /**
     * Landing page. 1. View Schedule 2. Request TimeOff
     * 3. Request Swap 4. Accept/Reject Swap
     * 5. Logout
     */
    private static void home (Scanner console) {
        //final Scanner console = new Scanner( System.in );
        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Schedule" );
            System.out.println( "2. Request TimeOff" );
            System.out.println( "3. Request Swap" );
            System.out.println( "4. Accept/Reject Swap" );
            System.out.println( "5. Logout\n" );
            final String input = console.nextLine();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
            if ( choose == 1 ) {
                viewSchedule( console );
            }
            else if ( choose == 2 ) {
                requestTimeOff( console );
            }
            else if ( choose == 3 ) {
                System.out.println("Not implemented.");
                //swap( console );
            }
            else if ( choose == 4 ) {
                System.out.println("Not implemented.");
                //arSwap( console );
            }
            else if ( choose == 5 ) {
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
        //console.close();

    }

    private static void viewSchedule(Scanner console) {
        Statement stmt = null;
        //stmt = null;
        ResultSet resultSet = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT * FROM timeslot WHERE timeslot_mechanic=" + currentUserId);

            System.out.println("You have following assigned time slot.");
            while (resultSet.next()) {
                int week = resultSet.getInt("timeslot_week");
                int days = resultSet.getInt("timeslot_days");
                int slot = resultSet.getInt("timeslot_slot");

                System.out.println("Week:" + week + " day:" + days + " slot:" + slot);
            }
            System.out.println();

        } catch ( final SQLException e ) {
            System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
        }
        catch ( final Exception e ) {
            e.printStackTrace();
        }
        finally {
            close( stmt );
            close( resultSet );
        }

    }

    private static void requestTimeOff(Scanner scanner) {
        Statement stmt = null;
        //stmt = null;
        ResultSet resultSet = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            resultSet = stmt.executeQuery("SELECT emp_center FROM emp WHERE emp_id=" + currentUserId);

            int centerId = 0;
            while (resultSet.next()) {
                centerId = resultSet.getInt("emp_center");
            }
            if (centerId == 0) {
                System.err.println("Cannot find center");
            }

            int action = 0;
            while (true) {
                System.out.println("Please enter the time slot you want to request for time off.");
                System.out.print("Week: ");
                String week = scanner.next();
                System.out.print("Day: ");
                String day = scanner.next();
                System.out.print("Time slot start: ");
                String start = scanner.next();
                System.out.print("Time slot end: ");
                String end = scanner.next();

                System.out.println("\nWhat's your next action?");
                System.out.println("Please input the index at first for actions.");
                System.out.println("1. Send the request");
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
                            System.out.println("Action: Send the request.\n");
                            console.close();
                            if (checkValid(week, day, start, end)) {
                                stmt.executeUpdate( "insert into timeoff values ('" + centerId + "', '" + week + "', '" + day
                                        + "', '" + start + "', '" + end + "', '" + currentUserId + "', '" + 0 + "')" );
                                System.out.println("Request sent.\n");
                                System.out.println("Keep on the page and start new Request.");
                                continue;
                            }
                            System.out.println("Request sending failed.\n");
                            System.out.println("Keep on the page and start new Request.");
                            continue;
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

        } catch ( final SQLException e ) {
            System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
        }
        catch ( final Exception e ) {
            e.printStackTrace();
        }
        finally {
            close( stmt );
            close( resultSet );
        }

    }

    private static boolean checkValid(String week, String day, String start, String end) {
        try {
            int w = Integer.parseInt(week);
            if (w < 1 || w > 4) {
                System.out.println("Invalid week.");
                return false;
            }

            int d = Integer.parseInt(day);
            if (w < 1 || w > 7) {
                System.out.println("Invalid day.");
                return false;
            }

            int s = Integer.parseInt(start);
            int e = Integer.parseInt(end);
            if (s < 0 || s > 24 || e < 0 || e > 24 || s >= e) {
                System.out.println("Invalid time slot.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Invalid time slot input type.");
            return false;
        }

        return true;
    }


    //viewSchedule()
    //列出时间点和对应的技师----------------------------------------
    //这里暂时写不了，之前的设计有问题
    //看description file 的Car Service Scheduling部分
    //每天有九个时间点，十二点到一点不干活（no work is done this period）
    //技师每周工作时常<=50，每个服务由一名技师负责
    //记得查看商店是否在周六营业

    //requestTimeOff()
    //技师可以请假，需要经理批准 || 这一条在flow里没列出，暂不清楚要不要实现
    //先让用户输入indicated by week, day, time slot start and end slot ids
    //然后列出1.Send the request 发送请求
    //2.Go back

    //以下是额外分，有时间就写

    //requestSwap()
    //先让用户输入A: indicated by week, day, time slot start and end slot ids
    //然后B: Employee ID of a mechanic that is being requested for swap
    //还有C: Timeslot range of the requested mechanic that is of interest
    //然后列出1.Send the request 发送请求
    //2.Go back

    //acceptOrRejectSwap()
    //另一个技师可以看到其他技师发送的交换请求
    //先列出RequestID ==> 好像要新建TABLE才能实现
    //右边显示对应技师名和交换时间
    //然后列出1.Manage Swap Requests
    //2. Go bac

    //manageSwapRequests()
    //先让用户输入准备处理的RequestID
    //然后列出1.Accept swap
    //2. Reject swap
    //3. Go back
        /**
         * Close the statement.
         *
         * @param st
         *            the statement
         */
        static void close ( final Statement st ) {
            if ( st != null ) {
                try {
                    st.close();
                }
                catch ( final Throwable whatever ) {
                }
            }
        }

        /**
         * Close the result
         *
         * @param rs
         *            the result
         */
        static void close ( final ResultSet rs ) {
            if ( rs != null ) {
                try {
                    rs.close();
                }
                catch ( final Throwable whatever ) {

                }
            }
        }
}
