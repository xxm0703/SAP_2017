package Day_1_Java;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SAP_HW_2 {
    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }

    protected static class Game {
        private ArrayList<Byte> num = new ArrayList<>();
        private ArrayList<Byte> guess = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        Game() {
            do {
                this.num.clear();
                for (int i = 0; i < 4; i++) {
                    this.num.add((byte) (Math.random() * 10));
                }
            } while (this.notValid(this.num));
        }

        Game(short num) {
            this.num = transform(num);
            if (this.notValid(this.num)) throw new NumberFormatException();

        }

        private boolean notValid(ArrayList<Byte> l) {
            if (l.get(0) == 0) return true;
            for (int i = 0; i < 3; i++) {
                if (l.subList(i + 1, l.size()).contains(l.get(i))) return true;
            }
            return false;
        }

        private ArrayList<Byte> transform(short num) {
            ArrayList<Byte> l = new ArrayList<>();
            for (int i = 0; i < this.num.size(); i++) {
                l.add((byte) 0);
            }
            for (int i = this.num.size() - 1; i >= 0; i--) {
                l.set(i, (byte) (num % 10));
                num /= 10;
            }
            return l;
        }

        void play() {
            do {
                this.guessing(sc.nextShort());
            } while (this.bulls() < 4);

            System.out.println("You WIN!");
        }

        private void guessing(short g) {
            this.guess = this.transform(g);
            if (this.notValid(this.guess)) throw new NumberFormatException();
            System.out.printf("Bulls: %d\nCows: %d\n", this.bulls(), this.cows());

        }

        private byte bulls() {
            byte count = 0;
            for (int i = 0; i < this.num.size(); i++) {
                if (this.num.get(i).equals(this.guess.get(i))) count++;
            }
            return count;
        }

        private byte cows() {
            byte count = 0;
            for (int i = 0; i < this.num.size(); i++) {
                if (!this.num.get(i).equals(this.guess.get(i)) && this.num.contains(this.guess.get(i))) count++;
            }
            return count;
        }
    }
}
