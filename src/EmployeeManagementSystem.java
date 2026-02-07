import java.util.*;

public class EmployeeManagementSystem {

    private static ArrayList<Employee> employees;
    private static HashMap<String, Employee> employeeMap;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        employees = EmployeeFileHandler.loadEmployees();
        employeeMap = new HashMap<>();

        for (Employee e : employees) {
            employeeMap.put(e.getId(), e);
        }

        while (true) {
            showMenu();
            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice!");
                continue;
            }

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> viewEmployees();
                case 3 -> searchEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> generateReports();
                case 7 -> EmployeeFileHandler.saveEmployees(employees);
                case 8 -> loadFromFile();
                case 9 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
        System.out.println("1. Add New Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Generate Reports");
        System.out.println("7. Save to File");
        System.out.println("8. Load from File");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addEmployee() {
        System.out.print("Enter Employee ID: ");
        String id = sc.nextLine();

        if (employeeMap.containsKey(id)) {
            System.out.println("Employee ID already exists!");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Department: ");
        String dept = sc.nextLine();
        System.out.print("Enter Position: ");
        String pos = sc.nextLine();

        System.out.print("Enter Salary: ");
        double salary;
        try {
            salary = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid salary!");
            return;
        }

        Employee e = new Employee(
                id, name, dept, pos, salary,
                java.time.LocalDate.now().toString()
        );

        employees.add(e);
        employeeMap.put(id, e);

        System.out.println("Employee added successfully!");
        EmployeeFileHandler.saveEmployees(employees);
    }

    private static void viewEmployees() {
        System.out.println("\n=== ALL EMPLOYEES ===");
        System.out.printf("%-8s %-15s %-15s %-18s %-10s %s%n",
                "ID", "Name", "Department", "Position", "Salary", "Join Date");
        System.out.println("--------------------------------------------------------------------------------");

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private static void searchEmployee() {
        System.out.println("\n1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Department");
        int ch = Integer.parseInt(sc.nextLine());

        System.out.print("Enter value: ");
        String value = sc.nextLine();

        boolean found = false;

        for (Employee e : employees) {
            if ((ch == 1 && e.getId().equalsIgnoreCase(value)) ||
                (ch == 2 && e.getName().toLowerCase().contains(value.toLowerCase())) ||
                (ch == 3 && e.getDepartment().equalsIgnoreCase(value))) {

                found = true;
                System.out.println(
                        "ID: " + e.getId() +
                        ", Name: " + e.getName() +
                        ", Dept: " + e.getDepartment() +
                        ", Position: " + e.getPosition() +
                        ", Salary: ₹" + e.getSalary() +
                        ", Joined: " + e.getJoinDate()
                );
            }
        }

        if (!found) {
            System.out.println("No employee found.");
        }
    }

    private static void updateEmployee() {
        System.out.print("Enter Employee ID to update: ");
        String id = sc.nextLine();

        Employee e = employeeMap.get(id);
        if (e == null) {
            System.out.println("Employee not found!");
            return;
        }

        System.out.print("Enter new salary: ");
        try {
            e.setSalary(Double.parseDouble(sc.nextLine()));
            System.out.println("Employee updated successfully!");
        } catch (NumberFormatException ex) {
            System.out.println("Invalid salary input!");
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        String id = sc.nextLine();

        Employee e = employeeMap.remove(id);
        if (e != null) {
            employees.remove(e);
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void generateReports() {
        System.out.println("\n1. Department-wise Summary");
        System.out.println("2. Salary Statistics");
        int ch = Integer.parseInt(sc.nextLine());

        if (ch == 1)
            EmployeeReportGenerator.departmentSummary(employees);
        else
            EmployeeReportGenerator.generateSalaryReport(employees);
    }

    private static void loadFromFile() {
        employees = EmployeeFileHandler.loadEmployees();
        employeeMap.clear();
        for (Employee e : employees) {
            employeeMap.put(e.getId(), e);
        }
        System.out.println("Employee data loaded from file.");
    }
}
