//package Day_365_Java;

import java.util.Scanner;

public class SAP_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        System.out.println(find(s));
    }

    static boolean find(String[] s) {
        char[] s1 = s[0].toLowerCase().toCharArray();
        char[] s2 = s[1].toLowerCase().toCharArray();
        for (int i = 0; i < s1.length; i++) {

            for (int j = 0; j < s2.length; ++j) {
                if (s1[i] == s2[j]) {
                    s1[i] = 0;
                    s2[j] = 0;
                    break;
                }
            }
        }
        return check(s1) && check(s2);
    }

    static boolean check(char[] s1) {
        for (char c : s1) {
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) return false;
        }
        return true;
    }

}
