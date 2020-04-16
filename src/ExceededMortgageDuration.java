public class ExceededMortgageDuration extends Exception {
    private int maxDuration;
    private int duration;

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ExceededMortgageDuration(int maxDuration, int duration) {
        this.maxDuration = maxDuration;
        this.duration = duration;
    }

    public ExceededMortgageDuration(String message, int maxDuration, int duration) {
//        super(message);
        message = "Due to exceed mortgage duration, mortgage application was canceled.";
        this.maxDuration = maxDuration;
        this.duration = duration;
    }
}
