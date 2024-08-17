package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RetrieveData {
    public static void main(String[] args) throws ClassNotFoundException{

        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
//      String query="Select * from employees where name = ? ";
        String query="Select * from employees where id = ? AND name = ?";//fetching query

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection= DriverManager.getConnection(url,username,password);//connecting with database
            System.out.println("Connection Established Successfully !!");

//            Statement statement=connection.createStatement();// Normal Statement
            PreparedStatement preparedStatement= connection.prepareStatement(query);//prepared statement;
            Scanner sc=new Scanner(System.in);
            System.out.print("ID: ");
            int id= sc.nextInt();
            System.out.print("NAME: ");
            String name= sc.next();

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);

            ResultSet resultSet=preparedStatement.executeQuery();
            // Check if the result set is empty
            if (!resultSet.isBeforeFirst()) {
                System.out.println("Sorry, no data available for the provided ID and Name.");
            }
            else {
            while (resultSet.next()){
                int result_id= resultSet.getInt("id");
                String result_name=resultSet.getString("name");
                String job_title=resultSet.getString("job_title");
                double salary=resultSet.getDouble("salary");
                System.out.println("\n==================================");
                System.out.printf("ID: %d\nNAME: %s\nJOB_TITLE: %s\nSALARY: %f", result_id, result_name, job_title,salary);
            }}

            resultSet.close();
            preparedStatement.close();
            connection.close();//demolished connection
            System.out.println("\nConnection Closed Successfully");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }
}
