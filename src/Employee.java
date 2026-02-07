import java.io.Serializable;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String department;
    private String position;
    private double salary;
    private String joinDate;

    public Employee(String id, String name, String department,
                    String position, double salary, String joinDate) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.joinDate = joinDate;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public String getJoinDate() { return joinDate; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format(
                "%-8s %-15s %-15s %-18s ₹%.2f %s",
                id, name, department, position, salary, joinDate
        );
    }
}
