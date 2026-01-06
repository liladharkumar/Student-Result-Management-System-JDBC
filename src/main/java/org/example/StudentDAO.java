package org.example;

import java.sql.*;
import java.util.Scanner;

public class StudentDAO {

    public void addStudent() throws Exception {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        System.out.print("Roll: ");
        int roll = sc.nextInt();
        sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Marks: ");
        int marks = sc.nextInt();

        String grade =
                marks >= 90 ? "A" :
                        marks >= 75 ? "B" :
                                marks >= 60 ? "C" : "D";

        String sql =
                "INSERT INTO student_result VALUES (?, ?, ?, ?)";
        PreparedStatement ps =
                con.prepareStatement(sql);

        ps.setInt(1, roll);
        ps.setString(2, name);
        ps.setInt(3, marks);
        ps.setString(4, grade);

        ps.executeUpdate();
        con.close();

        System.out.println("✔ Student Result Added");
    }

    public void viewAll() throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement("SELECT * FROM student_result");
        ResultSet rs = ps.executeQuery();

        System.out.println("Roll  Name   Marks  Grade");
        while (rs.next()) {
            System.out.println(
                    rs.getInt(1) + "    " +
                            rs.getString(2) + "    " +
                            rs.getInt(3) + "    " +
                            rs.getString(4)
            );
        }
        con.close();
    }

    public void searchByRoll(int roll) throws Exception {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "SELECT * FROM student_result WHERE roll=?");
        ps.setInt(1, roll);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println(
                    rs.getInt(1) + " " +
                            rs.getString(2) + " " +
                            rs.getInt(3) + " " +
                            rs.getString(4)
            );
        } else {
            System.out.println("❌ Result not found");
        }
        con.close();
    }

    public void updateMarks(int roll, int marks)
            throws Exception {

        String grade =
                marks >= 90 ? "A" :
                        marks >= 75 ? "B" :
                                marks >= 60 ? "C" : "D";

        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "UPDATE student_result SET marks=?, grade=? WHERE roll=?");

        ps.setInt(1, marks);
        ps.setString(2, grade);
        ps.setInt(3, roll);

        ps.executeUpdate();
        con.close();

        System.out.println("✔ Result Updated");
    }

    public void deleteResult(int roll)
            throws Exception {

        Connection con = DBConnection.getConnection();
        PreparedStatement ps =
                con.prepareStatement(
                        "DELETE FROM student_result WHERE roll=?");
        ps.setInt(1, roll);
        ps.executeUpdate();
        con.close();

        System.out.println("✔ Result Deleted");
    }
}

