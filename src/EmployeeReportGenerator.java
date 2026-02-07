import java.util.*;

public class EmployeeReportGenerator {

    public static void generateSalaryReport(Collection<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }

        double total = 0;
        Employee highest = null;
        Employee lowest = null;

        for (Employee e : employees) {
            total += e.getSalary();
            if (highest == null || e.getSalary() > highest.getSalary())
                highest = e;
            if (lowest == null || e.getSalary() < lowest.getSalary())
                lowest = e;
        }

        System.out.println("\n SALARY STATISTICS:");
        System.out.println("• Total Employees: " + employees.size());
        System.out.printf("• Total Salary: ₹%.2f%n", total);
        System.out.printf("• Average Salary: ₹%.2f%n", total / employees.size());
        System.out.printf("• Highest Salary: ₹%.2f (%s)%n",
                highest.getSalary(), highest.getName());
        System.out.printf("• Lowest Salary: ₹%.2f (%s)%n",
                lowest.getSalary(), lowest.getName());
    }

    public static void departmentSummary(Collection<Employee> employees) {
        Map<String, List<Employee>> deptMap = new HashMap<>();

        for (Employee e : employees) {
            deptMap.computeIfAbsent(
                    e.getDepartment(), k -> new ArrayList<>()).add(e);
        }

        System.out.println("\n DEPARTMENT SUMMARY:");
        for (String dept : deptMap.keySet()) {
            double avg = deptMap.get(dept)
                    .stream().mapToDouble(Employee::getSalary).average().orElse(0);
            System.out.printf("• %s: %d employees, Average: ₹%.2f%n",
                    dept, deptMap.get(dept).size(), avg);
        }
    }
}
