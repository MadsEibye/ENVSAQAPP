package com.example.envsaqapp;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuerys {
    /*public String SQL = "SQL";

    // Connect to (remote) PostgreSQL Server at RIST8016
    //private final String url = "jdbc:postgresql://[http://rist8016.uni.au.dk::5555]:5433/appdev_jk";
    // Connect to local PostgreSQL server
    private final String url = "jdbc:postgresql://localhost:5432/postgres";

    private final String user = "postgres";
    private Connection conn;
    private String dbName;
    // Remote database password
    //private final String password = "jkatpg11";
    // Local database password
    private final String password = "admin";


    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
    /*
    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Log.d("SQLTEST", "Connection to database successfully established.");
            System.out.println("Connection to database successfully established.");
            System.out.println(""+ conn);

        } catch (SQLException | ClassNotFoundException e) {
            Log.d("SQLTEST", "Connection to database unsuccessfully established.");
            Log.d("SQLTEST", e.getMessage());
            System.out.println(e.getMessage());
        };
        return conn;

    }

    public static void viewTable(Connection con, String dbName)
            throws SQLException {

        Statement stmt = null;
        String query = "select COF_NAME, SUP_ID, PRICE, " +
                "SALES, TOTAL " +
                "from " + dbName + ".COFFEES";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String coffeeName = rs.getString("COF_NAME");
                int supplierID = rs.getInt("SUP_ID");
                float price = rs.getFloat("PRICE");
                int sales = rs.getInt("SALES");
                int total = rs.getInt("TOTAL");
                System.out.println(coffeeName + "\t" + supplierID +
                        "\t" + price + "\t" + sales +
                        "\t" + total);
            }
        } catch (SQLException e ) {
            System.out.println(e);
        } finally {
            if (stmt != null) { stmt.close(); }
        }
    }*/

    /**
     * @author postgresqltutorial.com
     */

        // Connect to (remote) PostgreSQL Server at RIST8016
        //private final String url = "jdbc:postgresql://[http://rist8016.uni.au.dk::5555]:5433/appdev_jk";
        // Connect to local PostgreSQL server
        private final String url = "jdbc:postgresql://10.0.2.2/postgres";
        private final String user = "postgres";
        // Remote database password
        //private final String password = "jkatpg11";
        // Local database password
        private final String password = "admin";


        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         */
        public Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
                Log.d("SQLTEST", "Connection to database successfully established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                Log.d("SQLTEST", "Connection to database unsuccessfully established.");
                Log.d("SQLTEST", e.getMessage());
            }

            return conn;
        }

    }

