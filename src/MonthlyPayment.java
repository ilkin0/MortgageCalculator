import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class MonthlyPayment {
    private int paymentNumber;
    private LocalDate date;
    private BigDecimal interest;
    private BigDecimal base;
    private BigDecimal totalMonthly;
    private BigDecimal creditBalance;

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getBase() {
        return base;
    }

    public void setBase(BigDecimal base) {
        this.base = base;
    }

    public BigDecimal getTotalMonthly() {
        return totalMonthly;
    }

    public void setTotalMonthly(BigDecimal totalMonthly) {
        this.totalMonthly = totalMonthly;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }


    public MonthlyPayment(int paymentNumber, LocalDate date, BigDecimal interest, BigDecimal base, BigDecimal totalMonthly) {
        this.paymentNumber = paymentNumber;
        this.date = date;
        this.interest = interest;
        this.base = base;
        this.totalMonthly = totalMonthly;
    }

    public MonthlyPayment() {
    }

    @Override
    public String toString() {
        return "MonthlyPayment{" +
                "paymentNumber=" + paymentNumber +
                ", date=" + date +
                ", interest=" + interest.setScale(2, RoundingMode.HALF_UP) +
                ", base=" + base.setScale(2, RoundingMode.HALF_UP) +
                ", totalMonthly=" + totalMonthly.setScale(2, RoundingMode.HALF_UP) +
                ", creditBalance=" + creditBalance.setScale(2, RoundingMode.HALF_UP) +
                '}' + "\n";
    }
}
