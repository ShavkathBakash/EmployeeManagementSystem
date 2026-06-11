public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        Employee emp1 =
                new Employee(
                        "Shaariq",
                        "IT",
                        35000
                );

        dao.addEmployee(emp1);

        System.out.println("\nEmployee Records:");

        dao.viewEmployees();
    }
}