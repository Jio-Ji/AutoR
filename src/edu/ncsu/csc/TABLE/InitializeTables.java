package edu.ncsu.csc.TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
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
        user_pw();
        // insert default data
        center(); // center edu.ncsu.csc.TABLE.table
        emp();
        vehicle();
        customer();
        associate();
        invoice();
        service();
        indserv();
        repair();
        tire_service();
        rser();
        //Maintenance();
        //MSch();

    }



    /**
     *  DELETE ORDER
     *
     */
    public void dropAllTable() {
        //may implement later
    }
    public static void user_pw() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE USERPW " + "(u_id NUMBER(20), PWD VARCHAR(20),"
                    + "ROLE TINYINT");
            stmt.executeUpdate( "ALTER TABLE USERPW " + "ADD CONSTRAINT userpw_pk PRIMARY KEY (U_ID)");
        }catch ( final SQLException e ) {
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

    private static void tire_service () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE TSER " +
                    "(T_ID INTEGER NOT NULL REFERENCES REPAIR(REPAIR_ID) PRIMARY KEY, " +
                    "In_ID INTEGER NOT NULL, " +
                    "rt_cent VARCHAR(20) NOT NULL, "+
                    "S_NAME VARCHAR(20))" );
            stmt.executeUpdate( "ALTER TABLE TSER " + "ADD CONSTRAINT fk FOREIGN KEY(T_ID,rt_cent) REFERENCES INDSERV(IND_ID, IND_CENTER)");
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

    private static void repair () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate(
                    "CREATE TABLE REPAIR " + "(REPAIR_ID INTEGER REFERENCES SERVICE(SERVICE_ID) PRIMARY KEY, "
                            + "REPAIR_NAME VARCHAR(20))" );
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

    private static void indserv () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE INDSERV "
                    + "(IND_ID INTEGER, IND_NAME VARCHAR(20), PRICE INTEGER, OP_TIME INTEGER, IND_CENTER VARCHAR(20))" );
            // Add a primary key
            stmt.executeUpdate( "ALTER TABLE INDSERV " + "ADD CONSTRAINT IND_PK PRIMARY KEY (IND_ID, IND_CENTER)" );
            stmt.executeUpdate( "ALTER TABLE INDSERV "
                    + "ADD CONSTRAINT CENTER_FK FOREIGN KEY (IND_CENTER)REFERENCES CENTER(CENTER_ID)" );
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
    private static void service () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE SERVICE " + "(SERVICE_ID INTEGER PRIMARY KEY)" );
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

    private static void invoice () {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE INVOICE "
                    + "(INVOICE_ID VARCHAR(20) PRIMARY KEY, INVOICE_SERVICEID INTEGER, INVOICE_CUSTOMERID VARCHAR(20),"
                    + " INVOICE_DATE DATE, INVOICE_STATUS CHAR(1) DEFAULT 'f', INVOICE_VIN VARCHAR(20))" );

            stmt.executeUpdate( "ALTER TABLE INVOICE "
                    + "ADD CONSTRAINT SERVICE_FK FOREIGN KEY (INVOICE_SERVICEID)REFERENCES SERVICE(SERVICE_ID)" );
            stmt.executeUpdate(
                    "ALTER TABLE INVOICE " + "ADD CONSTRAINT VIN_FK FOREIGN KEY (INVOICE_VIN)REFERENCES VEHICLE(VIN)" );
            stmt.executeUpdate( "ALTER TABLE INVOICE "
                    + "ADD CONSTRAINT CUS_FK FOREIGN KEY (INVOICE_CUSTOMERID)REFERENCES CUSTOMER(CUSTOMER_ID)" );

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

    private static void associate() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE ASSOCIATE " + "(vin VARCHAR(20), CUSTOMER_ID VARCHAR(20)) ");
            // Add constraint
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT vin_pk FOREIGN KEY(vin)REFERENCES vehicle(vin)");
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT cus_pk FOREIGN KEY(C_ID)REFERENCES CUSTOMER(CUSTOMER_ID)");
            stmt.executeUpdate( "ALTER TABLE ASSOCIATE " + "ADD CONSTRAINT PK PRIMARY KEY (VIN, C_ID)");
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
