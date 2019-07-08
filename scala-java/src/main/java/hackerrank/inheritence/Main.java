package hackerrank.inheritence;

public class Main {

    public static void main(String []args) {
        String firstName = "Kathryn";
        String lastName = "Hodge";
        int id = 8292157;
        int[] testScores = {100, 90, 80, 75, 90, 88, 84, 88, 78, 67};
        Student s = new Student(firstName, lastName, id, testScores);
        s.printPerson();
        System.out.println("Grade: " + s.calculate() );
    }
}
