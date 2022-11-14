package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receptionist {
    private static int receptionist_id = 0;
    private static int customer_id     = 0;

    private  static int center_id = 0;

    public Receptionist(int logindUserId, Scanner scanner) {
        System.out.println("Access to Receptionist Landing page.");
        receptionist_id = logindUserId;
        receptionistPage(scanner);
    }

    public static void receptionistPage (Scanner sc) {
        boolean login = true;
        while ( login ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "\n1.Add New Customer Profile" );
            System.out.println( "2.Find Customers with Pending Invoices" );
            System.out.println( "3.Log out" );
            //final Scanner sc = new Scanner( System.in );


            final String input = sc.next();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
                continue;
            }
            if (count == 1) {
                //sc.close();
                addNewCustomerFile(sc);
            } else if (count == 2) {
                //sc.close();
                findCustomerWithPendingInvoices(sc);
            } else if (count == 3) {
                //sc.close();
                login = false;
            } else {
                System.out.println("Invalid input");
                System.out.println("Please enter number 1, 2 or 3.");
            }
        }
    }

    public static void findCustomerWithPendingInvoices ( final Scanner sc ) {
        Statement stmt = null;

        try {
            final Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                    "123" );
            stmt = conn.createStatement();

            final String queryCustomer = "SELECT emp_center FROM emp WHERE emp_id =" + receptionist_id;

            ResultSet rs = stmt.executeQuery( queryCustomer );
            while ( rs.next() ) {
                center_id = rs.getInt( "emp_center" );
            }
            rs.close();
            // select query - implement later
            final String queryCustomer3 = "select in_customer, in_id, in_date, in_cost from invoice where in_center = "
                    + center_id + " and in_status = 'f'";
            rs = stmt.executeQuery( queryCustomer3 );
            final List<Integer> in_customer = new ArrayList<>();
            final List<Integer> in_id = new ArrayList<>();
            final List<String> in_date = new ArrayList<>();
            final List<Integer> in_cost = new ArrayList<>();

            while ( rs.next() ) {
                in_customer.add( rs.getInt( "in_customer" ) );
                in_id.add( rs.getInt( "in_id" ) );
                in_date.add( rs.getString( "in_date" ) );
                in_cost.add( rs.getInt( "in_cost" ) );

            }
            rs.close();
            for ( int i = 0; i < in_customer.size(); i++ ) {
                final String queryFindCustomerName = "select customer_name from customer where customer_id ="
                        + in_customer.get( i ) + " and customer_center_id =" + center_id;
                rs = stmt.executeQuery( queryFindCustomerName );
                while ( rs.next() ) {
                    System.out.println( "A. Customer ID" );
                    System.out.println( in_customer.get( i ) );
                    System.out.println( "B. Customer Name" );
                    System.out.println( rs.getString( "customer_name" ) );
                    System.out.println( "C. Invoice ID" );
                    System.out.println( in_id.get( i ) );
                    System.out.println( "D. Invoice Date" );
                    System.out.println( in_date.get( i ) );
                    System.out.println( "E. Amount" );
                    System.out.println( in_cost.get( i ) );
                    System.out.println( " " );

                }
                rs.close();
            }
        }
        catch ( final SQLException e ) {
            System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
        }
        catch ( final Exception e ) {
            e.printStackTrace();
        }
        finally {
            close( stmt );
        }

        System.out.println( "All information showed!" );
        System.out.println( "1. GO BACK" );

        while ( sc.hasNext() ) {
            final String input = sc.next();
            int count = 0;
            try {
                count = Integer.valueOf( input );
            }
            catch ( final NumberFormatException e ) {
                continue;
            }

            if ( count == 1 ) {
                break;
            }
        }
    }

    public static void addNewCustomerFile ( final Scanner sc ) {

        while ( true ) {
            System.out.println( "Please enter customer name" );
            sc.nextLine();
            final String name = sc.nextLine();
            System.out.println( "Please enter customer address" );
            final String address = sc.nextLine();
            System.out.println( "Please enter customer Email address" );
            final String email = sc.next();
            System.out.println( "Please enter customer Phone number" );
            final int Phone = Integer.parseInt( sc.next() );
            System.out.println( "Please enter customer Username" );
            final String username = sc.next();
            System.out.println( "Please enter VIN number" );
            final String VIN = sc.next();
            System.out.println( "Please enter car manufacturer" );
            final String car_m = sc.next();
            System.out.println( "Please enter mileage" );
            final String mileage = sc.next();
            System.out.println( "Please enter car's year" );
            final String year = sc.next();

            // Connection conn = null;
            int b = 0;
            final List<Integer> c = new ArrayList<>();
            Statement stmt = null;
            try (
                    Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "123" ) ) {
                stmt = conn.createStatement();

                // find center id
                final String queryCustomer = "SELECT emp_center FROM emp WHERE emp_id =" + receptionist_id;

                final ResultSet rs = stmt.executeQuery( queryCustomer );
                while ( rs.next() ) {
                    center_id = rs.getInt( "emp_center" );
                }
                // find customer size
                final String queryFindCustomer = "SELECT customer_id FROM customer WHERE customer_center_id ="
                        + center_id;
                final ResultSet qs = stmt.executeQuery( queryFindCustomer );
                while ( qs.next() ) {
                    b = qs.getInt( "customer_id" );
                    c.add( b );
                }

                // determine customer id
                if ( c.size() == 0 ) {
                    customer_id = 50000;
                }
                else {
                    customer_id = 50000 + c.size();
                }

                // generate user id and password
                final String center_id_string = String.valueOf( center_id );
                final String customer_id_string = String.valueOf( customer_id );
                final String loginUserName = customer_id_string + center_id_string;
                final int dex = name.lastIndexOf( " " );
                final String passWord = name.substring( dex + 1 );
                // insert data into user_pw
                stmt.executeUpdate(
                        "insert into user_pw values ('" + loginUserName + "', '" + passWord + "', " + 3 + ")" );

                // insert customer data into the database
                final PreparedStatement ps = conn
                        .prepareStatement( "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, "
                                + "C_EMAIL,C_PHONE, C_USERNAME, CUSTOMER_STATUS, CUSTOMER_CENTER_ID) VALUES(?,?,?,?,?,?,'t',?)" );
                ps.setInt( 1, customer_id );
                ps.setString( 2, name );
                ps.setString( 3, address );
                ps.setString( 4, email );
                ps.setInt( 5, Phone );
                ps.setString( 6, loginUserName );
                ps.setInt( 7, center_id );
                ps.executeUpdate();
                ps.close();
                // insert vehicle data into the database
                final PreparedStatement ws = conn
                        .prepareStatement( "INSERT INTO VEHICLE (VIN, MANU, MILEAGE, " + "YEAR) VALUES(?,?,?,?)" );
                ws.setString( 1, VIN );
                ws.setString( 2, car_m );
                ws.setString( 3, mileage );
                ws.setString( 4, year );
                ws.executeUpdate();
                ws.close();
                // insert data into associate table
                stmt.executeUpdate(
                        "insert into associate values ('" + VIN + "', " + customer_id + ", " + center_id + ")" );
            }

            catch ( final SQLException e ) {
                System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
            }
            catch ( final Exception e ) {
                e.printStackTrace();
            }
            finally {
                close( stmt );
                // close(conn);
            }
            System.out.println( "All information insert!" );

            while ( sc.hasNextLine() ) {
                final String input = sc.nextLine();
                int count = 0;
                try {
                    count = Integer.valueOf( input );
                }
                catch ( final NumberFormatException e ) {
                    continue;
                }

                if ( count == 1 ) {
                    break;
                }
            }
            break;
            //sc.close();
        }
    }

    static void close ( final Statement st ) {
        if ( st != null ) {
            try {
                st.close();
            }
            catch ( final Throwable whatever ) {
            }
        }
    }
}
