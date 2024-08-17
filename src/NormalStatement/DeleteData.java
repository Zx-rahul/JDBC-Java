package NormalStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/mydatabase";
        String username = "root";
        String password = "root";
        String query = "DELETE FROM employees where id = 9;";//deletion query
        try {//loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection con = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection established successfully");
            Statement smt = con.createStatement();//getting statement
            int rowsAffected = smt.executeUpdate(query);//deleting data
            if (rowsAffected > 0) {//if updated
                System.out.printf("Delete Successful %d row(s) affected.\n", rowsAffected);
            } else {//if not updated
                System.out.println("Deletion Failed!!");
            }

            smt.close();
            con.close();
            System.out.println("\nConnection demolished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
