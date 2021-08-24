package task2.B;

import Variables.Variables;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskB_Var11 {

    public static void main(String[] args) throws IOException {
        File file = new File("src/main/java/task2/B/list.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        int count = Variables.faker.number().numberBetween(15, 30);
        int positionNegative = Variables.faker.number().numberBetween(1, count - 1);
        for (int i = 0; i < count; i++) {
            if (i == positionNegative) {
                fileWriter.write(Integer.toString(Variables.faker.number().numberBetween(-1, -20)));
            } else
                fileWriter.write(Integer.toString(Variables.faker.number().numberBetween(0, 20)));


            if (i != count - 1)
                fileWriter.write(", ");

        }
        fileWriter.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String c;
        StringBuilder sb = new StringBuilder();
        parseString(bufferedReader.readLine());
    }

    public static void parseString(String str){
        String[] arr = str.split(", ");
        // Arrays.stream(arr).forEach(symbol-> );
    }
}

