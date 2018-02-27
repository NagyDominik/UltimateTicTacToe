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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ultimatetictactoe.gui.model.Model;

/**
 *
 * @author Dominik
 */
public class MainWindowController implements Initializable {

    @FXML
    private GridPane gridPaneMain;
    
    @FXML
    private Label lblTurn;
    
    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpIds();
    }

    @FXML
    private void btnClicked(ActionEvent event) {
        Button srcButton = (Button) event.getSource();
        int buttonId = Integer.parseInt(srcButton.getId());
        int macroId = Integer.parseInt(srcButton.getParent().getId());
        System.out.println(buttonId);
        System.out.println(macroId);
        
        int x = getX(buttonId);
        int y = getY(buttonId);
        System.out.println(x + "; " + y);
        //model.playMove(x, y); 
    }
    
    /**
     * Add an id to the grid panes and buttons
     */
    private void setUpIds()
    {
        int btnId = 0;
        int grdId = 0;
        for (Object o : gridPaneMain.getChildren())
        {
            if (GridPane.class.isInstance(o))
            {
                GridPane pane = (GridPane) o;
                pane.setId(Integer.toString(grdId));
                
                for (Object o2 : pane.getChildren()) {
                    if (Button.class.isInstance(o2)) {
                        Button b = (Button) o2;
                        b.setId(Integer.toString(btnId));
                        btnId++;
                    }
                }
                grdId++;
            }
        }
    }

    @FXML
    private void gameModeChooser(ActionEvent event) throws Exception {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/gui/view/GameModes.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) lblTurn.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
    
    private int getX(int buttonId)
    {
        return buttonId/9;
    }

    private int getY(int buttonId)
    {
        return buttonId%9;
    }
}
