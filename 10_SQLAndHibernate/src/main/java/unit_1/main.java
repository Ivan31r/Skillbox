package unit_1;

import com.mysql.jdbc.Driver;


import java.sql.*;

public class main {
    private static final String URL = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "rootpass";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT course_name, count(course_name)/(MAX(MONTH(subscription_date))-(MIN(MONTH(subscription_date))-1)) as avg_purchase_per_month FROM purchaselist group by course_name");
            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                double avgPerMonth = Double.parseDouble(resultSet.getString("avg_purchase_per_month"));
                System.out.println(courseName + " - " + avgPerMonth);
            }
        }
        catch (Exception e){
            e.getMessage();
        }
    }

}
