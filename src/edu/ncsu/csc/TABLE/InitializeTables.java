package edu.ncsu.csc.TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeTables {



    // 创建所有TABLE
    /*public InitializeTables() {
        center();
        emp();
    }*/

    //
    public static void main ( final String[] args ) {
        // initialize all tables
        // insert default data
        center(); // center edu.ncsu.csc.TABLE.table
        emp();
        vehicle();
        customer();
        associate();
    }

    /**
     *  DELETE ORDER
     *
     */
    public void dropAllTable() {

    }

    private static void associate() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE ASSOCIATE " + "(vin VARCHAR(20), CUSTOMER_ID VARCHAR(20)) ");
            // Add constraint
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT AK FOREIGN KEY(vin)REFERENCES vehicle(vin)," );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT AC FOREIGN KEY(CUSTOMER_ID)REFERENCES customer(CUSTOMER_ID)," );
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT PK PRIMARY KEY (VIN, CUSTOMER_ID)," );

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

    private static void emp() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE EMP " + "(EMP_ID INTEGER PRIMARY KEY , EMP_NAME VARCHAR(20), "
                    + "EMP_ADDRESS VARCHAR(20), EMP_EMAIL VARCHAR(20), EMP_PHONE INTEGER, CENTER_ID VARCHAR(20), EMP_ROLE varchar(20))" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE EMP " + "ADD CONSTRAINT CENTER_FK FOREIGN KEY (CENTER_ID)REFERENCES (CENTER_ID)" );
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

    /**
     * Initialize the center edu.ncsu.csc.TABLE.table.
     */
    public static void center () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE CENTER " + "(CENTER_ID VARCHAR(20), CENTER_ADDRESS VARCHAR(50), "
                    + "CENTER_TELEPHONE INTEGER)" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE CENTER " + "ADD CONSTRAINT center_pk PRIMARY KEY (CENTER_ID)" );

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

    public static void vehicle() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {
            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE VEHICLE " + "(VIN VARCHAR(20), MANU VARCHAR(10), "
                    + "MILEAGE VARCHAR(10), YEAR INTEGER,  CLASS VARCHAR(20))" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE VEHICLE " + "ADD CONSTRAINT ID PRIMARY KEY (VIN)" );
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

    public static void customer() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE CUSTOMER " + "(CUSTOMER_ID VARCHAR(20), FIRST_NAME VARCHAR(10), "
                    + "LAST_NAME VARCHAR(10), CUSTOMER_STATUS CHAR(1) DEFAULT 'f')" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE CUSTOMER " + "ADD CONSTRAINT customer_pk PRIMARY KEY (CUSTOMER_ID)" );

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

}
