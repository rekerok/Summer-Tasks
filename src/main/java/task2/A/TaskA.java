package task2.A;

import Variables.Variables;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Stream;

public class TaskA {
    public static void main(String[] args) {
        StringBuilder str = new StringBuilder(Variables.faker.number().digits(Variables.faker.number().numberBetween(2, 10)));
        System.out.println(str);
        Stack<Integer> stack = new Stack<>();
        for (char symbol : str.toString().toCharArray()) {
            stack.push(Character.getNumericValue(symbol));
            System.out.println(stack.peek());
        }
        str.setLength(0);
        System.out.println(stack);
        stack.stream().forEach(digit -> str.insert(0, digit));
        // stack.stream().forEach(str::append);
        System.out.println(str);
    }

}
