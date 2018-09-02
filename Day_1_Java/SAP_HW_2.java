package Day_1_Java;

import java.util.Scanner;

public class SAP_HW_2 {
    public static void main(String[] args) {
        int[] num = new int[4];
        boolean duplicate = false;
        do {
            for (int i = 0; i < 4; i++) {
                num[i] = (int) (Math.random() * 10);
            }
            for (int i = 0; i < 4; i++) {
                int[] test = num.clone();
                test[i] = -1;
                duplicate = SAP_HW_2.int_in_list(test, num[i]);
            }
        } while (num[0] == 0 || duplicate);
        Scanner sc = new Scanner(System.in);
        int digit, cow, bull;
        do {
            cow = 0;
            bull = 0;
            int guess = sc.nextInt();
            for (int i = 3; i >= 0; i--) {
                digit = guess % 10;
                guess /= 10;
                if (SAP_HW_2.int_in_list(num, digit)) {
                    if (digit == num[i]) bull++;
                    else cow++;
                }
            }
            System.out.println(String.format("Cows: %d\tBulls: %d", cow, bull));
        } while (bull != 4);
    }

    private static boolean int_in_list(int[] l, int s) {
        for (int element : l) {
            if (element == s) return true;
        }
        return false;
    }
}
