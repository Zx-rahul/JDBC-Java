package PreparedStatement;

import java.io.*;
import java.sql.*;

public class ImageFetch {
    public static void main(String[] args) throws ClassNotFoundException{

        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";

        String folder_path="C:\\Users\\bwbsr\\OneDrive\\Desktop\\Super AI\\JDBC Libraries\\Fetched Images\\";
        String query="SELECT image_data from image_table where image_id = (?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection= DriverManager.getConnection(url,username,password);//connecting with database
            System.out.println("Connection Established Successfully !!");
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet= preparedStatement.executeQuery();

            if (resultSet.next()){
                byte[] image_data = resultSet.getBytes("image_data");
                String image_path=folder_path+"extracted.png";
                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);
            }else {
                System.out.println("Image not found");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
