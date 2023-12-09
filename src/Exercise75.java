import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Exercise75 {
    public static void main(String[] args) {
        List<String> executionResult = Arrays.asList("00171", "00181", "00190", "00200", "0021X", "00220");

        // a)
        System.out.println("Total execution number: " + executionResult.size());
        // b)
        float passNumber = 0;
        float failedNumber = 0;
        float skipNumber = 0;
        for (String execution:executionResult){
            if (execution.endsWith("1")) {
                passNumber ++;
            } else if (execution.endsWith("0")) {
                failedNumber++;
            } else if (execution.endsWith("X")) {
                skipNumber++;
            }
        }
        System.out.println(passNumber);
        System.out.println(failedNumber);
        System.out.println(skipNumber);
        // c)
        float dividePassNumber = (passNumber/6)*100;
        float divideFailedNumber = (failedNumber/6)*100;
        float divideSkipNumber = (skipNumber/6)*100;

        System.out.println((String.format("%.0f", dividePassNumber) + "%"));
        System.out.println((String.format("%.0f", divideFailedNumber) + "%"));
        System.out.println((String.format("%.0f", divideSkipNumber) + "%"));
    }E
}
