public class ExceededAge extends Exception {
    private int age;
    private int maxAge;
    private int minAge;

    public int getAge() {
        return age;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public int getMinAge() {
        return minAge;
    }


    public ExceededAge(String message, int age, int maxAge, int minAge) {
        super(message);
        this.age = age;
        this.maxAge = maxAge;
        this.minAge = minAge;
    }
}
