import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Summary {
    private Credit property;
    private Customer customer;
    private BigDecimal initialPayment;
    private BigDecimal totalInterest;
    private BigDecimal totalBase;
    private BigDecimal totalAmount;

    private List<MonthlyPayment> paymentTable;

    public Credit getProperty() {
        return property;
    }

    public void setProperty(Credit property) {
        this.property = property;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(BigDecimal initialPayment) {
        this.initialPayment = initialPayment;
    }

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public void setTotalInterest(BigDecimal totalInterest) {
        this.totalInterest = totalInterest;
    }

    public BigDecimal getTotalBase() {
        return totalBase;
    }

    public void setTotalBase(BigDecimal totalBase) {
        this.totalBase = totalBase;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<MonthlyPayment> getPaymentTable() {
        return paymentTable;
    }

    public void setPaymentTable(List<MonthlyPayment> paymentTable) {
        this.paymentTable = paymentTable;
    }


    public Summary() {
        this.paymentTable = new ArrayList<>();
        this.totalInterest = BigDecimal.ZERO;
        this.totalBase = BigDecimal.ZERO;
        this.totalAmount = BigDecimal.ZERO;
    }


    @Override
    public String toString() {
        return "Summary{" +
                "property=" + property +
                ", customer=" + customer +
                ", initialPayment=" + initialPayment +
                ", totalInterest=" + totalInterest.setScale(2, RoundingMode.HALF_UP) +
                ", totalBase=" + totalBase.setScale(2, RoundingMode.HALF_UP) +
                ", totalAmount=" + totalAmount.setScale(2, RoundingMode.HALF_UP) +
                ", paymentTable=" + paymentTable +
                '}';
    }
}
