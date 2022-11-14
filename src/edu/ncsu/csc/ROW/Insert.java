package edu.ncsu.csc.ROW;

import edu.ncsu.csc.AUTOR.*;

import java.sql.*;

public class Insert {

    public Insert() {

    }

    public static void user_pw_Insert(long id, String pwd, int r) {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            // check if the id is already used
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM user_pw");
            while (resultSet.next()) {
                long USERPW_id = resultSet.getLong("u_id");
                if (id == USERPW_id) {
                    System.err.println("This ID has already been registered.\n");
                    return;
                }
            }

            switch (r) {
                case 1:
                    //System.out.println("The admin functions can just be accessible directly without logging in first.\n");
                    //return;
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid role.\n");
                    return;
            }


            // check the ROLE of the user
            stmt.executeUpdate(
                    "insert into user_pw values ('" + id + "', '" + pwd + "', " + r + ")" );
            //Object o = new Object();

            //System.out.println("未实现 Success or not?\n");
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
