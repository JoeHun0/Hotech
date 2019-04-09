package Primary;


import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.chart.RadarChart;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;


import java.lang.reflect.InvocationTargetException;
import java.util.Random;


public class Main extends Application {
    private static final double TILE_SIZE = 150;
    private static final Random RND       = new Random();
    private ChartData chartData1;
    private ChartData         chartData2;
    private ChartData         chartData3;
    private ChartData         chartData4;
    private ChartData         chartData5;
    private ChartData         chartData6;
    private ChartData         chartData7;
    private ChartData         chartData8;
    private Tile radarChartTile1;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();

    }
   // @Override public void init() {
   // }

    public static void main(String[] args) {
        launch(args);
    }
}
