import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] list = br.readLine().split("");

        Stack<String> stack = new Stack<>();

        if(list.length < 4) {
            if(list.length == 1 && Objects.equals(list[0], "P")) {
                System.out.println("PPAP");
            }
            else {
                System.out.println("NP");
            }
            return;
        }
        for(int i = 0; i < list.length; i++) {
            if(Objects.equals(list[i], "P")) {
                stack.push(list[i]);
            }
            else {
                if(stack.size() >= 2 && i < list.length - 1
                        && Objects.equals(stack.get(stack.size() - 1), "P")
                        && Objects.equals(stack.get(stack.size() - 2), "P")
                        && Objects.equals(list[i + 1], "P")) {
                    stack.pop();
                    i++;
                }
                else {
                    stack.push("A");
                }
            }
        }

        if(stack.size() == 1) {
            System.out.println("PPAP");
        }
        else {
            System.out.println("NP");
        }
    }
}