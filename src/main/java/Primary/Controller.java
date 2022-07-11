package Primary;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import eu.hansolo.tilesfx.chart.RadarChart.Mode;

import java.io.File;

public class Controller {

    public TextField fileKiir;
    public Button Btn1;
    public Button Btn2;
    File selectedFile;
    public void onMouseClick(MouseEvent mouseEvent) {
        Tasks t = new Tasks();
        t.InputFromExcell(selectedFile);
        t.Tuzelo();
        Double a = t.getA();
        Double b = t.getB();

        XYChart.Series series = new XYChart.Series<>();
        series.getData().add(new XYChart.Data("100",a*Math.pow(100.0,b)));
        series.getData().add(new XYChart.Data("200",a*Math.pow(200.0,b)));
        series.getData().add(new XYChart.Data("300",a*Math.pow(300.0,b)));
        series.getData().add(new XYChart.Data("400",a*Math.pow(400.0,b)));
        series.getData().add(new XYChart.Data("500",a*Math.pow(500.0,b)));
        series.getData().add(new XYChart.Data("600",a*Math.pow(600.0,b)));
        series.getData().add(new XYChart.Data("700",a*Math.pow(700.0,b)));
        series.getData().add(new XYChart.Data("800",a*Math.pow(800.0,b)));
        series.getData().add(new XYChart.Data("900",a*Math.pow(900.0,b)));
        series.getData().add(new XYChart.Data("1000",a*Math.pow(1000.0,b)));
        series.getData().add(new XYChart.Data("1100",a*Math.pow(1100.0,b)));
        series.getData().add(new XYChart.Data("1200",a*Math.pow(1200.0,b)));
        series.getData().add(new XYChart.Data("1300",a*Math.pow(1300.0,b)));
        series.getData().add(new XYChart.Data("1400",a*Math.pow(1400.0,b)));
        series.getData().add(new XYChart.Data("1500",a*Math.pow(1500.0,b)));

        System.out.println(t.getA());
        LineChart.getData().addAll(series);
    }
    public void tiles(){

    }
    @FXML
    private javafx.scene.chart.LineChart<?, ?> LineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;


    public void beOlvas(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile);
        fileKiir.setText(selectedFile.toString());
        Btn1.setVisible(true);

    }
}
