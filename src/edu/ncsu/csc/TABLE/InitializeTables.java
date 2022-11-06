package edu.ncsu.csc.TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeTables {



    // 创建所有TABLE
    public InitializeTables() {
        user_pw();
        // insert default data
        System.out.println(1);
        center(); // center edu.ncsu.csc.TABLE.table
        System.out.println(2);
        emp();
        System.out.println(3);
        vehicle();
        System.out.println(4);
        customer();
        System.out.println(5);
        associate();

        System.out.println(6);
        //SQL State: 42000
        //ORA-00942: 表或视图不存在
        invoice();

        System.out.println(7);
        service();

        System.out.println(8);
        //SQL State: 42000
        //ORA-02264: 名称已被一现有约束条件占用
        indserv();

        System.out.println(9);
        repair();
        System.out.println(10);
        tire_service();
        System.out.println(11);
        rser();
        //Maintenance();
        //MSch();
    }


    //
    /*public static void main ( final String[] args ) {
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

    }*/



    /**
     *  DELETE ORDER
     *
     */
    /*public void dropAllTable() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            stmt.executeUpdate("DROP TABLE USERPW ");
            stmt.executeUpdate("DROP TABLE USERPW ");
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
    }*/
    public static void user_pw() {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE USERPW (" +
                    "u_id NUMBER(20) PRIMARY KEY, " +
                    "PWD VARCHAR(20)," +
                    "ROLE INTEGER)");
            //stmt.executeUpdate( "ALTER TABLE USERPW " + "ADD CONSTRAINT userpw_pk PRIMARY KEY (U_ID)");
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
            stmt.executeUpdate( "CREATE TABLE ASSOCIATE " + "(vin VARCHAR(20), C_ID VARCHAR(20)) ");
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
            stmt.executeUpdate( "ALTER TABLE EMP " + "ADD CONSTRAINT CENTER_FK FOREIGN KEY (CENTER_ID)REFERENCES CENTER(CENTER_ID)" );
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

    private static void rser() {
        // Connection conn = null;
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" ) ) {

            stmt = conn.createStatement();
            stmt.executeUpdate( "CREATE TABLE RSER " +
                                        "(R_ID INTEGER NOT NULL REFERENCES REPAIR(REPAIR_ID) PRIMARY KEY, " +
                                        "I_ID INTEGER NOT NULL, " +
                                        "r_cent VARCHAR(20) NOT NULL, "+
                                        "RS_NAME VARCHAR(20))" );
            stmt.executeUpdate( "ALTER TABLE RSER " + "ADD CONSTRAINT tfk FOREIGN KEY(I_ID,r_cent) REFERENCES INDSERV(IND_ID, IND_CENTER)");
        }
        //NOT NULL REFERENCES INDSERV(IND_ID)
        //NOT NULL REFERENCES INDSERV(IND_CENTER)
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

   private static void MSch(){
        Statement stmt = null;

        try (Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                      "123" )){

            stmt = conn.createStatement();
            stmt.executeUpdate(" CREATE TABLE MSch " + "(MS_name varchar(20),"+ "Ms_id INTEGER NOT NULL REFERENCES Maintenance(M_id),"+"Ms_iid INTEGER NOT NULL," +"Ms_cent VARCHAR(20) NOT NULL)");


            stmt.executeUpdate("ALTER TABLE MSch"+ "ADD CONSTRAINT MSch_fk FOREIGN KEY(Ms_iid,MS_cent) REFEREBCES INDSERV(IND_ID, IND_CENTER)");
            stmt.executeUpdate("ALTER TABLE MSch"+ "ADD CONSTRAINT MSch_fk1 FOREIGN KEY REFEREBCES Maintenance(M_id)");
            stmt.executeUpdate("ALTER TABLE MSch " + "ADD CONSTRAINT MSch_pk PRIMARY KEY (Ms_iid, M_id)");
        } catch ( final SQLException e ) {
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
//    CREATE TABLE Maintenance(
//            M_id Integer(20) NOT NULL REFERENCES Service(sid),
//    M_name(20),
//    primary key(sid)
//);
    private static void Maintenance() {
        Statement stmt = null;
        try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system",
                "123")) {
            stmt.executeUpdate("CREATE TABLE Maintenance" + "(M_id Integer(20) NOT NULL REFERENCES Service(sid) PRIMARY KEY," +
                    "M_name VARCHAR(20)" );

        } catch (final SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (final Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt);
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
