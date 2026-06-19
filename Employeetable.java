import java.sql.*;
public class Employeetable {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mits";
        String username = "root";
        String password = "root";
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            String query = "create table employee1 (" +
                           "emp_id int primary key, " +
                           "emp_name varchar(50), " +
                           "emp_jobrole varchar(50), " +
                           "emp_hiredate date, " +
                           "emp_salary decimal(10,2))";
            stmt.executeUpdate(query);
            System.out.println("Table employee1 created successfully.");
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}