/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bence
 */
public class GameModesController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Label lbl;
    FXMLLoader loader = new FXMLLoader();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void humanVsHuman(ActionEvent event) throws Exception {
        
        loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/gui/view/MainWindow.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) label.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void humanVsComputer(ActionEvent event) {
    }

    @FXML
    private void computerVsComputer(ActionEvent event) {
    }
   

    @FXML
    private void sajtHelp(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/gui/view/HelpWindow.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) lbl.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("How to play?");
        stage.setResizable(false);
        stage.show();
    }
    

    
}
