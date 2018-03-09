/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.controller;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Bence
 */
public class HelpWindowController implements Initializable {

    @FXML
    private Label lbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    String link = "goo.gl/wf5adF";
    FXMLLoader loader = new FXMLLoader();

    @FXML
    private void openInBrowser(ActionEvent event) throws StringIndexOutOfBoundsException {
    	WebView web = new WebView();
    	web.getEngine().load("https://goo.gl/wf5adF");
    	Scene scene = new Scene(web);
        Stage st = new Stage();
    	st.setScene(scene);
        st.setTitle("Game rules");
    	st.show();
	}

 

    @FXML
    private void exitPressed(ActionEvent event) throws Exception  {
        loader = new FXMLLoader(getClass().getResource("/ultimatetictactoe/gui/view/GameModes.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage) lbl.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
     }
    
    
}
