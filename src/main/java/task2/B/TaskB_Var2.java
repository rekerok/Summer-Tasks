package task2.B;

import Variables.Variables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskB_Var2 {
    public static void main(String[] args) {
        int count = 20;
        ArrayList<Integer> list = randomList(count);
        System.out.println(list);
        Stream.concat(
                list.stream().filter(x->x<=0),
                list.stream().filter(x->x>=0)
        ).forEach(x->System.out.print(x+" "));
    }

    public static ArrayList<Integer> randomList(int count) {
        return (ArrayList<Integer>) Stream.generate(() -> Variables.faker.number().numberBetween(-100, 100)).limit(count).collect(Collectors.toList());
    }
}
