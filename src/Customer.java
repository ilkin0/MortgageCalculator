import java.math.BigDecimal;
import java.time.LocalDate;

public class Customer {
    private String fullName;
    private LocalDate birthday;
    private int age;
    private BigDecimal salary;
    private int familyMembers;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public int getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(int familyMembers) {
        this.familyMembers = familyMembers;
    }

    //When calculating the mortgage client's income,
    // its income should be deducted from the maintenance cost of each family member.
    public BigDecimal calculateMaxPaymentAmount() {
        BigDecimal minLivingCost = BigDecimal.valueOf(getFamilyMembers()).multiply(BigDecimal.valueOf(Constants.MINIMUM_LIVING_COST));
        return getSalary().subtract(minLivingCost);
    }

    public int calcAge(LocalDate birthday, LocalDate today) {
        return today.getYear() - birthday.getYear();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", salary=" + salary +
                ", familyMembers=" + familyMembers +
                '}';
    }
}
