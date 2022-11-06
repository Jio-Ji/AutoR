package edu.ncsu.csc.ROW;

import edu.ncsu.csc.AUTOR.*;

import java.sql.*;

public class Insert {

    public Insert() {

    }

    public static void user_pw_Insert(long id, int r) {
        Statement stmt = null;
        try (
                Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe", "system",
                        "123" )) {

            stmt = conn.createStatement();
            // check if the id is already used
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM USERPW");
            while (resultSet.next()) {
                long USERPW_id = resultSet.getLong("u_id");
                if (id == USERPW_id) {
                    System.err.println("This ID has already been registered.\n");
                    return;
                }
            }

            String pwd = "";
            switch (r) {
                case 1:
                    System.out.println("The admin functions can just be accessible directly without logging in first.\n");
                    return;
                case 2:
                    System.out.println("未实现");
                    // 去对应的表里找id，找到了记录last name，往后走
                    // 找不到Print invalid message，然后return;
                    break;
                case 3:
                    System.out.println("未实现");
                    // 去对应的表里找id，找到了记录last name，往后走
                    // 找不到Print invalid message，然后return;
                    break;
                case 4:
                    System.out.println("未实现");
                    // 去对应的表里找id，找到了记录last name，往后走
                    // 找不到Print invalid message，然后return;
                    break;
                case 5:
                    System.out.println("未实现");
                    // 去对应的表里找id，找到了记录last name，往后走
                    // 找不到Print invalid message，然后return;
                    break;
                default:
                    System.out.println("Invalid role.\n");
                    return;
            }


            // check the ROLE of the user
            stmt.executeUpdate("INSERT INTO USERPW (u_id, PWD, ROLE)" +
                                    "VALUES (" + String.valueOf(id) + ", " + pwd + ", " + String.valueOf(r) + ")");

            Object o = new Object();
            o.wait(100); // 等待数据库信息

            System.out.println("未实现 Success or not?\n");
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
