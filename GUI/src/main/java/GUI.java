import blocks.Cell;
import blocks.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends Application {
    private final static int w = 10, h = 18, r = 20, o = 2, b = 2, s = 10;
    private Canvas canvas;
    private Grid grid;
    private Tetris tetris;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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
//        gc.setStroke(Color.GRAY);
//        for (int y = (-1*(int)canvas.getHeight()); y < canvas.getHeight(); y += s){
//            canvas.getGraphicsContext2D().strokeLine(0, y, canvas.getWidth(), y+canvas.getHeight());
//        }
//        gc.setFill(Color.LIGHTCYAN);
//        gc.fillRect(5, 0, 15, 15);
        Cell cell;
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                if ((cell = grid.get(y, x)) != null){
                    drawCell(y, x, cell.getValue());
                }
            }
        }
        gc.setFill(getPrimaryColor(tetris.getNextBlock().getId()));
        gc.fillRect(0,0, grid.getWidth()*r, o);
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(tetris.getScore()), 10, 10 + o);
    }

    private void drawCell(int y, int x, int id) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillRect(y*r-b,      x*r-b+o,      r+2*b,      r+2*b);

        gc.setFill(getPrimaryColor(id));
        gc.fillRect(y*r+b,      x*r+b+o,      r-2*b,      r-2*b);

        gc.setFill(getLightColor(id));
        gc.fillRect(y*r+3*b,    x*r+b+o,      r-4*b,      r-4*b);

        gc.setFill(getDarkColor(id));
        gc.fillRect(y*r+b,      x*r+3*b+o,    r-4*b,      r-4*b);

        gc.setFill(getPrimaryColor(id));
        gc.fillRect(y*r+3*b,    x*r+3*b+o,    r-6*b,      r-6*b);
    }

    private static Color getPrimaryColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(0,0,255),         //RLBlock
                Color.rgb(255,0,0),         //BlockBlock
                Color.rgb(255,102,0),       //LineBlock
                Color.rgb(0,255,0),         //ZBlock
                Color.rgb(102,204,255),     //SBlock
                Color.rgb(255,255,0),       //TBlock
                Color.rgb(204,0,255)        //LLBlock
        );
        return color.get(i % color.size());
    }

    private static Color getLightColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(102,102,255),     //RLBlock
                Color.rgb(255,102,102),     //BlockBlock
                Color.rgb(255,153,51),      //LineBlock
                Color.rgb(102,255,102),     //ZBlock
                Color.rgb(153,255,255),     //SBlock
                Color.rgb(255,255,153),     //TBlock
                Color.rgb(204,102,255)      //LLBlock
        );
        return color.get(i % color.size());
    }

    private static Color getDarkColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(0,0,204),         //RLBlock
                Color.rgb(204,0,0),         //BlockBlock
                Color.rgb(204,102,0),       //LineBlock
                Color.rgb(0,204,0),         //ZBlock
                Color.rgb(51,153,204),      //SBlock
                Color.rgb(255,204,0),       //TBlock
                Color.rgb(153,0,204)        //LLBlock
        );
        return color.get(i % color.size());
    }
}
