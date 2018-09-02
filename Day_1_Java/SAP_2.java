package Day_1_Java;

import java.util.Scanner;

public class SAP_2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        boolean g;
        int index = 0;
        String[] c = s.split(" ");
        int[] list = new int[c.length];
        for (String aList : c) {
            g = false;
            int d = Integer.parseInt(aList);
            for (int i = 0; i < index; i++) {
                if(d == list[i]) g = true;
            }
            if(!g) list[index++] = d;
        }
        for (int i=0; i< index;i++) {
            int k = 0;
            for (String aC : c) {
                if (list[i] == Integer.parseInt(aC)) k++;
            }
            System.out.print(list[i]+" "+ k);
            if(i<index-1) System.out.print(" ");
        }
        System.out.println();
    }
}
