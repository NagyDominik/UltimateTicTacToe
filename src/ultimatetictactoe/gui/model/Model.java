/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.model;

import ultimatetictactoe.bll.game.GameManager;

/**
 *
 * @author Dominik
 */
public class Model {

    Model instance;
    GameManager gamemanager;

    public Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

}
