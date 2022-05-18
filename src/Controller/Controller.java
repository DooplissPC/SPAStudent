package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button MatchingView;

    public void changeScene(ActionEvent event) throws IOException {
        Parent MatchingParent = FXMLLoader.load(getClass().getResource("../View/matching.fxml"));
        Scene MatchingScene = new Scene(MatchingParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(MatchingScene);
        window.show();
    }

}
