import java.math.BigDecimal;
import java.math.RoundingMode;

public class Credit {
    private BigDecimal propertyValue;
    private BigDecimal creditAmount;
    private BigDecimal creditBalance;
    private BigDecimal annualRate;
    private BigDecimal monthlyRate;
    private int mortgageDuration;

    public BigDecimal getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(BigDecimal propertyValue) {
        this.propertyValue = propertyValue;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getCreditBalance() {
        return creditBalance;
    }

    public void setCreditBalance(BigDecimal creditBalance) {
        this.creditBalance = creditBalance;
    }

    public BigDecimal getAnnualRate() {
        return annualRate;
    }

    public void setAnnualRate(BigDecimal rate) {
        this.annualRate = rate.divide(BigDecimal.valueOf(100));

    }

    public BigDecimal getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(BigDecimal annualRate) {

        this.monthlyRate = annualRate.divide(BigDecimal.valueOf(12),10,RoundingMode.HALF_UP);
    }

    public int getMortgageDuration() {
        return mortgageDuration;
    }

    public void setMortgageDuration(int mortgageDuration) {
        this.mortgageDuration = mortgageDuration;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "propertyValue=" + propertyValue +
                ", creditAmount=" + creditAmount +
                ", mortgageDuration=" + mortgageDuration +
                '}';
    }
}
