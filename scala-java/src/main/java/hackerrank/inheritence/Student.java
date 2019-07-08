package hackerrank.inheritence;

public class Student extends Person {
    private int[] testScores;

    public Student() {
        super("","",0);
    }

    /*
     *   Class Constructor
     *
     *   @param firstName - A string denoting the Person's first name.
     *   @param lastName - A string denoting the Person's last name.
     *   @param id - An integer denoting the Person's ID number.
     *   @param scores - An array of integers denoting the Person's test scores.
     */
    // Write your constructor here
    public Student(String firstName, String lastName, int id, int[] scores) {
        super(firstName, lastName, id);
        this.testScores = scores;
    }

    /*
     *   Method Name: calculate
     *   @return A character denoting the grade.
     */
    // Write your method here
    public char calculate() {
        int average = (int)calculateAverage();
        if (average < 40) return 'T';
        else if (average < 55 && 40 <= average) return 'D';
        else if (average < 70 && 55 <= average) return 'P';
        else if (average < 80 && 70 <= average) return 'A';
        else if (average < 90 && 80 <= average) return 'E';
        else if (average <= 100 && 90 <= average) return 'O';
        else return 'Z';
    }

    private float calculateAverage() {
        float sum = 0;
        float size = (float)this.testScores.length;
        for (int score: this.testScores) {
            sum+=score;
        }
        float avg = (sum / size);
        System.out.println("avg: " + avg);
        return (sum / size);
    }
}
