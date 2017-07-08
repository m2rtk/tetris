import blocks.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class GUI extends Application {
    private final static int w = 10, h = 18, r = 20;
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

        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            public void run() {
                if (!tetris.update()) {
                    System.exit(0); // perfect
                }
                paint();
            }
        };

        timer.schedule(task, 0, 1000);
    }

    private void paint() {
        for (int x = 0; x < grid.getHeight(); x++) {
            for (int y = 0; y < grid.getWidth(); y++) {
                if (grid.get(y, x) == null) canvas.getGraphicsContext2D().setFill(Color.WHITE);
                else canvas.getGraphicsContext2D().setFill(Color.RED);

                canvas.getGraphicsContext2D().fillRect(y*r, x*r, r, r);
            }
        }
    }

}