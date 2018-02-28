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
    
    private int[][] board = new int[9][9];

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setUpIds();
    }

    @FXML
    private void btnClicked(ActionEvent event) throws Exception {
        Button srcButton = (Button) event.getSource();
        int buttonId = Integer.parseInt(srcButton.getId());
        int macroId = Integer.parseInt(srcButton.getParent().getId());
        
        int x = getX(buttonId);
        int y = getY(buttonId);
        
        int newActiveMacroBoard = board[x][y];
        
        String result = "The id of the button: " + buttonId + ". \nCoordinates: (" + x + "; " + y + "). " + "\nMacroBoard id: " + macroId + "\nNew micro board: " + newActiveMacroBoard;
        System.out.println(result);
       
//        if(!model.playMove(x, y))
//        {
//            throw new Exception("Unable to play move!");
//        }
    }
    
    /**
     * Add an id to the grid panes and buttons
     */
    private void setUpIds()
    {
        int gridId = 0;
        int countId = 0;
        for (Object o : gridPaneMain.getChildren())
        {
            if (GridPane.class.isInstance(o))
            {
                GridPane pane = (GridPane) o;
                pane.setId(Integer.toString(gridId));
                
                int col = GridPane.getColumnIndex(pane);
                int row = GridPane.getRowIndex(pane);
                
                for (Object o2 : pane.getChildren()) {
                    if (Button.class.isInstance(o2)) {
                        Button b = (Button) o2;
                        int btnCol = GridPane.getColumnIndex(b);
                        int btnRow = GridPane.getRowIndex(b);
                        int id = (col*3 + row*27 + btnCol + btnRow*9);
                        b.setId(Integer.toString(id));
                        int x = getX(id);
                        int y = getY(id);
                        board[x][y] = countId%9;
                        countId++;
                    }
                }
            }
            gridId++;
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
    
    /**
     * If we divide the id of a given button with the size of the matrix (9), the whole part of the result will be the x coordinate.
     * @param id The ID of the clicked button.
     * @return The X coordinate of the clicked button;
     */
    private int getX(int id)
    {
        return id/9;
    }
    
    /**
     * If we modulo the id of a given button with the size of the matrix (9), the result will be the y coordinate.
     * @param id The ID of the clicked button.
     * @return The Y coordinate of the clicked button;
     */
    private int getY(int id)
    {
        return id%9;
    }
}
