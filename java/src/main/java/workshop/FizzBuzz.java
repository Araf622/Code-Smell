package workshop;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
    public static String checkFactors(int number) {

        if (number % 15 == 0) {
            return "FizzBuzz";
        }
        else if (number % 3 == 0) return "Fizz";

        else if (number % 5 == 0) return "Buzz";


        return String.valueOf(number);
    }
}