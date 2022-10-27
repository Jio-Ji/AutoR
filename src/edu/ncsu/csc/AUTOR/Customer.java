package edu.ncsu.csc.AUTOR;

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
    public Customer ( final long loginUserId ) {
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
     * Home page. 1. View and Update Profile 2. View and Schedule Service 3.
     * Invoices 4. Logout
     */
    private static void home () {
        final Scanner console = new Scanner( System.in );
        boolean notLogout = true;
        while ( notLogout ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View and Update Profile" );
            System.out.println( "2. View and Schedule Service" );
            System.out.println( "3. Invoices" );
            System.out.println( "4. Logout\n" );
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
        console.close();

    }

    /**
     * ViewAndUpdateProfile page.
     * @param console the scanner
     */
    private static void viewAndUpdateProfile ( final Scanner console ) {
        boolean notGoback = true;
        while ( notGoback ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. View Profile" );
            System.out.println( "2. Add Car" );
            System.out.println( "3. Delete Car" );
            System.out.println( "4. Go Back\n" );
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
                // View Profile
                viewProfile( console );
            }
            else if ( choose == 2 ) {
                // Add Car
                addCar( console );
            }
            else if ( choose == 3 ) {
                // Delete Car
                deleteCar( console );
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
     * @param console the scanner
     */
    private static void viewProfile ( final Scanner console ) {
        final String fullname = null;
        final String address = null;
        final String email = null;
        final String phone = null;
        final String cars[];

        System.out.println( "Your profile:" );
        System.out.println( "Customer ID:" + customer_id );
        // to be continued
        System.out.println( "Full name:" );
        System.out.println( "Address:" );
        System.out.println( "Email Address:" );
        System.out.println( "Phone Number:" );
        System.out.println( "List of All Cars:\n" );

        while ( true ) {
            System.out.println( "Please enter a choice." );
            System.out.println( "1. Go Back\n" );
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
                break;
            }
            else {
                System.out.println( "Your choice is invalid.\n" );
            }
        }

    }

    /**
     * Add the car.
     * @param console the sacnner
     */
    private static void addCar( final Scanner console ) {

    }

    /**
     * Delete the car.
     * @param console the sacnner
     */
    private static void deleteCar( final Scanner console ) {

    }
    /**
     * View and schedule service.
     * @param console the scanner
     */
    private static void viewAndScheduleService ( final Scanner console ) {
    }
    /**
     * Invoices
     * @param console the scanner
     */
    private static void invoices ( final Scanner console ) {

    }

}