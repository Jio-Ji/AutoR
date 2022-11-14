package edu.ncsu.csc.AUTOR;

import java.sql.*;
import java.util.Scanner;

public class Admin {

    public Admin(long loginedUserId) {
        System.out.println("Access to Admin Landing page.");
        home(scanner);
    }

    private static void home(Scanner scanner){
        //Scanner scanner = new Scanner(System.in);
        int action = 0;
        while (true) {
            System.out.println("This is CSC 440 edu.ncsu.csc.AUTOR.AUTOR system application.");

            System.out.println("1. System Set Up");
            System.out.println("2. Add New Store");
            System.out.println("3. Add New Service");
            System.out.println("4. Logout\n");

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
                        setUp(scanner);
                    } else if (action == 2) {
                        console.close();
                        addStore(scanner);
                        System.out.println("Action: Go back to home page.");
                    } else if (action == 3){
                        console.close();
                        addNewService(scanner);
                        System.out.println("Action: Go back to home page.");
                    }else if (action == 4) {
                        console.close();
                        System.out.println("Action: Exit.");
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
        //scanner.close();
    }

    private static void addNewService ( final Scanner sc ) {
        boolean login = true;
        while ( login ) {
            System.out.println( "A. Enter existing service category" );
            sc.nextLine();
            final String s_category = sc.nextLine();
            System.out.println( "B. Service Name" );
            final String s_name = sc.nextLine();
            System.out.println( "C. Duration of a service" );
            final int s_time = sc.nextInt();

            System.out.println( "1. Add Service" );
            System.out.println( "2. Go Back" );

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
                    Statement stmt = null;
                    try {
                        final Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe",
                                "system", "123" );
                        stmt = conn.createStatement();
                        final String s_type = "r";
                        if ( s_category.equals( "Engine Services" ) ) {
                            final String s_id = "116";
                            final int s_pTier = 2;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );

                        }
                        else if ( s_category.equals( "Exhaust Services" ) ) {
                            final String s_id = "117";
                            final int s_pTier = 3;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );
                        }
                        else if ( s_category.equals( "Electrical Services" ) ) {
                            final String s_id = "118";
                            final int s_pTier = 2;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );
                        }
                        else if ( s_category.equals( "Transmission Services" ) ) {
                            final String s_id = "119";
                            final int s_pTier = 4;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );
                        }
                        else if ( s_category.equals( "Tire Services" ) ) {
                            final String s_id = "120";
                            final int s_pTier = 3;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );
                        }
                        else if ( s_category.equals( "Heating and A/C Services" ) ) {
                            final String s_id = "121";
                            final int s_pTier = 3;
                            stmt.executeUpdate( "insert into service values ('" + s_id + "', " + s_time + ", " + s_pTier
                                    + ", '" + s_name + "')" );
                            stmt.executeUpdate( "insert into serviceType values ('" + s_id + "', '" + s_name + "', '"
                                    + s_type + "')" );
                        }
                        System.out.println( "Service added successfully!" );

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
                    break;
                }
                else if ( count == 2 ) {

                    login = false;
                    break;
                }
            }
        }

    }

    private static void setUp(Scanner scan){
        boolean runner = true;
        while (runner) {
            System.out.println("Please enter the name of input file that contains the service.");
            String setup = scan.next();
            System.out.println("Please enter the name of input file that contains the store general information.");
            String pop = scan.next();
            while (true) {
                System.out.println("Please enter the choice.");
                System.out.println("1. Upload service general information");
                System.out.println("2. Upload store general information");
                System.out.println("3. Go Back");
                final String input = scan.next();
                int choose = 0;
                try {
                    choose = Integer.parseInt( input );
                }
                catch ( final NumberFormatException e ) {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
                if ( choose == 1 || choose == 2 ) {

                    try (
                            Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                                    "123" ) ) {

                        final ScriptRunner run = new ScriptRunner( conn );
                        run.setLogWriter( null );
                        run.setStopOnError( true );
                        if (choose == 1) {
                            run.runScript( Resources.getResourceAsReader( setup ) );
                        } else {
                            run.runScript( Resources.getResourceAsReader( pop ) );
                        }
                    }
                    catch ( final SQLException e ) {
                        System.err.format( "SQL State: %s\n%s", e.getSQLState(), e.getMessage() );
                    }
                    catch ( final Exception e ) {
                        System.out.println("fail");
                        break;
                    }
                    System.out.println("success");
                } else if ( choose == 3 ) {
                    runner = false;
                    break;
                }
                else {
                    System.out.println( "Your choice is invalid.\n" );
                    continue;
                }
            }
        }
    }


    private static void addStore(Scanner scan) {
        // Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
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
            System.out.println();
        }
        finally {
            close( stmt );
            // close(conn);
        }
        //System.out.println( "All information insert!" );
        System.out.println( "1. GO BACK" );

        while ( scan.hasNext() ) {
            final String input = scan.next();
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
