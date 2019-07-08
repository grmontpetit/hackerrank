package hackerrank.exceptionsstringtointeger;

public class Solution {

    public static void main(String[] args) {

        String anInt = "1";
        String notAnInt = "a";

        printInt(anInt);
        printInt(notAnInt);
    }

    private static void printInt(String anInt) {
        try {
            int i = Integer.parseInt(anInt);
            System.out.println(i);
        } catch (NumberFormatException e) {
            System.out.println("Bad String");
        }
    }
}
