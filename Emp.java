import java.sql.*;
import java.util.Scanner;
public class Emp {
          static Scanner sc = new Scanner(System.in);
          static String url = "jdbc:mysql://localhost:3306/mits";
          static String user = "root";
          static String pass = "root";
          public static void main(String[] args) {
            while (true) {
                System.out.println(" ");
                
                System.out.println("1/ --> To Add Employee");
                System.out.println("2/ --> To Display Employee Data");
                System.out.println("3/ --> To Update Salary");
                System.out.println("4/ --> To Delete Employee");
                System.out.println("5/ --> To Display Column");
                System.out.println("6/ --> To Exit");
                System.out.println("Choose an Option (1-6) : ");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        displayDetails();
                        break;
                    case 3:
                        updateSalary();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        columnDetails();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Enter 1-6.");
                }
            }
        }
            public static void addEmployee(){
                try(Connection conn = DriverManager.getConnection(url, user, pass)){
                    System.out.println("Enter emp_id");
                    int emp_id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter emp_name");
                    String emp_name = sc.nextLine();
                    System.out.println("Enter emp_joberole");
                    String emp_jobrole = sc.nextLine();
                    System.out.print("Enter emp_hiredate (YYYY-MM-DD): ");
                    String hireDate = sc.nextLine();
                    System.out.print("Enter emp_salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    String sql = "INSERT INTO emp (empIid, emp_name, emp_jobrole, emp_hiredate, emp_salary) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, emp_id);
                    pstmt.setString(2, emp_name);
                    pstmt.setString(3, emp_jobrole);
                    pstmt.setString(4, hireDate);
                    pstmt.setDouble(5, salary);
                    int rows = pstmt.executeUpdate();
                    if (rows > 0) System.out.println("Employee added successfully!");
                } catch (SQLException e) {
            System.out.println("Error adding employee: " + e.getMessage());
                }
            }
            public static void displayDetails(){
                try (Connection conn = DriverManager.getConnection(url, user, pass);
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM emp")){
                while (rs.next());
                        System.out.println(rs.getInt("empIid") + " | " +
                        rs.getString("emp_name") + " | " +
                        rs.getString("emp_jobrole") + " | " +
                        rs.getDate("emp_hiredate") + " | " +
                        rs.getDouble("emp_salary"));
                }catch (SQLException e) {
                        System.out.println("Error displaying data: " + e.getMessage());
                    }
                }
            public static void updateSalary() {
                    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                        System.out.print("Enter empId to update: ");
                        int empId = sc.nextInt();
                        System.out.print("Enter new salary: ");
                        double newSalary = sc.nextDouble();
                        sc.nextLine();
                        String sql = "UPDATE emp SET emp_salary = ? WHERE empIid = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setDouble(1, newSalary);
                        pstmt.setInt(2, empId);
                        int rows = pstmt.executeUpdate();
                        if (rows > 0) System.out.println("Salary updated successfully!");
                        else System.out.println("Employee ID not found!");
                    } catch (SQLException e) {
                        System.out.println("Error updating salary: " + e.getMessage());
                    }
            }
            public static void deleteEmployee(){
                    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                        System.out.print("Enter empId to delete: ");
                        int empId = sc.nextInt();
                        sc.nextLine();
                        String sql = "DELETE FROM emp WHERE empIid = ?";
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setInt(1, empId);

                        int rows = pstmt.executeUpdate();
                        if (rows > 0) System.out.println("Employee deleted successfully!");
                        else System.out.println("Employee ID not found!");
                    } catch (SQLException e) {
                        System.out.println("Error deleting employee: " + e.getMessage());
                    }
                }
            
            public static void columnDetails(){
                    try(Connection conn = DriverManager.getConnection(url, user, pass)){
                        System.out.println("Enter column name(empIid,emp_name,emp_jobrole,emp_hiredate,emp_salary");
                        String colName = sc.nextLine();
                        sc.nextLine();
                        String sql = "SELECT" + colName + "FROM p.emp";
                        try(Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(sql);){
                            System.out.println("Column" + colName +" data as follows");
                            while(rs.next()){
                        System.out.println(rs.getString(1));
                    }
                }
            }catch (SQLException e){
                System.out.println("An Invalid column name!! ");
                e.printStackTrace();
            }

        }
    }
