package fi.aa.aaproject;

public class StepsCounter {
    private int counter;

    public StepsCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }

    public void plusValue() {
        this.counter += 1;
    }
}
