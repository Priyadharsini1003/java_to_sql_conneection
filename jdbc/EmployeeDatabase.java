package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDatabase {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo"; 
        String user = "root"; 
        String password = "Priyoo@1003"; // 

        // Employee data to be inserted
        int[] empCodes = {101, 102, 103, 104, 105};
        String[] empNames = {"Jenny", "Jacky", "Joe", "John", "Shameer"};
        int[] empAges = {25, 30, 20, 40, 25};
        int[] empSalaries = {10000, 20000, 40000, 80000, 90000};

        //statement
        String sql = "INSERT INTO employee_details(empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            pstmt = conn.prepareStatement(sql);

            // Insert each employee data
            for (int i = 0; i < empCodes.length; i++) {
                pstmt.setInt(1, empCodes[i]);
                pstmt.setString(2, empNames[i]);
                pstmt.setInt(3, empAges[i]);
                pstmt.setInt(4, empSalaries[i]);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}

