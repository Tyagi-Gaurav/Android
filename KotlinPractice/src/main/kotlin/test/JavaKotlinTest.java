package test;

import java.util.Arrays;
import java.util.List;

public class JavaKotlinTest {
    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(1, 2, 3);
        System.out.println(CollectionExamplesKt.joinToString(inputList, inputList, ",", "", ""));
    }
}
