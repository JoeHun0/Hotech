package Primary;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    public TextField fileKiir;
    public Button Btn1;
    public Button Btn2;
    File selectedFile;
    public void onMouseClick(MouseEvent mouseEvent) {
        Input i = new Input();
        i.InputFromExcell(selectedFile);
    }

    public void beOlvas(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);
        System.out.println(selectedFile);
        fileKiir.setText(selectedFile.toString());
        Btn1.setVisible(true);
    }

}
