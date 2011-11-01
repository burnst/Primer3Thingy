package util;

import play.Logger;

import java.sql.*;
import java.util.*;


public class DatabaseUtil {

    public static Connection getConnection(String dbms, String serverName, String portNumber, String databaseName, String userName, String password) throws SQLException {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", userName);
        connectionProps.put("password", password);

        Logger.info("Database type: " + dbms);

        if ("MYSQL".equals(dbms.trim().toUpperCase())) {

            Logger.info("Connecting with a mysql database");
            if ((portNumber == null) || (portNumber.isEmpty()))
                portNumber = "3306";

            String uri = "jdbc:" + dbms + "://" + serverName + ":" + portNumber + "?";
            Logger.info("Connection URI: " + uri);

            conn = DriverManager.getConnection("jdbc:" + dbms + "://" + serverName + ":" + portNumber + "?", connectionProps);

        } else if ("ORACLE".equals(dbms.trim().toUpperCase())) {

            Logger.info("Connecting with a oracle database");
            if ((portNumber == null) || (portNumber.isEmpty()))
                portNumber = "1521";
            String uri = "jdbc:" + dbms + ":thin:@" + serverName + ":" + portNumber + ":" + databaseName;
            Logger.info("Connection URI: " + uri);
            conn = DriverManager.getConnection(uri, connectionProps);

        } else if ("SQLSERVER".equals(dbms.trim().toUpperCase())) {

            // jdbc:jtds:sqlserver://itsnt344.iowa.uiowa.edu:1433;DatabaseName=DWPROD
            Logger.info("Connecting with a sqlserver database");
            if ((portNumber == null) || (portNumber.isEmpty()))
                portNumber = "1433";

            String uri = "jdbc:jtds:" + dbms + "://" + serverName + ":" + portNumber;
            Logger.info("Connection URI: " + uri);

            conn = DriverManager.getConnection("jdbc:jtds:" + dbms + "://" + serverName + ":" + portNumber, connectionProps);

        } else if ("POSTGRES".equals(dbms.trim().toUpperCase())) {

            // Need to implement

        }
        Logger.info("Connected to database");
        return conn;
    }

    /**
     * Test Validity of JDBC Installation
     *
     * @param conn     a JDBC connection object
     * @param dbVendor db vendor {"oracle", "mysql" }
     * @return true if a given connection object is a valid one; otherwise return
     *         false.
     * @throws Exception Failed to determine if a given connection is valid.
     */
    public static boolean isValidConnection(Connection conn, String dbVendor) {
        try {
            if (conn == null)
                return false;

            if (conn.isClosed())
                return false;

            // depends on the vendor of the database:
            //
            // for MySQL database:
            // you may use the connection object
            // with query of "select 1"; if the
            // query returns the result, then it
            // is a valid Connection object.
            //
            // for Oracle database:
            // you may use the Connection object
            // with query of "select 1 from dual"; if
            // the query returns the result, then it
            // is a valid Connection object.
            if (dbVendor.equalsIgnoreCase("mysql"))
                return testConnection(conn, "select 1");

            else if (dbVendor.equalsIgnoreCase("oracle"))
                return testConnection(conn, "select 1 from dual");

            else
                return false;
        } catch (Exception e) {
            return false;
        }


    }

    /**
     * Test Validity of a Connection
     *
     * @param conn  a JDBC connection object
     * @param query a sql query to test against db connection
     * @return true if a given connection object is a valid one; otherwise return
     *         false.
     */
    private static boolean testConnection(Connection conn, String query) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            if (stmt == null) {
                return false;
            }

            rs = stmt.executeQuery(query);
            if (rs == null) {
                return false;
            }

            // connection object is valid: you were able to
            // connect to the database and return something useful.
            if (rs.next()) {
                return true;
            }

            // there is no hope any more for the validity
            // of the Connection object
            return false;
        } catch (Exception e) {
            // something went wrong: connection is bad
            return false;
        } finally {
            // close database resources
            try {
                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public List<Map<String, String>> executeQuery(Connection conn, String query) {
        Logger.info("Executing query");

        Statement s = null;
        ResultSet rs = null;
        try {
            Logger.info("Attempting to get Connection");
            Logger.info("Retrieved Connection");

            s = conn.createStatement();
            Logger.info("Statement created: " + s.toString());

            rs = s.executeQuery(query);
            Logger.info("ResultSet columns retrieved: " + rs.getMetaData().getColumnCount());
            Logger.info("ResultSet columns retrieved: " + rs);

            if (rs != null) {
                List<Map<String, String>> members = new ArrayList<Map<String, String>>();
                String columnLabel = null;
                String value = null;
                try {
                    while (rs.next()) {
                        Map<String, String> results = new HashMap<String, String>();
                        ResultSetMetaData rsmd = rs.getMetaData();
                        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                            try {
                                columnLabel = rsmd.getColumnLabel(i);
                                value = rs.getString(columnLabel);
                                if (value != null) {
                                    results.put(columnLabel, value);
                                }
                            }
                            catch (Exception ex) {
                                Logger.error("Could not set property (" + columnLabel + "), ex: " + ex);
                            }
                        }
                        members.add(results);
                    }

                    return members;

                } catch (SQLException sql){
                    return new ArrayList<Map<String,String>>();
                }
            }

        } catch (SQLException sqe) {
            Logger.error("Connection and query failed.", sqe);
            DatabaseUtil.release(rs, s, conn);
            return null;
        } finally {
            DatabaseUtil.release(rs, s, conn);
        }

        return null;
    }
    
    public static void release(ResultSet rs,
                               Statement st,
                               Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            }
            catch (SQLException e) {
                Logger.warn("DAOUtil.release()", e);
            }
        }

        if (st != null) {
            try {
                st.close();
            }
            catch (SQLException e) {
                Logger.warn("DAOUtil.release()", e);
            }
        }

        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                Logger.warn("DAOUtil.release()", e);
            }
        }
    }

}
