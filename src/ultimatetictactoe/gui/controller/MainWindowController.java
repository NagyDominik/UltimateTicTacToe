/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import ultimatetictactoe.gui.model.Model;

/**
 *
 * @author Dominik
 */
public class MainWindowController implements Initializable {

    private final Model model = Model.getInstance();
    @FXML
    private GridPane gridPaniMain;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpIds();
    }

    @FXML
    private void btnClicked(ActionEvent event) {
        Button srcButton = (Button) event.getSource();
        System.out.println(srcButton.getId());
    }
    
    private void setUpIds()
    {
        int id = 0;
        for (Object o : gridPaniMain.getChildren())
        {
            if (GridPane.class.isInstance(o))
            {
                GridPane pane = (GridPane) o;
                for (Object o2 : pane.getChildren())
                {
                    if (Button.class.isInstance(o2))
                    {
                        Button b = (Button) o2;
                        b.setId(Integer.toString(id));
                        id++;
                    }
                }
            }
        }
    }
}
