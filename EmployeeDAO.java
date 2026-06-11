import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeDAO {

    public void addEmployee(Employee emp) {

        try {
            Connection con = DBConnection.getConnection();

            String query =
                    "INSERT INTO employee(name, department, salary) VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getDepartment());
            ps.setDouble(3, emp.getSalary());

            ps.executeUpdate();

            System.out.println("Employee Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewEmployees() {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM employee";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                System.out.println(
                        rs.getInt("id") + " " +
                        rs.getString("name") + " " +
                        rs.getString("department") + " " +
                        rs.getDouble("salary")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}