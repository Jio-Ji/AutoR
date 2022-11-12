package edu.ncsu.csc.AUTOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Customer {
    private static int customer_id = 0;
    private static int center_id   = 0;

    /**
     * The constructor for customer.
     *
     * @param loginUserId
     *            the customer user id
     */
    public Customer ( final long loginUserId, Scanner scanner) {
        System.out.println( "Access to Customer Landing page." );
        // Customer user id is 10
        final String str_customer_user = Long.toString( loginUserId );
        // First 5 is customer id
        final String str_customer_id = str_customer_user.substring( 0, 5 );
        // Second 5 is center id
        final String str_center_id = str_customer_user.substring( 5 );
        // Get the customer id and his center id
        customer_id = Integer.parseInt( str_customer_id );
        center_id = Integer.parseInt( str_center_id );
        home();
    }

    /**
     * Landing page. 1. View and Update Profile 2. View and Schedule Service 3.
     * Invoices 4. Logout
     */
    private static void home (Scanner console) {
        //final Scanner console = new Scanner( System.in );
        boolean notLogout = true;
        while ( notLogout ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View and Update Profile" );
            System.out.println( "2. View and Schedule Service" );
            System.out.println( "3. Invoices" );
            System.out.println( "4. Logout\n" );
            final String input = console.next();

            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // View and Update Profile
                viewAndUpdateProfile( console );
            }
            else if ( choose == 2 ) {
                // View and Schedule Service
                viewAndScheduleService( console );
            }
            else if ( choose == 3 ) {
                // Invoices
                invoices( console );
            }
            else if ( choose == 4 ) {
                notLogout = false;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
        //console.close();

    }

    /**
     * Profile page.
     *
     * @param console
     *            the scanner
     */
    private static void viewAndUpdateProfile ( final Scanner console ) {
        boolean notGoback = true;
        while ( notGoback ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Profile" );
            System.out.println( "2. Add Car" );
            System.out.println( "3. Delete Car" );
            System.out.println( "4. Go Back\n" );
            console.nextLine();
            final String input = console.next();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
            if ( choose == 1 ) {
                // View Profile
                viewProfile( console );
            }
            else if ( choose == 2 ) {
                // Add Car
                addCar( console );
                notGoback = false;
            }
            else if ( choose == 3 ) {
                // Delete Car
                deleteCar( console );
                notGoback = false;
            }
            else if ( choose == 4 ) {
                // Go back
                notGoback = false;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
        }
    }

    /**
     * View user profile.
     *
     * @param console
     *            the scanner
     */
    private static void viewProfile ( final Scanner console ) {
        String fullname = null;
        String address = null;
        String email = null;
        String phone = null;
        ResultSet rs = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            rs = stmt.executeQuery( "select customer_name, address, c_email, c_phone from customer where customer_id="
                    + customer_id + " and customer_center_id=" + center_id );
            while ( rs.next() ) {
                fullname = rs.getString( "customer_name" );
                address = rs.getString( "address" );
                email = rs.getString( "c_email" );
                phone = Integer.toString( rs.getInt( "c_phone" ) );
                if ( phone.equals( "0" ) ) {
                    phone = null;
                }
                System.out.println( "Your profile:\n" );
                System.out.println( "Customer ID:" + customer_id );
                System.out.println( "Full name:" + fullname );
                System.out.println( "Address:" + address );
                System.out.println( "Email Address:" + email );
                System.out.println( "Phone Number:" + phone + "\n" );
            }
            System.out.println( "List of All Cars:\n" );
            close( rs );
            rs = stmt.executeQuery( "select * from vehicle where vin = any (select ass_vin from associate"
                    + " where ass_customer_id=" + customer_id + " and ass_center_id=" + center_id + ")" );
            while ( rs.next() ) {
                System.out.println( "VIN number:" + rs.getString( 1 ) );
                System.out.println( "Car Manufacturer name:" + rs.getString( 2 ) );
                System.out.println( "Current mileage:" + rs.getString( 3 ) );
                System.out.println( "Year:" + rs.getString( 4 ) + "\n" );
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
            close( rs );
        }

        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Go Back\n" );
            final String input = console.next();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
            if ( choose == 1 ) {
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
            }
        }

    }

    /**
     * Add the car.
     *
     * @param console
     *            the sacnner
     */
    private static void addCar ( final Scanner console ) {
        System.out.println( "Please enter the VIN number." );
        final String vin = console.nextLine();
        System.out.println( "Please enter the car manufacturer name." );
        final String manufacturer = console.nextLine();
        System.out.println( "Please enter current mileage." );
        final String mileage = console.nextLine();
        System.out.println( "Please enter the year." );
        final String year = console.nextLine();
        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Save Information" );
            System.out.println( "2. Cancel\n" );
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
                Statement stmt = null;
                try (
                        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                                "123" ) ) {

                    stmt = conn.createStatement();
                    stmt.executeUpdate( "insert into vehicle values ('" + vin + "', '" + manufacturer + "', '" + mileage
                            + "', " + year + ")" );
                    stmt.executeUpdate(
                            "insert into associate values ('" + vin + "' ," + customer_id + ", " + center_id + ")" );
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
                System.out.println( "The vehicle record was saved.\n" );
                break;
            }
            else if ( choose == 2 ) {
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
            }
        }

    }

    /**
     * Delete the car.
     *
     * @param console
     *            the sacnner
     */
    private static void deleteCar ( final Scanner console ) {
        Statement stmt = null;
        ResultSet rs = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            System.out.println( "List of All Cars:\n" );
            rs = stmt.executeQuery( "select * from vehicle where vin = any (select ass_vin from associate"
                    + " where ass_customer_id=" + customer_id + " and ass_center_id=" + center_id + ")" );
            while ( rs.next() ) {
                System.out.println( "VIN number:" + rs.getString( 1 ) );
                System.out.println( "Car Manufacturer name:" + rs.getString( 2 ) );
                System.out.println( "Current mileage:" + rs.getString( 3 ) );
                System.out.println( "Year:" + rs.getString( 4 ) + "\n" );
            }
            while ( true ) {
                System.out.println( "Please enter a choice." );
                System.out.println( "1. Select the car to delete" );
                System.out.println( "2. Go Back\n" );
                console.nextLine();
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
                    System.out.println( "Please enter the VIN number of the car you want to delete." );
                    final String vin = console.nextLine();
                    final String deleteAss = "DELETE FROM associate WHERE ass_vin = '" + vin + "'";
                    final String deleteVeh = "delete from vehicle where vin = '" + vin + "'";
                    stmt.executeUpdate( deleteAss );
                    stmt.executeUpdate( deleteVeh );
                    System.out.println( "The vehicle record was deleted.\n" );
                    break;
                }
                else if ( choose == 2 ) {
                    break;
                }
                else {
                    System.out.println( "Your choice is invalid.\n" );
                }
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
            close( rs );
        }
    }

    /**
     * View and schedule service.
     *
     * @param console
     *            the scanner
     */
    private static void viewAndScheduleService ( final Scanner console ) {
        console.nextLine();
        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Service History" );
            System.out.println( "2. Schedule Service" );
            System.out.println( "3. Go Back\n" );
            final String input = console.next();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // view service history
                viewServiceHistory( console );
            }
            else if ( choose == 2 ) {
                // schedule service
                scheduleService( console );
            }
            else if ( choose == 3 ) {
                // Go Back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * View Service History page.
     *
     * @param console
     *            the scanner
     */
    private static void viewServiceHistory ( final Scanner console ) {
        console.nextLine();
        while ( true ) {
            System.out.println( "Please enter the VIN number of a car." );
            final String vin = console.nextLine();

            System.out.println( "Please enter a choice." );
            System.out.println( "1. Show History" );
            System.out.println( "2. Go Back\n" );
            final String input = console.nextLine();

            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // view service history
                ResultSet rs = null;
                Statement stmt = null;
                try (
                        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                                "123" ) ) {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery( "SELECT " );
                }
                catch ( final SQLException e ) {
                    System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
                }
                catch ( final Exception e ) {
                    e.printStackTrace();
                }
                finally {
                    close( stmt );
                    close( rs );
                }

            }
            else if ( choose == 2 ) {
                // Go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * Schedule Service page.
     *
     * @param console
     *            the scanner
     */
    private static void scheduleService ( final Scanner console ) {
        console.nextLine();
        while ( true ) {
            System.out.println( "Please enter VIN number." );
            final String vin = console.next();
            //System.out.println( "VIN:" + vin);
            System.out.println( "Please enter current mileage." );
            final String mileage = console.nextLine();

            System.out.println( "Please enter a choice." );
            System.out.println( "1. Add Schedule Maintenance" );
            System.out.println( "2. Add Schedule Repair" );
            System.out.println( "3. View cart and select schedule time" );
            System.out.println( "4. Go Back\n" );
            final String input = console.next();
            //System.out.println( "next:" + input);
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // add schedule maintenance
                addScheduleMaintenance( console );
            }
            else if ( choose == 2 ) {
                // add schedule repair
                addScheduleRepair( console );
            }
            else if ( choose == 3 ) {
                // view cart and select schedule time
                viewCartAndSelectScheduleTime( console );
            }
            else if ( choose == 4 ) {
                // Go Back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * Add Schedule Maintenance page.
     *
     * @param console
     *            the scanner
     */
    private static void addScheduleMaintenance ( final Scanner console ) {
        while ( true ) {
            // to be continued
            System.out.println( "You is eligible for service ?(A/B/C), and the cost is ?" );

            System.out.println( "Please enter a choice." );
            System.out.println( "1. Accept and add to cart" );
            System.out.println( "2. Decline and go back to previous\n" );
            final String input = console.nextLine();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // add to cart
                // to be continued
                break;
            }
            else if ( choose == 2 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * Add Schedule Repair page.
     *
     * @param console
     *            the scanner
     */
    private static void addScheduleRepair ( final Scanner console ) {
        console.nextLine();
        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Engine Services" );
            System.out.println( "2. Exhaust Services" );
            System.out.println( "3. Electrical Services" );
            System.out.println( "4. Transmission Services" );
            System.out.println( "5. Tire Services" );
            System.out.println( "6. Heating and AC Services" );
            System.out.println( "7. Go Back\n" );
            final String input = console.nextLine();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // engine services
            }
            else if ( choose == 2 ) {
                // exhaust services
            }
            else if ( choose == 3 ) {
                // electrical services
            }
            else if ( choose == 4 ) {
                // transmission services
            }
            else if ( choose == 5 ) {
                // tire services
            }
            else if ( choose == 6 ) {
                // heating and ac services
            }
            else if ( choose == 7 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * View cart and select schedule time.
     *
     * @param console
     *            the scanner
     */
    private static void viewCartAndSelectScheduleTime ( final Scanner console ) {
        while ( true ) {
            // display the services in the cart

            System.out.println( "Please enter a choice." );
            System.out.println( "1. Proceed with scheduling" );
            System.out.println( "2. Go Back\n" );
            final String input = console.nextLine();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                // schedule services in cart page
                proceedWithScheduling( console );
                break;
            }
            else if ( choose == 2 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * Proceed with scheduling page.
     *
     * @param console
     *            the scanner
     */
    private static void proceedWithScheduling ( final Scanner console ) {
        while ( true ) {
            // display possible service times

            System.out.println( "Please enter a choice 1 - N of possible time slots." );
            final String input = console.nextLine();
            int choose = 0;
            try {
                choose = Integer.parseInt( input );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
            if ( choose == 1 ) {
                break;
            }
            else if ( choose == 2 ) {
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

    /**
     * Invoices page.
     *
     * @param console
     *            the scanner
     */
    private static void invoices ( final Scanner console ) {
        while ( true ) {
            // display invoices
            System.out.println( "The list of your invoices:" );
            ResultSet rs = null;
            Statement stmt = null;
            final List<Integer> invoiceId = new ArrayList<Integer>();
            try (
                    Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "123" ) ) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery( "select in_id, in_status from invoice where in_customer =" + customer_id
                        + " and in_center = " + center_id );
                while ( rs.next() ) {
                    final StringBuffer invoice = new StringBuffer();
                    invoice.append( "Invoice id:" );
                    final int temp = rs.getInt( "in_id" );
                    invoiceId.add( temp );
                    invoice.append( temp );
                    invoice.append( ", " );
                    if ( rs.getString( "in_status" ).equals( "f" ) ) {
                        invoice.append( "unpaid" );
                    }
                    else {
                        invoice.append( "paid" );
                    }
                    System.out.println( invoice );
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
                close( rs );
            }
            System.out.print( "\n" );
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Invoice details" );
            System.out.println( "2. Pay invoice" );
            System.out.println( "3. Go Back\n" );
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
                // view invoice details
                viewInvoiceDetails( console, invoiceId );
            }
            else if ( choose == 2 ) {
                // pay invoice
                payInvoice( console, invoiceId );
            }
            else if ( choose == 3 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
        }
    }

    /**
     * View Invoice details page.
     *
     * @param console
     *            the scanner
     */
    private static void viewInvoiceDetails ( final Scanner console, final List<Integer> invoices ) {
        while ( true ) {
            System.out.println( "Please enter the invoice ID." );
            final String invoice = console.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt( invoice );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your invoice id is invalid.\n" );
                continue;
            }
            if ( !invoices.contains( id ) ) {
                System.out.println( "Your invoice id is invalid.\n" );
                continue;
            }
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Invoice" );
            System.out.println( "2. Go Back\n" );
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
                // view invoice details
                ResultSet rs = null;
                Statement stmt = null;
                try (
                        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                                "123" ) ) {
                    stmt = conn.createStatement();
                    final StringBuffer detail = new StringBuffer();
                    detail.append( "Invoice ID:" );
                    detail.append( id );
                    detail.append( "\n" );
                    detail.append( "Customer ID:" );
                    detail.append( customer_id );
                    detail.append( "\n" );
                    rs = stmt.executeQuery(
                            "select in_vin, in_date, in_status, in_mechanicName, in_cost from invoice where in_id="
                                    + id );
                    String status = null;
                    String name = null;
                    int total = 0;
                    while ( rs.next() ) {
                        detail.append( "VIN:" );
                        detail.append( rs.getString( 1 ) );
                        detail.append( "\n" );
                        detail.append( "Service Date:" );
                        detail.append( rs.getString( 2 ) );
                        detail.append( "\n" );
                        status = rs.getString( 3 );
                        name = rs.getString( 4 );
                        total = rs.getInt( "in_cost" );
                    }
                    close( rs );

                    rs = stmt.executeQuery(
                            "select inAndS_service, inAndS_type, inAndS_price from invoiceAndService where inAndS_id="
                                    + id );
                    while ( rs.next() ) {
                        detail.append( "Service ID:" );
                        detail.append( rs.getInt( "inAndS_service" ) );
                        detail.append( "\n" );
                        detail.append( "Service Type:" );
                        detail.append( rs.getString( "inAndS_type" ) );
                        detail.append( "\n" );
                        detail.append( "Service Cost:" );
                        detail.append( rs.getInt( "inAndS_price" ) );
                        detail.append( "\n" );
                    }
                    detail.append( "Invoice Status:" );
                    if ( status.equals( "f" ) ) {
                        detail.append( "unpaid" );
                    }
                    else {
                        detail.append( "paid" );
                    }

                    detail.append( "\n" );
                    detail.append( "Mechanic's name:" );
                    detail.append( name );
                    detail.append( "\n" );
                    detail.append( "Total Cost:" );
                    detail.append( total );
                    System.out.println( detail );
                }
                catch ( final SQLException e ) {
                    System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
                }
                catch ( final Exception e ) {
                    e.printStackTrace();
                }
                finally {
                    close( stmt );
                    close( rs );
                }
                continue;
            }
            else if ( choose == 2 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
        }
    }

    /**
     * Pay Invoice page.
     *
     * @param console
     *            the scanner
     */
    private static void payInvoice ( final Scanner console, final List<Integer> invoices ) {
        while ( true ) {
            System.out.println( "Please enter the invoice ID." );
            final String invoice = console.nextLine();
            int id = 0;
            try {
                id = Integer.parseInt( invoice );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "Your invoice id is invalid.\n" );
                continue;
            }
            if ( !invoices.contains( id ) ) {
                System.out.println( "Your invoice id is invalid.\n" );
                continue;
            }
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Pay Invoice" );
            System.out.println( "2. Go Back\n" );
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
                // pay voice
                ResultSet rs = null;
                Statement stmt = null;
                try (
                        Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                                "123" ) ) {
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery( "select in_status from invoice where in_id=" + id );
                    String status = null;
                    while ( rs.next() ) {
                        status = rs.getString( 1 );
                    }
                    if ( status.equals( "t" ) ) {
                        System.out.println( "The invoice is already paid." );
                    }
                    else {
                        stmt.executeUpdate( "update invoice set in_status = 't' where in_id =" + id );
                        System.out.println( "The invoice is successfully paid." );
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
                    close( rs );
                }
                continue;
            }
            else if ( choose == 2 ) {
                // go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\\n" );
                continue;
            }
        }
    }

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
