import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Construct {
    public static void constructCalculator(){
        Calculator calculator = new Calculator();

        System.out.println("Welcome to Mortgage Calculator simulator. Please fill below fields in order to calculate mortgage ");
        Scanner scn = new Scanner(System.in);
        System.out.print("Your full name: ");

        Customer customer = new Customer();
        customer.setFullName(scn.nextLine());


        System.out.print("Your birthday (dd.mm.yyyy): ");
        String birthday = scn.next();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        customer.setBirthday(LocalDate.parse(birthday, dateTimeFormatter));
        customer.setAge(customer.calcAge(customer.getBirthday(), LocalDate.now()));

        System.out.print("Monthly salary: ");
        customer.setSalary(scn.nextBigDecimal());

        System.out.print("How many people do you have in your family: ");
        customer.setFamilyMembers(scn.nextInt());

        System.out.print("What is the value of property which you want: ");
        Credit property = new Credit();
        property.setPropertyValue(scn.nextBigDecimal());

        System.out.print("In how many months would you get to pay mortgage : ");
        property.setMortgageDuration(scn.nextInt());

        System.out.print("How much annual rate should be : ");
        property.setAnnualRate(BigDecimal.valueOf(scn.nextDouble()));
        property.setMonthlyRate(property.getAnnualRate());
        System.out.println();

        try {
            Summary summary = calculator.calculator(property, customer);
            System.out.println(summary);
        } catch (InsufficientSalary insufficientSalary) {
            insufficientSalary.printStackTrace();
            System.out.println("Monthly salary " + insufficientSalary.getSalary() + " , " +
                    "but monthly mortgage payment is " + insufficientSalary.getMonthlyPayment());
        } catch (ExceededMortgageDuration exceededMortgageDuration) {
            exceededMortgageDuration.printStackTrace();
            System.out.println("Your wanted repayment duration is exceed limit of mortgage. Duration you want " +
                    exceededMortgageDuration.getDuration() + " , " +
                    "but limit is " + exceededMortgageDuration.getMaxDuration());
        } catch (ExceededAge exceededAge) {
            exceededAge.printStackTrace();
            System.out.println("Your age is " + exceededAge.getAge() + " ,but required age limit is between " +
                    Constants.MINIMUM_AGE_FOR_MORTGAGE + " and " + exceededAge.getMaxAge());
        }

    }
}
