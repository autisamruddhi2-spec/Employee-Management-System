import java.io.*;
import java.util.ArrayList;

public class EmployeeFileHandler {

    private static final String FILE_NAME = "employees.dat";

    public static void saveEmployees(ArrayList<Employee> employees) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
            System.out.println("Employee data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving employee data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Employee> loadEmployees() {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (ArrayList<Employee>) ois.readObject();
        } catch (Exception e) {
            System.out.println("No existing data found. Starting fresh.");
            return new ArrayList<>();
        }
    }
}
