//package Day_365_Java;

import java.util.Scanner;

public class SAP_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] sa;
        String s = sc.nextLine();
        sa = s.split(" ");
        System.out.println(calc(sa));
    }
    
    private static Num calc(String[] s){
        byte a = 0;
        Num n = new Num(0, a);
        for (byte i = 0; i < s.length; i++) {
            a = count(i, s);
            if(a > n.amount || (a == n.amount && Integer.parseInt(s[i]) < n.value)){
                n = new Num(Integer.parseInt(s[i]), a);
            }
        }
        return n;
    }
    
    private static byte count(byte i, String[] s){
        byte n = 0;
        for (String value : s) {
            if (value.equals(s[i])) n++;
        }
        return n;
    }
}

class Num{
    int value;
    byte amount;
    
    Num(int v, byte a){
        this.value = v;
        this.amount = a;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value) + ' ' + Byte.toString(this.amount);
    }
}