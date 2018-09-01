package Day_365_Java;

import java.util.Scanner;

public class First_try {
    public static void main(String[] args) {
        Cell[] cells = new Cell[3];
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 3; ++i){
//            System.out.println(sc.nextInt());
            cells[i] = new Cell(sc.nextInt(), sc.nextInt());
        }
        for(int i = 0; i < 3; ++i) {
            System.out.println(cells[i].surface());
        }
    }
}

class Cell {
    private int a;
    private int b;

    Cell(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int surface() {
        return this.a * this.b;
    }
}