package hackerrank.scope;


public class Difference {

    private int[] elements;
    public int maximumDifference;

    public Difference(int[] elements) {
        this.elements = elements;
    }

    public void computeDifference() {
        int min = 99999999;
        int max = 0;

        for (int e: this.elements) {
            if (e < min) min = e;
            if (e > max) max = e;
        }

        this.maximumDifference = Math.abs(min - max);
    }
}
