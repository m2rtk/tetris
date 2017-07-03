import blocks.Game;
import blocks.Grid;

import java.util.Scanner;

public class ConsoleLoop {
    public static void main(String[] args) {
        Grid grid = new Grid(6,5);
        Game game = new  Game(grid);
//        for (int i = 0; i < 16; i++) {
//            System.out.println(i);
//            if (i == 12) game.moveRight();
//            if (i == 12) game.moveRight();
//            print(grid);
//            game.update();
//        }
        Scanner in = new Scanner(System.in);
        while (true) {
            switch (in.nextLine()) {
                case "a":
                    game.moveLeft();
                    break;
                case "d":
                    game.moveRight();
                    break;
                case "s":
                    game.update();
                    break;
                case "w":
                    game.rotate();
                    break;
            }
            print(grid);
        }
    }

    private static void print(Grid grid) {
        System.out.println("-------------------------------------");
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                String a;
                if (grid.get(y,x) == null) a = "0";
                else a = "X"; //grid.get(y,x).toString();

                System.out.print(a);
//                System.out.print(a);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }
}
