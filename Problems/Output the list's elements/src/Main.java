import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> nameList = new ArrayList<>(Arrays.asList("Mr.Green", "Mr.Yellow", "Mr.Red"));
        // write your code here
//        for(String n:nameList){
//            System.out.println(n);
//        }
        nameList.forEach(System.out::println);
    }
}