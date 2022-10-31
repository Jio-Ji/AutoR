package edu.ncsu.csc.TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DROP {
    public static void main ( final String[] args ) {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            System.out.println(1);
            stmt.executeUpdate("DROP TABLE USERPW ");
            System.out.println(2);
            stmt.executeUpdate("DROP TABLE rser ");
            System.out.println(3);
            stmt.executeUpdate("DROP TABLE TSER ");
            System.out.println(4);
            stmt.executeUpdate("DROP TABLE repair ");
            System.out.println(5);
            stmt.executeUpdate("DROP TABLE INDSERV ");
            System.out.println(6);
            stmt.executeUpdate("DROP TABLE service ");
            System.out.println(7);
            stmt.executeUpdate("DROP TABLE invoice ");
            System.out.println(8);
            stmt.executeUpdate("DROP TABLE associate ");
            System.out.println(9);
            stmt.executeUpdate("DROP TABLE customer ");
            System.out.println(10);
            stmt.executeUpdate("DROP TABLE vehicle ");
            System.out.println(11);
            stmt.executeUpdate("DROP TABLE emp ");
            System.out.println(12);
            stmt.executeUpdate("DROP TABLE center ");
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
