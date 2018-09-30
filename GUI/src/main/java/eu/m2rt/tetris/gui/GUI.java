package eu.m2rt.tetris.gui;

import eu.m2rt.tetris.logic.Block;
import eu.m2rt.tetris.logic.Grid;
import eu.m2rt.tetris.logic.Tetris;
import eu.m2rt.tetris.logic.Cell;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GUI extends Application {
    private final static int w = 10, h = 18, r = 20, o = 2, b = 2;
    private Canvas canvas;
    private Grid grid;
    private Tetris tetris;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        grid = new Grid(h, w);
        tetris = new Tetris(grid);
        canvas = new Canvas(w*r, h*r+o);

        StackPane root = new StackPane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, w*r, h*r+o);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    tetris.rotate();
                    paint();
                    break;
                case LEFT:
                    tetris.moveLeft();
                    paint();
                    break;
                case RIGHT:
                    tetris.moveRight();
                    paint();
                    break;
                case DOWN:
                    tetris.update();
                    paint();
                    break;
                case SPACE:
                    tetris.down();
                    paint();
                    break;
            }
        });

        int t  = 1000;
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run() {
                if (!tetris.update()) {
                    System.exit(0); // perfect
                }
                paint();
            }
        };
        timer.schedule(task, 0, t);

        primaryStage.setOnCloseRequest(we -> timer.cancel());
    }

    private void paint() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTCYAN);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                drawCell(y, x, grid.get(y, x));
            }
        }

        gc.setFill(getPrimaryColor(tetris.getNextBlock().block));
        gc.fillRect(0,0, grid.getWidth()*r, o);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(tetris.getScore()), 10, 10 + o);
    }

    private void drawCell(int y, int x, Cell cell) {
        if (cell == null) return;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(y*r-b,      x*r-b+o,      r+2*b,      r+2*b);

        gc.setFill(getPrimaryColor(cell.block));
        gc.fillRect(y*r+b,      x*r+b+o,      r-2*b,      r-2*b);

        gc.setFill(getLightColor(cell.block));
        gc.fillRect(y*r+3*b,    x*r+b+o,      r-4*b,      r-4*b);

        gc.setFill(getDarkColor(cell.block));
        gc.fillRect(y*r+b,      x*r+3*b+o,    r-4*b,      r-4*b);

        gc.setFill(getPrimaryColor(cell.block));
        gc.fillRect(y*r+3*b,    x*r+3*b+o,    r-6*b,      r-6*b);
    }

    private static Color getPrimaryColor(Block block) {
        switch (block) {
            case BLOCK: return Color.rgb(255,0,0);
            case LINE: return Color.rgb(255,102,0);
            case Z: return Color.rgb(0,255,0);
            case S: return Color.rgb(102,204,255);
            case T: return Color.rgb(255,255,0);
            case LL: return Color.rgb(204,0,255);
            case RL: return Color.rgb(0,0,255);
            default: return Color.rgb(128, 128, 128);
        }
    }

    private static Color getLightColor(Block block) {
        switch (block) {
            case BLOCK: return Color.rgb(255,102,102);
            case LINE: return Color.rgb(255,153,51);
            case Z: return Color.rgb(102,255,102);
            case S: return Color.rgb(153,255,255);
            case T: return Color.rgb(255,255,153);
            case LL: return Color.rgb(204,102,255);
            case RL: return Color.rgb(102,102,255);
            default: return Color.rgb(255, 255, 255);
        }
    }

    private static Color getDarkColor(Block block) {
        switch (block) {
            case BLOCK: return Color.rgb(204,0,0);
            case LINE: return Color.rgb(204,102,0);
            case Z: return Color.rgb(0,204,0);
            case S: return Color.rgb(51,153,204);
            case T: return Color.rgb(255,204,0);
            case LL: return Color.rgb(153,0,204);
            case RL: return Color.rgb(0,0,204);
            default: return Color.rgb(0, 0, 0);
        }
    }
}
