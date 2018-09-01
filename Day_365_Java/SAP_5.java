package Day_365_Java;

import java.util.Scanner;

public class SAP_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        System.out.println(find(s));
    }

    static boolean find(String[] s) {
        if(s[0].length() != s[1].length()) return false;
        char[] s1 = s[0].toCharArray();
        char[] s2 = s[1].toCharArray();
        int l = s1.length;
        for (int i = 0; i < l; i++) {

            byte n = 1;
            for (int j = 0; j < l; ++j){
                if (s1[i] == s2[j]){
                    s1[i] = 0;
                    s2[j] = 0;
                    n = 0;
                    break;
                }
            }
            if (n == 1) return false;
        }
        return check(s1) && check(s2);
    }

    static boolean check(char[] s1){
        for (char c: s1) {
            if(c != 0) return false;
        }
        return true;
    }

}
