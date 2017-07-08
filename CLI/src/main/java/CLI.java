import blocks.Grid;

import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        Grid grid;
        try {
            grid = new Grid(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (Exception e) {
            grid = new Grid(10, 18);
        }

        Tetris tetris = new Tetris(grid);
        Scanner in = new Scanner(System.in);
        while (true) {
            for (char c : in.nextLine().toCharArray()) {
                switch (c) {
                    case 'a':
                        tetris.moveLeft();
                        break;
                    case 'd':
                        tetris.moveRight();
                        break;
                    case 's':
                        tetris.update();
                        break;
                    case 'w':
                        tetris.rotate();
                        break;
                    case 'q':
                        return;
                }
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
                else a = grid.get(y,x).toString();

                System.out.print(a);
            }
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }
}
