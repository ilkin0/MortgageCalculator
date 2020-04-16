import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.sql.*;
import java.time.LocalDate;

public class Calculator {
    public Summary calculator(Credit credit, Customer customer) throws InsufficientSalary, ExceededMortgageDuration, ExceededAge {

//        mortgage duration must not be exceed 300 month
        if (credit.getMortgageDuration() > Constants.MAX_MORTGAGE_DUR) {
            throw new ExceededMortgageDuration(
                    "Due to exceeded mortgage duration mortgage application has been declined. ",
                    credit.getMortgageDuration(),
                    Constants.MAX_MORTGAGE_DUR
            );
        }

        if (customer.getAge() > Constants.MAXIMUM_AGE_FOR_MORTGAGE || customer.getAge() < Constants.MINIMUM_AGE_FOR_MORTGAGE) {
            throw new ExceededAge(
                    "Due to age is not between 18 and 65, mortgage application was canceled. ",
                    customer.getAge(),
                    Constants.MAXIMUM_AGE_FOR_MORTGAGE,
                    Constants.MINIMUM_AGE_FOR_MORTGAGE
            );
        }

        Summary summary = new Summary();
        summary.setInitialPayment(calculateInitialPayment(credit.getPropertyValue()));

        summary.setCustomer(customer);
        summary.setProperty(credit);

        //Formula to calculate  monthly payments manually: M= P[r(1+r)^n/((1+r)^n)-1)]
        //Dedicate initial payment from total house value
        BigDecimal creditAmount = credit.getPropertyValue().
                subtract(calculateInitialPayment(credit.getPropertyValue()));
//        (1+r)^n
        BigDecimal compoundInterest = (credit.getMonthlyRate().add(BigDecimal.ONE)).pow(credit.getMortgageDuration());

//        r(1+r)^n/((1+r)^n)-1)
        BigDecimal discountFactor = ((compoundInterest).multiply(credit.getMonthlyRate())).
                divide((compoundInterest).subtract(BigDecimal.ONE), RoundingMode.HALF_UP);

//        P[r(1+r)^n/((1+r)^n)-1)]
        BigDecimal totalAmountMonthly = creditAmount.multiply(discountFactor, new MathContext(10));

//        Borrower's monthly loan payment should not be more than 70% of the total monthly income
        if (totalAmountMonthly.compareTo(customer.getSalary().multiply(BigDecimal.valueOf(0.7))) > 0) {
            throw new InsufficientSalary(
                    "Due to amount of monthly payment exceeded net monthly salary, " +
                            "mortgage application has been declined. ",
                    customer.calculateMaxPaymentAmount(),
                    totalAmountMonthly
            );
        }


        credit.setCreditAmount(creditAmount);
        credit.setCreditBalance(creditAmount);

        for (int i = 0; i < credit.getMortgageDuration(); i++) {

            MonthlyPayment monthlyPayment = new MonthlyPayment();
            monthlyPayment.setPaymentNumber(i);
            monthlyPayment.setDate(LocalDate.now().plusMonths(i));
            monthlyPayment.setTotalMonthly(totalAmountMonthly);

            monthlyPayment.setInterest(calculateInterest(credit));
            monthlyPayment.setBase(calculateBase(monthlyPayment));

            credit.setCreditBalance(calculateBalance(credit, monthlyPayment));
            monthlyPayment.setCreditBalance(credit.getCreditBalance());


            summary.getPaymentTable().add(monthlyPayment);
            summary.setTotalInterest(summary.getTotalInterest().add(monthlyPayment.getInterest(), new MathContext(10)));
            summary.setTotalBase(summary.getTotalBase().add(monthlyPayment.getBase(), new MathContext(10)));
            summary.setTotalAmount(summary.getTotalBase().add(summary.getTotalInterest(), new MathContext(10)));

        }

        return summary;
    }

    private BigDecimal calculateInterest(Credit credit) {
        //            interest amount = rate * previous creditAmount
        return credit.getCreditBalance().multiply(credit.getMonthlyRate(), new MathContext(10));
    }

    private BigDecimal calculateBase(MonthlyPayment monthlyPayment) {
        //            base amount = Monthly payment - interest amount
        return monthlyPayment.getTotalMonthly().subtract(monthlyPayment.getInterest(), new MathContext(10));
    }

    private BigDecimal calculateBalance(Credit credit, MonthlyPayment monthlyPayment) {
//        balance = credit amount - base amount
        return credit.getCreditBalance().subtract((monthlyPayment.getBase()), new MathContext(10));
    }

    public BigDecimal calculateInitialPayment(BigDecimal getHouseValue) {
        BigDecimal initialPayment = getHouseValue.multiply(BigDecimal.valueOf(0.15));
        if (getHouseValue.compareTo(Constants.MAX_MORTGAGE_AMOUNT) > 0) {
            System.out.println("Value of house exceed maximum applicable mortgage. Difference will be added up to initial payment");
            initialPayment = initialPayment.add(getHouseValue.subtract(Constants.MAX_MORTGAGE_AMOUNT));
            System.out.println((getHouseValue.subtract(Constants.MAX_MORTGAGE_AMOUNT)) + " added to initial payment.");
        }
        return initialPayment;
    }

}
