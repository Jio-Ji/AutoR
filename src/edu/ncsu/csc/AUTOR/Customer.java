package edu.ncsu.csc.AUTOR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
    private static int          customer_id = 0;
    private static int          center_id   = 0;
    private static List<String> cartService = new ArrayList<String>();

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
        home(scanner);
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
                System.out.println( "Your choice is invalid.\n" );
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
                System.out.println( "Your choice is invalid.\n" );
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
        String vin;
        String manufacturer;
        String mileage;
        String year;
        System.out.println( "Please enter the VIN number." );
        console.nextLine();
        vin = console.nextLine();
        System.out.println( "Please enter the car manufacturer name." );
        manufacturer = console.nextLine();
        while ( true ) {
            System.out.println( "Please enter current mileage." );
            mileage = console.nextLine();
            try {
                Integer.parseInt( mileage );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "ERROR: Mileage should be an integer.\n" );
                continue;
            }
            break;
        }
        while ( true ) {
            System.out.println( "Please enter the year." );
            year = console.nextLine();
            try {
                Integer.parseInt( year );
            }
            catch ( final NumberFormatException e ) {
                System.out.println( "ERROR: Year should be an integer.\n" );
                continue;
            }
            break;
        }
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
                    stmt.executeUpdate( "update customer set customer_status='t' where customer_id=" + customer_id );
                    stmt.executeUpdate( "insert into vehicleAndMaintenance values('" + vin + "', '113')" );
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
            int numberOfCars = 0;
            System.out.println( "List of All Cars:\n" );
            rs = stmt.executeQuery( "select * from vehicle where vin = any (select ass_vin from associate"
                    + " where ass_customer_id=" + customer_id + " and ass_center_id=" + center_id + ")" );
            while ( rs.next() ) {
                System.out.println( "VIN number:" + rs.getString( 1 ) );
                System.out.println( "Car Manufacturer name:" + rs.getString( 2 ) );
                System.out.println( "Current mileage:" + rs.getString( 3 ) );
                System.out.println( "Year:" + rs.getString( 4 ) + "\n" );
                numberOfCars++;
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
                    final String deleteMainService = "DELETE FROM vehicleAndMaintenance WHERE main_vin = '" + vin + "'";
                    final String deleteVeh = "delete from vehicle where vin = '" + vin + "'";
                    stmt.executeUpdate( deleteAss );
                    stmt.executeUpdate( deleteMainService );
                    stmt.executeUpdate( deleteVeh );
                    numberOfCars--;
                    System.out.println( "The vehicle record was deleted.\n" );
                    if ( numberOfCars == 0 ) {
                        stmt.executeUpdate(
                                "update customer set customer_status='f' where customer_id=" + customer_id );
                    }
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
                System.out.println( "Your choice is invalid.\n" );
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
                System.out.println( "Your choice is invalid.\n" );
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
                System.out.println( "Your choice is invalid.\n" );
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
                    rs = stmt.executeQuery( "select in_id, in_date from invoice where in_vin = '" + vin + "'" );
                    final List<Integer> id = new ArrayList<Integer>();
                    final List<String> date = new ArrayList<String>();
                    while ( rs.next() ) {
                        id.add( rs.getInt( "in_id" ) );
                        date.add( rs.getString( "in_date" ) );
                    }
                    close( rs );
                    if ( id.size() == 0 ) {
                        System.out.println( "You do not have service history for this car.\n" );
                        continue;
                    }
                    for ( int i = 0; i < id.size(); i++ ) {
                        String name = null;
                        rs = stmt.executeQuery( "select in_mechanicName from invoice where in_id = " + id.get( i ) );
                        while ( rs.next() ) {
                            name = rs.getString( 1 );
                        }
                        close( rs );

                        rs = stmt.executeQuery(
                                "select inands_service, inands_type, inands_price from invoiceAndService where inAndS_id = "
                                        + id.get( i ) );
                        while ( rs.next() ) {
                            System.out.println( "Service ID:" + id.get( i ) );
                            System.out.println( "VIN Number:" + vin );
                            System.out.println( "Service Type:" + rs.getString( "inands_type" ) );
                            System.out.println( "Service Cost:" + rs.getInt( "inands_price" ) );
                            System.out.println( "Mechanic Name:" + name );
                            System.out.println( "Service Start:" + date.get( i ) );
                            System.out.println( "Service End:" + date.get( i ) + "\n" );
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
            else if ( choose == 2 ) {
                // Go back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
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
            // final String mileage = console.nextLine();
            console.next();
            //System.out.println( "?:" + console.next());

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
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
            if ( choose == 1 ) {
                // add schedule maintenance
                addScheduleMaintenance( console, vin );
            }
            else if ( choose == 2 ) {
                // add schedule repair
                addScheduleRepair( console );
            }
            else if ( choose == 3 ) {
                // view cart and select schedule time
                viewCartAndSelectScheduleTime( console, vin );
            }
            else if ( choose == 4 ) {
                // Go Back
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
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
    private static void addScheduleMaintenance ( final Scanner console, final String vin ) {
        console.nextLine();
        Statement stmt = null;
        ResultSet rs = null;
        String serviceid = null;
        String service = null;
        String serviceName = null;
        int cost = 0;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            rs = stmt.executeQuery( "select main_id from vehicleAndMaintenance where main_vin='" + vin + "'" );
            while ( rs.next() ) {
                serviceid = rs.getString( 1 );
            }
            if ( serviceid.equals( "113" ) ) {
                service = "A";
                serviceName = "Maintenance Service A";
            }
            else if ( serviceid.equals( "114" ) ) {
                service = "B";
                serviceName = "Maintenance Service B";
            }
            else {
                service = "C";
                serviceName = "Maintenance Service C";
            }
            close( rs );
            rs = stmt.executeQuery( "select p_dollar from prices where p_centerId=" + center_id
                    + " and p_model= (select manu from vehicle where vin ='" + vin
                    + "') and p_tier=(select s_priceTier from service where s_id=" + serviceid + ")" );
            while ( rs.next() ) {
                cost = rs.getInt( 1 );
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
            // to be continued
            System.out.println( "You is eligible for service " + service + ", and the cost is " + cost );

            System.out.println( "Please enter a choice." );
            System.out.println( "1. Accept and add to cart" );
            System.out.println( "2. Decline and go back to previous\n" );
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
                cartService.add( serviceName );
                // to be continued
                break;
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
                System.out.println( "Your choice is invalid.\n" );
                continue;
            }
            if ( choose == 1 ) {
                // engine services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Engine Services:" );
                    System.out.println( "1. Belt Replacement" );
                    System.out.println( "2. Engine Repair" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Belt Replacement" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Engine Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }

            }
            else if ( choose == 2 ) {
                // exhaust services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Exhaust Services:" );
                    System.out.println( "1. Exhaust Repair" );
                    System.out.println( "2. Muffler Repair" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Exhaust Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Muffler Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }
            }
            else if ( choose == 3 ) {
                // electrical services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Electrical Services:" );
                    System.out.println( "1. Alternator Repair" );
                    System.out.println( "2. Power Lock Repair" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Alternator Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Power Lock Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }
            }
            else if ( choose == 4 ) {
                // transmission services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Transmission Services:" );
                    System.out.println( "1. Axle Repair" );
                    System.out.println( "2. Brake Repair" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Axle Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Brake Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }
            }
            else if ( choose == 5 ) {
                // tire services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Tire Services:" );
                    System.out.println( "1. Tire Balancing" );
                    System.out.println( "2. Wheel Alignment" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Tire Balancing" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Wheel Alignment" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }
            }
            else if ( choose == 6 ) {
                // heating and ac services
                while ( true ) {
                    System.out.println( "Please enter a choice." );
                    System.out.println( "Heating and AC Services:" );
                    System.out.println( "1. Compressor Repair" );
                    System.out.println( "2. Evaporator Repair" );
                    System.out.println( "3. Go Back\n" );
                    final String choice = console.nextLine();
                    int temp = 0;
                    try {
                        temp = Integer.parseInt( choice );
                    }
                    catch ( final NumberFormatException e ) {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                    if ( temp == 1 ) {
                        cartService.add( "Compressor Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 2 ) {
                        cartService.add( "Evaporator Repair" );
                        System.out.println( "The service is added successfully." );
                    }
                    else if ( temp == 3 ) {
                        break;
                    }
                    else {
                        System.out.println( "Your choice is invalid.\n" );
                        continue;
                    }
                }
            }
            else if ( choose == 7 ) {
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
     * View cart and select schedule time.
     *
     * @param console
     *            the scanner
     */
    private static void viewCartAndSelectScheduleTime ( final Scanner console, final String vin ) {
        console.nextLine();
        while ( true ) {
            // display the services in the cart
            System.out.println( "Your cart:" );
            if ( cartService.size() == 0 ) {
                System.out.println( "Nothing in your cart.\n" );
                break;
            }
            for ( int i = 0; i < cartService.size(); i++ ) {
                System.out.println( cartService.get( i ) );
            }
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Proceed with scheduling" );
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
                // schedule services in cart page
                proceedWithScheduling( console, vin );
                break;
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
     * Proceed with scheduling page.
     *
     * @param console
     *            the scanner
     */
    private static void proceedWithScheduling ( final Scanner console, final String vin ) {
        final int[][][] time = new int[4][7][11];
        Statement stmt = null;
        ResultSet rs = null;
        boolean open = false;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            rs = stmt.executeQuery( "select open_sat from center where center_id='" + center_id + "'" );
            String openSat = null;
            while ( rs.next() ) {
                openSat = rs.getString( 1 );
            }
            close( rs );
            if ( openSat.equals( "t" ) ) {
                open = true;
            }
            rs = stmt.executeQuery( "select count(*) from emp where emp_center=" + center_id );
            int count = 0;
            while ( rs.next() ) {
                count = rs.getInt( 1 );
            }
            close( rs );
            rs = stmt.executeQuery( "select timeslot_week, timeslot_days, timeslot_slot, count(timeslot_mechanic) "
                    + "from timeslot where timeslot_center = " + center_id
                    + " group by timeslot_week, timeslot_days, timeslot_slot" );
            while ( rs.next() ) {
                final int week = rs.getInt( 1 );
                final int day = rs.getInt( 2 );
                final int slot = rs.getInt( 3 );
                final int numWork = rs.getInt( 4 );
                if ( numWork == count ) {
                    time[week - 1][day - 1][slot - 1] = 1;
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

        System.out.println( "The time slots:" );
        for ( int i = 0; i < 4; i++ ) {
            final StringBuffer timeSlot = new StringBuffer();
            timeSlot.append( "Week " );
            timeSlot.append( i + 1 );
            timeSlot.append( ":\n" );
            for ( int j = 0; j < 7; j++ ) {
                if ( j == 0 ) {
                    timeSlot.append( "Monday:    " );
                }
                else if ( j == 1 ) {
                    timeSlot.append( "Tuesday:   " );
                }
                else if ( j == 2 ) {
                    timeSlot.append( "Wednesday: " );
                }
                else if ( j == 3 ) {
                    timeSlot.append( "Thursday:  " );
                }
                else if ( j == 4 ) {
                    timeSlot.append( "Friday:    " );
                }
                else if ( j == 5 ) {
                    timeSlot.append( "Saturday:  " );
                    if ( !open ) {
                        timeSlot.append( "The center is closed today\n" );
                        continue;
                    }
                }
                else {
                    timeSlot.append( "Sunday:    " );
                }
                for ( int z = 0; z < 11; z++ ) {
                    timeSlot.append( z + 1 );
                    timeSlot.append( ". " );
                    if ( z == 0 ) {
                        timeSlot.append( "8:00-9:00 " );
                    }
                    else if ( z == 1 ) {
                        timeSlot.append( "9:00-10:00 " );
                    }
                    else if ( z == 2 ) {
                        timeSlot.append( "10:00-11:00 " );
                    }
                    else if ( z == 3 ) {
                        timeSlot.append( "11:00-12:00 " );
                    }
                    else if ( z == 4 ) {
                        timeSlot.append( "13:00-14:00 " );
                    }
                    else if ( z == 5 ) {
                        timeSlot.append( "14:00-15:00 " );
                    }
                    else if ( z == 6 ) {
                        timeSlot.append( "15:00-16:00 " );
                    }
                    else if ( z == 7 ) {
                        timeSlot.append( "16:00-17:00 " );
                    }
                    else if ( z == 8 ) {
                        timeSlot.append( "17:00-18:00 " );
                    }
                    else if ( z == 9 ) {
                        timeSlot.append( "18:00-19:00 " );
                    }
                    else {
                        timeSlot.append( "19:00-20:00 " );
                    }
                    if ( time[i][j][z] == 1 ) {
                        timeSlot.append( "not available " );
                    }
                    else {
                        timeSlot.append( "  available   " );
                    }
                }
                timeSlot.append( "\n" );
            }
            System.out.println( timeSlot );
        }
        System.out.println( "Please choose the available time slot." );
        String weekChose = null;
        String dayChose = null;
        String slotChose = null;
        int weekNum = 0;
        int dayNum = 0;
        int slotNum = 0;

        while ( true ) {
            while ( true ) {
                System.out.println( "Please enter the week number (1-4):" );
                weekChose = console.nextLine();
                try {
                    weekNum = Integer.parseInt( weekChose );
                }
                catch ( final NumberFormatException e ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                if ( weekNum != 1 && weekNum != 2 && weekNum != 3 && weekNum != 4 ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                break;
            }
            while ( true ) {
                System.out.println( "Please enter the day number (1-7):" );
                System.out.println( "1. Monday 2.Tuesday 3. Wednesday 4. Thursday 5.Friday 6.Saturday 7.Sunday" );
                dayChose = console.nextLine();
                try {
                    dayNum = Integer.parseInt( dayChose );
                }
                catch ( final NumberFormatException e ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                if ( dayNum != 1 && dayNum != 2 && dayNum != 3 && dayNum != 4 && dayNum != 5 && dayNum != 6
                        && dayNum != 7 ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                if ( !open && dayNum == 6 ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                break;
            }
            while ( true ) {
                System.out.println( "Please enter the slot number (1-11):" );
                slotChose = console.nextLine();
                try {
                    slotNum = Integer.parseInt( slotChose );
                }
                catch ( final NumberFormatException e ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                if ( slotNum != 1 && slotNum != 2 && slotNum != 3 && slotNum != 4 && slotNum != 5 && slotNum != 6
                        && slotNum != 7 && slotNum != 8 && slotNum != 9 && slotNum != 10 && slotNum != 11 ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                break;
            }
            if ( time[weekNum - 1][dayNum - 1][slotNum - 1] == 1 ) {
                System.out.println( "Your choice of the slot is not available.\n" );
                continue;
            }
            int cartTime = 0;
            try (
                    Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                            "123" ) ) {

                stmt = conn.createStatement();
                for ( int i = 0; i < cartService.size(); i++ ) {
                    rs = stmt.executeQuery( "select s_time from service where s_id="
                            + "(select S.s_id from service S where S.s_name='" + cartService.get( i ) + "')" );
                    while ( rs.next() ) {
                        cartTime += rs.getInt( 1 );
                    }
                    close( rs );
                }
                if ( slotNum + cartTime - 1 > 11 ) {
                    System.out.println(
                            "Invalid: You should choose a continuous and available time slot in one day and your total service time is"
                                    + cartTime );
                    continue;
                }
                boolean slotValid = true;
                for ( int i = 0; i < cartTime; i++ ) {
                    final int temp = slotNum - 1 + i;
                    if ( time[weekNum - 1][dayNum - 1][temp] == 1 ) {
                        slotValid = false;
                        break;
                    }
                }
                if ( !slotValid ) {
                    System.out.println(
                            "Invalid: You should choose a continuous and available time slot in one day and your total service time is"
                                    + cartTime );
                    continue;
                }
                final List<Integer> avaMechanic = new ArrayList<Integer>();
                rs = stmt.executeQuery( "select mwh_hour, mwh_mechanic from mechanicWeekHour where mwh_week=" + weekNum
                        + " and mwh_center=" + center_id );
                while ( rs.next() ) {
                    final int workerHour = rs.getInt( "mwh_hour" );
                    final int me_id = rs.getInt( "mwh_mechanic" );
                    if ( workerHour + cartTime <= 50 ) {
                        avaMechanic.add( me_id );
                    }
                }
                close( rs );
                if ( avaMechanic.size() == 0 ) {
                    System.out.println( "No mechanic available for this week" );
                    continue;
                }
                for ( int i = 0; i < avaMechanic.size(); i++ ) {
                    final int temp = avaMechanic.get( i );
                    for ( int j = 0; j < cartTime; j++ ) {
                        final int tempSlot = slotNum + j;
                        rs = stmt.executeQuery( "select * from timeslot where timeslot_center = " + center_id
                                + " and timeslot_week = " + weekNum + " and timeslot_days = " + dayNum
                                + " and timeslot_slot = " + tempSlot + " and timeslot_mechanic =" + temp );
                        while ( rs.next() ) {
                            avaMechanic.remove( i );
                            i--;
                            break;
                        }
                        close( rs );
                    }
                }
                if ( avaMechanic.size() == 0 ) {
                    System.out.println( "No mechanic available for this time slot" );
                    continue;
                }
                // Valid
                rs = stmt.executeQuery( "select mwh_hour from mechanicWeekHour where mwh_mechanic="
                        + avaMechanic.get( 0 ) + " and mwh_week=" + weekNum );
                int totalHour = cartTime;
                while ( rs.next() ) {
                    totalHour += rs.getInt( 1 );
                }
                close( rs );
                stmt.executeUpdate( "update mechanicWeekHour set mwh_hour=" + totalHour + " where mwh_mechanic="
                        + avaMechanic.get( 0 ) + " and mwh_week=" + weekNum );
                for ( int i = 0; i < cartTime; i++ ) {
                    final int tempSlot = slotNum + i;
                    stmt.executeUpdate( "insert into timeslot values(" + center_id + ", " + weekNum + ", " + dayNum
                            + ", " + tempSlot + ", " + avaMechanic.get( 0 ) + ")" );
                }
                // invoice
                int invoice = 900;
                rs = stmt.executeQuery( "select count(*) from invoice" );
                while ( rs.next() ) {
                    invoice += rs.getInt( 1 );
                }
                close( rs );
                rs = stmt
                        .executeQuery( "select mechanic_name from mechanic where mechanic_id=" + avaMechanic.get( 0 ) );
                String nameOfWorker = null;
                while ( rs.next() ) {
                    nameOfWorker = rs.getString( 1 );
                }
                close( rs );
                int cost = 0;
                final List<Integer> eachCost = new ArrayList<Integer>();
                for ( int i = 0; i < cartService.size(); i++ ) {
                    rs = stmt.executeQuery( "select p_dollar from prices where p_centerId=" + center_id
                            + " and p_model= (select manu from vehicle where vin ='" + vin
                            + "') and p_tier=(select s_priceTier from service where s_name='" + cartService.get( i )
                            + "')" );
                    while ( rs.next() ) {
                        final int temp = rs.getInt( 1 );
                        eachCost.add( temp );
                        cost += temp;
                    }
                    close( rs );
                }
                final StringBuffer date = new StringBuffer();
                date.append( "11/" );
                final int day = ( weekNum - 1 ) * 7 + dayNum;
                date.append( day );
                date.append( "/2022" );
                stmt.executeUpdate( "insert into invoice values(" + invoice + ", " + center_id + ", '" + vin + "', "
                        + customer_id + ", '" + date + "', 'f', '" + nameOfWorker + "', " + cost + ")" );
                for ( int i = 0; i < cartService.size(); i++ ) {
                    rs = stmt.executeQuery( "select s_id from service where s_name = '" + cartService.get( i ) + "'" );
                    int tempId = 0;
                    while ( rs.next() ) {
                        tempId = rs.getInt( 1 );
                    }
                    close( rs );
                    String type = null;
                    if ( cartService.get( i ).equals( "Belt Replacement" )
                            || cartService.get( i ).equals( "Engine Repair" ) ) {
                        type = "Engine Services";
                    }
                    else if ( cartService.get( i ).equals( "Exhaust Repair" )
                            || cartService.get( i ).equals( "Muffler Repair" ) ) {
                        type = "Exhaust Services";
                    }
                    else if ( cartService.get( i ).equals( "Alternator Repair" )
                            || cartService.get( i ).equals( "Power Lock Repair" ) ) {
                        type = "Electrical Services";
                    }
                    else if ( cartService.get( i ).equals( "Axle Repair" )
                            || cartService.get( i ).equals( "Brake Repair" ) ) {
                        type = "Transmission Services";
                    }
                    else if ( cartService.get( i ).equals( "Tire Balancing" )
                            || cartService.get( i ).equals( "Wheel Alignment" ) ) {
                        type = "Tire Services";
                    }
                    else if ( cartService.get( i ).equals( "Compressor Repair" )
                            || cartService.get( i ).equals( "Evaporator Repair" ) ) {
                        type = "Heating and A/C Services";
                    }
                    else {
                        type = "Maintenance Services";
                        if ( cartService.get( i ).equals( "Maintenance Service A" ) ) {
                            stmt.executeUpdate(
                                    "update vehicleAndMaintenance set main_id = 114 where main_vin='" + vin + "'" );
                        }
                        else if ( cartService.get( i ).equals( "Maintenance Service B" ) ) {
                            stmt.executeUpdate(
                                    "update vehicleAndMaintenance set main_id = 115 where main_vin='" + vin + "'" );
                        }
                        else {
                            stmt.executeUpdate(
                                    "update vehicleAndMaintenance set main_id = 113 where main_vin='" + vin + "'" );
                        }
                    }
                    stmt.executeUpdate( "insert into invoiceAndService values(" + invoice + ", " + tempId + ", '" + type
                            + "', " + eachCost.get( i ) + ")" );
                }
                System.out.println( "Your service has been successfully booked!" );
                final int size = cartService.size();
                for ( int i = 0; i < size; i++ ) {
                    cartService.remove( cartService.size() - 1 );
                }
                break;
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

    }

    /**
     * Invoices page.
     *
     * @param console
     *            the scanner
     */
    private static void invoices ( final Scanner console ) {
        console.nextLine();
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
                System.out.println( "Your choice is invalid.\n" );
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
