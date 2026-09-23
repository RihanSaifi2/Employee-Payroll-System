public class FullTimeEmployee extends Employee {

    private double monthlySalary;

    public FullTimeEmployee(
            int id,
            String name,
            double monthlySalary) {

        super(id, name);
        this.monthlySalary = monthlySalary;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}