package NormalStatement;


import java.sql.*;


public class BatchProcessing {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection Established Successfully !!");
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES ('vashu','HR manager',65000)");
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES ('karan','Cyber Security " +
                    "Analyst',90000)");
            statement.addBatch("INSERT INTO employees(name, job_title, salary) VALUES ('rishab','AI Developer'," +
                    "180000)");
            int[] batchResult=statement.executeBatch();
            connection.commit();
            System.out.println("Batch Executed Successfully!!");



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
