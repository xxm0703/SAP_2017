package Day_1_Java;

import java.util.Scanner;

public class SAP_3 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] c = s.toCharArray();
        int lo, max = 0;
        for (int i = 0; i < c.length; i++) {
            lo = 0;
            for (int j = i; j < c.length; j++) {
                if(c[i] == c[j]) lo++;
                else break;
            }
            if(lo > max) max = lo;
        }
        System.out.println(max);
    }
}
