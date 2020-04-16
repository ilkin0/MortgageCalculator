import java.math.BigDecimal;
import java.math.RoundingMode;

public class InsufficientSalary extends Exception {
    private BigDecimal salary;
    private BigDecimal monthlyPayment;

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public InsufficientSalary(BigDecimal salary, BigDecimal monthlyPayment) {
        this.salary = salary;
        this.monthlyPayment = monthlyPayment;
    }

    public InsufficientSalary(String message, BigDecimal salary, BigDecimal monthlyPayment) {
        super(message);
        this.salary = salary;
        this.monthlyPayment = monthlyPayment.setScale(2, RoundingMode.HALF_UP);
    }
}
