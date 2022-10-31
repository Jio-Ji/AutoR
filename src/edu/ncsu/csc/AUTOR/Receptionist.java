package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.Scanner;

public class Receptionist {

    public Receptionist(long loginedUserId) {
        System.out.println("Access to Receptionist Landing page.");

        System.out.println("还没实现");
        main();

        //center();
    }

    public static void main () {
        System.out.println( "1.Add New Customer Profile" );
        System.out.println( "2.Find Customers with Pending Invoices" );
        System.out.println( "3.Log out" );
        final Scanner sc = new Scanner( System.in );

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
                findCustomerWithPendingInvoices();
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

    private static void findCustomerWithPendingInvoices() {
        // TODO Auto-generated method stub

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

        sc.close();

        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            //stmt.executeUpdate( "DROP TABLE ASSOCIATE" );
            //stmt.executeUpdate( "DROP TABLE CUSTOMER" );
            //stmt.executeUpdate( "DROP TABLE VEHICLE" );

            // CUSTOMER
            stmt.executeUpdate( "CREATE TABLE CUSTOMER " + "(CUSTOMER_ID VARCHAR(20), CUSTOMER_NAME VARCHAR(20), "
                    + "ADDRESS VARCHAR(50), C_EMAIL VARCHAR(20), C_PHONE INTEGER, "
                    + "C_USERNAME VARCHAR(20), CUSTOMER_STATUS CHAR(1) DEFAULT 'f')" );
            stmt.executeUpdate( "ALTER TABLE CUSTOMER " + "ADD CONSTRAINT customer_pk PRIMARY KEY (CUSTOMER_ID)" );
            // VEHICLE
            stmt.executeUpdate( "CREATE TABLE VEHICLE " + "(VIN VARCHAR(20) PRIMARY KEY, MANU VARCHAR(10), "
                    + "MILEAGE VARCHAR(10), YEAR INTEGER)" );
            stmt.executeUpdate( "CREATE TABLE ASSOCIATE " + "(VIN VARCHAR(20), C_ID VARCHAR(20)) " );
            // ASSOCIATE
            stmt.executeUpdate(
                    "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT vin_pk FOREIGN KEY(vin)REFERENCES vehicle(vin)" );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE "
                    + "ADD CONSTRAINT cus_pk FOREIGN KEY(C_ID)REFERENCES CUSTOMER(CUSTOMER_ID)" );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT PK PRIMARY KEY (VIN, C_ID)" );
            // insert customer data into the database
            final PreparedStatement ps = conn
                    .prepareStatement( "INSERT INTO CUSTOMER (CUSTOMER_ID, CUSTOMER_NAME, ADDRESS, "
                            + "C_EMAIL,C_PHONE, C_USERNAME, CUSTOMER_STATUS) VALUES(?,?,?,?,?,?,'t')" );
            ps.setString( 1, "11111" );
            ps.setString( 2, name );
            ps.setString( 3, address );
            ps.setString( 4, email );
            ps.setInt( 5, Phone );
            ps.setString( 6, username );
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

        //return to landing page
        // 晚上写

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
