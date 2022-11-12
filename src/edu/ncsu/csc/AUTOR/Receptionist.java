package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Receptionist {
    private static int customer_id = 0;
    private static int center_id = 0;
    public Receptionist(long logindUserId) {
        System.out.println("Access to Receptionist Landing page.");
        // Customer user id is 10
        final String str_customer_user = Long.toString(logindUserId);
        // First 5 is customer id
        final String str_customer_id = str_customer_user.substring( 0, 5 );
        // Second 5 is center id
        final String str_center_id = str_customer_user.substring( 5 );
        // Get the customer id and his center id
        customer_id = Integer.parseInt( str_customer_id );
        center_id = Integer.parseInt( str_center_id );
        main();
    }

    public static void receptionistPage (Scanner sc) {
        boolean login = true;
        while ( login ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "\n1.Add New Customer Profile" );
            System.out.println( "2.Find Customers with Pending Invoices" );
            System.out.println( "3.Log out" );
            //final Scanner sc = new Scanner( System.in );

        while ( sc.hasNextLine() ) {
            final String input = sc.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch(NumberFormatException e) {
                System.out.println("Please enter a invalid choice.");
                continue;
            }
            if (count == 1) {
                sc.close();
                addNewCustomerFile(sc);
                break;
            } else if (count == 2) {
                sc.close();
                findCustomerWithPendingInvoices(sc);
                break;
            } else if (count == 3) {
                sc.close();
                break;
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
                sc.close();
                receptionistPage(sc);
                break;
            }
        }
    }

    private static void addNewCustomerFile(Scanner sc) {

        System.out.println( "Please enter customer name" );
        final String name = sc.nextLine();
        System.out.println( "Please enter customer address" );
        final String address = sc.nextLine();
        System.out.println( "Please enter customer Email address" );
        final String email = sc.nextLine();
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
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            stmt.executeUpdate( "DROP TABLE ASSOCIATE" );
            stmt.executeUpdate( "DROP TABLE CUSTOMER" );
            stmt.executeUpdate( "DROP TABLE VEHICLE" );

            // CUSTOMER
            stmt.executeUpdate( "CREATE TABLE CUSTOMER " + "(CUSTOMER_ID INTEGER, CUSTOMER_NAME VARCHAR(20), "
                    + "ADDRESS VARCHAR(50), C_EMAIL VARCHAR(20), C_PHONE INTEGER, "
                    + "C_USERNAME VARCHAR(20), CUSTOMER_STATUS CHAR(1) DEFAULT 'f', CUSTOMER_CENTER_ID INTEGER)" );
            stmt.executeUpdate( "ALTER TABLE CUSTOMER " + "ADD CONSTRAINT customer_pk PRIMARY KEY (CUSTOMER_ID)" );
            // VEHECLE
            stmt.executeUpdate( "CREATE TABLE VEHICLE " + "(VIN VARCHAR(20) PRIMARY KEY, MANU VARCHAR(10), "
                    + "MILEAGE VARCHAR(10), YEAR INTEGER)" );
            stmt.executeUpdate( "CREATE TABLE ASSOCIATE " + "(VIN VARCHAR(20), C_ID VARCHAR(20)) " );
            stmt.executeUpdate(
                    "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT vin_pk FOREIGN KEY(vin)REFERENCES vehicle(vin)" );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE "
                    + "ADD CONSTRAINT cus_pk FOREIGN KEY(C_ID)REFERENCES CUSTOMER(CUSTOMER_ID)" );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT PK PRIMARY KEY (VIN, C_ID)" );
            // insert customer data into the database
            final PreparedStatement ps = conn
                    .prepareStatement( "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, "
                            + "C_EMAIL,C_PHONE, C_USERNAME, CUSTOMER_CENTER_ID, CUSTOMER_STATUS) VALUES(?,?,?,?,?,?,?,'t')" );
            ps.setInt( 1, customer_id );
            ps.setString( 2, name );
            ps.setString( 3, address );
            ps.setString( 4, email );
            ps.setInt( 5, Phone );
            ps.setString( 6, username );
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
        System.out.println( "1. GO BACK" );

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
                main();
                break;
            }
        }
        sc.close();

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
