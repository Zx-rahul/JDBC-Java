package NormalStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class RetrieveData {
    public static void main(String[] args) {

        String url="jdbc:mysql://127.0.0.1:3306/mydatabase";//url
        String username="root";//username
        String password="root";//password
        String query="SELECT * FROM employees;";//fetching query


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
            System.out.println("Drivers loaded successfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection con= DriverManager.getConnection(url,username,password);//connecting with database
            System.out.println("Connection established successfully");
            Statement smt=con.createStatement();//getting statement
            ResultSet rs=smt.executeQuery(query);//fetching data
            while (rs.next()){//traversing the table
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String job_title=rs.getString("job_title");
                double salary=rs.getDouble("salary");
                System.out.println("\n==================================");
                System.out.printf("ID: %d\nNAME: %s\nJOB_TITLE: %s\nSALARY: %f", id, name, job_title,salary);
            }

            rs.close();
            smt.close();
            con.close();
            System.out.println("\nConnection demolished successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}