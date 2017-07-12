import blocks.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Arrays;
import java.util.List;
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
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        canvas = new Canvas(w*r, h*r+o);
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, w*r, h*r+o);
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

        primaryStage.setOnCloseRequest(we -> timer.cancel());
    }

    private void paint() {
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                if (grid.get(y, x) == null){
                	canvas.getGraphicsContext2D().setFill(Color.WHITE);
					canvas.getGraphicsContext2D().fillRect(y*r, x*r+o, r, r);
				}
                else {
					canvas.getGraphicsContext2D().setFill(Color.BLACK);
					canvas.getGraphicsContext2D().fillRect(y*r, x*r+o, r, r);
                	canvas.getGraphicsContext2D().setFill(getPrimaryColor(grid.get(y,x).getValue()));
					canvas.getGraphicsContext2D().fillRect(y*r+b, x*r+b+o, r-2*b, r-2*b);
				}

            }
        }
        canvas.getGraphicsContext2D().setFill(getPrimaryColor(tetris.getNextBlock().getId()));
        canvas.getGraphicsContext2D().fillRect(0,0, grid.getWidth()*r, o);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillText(String.valueOf(tetris.getScore()), 10, 10 + o);
    }

    private static Color getPrimaryColor(int i) {
        List<Color> color = Arrays.asList(
                Color.BLUE,
                Color.RED,
                Color.ORANGE,
                Color.GREEN,
                Color.CYAN,
                Color.PURPLE,
                Color.YELLOWGREEN
        );
        return color.get(i % color.size());
    }
}
