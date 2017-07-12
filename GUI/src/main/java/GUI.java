import blocks.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends Application {
    private final static int w = 10, h = 18, r = 20, o = 20, b = 1, s = 10;
    private Canvas canvas;
    private Grid grid;
    private Tetris tetris;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        canvas = new Canvas(w*r, h*r);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, w*r, h*r);
        primaryStage.setScene(scene);
        primaryStage.show();

        grid = new Grid(h, w);
        tetris = new Tetris(grid);

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
    }

    private void paint() {
        canvas.getGraphicsContext2D().setFill(Color.LIGHTCYAN);
        canvas.getGraphicsContext2D().fillRect(0,0,canvas.getWidth(), canvas.getHeight());
        canvas.getGraphicsContext2D().setStroke(Color.GRAY);
        for (int y = (-1*(int)canvas.getHeight()); y < canvas.getHeight(); y += s){
            canvas.getGraphicsContext2D().strokeLine(0, y, canvas.getWidth(), y+canvas.getHeight());
        }
        canvas.getGraphicsContext2D().setFill(Color.LIGHTCYAN);
        canvas.getGraphicsContext2D().fillRect(5, 0, 15, 15);
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                if (grid.get(y, x) != null){
                    canvas.getGraphicsContext2D().setFill(Color.BLACK);
                    canvas.getGraphicsContext2D().fillRect(y*r-b, x*r-b, r+2*b, r+2*b);
                    canvas.getGraphicsContext2D().setFill(getPrimaryColor(grid.get(y,x).getValue()));
                    canvas.getGraphicsContext2D().fillRect(y*r+b, x*r+b, r-2*b, r-2*b);
                    canvas.getGraphicsContext2D().setFill(getLightColor(grid.get(y,x).getValue()));
                    canvas.getGraphicsContext2D().fillRect(y*r+3*b, x*r+b, r-4*b, r-4*b);
                    canvas.getGraphicsContext2D().setFill(getDarkColor(grid.get(y,x).getValue()));
                    canvas.getGraphicsContext2D().fillRect(y*r+b, x*r+3*b, r-4*b, r-4*b);
                    canvas.getGraphicsContext2D().setFill(getPrimaryColor(grid.get(y,x).getValue()));
                    canvas.getGraphicsContext2D().fillRect(y*r+3*b, x*r+3*b, r-6*b, r-6*b);
                }
            }
        }
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillText(String.valueOf(tetris.getScore()), 10, 10);
    }

    private static Color getPrimaryColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(0,0,255),      //RLBlock
                Color.rgb(255,0,0),      //BlockBlock
                Color.rgb(255,102,0),      //LineBlock
                Color.rgb(0,255,0),      //ZBlock
                Color.rgb(102,204,255),      //SBlock
                Color.rgb(255,255,0),      //TBlock
                Color.rgb(204,0,255)       //LLBlock
        );
        return color.get(i % color.size());
    }

    private static Color getLightColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(102,102,255),      //RLBlock
                Color.rgb(255,102,102),      //BlockBlock
                Color.rgb(255,153,51),      //LineBlock
                Color.rgb(102,255,102),      //ZBlock
                Color.rgb(153,255,255),      //SBlock
                Color.rgb(255,255,153),      //TBlock
                Color.rgb(204,102,255)       //LLBlock
        );
        return color.get(i % color.size());
    }

    private static Color getDarkColor(int i) {
        List<Color> color = Arrays.asList(
                Color.rgb(0,0,204),      //RLBlock
                Color.rgb(204,0,0),      //BlockBlock
                Color.rgb(204,102,0),      //LineBlock
                Color.rgb(0,204,0),      //ZBlock
                Color.rgb(51,153,204),      //SBlock
                Color.rgb(255,204,0),      //TBlock
                Color.rgb(153,0,204)       //LLBlock
        );
        return color.get(i % color.size());
    }
}
