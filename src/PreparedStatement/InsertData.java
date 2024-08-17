package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class InsertData {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO employees(id, name, job_title, salary) VALUES(?,?,?,?)";//fetching query

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection Established Successfully !!");

//            Statement statement=connection.createStatement();// Normal Statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);//prepared statement;
            Scanner sc=new Scanner(System.in);

            System.out.print("ID: ");
            preparedStatement.setInt(1,sc.nextInt());

            System.out.print("NAME: ");
            preparedStatement.setString(2,sc.next());

            System.out.print("JOB_TITLE: ");
            preparedStatement.setString(3, sc.next());

            System.out.print("SALARY: ");
            preparedStatement.setInt(4,sc.nextInt());



            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("Data Inserted Successfully !!");
            }else {
                System.out.println("Data Inserted Failed !!");
            }


            preparedStatement.close();
            connection.close();//demolished connection
            System.out.println("\n Connection Closed Successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
