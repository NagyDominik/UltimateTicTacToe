/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.model;

/**
 *
 * @author Dominik
 */
public class Model {

    Model instance;

    public Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

}
