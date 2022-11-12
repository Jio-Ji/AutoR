package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.Scanner;

public class Admin {

    public Admin(long loginedUserId) {
        System.out.println("Access to Admin Landing page.");
        center();
    }
    private static void center(){
        Scanner scanner = new Scanner(System.in);
        int action = 0;
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");
            System.out.println("1. Add Store");
            System.out.println("2. Exit\n");
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
                        AddStore(scanner);
                        break;
                    } else if (action == 2) {
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
        scanner.close();
    }
    private static void AddStore(Scanner scan) {

        System.out.println( "Please enter center address" );
        final String address = scan.next();
        System.out.println( "Please enter center tele" );
        final String tele = scan.next();


        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            stmt.executeUpdate( "DROP TABLE EMP" );
            stmt.executeUpdate( "DROP TABLE CENTER" );


            // CENTER
            stmt.executeUpdate( "CREATE TABLE CENTER " + "(CENTER_ID VARCHAR(20), CENTER_ADD VARCHAR(50),STATE VARCHAR(20), "
                    + "CENTER_TELE INTEGER)" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE CENTER " + "ADD CONSTRAINT center_pk PRIMARY KEY (CENTER_ID)" );

            // EMP
            stmt.executeUpdate( "CREATE TABLE EMP " + "(EMP_ID INTEGER PRIMARY KEY , EMP_NAME VARCHAR(20), "
                    + "EMP_ADDRESS VARCHAR(20), EMP_EMAIL VARCHAR(20), EMP_PHONE INTEGER, CENTER_ID VARCHAR(20), EMP_ROLE varchar(20))" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE EMP " + "ADD CONSTRAINT CENTER_FK FOREIGN KEY (CENTER_ID)REFERENCES CENTER(CENTER_ID)" );

            final PreparedStatement ps = conn
                    .prepareStatement( "INSERT INTO CENTER (CENTER_ID, CENTER_ADD, CENTER_TELE,STATE "
                            + ") VALUES(?,?,?,?)" );
            ps.setInt( 1, 1 );
            ps.setString( 2, address );
            ps.setString( 3, tele );
            ps.setString( 4, "unStepUp" );
            ps.executeUpdate();
            ps.close();
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

        while ( scan.hasNextLine() ) {
            final String input = scan.nextLine();
            int count = 0;
            try {
                count = Integer.valueOf( input );
            }
            catch ( final NumberFormatException e ) {
                continue;
            }

            if ( count == 1 ) {
                center();
                break;
            }
        }
        scan.close();
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
