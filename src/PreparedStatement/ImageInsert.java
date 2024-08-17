package PreparedStatement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageInsert {
    public static void main(String[] args) throws ClassNotFoundException{

        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="root";
        String image_path="C:\\Users\\bwbsr\\OneDrive\\Pictures\\Screenshots\\Screenshot 2024-08-05 132852.png";
        String query="insert into image_table (image_data) VALUES (?)";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//loading driver
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {//establishing connection
            Connection connection= DriverManager.getConnection(url,username,password);//connecting with database
            System.out.println("Connection Established Successfully !!");
            FileInputStream file=new FileInputStream(image_path);
            byte[] imageData=new byte[file.available()];
            file.read(imageData);
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected>0){
                System.out.println("Data Inserted Successfully !!");
            }else {
                System.out.println("Data Inserted Failed !!");
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
