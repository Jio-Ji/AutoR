package edu.ncsu.csc.AUTOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
        final Scanner scanner = new Scanner( System.in );

        while ( scanner.hasNextLine() ) {
            final String input = scanner.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf(input);
            } catch(NumberFormatException e) {
                System.out.println("Please enter a invalid choice.");
                continue;
            }
            if (count == 1) {
                scanner.close();
                addNewCustomerFile();
                break;
            } else if (count == 2) {
                scanner.close();
                findCustomerWithPendingInvoices();
                break;
            } else if (count == 3) {
                scanner.close();
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

    private static void addNewCustomerFile() {
        Scanner sc = new Scanner(System.in);

            System.out.println("Please enter customer name");
            String name = sc.next();
            System.out.println("Please enter customer address");
            String address = sc.next();
            System.out.println("Please enter customer Email address");
            String email = sc.next();
            System.out.println("Please enter customer Phone number");
            int Phone = Integer.parseInt(sc.next());
            System.out.println("Please enter customer Username");
            String username = sc.next();
            System.out.println("Please enter VIN number");
            int VIN = Integer.parseInt(sc.next());
            System.out.println("Please enter car manufacturer");
            String car_m = sc.next();
            System.out.println("Please enter mileage");
            double mileage = Double.parseDouble(sc.next());
            System.out.println("Please enter car's year");
            int year = Integer.parseInt(sc.next());
            sc.close();


        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "INSERT TABLE CUSTOMER " +
                    "()" );
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
