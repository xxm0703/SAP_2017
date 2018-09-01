package Day_1_Java;

// No duplicate chars in a string

import java.util.Scanner;
public class SAP_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length-1; i++) {
            for (int j = i+1; j < c.length; j++) {
                if(c[i] == c[j]){
                    System.out.println(false);
                    return;
                }
            }
        }
        System.out.println(true);
    }

}

