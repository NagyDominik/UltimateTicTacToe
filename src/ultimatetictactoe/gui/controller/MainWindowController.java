/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        for (Object o : gridPaneMain.getChildren())
        {
            if (GridPane.class.isInstance(o))
            {
                GridPane pane = (GridPane) o;
                int col = gridPaneMain.getColumnIndex(pane);
                int row = gridPaneMain.getRowIndex(pane);
                //pane.setId(+col+")"));
                System.out.println("Out");
                                
                for (Object o2 : pane.getChildren()) {
                    if (Button.class.isInstance(o2)) {
                        Button b = (Button) o2;
                        int btnCol = pane.getColumnIndex(b);
                        int btnRow = pane.getRowIndex(b);
                        b.setId(Integer.toString(col*3 + row*27 + btnCol + btnRow*9));

                    }
                }
            }
        }
        
        for (int i = 0; i < 3; i++)
        {
            
        }
     
    }
    
    /**
     * Dividing the id number with the size of the matrix (9) returns the row number.
     * @param buttonId The id of the button.
     * @return The x coordinate of the button.
     */
    private int getX(int buttonId)
    {
        return buttonId/9;
    }

    /**
     * Performing mudolo on the id with the size of the matrix (9) returns the column number.
     * @param buttonId The id of the button.
     * @return The y coordinate of the button.
     */
    private int getY(int buttonId)
    {
        return buttonId%9;
    }
}
