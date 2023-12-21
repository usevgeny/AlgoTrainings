package CrackingInterview;

public class ValidateLeapYear {
    public static void main(String[] args) {
        System.out.println(isLeapYear(2024));

    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
}
