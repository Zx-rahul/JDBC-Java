package PreparedStatement;

import java.sql.*;
import java.util.Scanner;

public class BatchProcessing {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String query = "INSERT INTO employees (name, job_title, salary) VALUES (?, ?, ?)";//fetching query

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection Established Successfully !!");
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            Scanner sc=new Scanner(System.in);
            while (true){
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Job Title: ");
                String job_title = sc.nextLine();
                System.out.print("Salary: ");
                double salary= sc.nextDouble();
                preparedStatement.setString(1,name);
                preparedStatement.setString(2,job_title);
                preparedStatement.setDouble(3,salary);
                preparedStatement.addBatch();
                System.out.println("Add more values Y/N");
                String decision = sc.nextLine();
                if (decision.equalsIgnoreCase("N")){
                    break;
                }
            }

            int[] batchResult = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch Executed Successfully");



            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
