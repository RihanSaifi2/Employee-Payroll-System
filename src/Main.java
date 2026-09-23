import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeDAO dao = new EmployeeDAO();

        while (true) {

            System.out.println("    PAYROLL MANAGEMENT SYSTEM");

            System.out.println("1. Add Full-Time Employee");

            System.out.println("2. Add Part-Time Employee");

            System.out.println("3. View All Employees");

            System.out.println("4. Search Employee");

            System.out.println("5. Delete Employee");

            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            sc.nextLine();


            switch (choice) {

                // ADD FULL TIME
                case 1:

                    System.out.print("Enter employee name: ");

                    String fullName = sc.nextLine();

                    System.out.print("Enter monthly salary: ");

                    double salary = sc.nextDouble();

                    Employee fullTime = new FullTimeEmployee(0, fullName, salary);

                    dao.addEmployee(fullTime);

                    break;


                // ADD PART TIME
                case 2:

                    System.out.print("Enter employee name: ");

                    String partName = sc.nextLine();

                    System.out.print("Enter hours worked: ");

                    int hours = sc.nextInt();

                    System.out.print("Enter hourly rate: ");

                    double rate = sc.nextDouble();

                    Employee partTime = new PartTimeEmployee(0, partName, hours, rate);

                    dao.addEmployee(partTime);

                    break;


                // DISPLAY
                case 3:

                    dao.displayEmployees();

                    break;


                // SEARCH
                case 4:

                    System.out.print("Enter employee ID: ");

                    int searchId = sc.nextInt();

                    dao.searchEmployee(searchId);

                    break;


                // DELETE
                case 5:

                    System.out.print("Enter employee ID: ");

                    int deleteId = sc.nextInt();

                    dao.deleteEmployee(deleteId);

                    break;


                // EXIT
                case 6:

                    System.out.println("Thank you for using Payroll System.");

                    sc.close();

                    return;


                default:

                    System.out.println("Invalid choice!");
            }
        }
    }
}