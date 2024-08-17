package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteData {
    public static void main(String[] args) {

        String url = "jdbc:mysql://127.0.0.1:3306/mydatabase";
        String username = "root";
        String password = "root";
        String query = "DELETE FROM employees WHERE id = ?;";//deletion query
        try {//loading driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection established successfully");
            PreparedStatement preparedStatement = connection.prepareStatement(query);//getting statement
            Scanner sc=new Scanner(System.in);
            System.out.print("ID: ");
            preparedStatement.setInt(1,sc.nextInt());
            int rowsAffected = preparedStatement.executeUpdate();//deleting data
            if (rowsAffected > 0) {//if updated
                System.out.printf("Delete Successful %d row(s) affected.\n", rowsAffected);
            } else {//if not updated
                System.out.println("Deletion Failed!!");
            }

            preparedStatement.close();
            connection.close();
            System.out.println("\nConnection demolished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
