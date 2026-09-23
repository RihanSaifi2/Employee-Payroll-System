import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class EmployeeDAO {

    // =========================
    // ADD EMPLOYEE
    // =========================
    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employees "
                + "(name, employee_type, monthly_salary, hours_worked, hourly_rate) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            // Employee name
            ps.setString(1, employee.getName());

            // Full-Time Employee
            if (employee instanceof FullTimeEmployee) {

                FullTimeEmployee fullTime = (FullTimeEmployee) employee;

                ps.setString(2, "FULL_TIME");

                // Monthly salary
                ps.setDouble(3, fullTime.getMonthlySalary());

                // Not applicable for full-time
                ps.setNull(4, Types.INTEGER);
                ps.setNull(5, Types.DOUBLE);
            }

            // Part-Time Employee
            else if (employee instanceof PartTimeEmployee) {

                PartTimeEmployee partTime = (PartTimeEmployee) employee;

                ps.setString(2, "PART_TIME");

                // Monthly salary not applicable
                ps.setNull(3, Types.DOUBLE);

                // Hours worked
                ps.setInt(4, partTime.getHoursWorked());

                // Hourly rate
                ps.setDouble(5, partTime.getHourlyRate());
            }

            ps.executeUpdate();

            System.out.println("Employee added successfully.");

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());
        }
    }


    // =========================
    // DISPLAY ALL EMPLOYEES
    // =========================
    public void displayEmployees() {

        String sql = "SELECT * FROM employees";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (!rs.isBeforeFirst()) {

                System.out.println("No employees found.");

                return;
            }

            System.out.println("\n========== EMPLOYEES ==========");

            while (rs.next()) {

                int id = rs.getInt("id");

                String name = rs.getString("name");

                String type = rs.getString("employee_type");

                System.out.println("ID: " + id);

                System.out.println("Name: " + name);

                System.out.println("Type: " + type);

                // Full-Time
                if (type.equals("FULL_TIME")) {

                    double salary = rs.getDouble("monthly_salary");

                    System.out.println("Monthly Salary: " + salary);
                }

                // Part-Time
                else if (type.equals("PART_TIME")) {

                    int hours = rs.getInt("hours_worked");

                    double rate = rs.getDouble("hourly_rate");

                    double salary = hours * rate;

                    System.out.println("Hours Worked: " + hours);

                    System.out.println("Hourly Rate: " + rate);

                    System.out.println("Salary: " + salary);
                }

                System.out.println("-----------------------------");
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());
        }
    }


    // =========================
    // SEARCH EMPLOYEE
    // =========================
    public void searchEmployee(int id) {

        String sql = "SELECT * FROM employees WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (
                    ResultSet rs = ps.executeQuery()
            ) {

                if (rs.next()) {

                    int employeeId = rs.getInt("id");

                    String name = rs.getString("name");

                    String type = rs.getString("employee_type");

                    System.out.println("\n========== EMPLOYEE FOUND ==========");

                    System.out.println("ID: " + employeeId);

                    System.out.println("Name: " + name);

                    System.out.println("Type: " + type);

                    // Full-Time
                    if (type.equals("FULL_TIME")) {

                        double salary = rs.getDouble("monthly_salary");

                        System.out.println("Monthly Salary: " + salary);
                    }

                    // Part-Time
                    else if (type.equals("PART_TIME")) {

                        int hours = rs.getInt("hours_worked");

                        double rate = rs.getDouble("hourly_rate");

                        double salary = hours * rate;

                        System.out.println("Hours Worked: " + hours);

                        System.out.println("Hourly Rate: " + rate);

                        System.out.println("Salary: " + salary);
                    }

                } else {

                    System.out.println("Employee not found.");
                }
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());
        }
    }


    // =========================
    // DELETE EMPLOYEE
    // =========================
    public void deleteEmployee(int id) {

        String sql = "DELETE FROM employees WHERE id = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee deleted successfully.");

            } else {

                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {

            System.out.println("Database Error: " + e.getMessage());
        }
    }
}