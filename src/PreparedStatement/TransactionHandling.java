package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionHandling {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";//fetching query
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection = DriverManager.getConnection(url, username, password);//connecting with database
            System.out.println("Connection Established Successfully !!");
            connection.setAutoCommit(false);
            try {
                PreparedStatement withdrawStatement = connection.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = connection.prepareStatement(depositQuery);
                withdrawStatement.setInt(1,500);
                withdrawStatement.setString(2,"account456");
                depositStatement.setInt(1,500);
                depositStatement.setString(2,"account837");
                int rowsAffectedWithdrawal = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();
                if (rowsAffectedWithdrawal>0 && rowsAffectedDeposit>0){
                    connection.commit();
                    System.out.println("Transaction Successful !!");
                }else {
                    connection.rollback();
                    System.out.println("Transaction Failed !!");
                }
                withdrawStatement.close();
                depositStatement.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
