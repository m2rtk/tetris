import blocks.Grid;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        Tetris tetris = new Tetris(grid);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO: 08.07.2017 this
    }
}