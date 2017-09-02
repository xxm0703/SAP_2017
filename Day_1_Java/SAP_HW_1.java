package Day_1_Java;

import java.util.Scanner;

public class SAP_HW_1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        boolean exit = false;
        char[] s = inp.split(" ")[0].toCharArray();
        char[] p = inp.split(" ")[1].toCharArray();
        int p_index = 0;
        for (int i = 0; i < s.length; i++) {
            if(p[p_index] == s[i] || p[p_index] == '?' || p[p_index] == '*') p_index++;
            else if(p_index != 0 && p[p_index-1] != '*') p_index = 0;
            if(p_index == p.length){
                exit = true;
                break;
            }
        }
        System.out.println(exit);
    }
}
