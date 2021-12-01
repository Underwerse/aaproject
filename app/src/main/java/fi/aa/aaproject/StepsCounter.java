package fi.aa.aaproject;

public class StepsCounter {
    private int counter;
    private int stepsTarget;

    public StepsCounter(int counter, int stepsTarget) {
        this.counter = counter;
        this.stepsTarget = stepsTarget;
    }

    public int getCounter() {
        return counter;
    }

    public void setStepsTarget(int stepsTarget) {
        this.stepsTarget = stepsTarget;
    }

    public int getStepsTarget() {
        return stepsTarget;
    }

    public void plusValue() {
        this.counter += 1;
    }
}
