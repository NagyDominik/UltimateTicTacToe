/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.gui.model;

import javafx.collections.ObservableList;

import ultimatetictactoe.bll.bot.MCBot;
import ultimatetictactoe.bll.field.GameField;
import ultimatetictactoe.bll.game.GameManager;
import ultimatetictactoe.bll.game.GameState;
import ultimatetictactoe.bll.move.IMove;
import ultimatetictactoe.bll.move.Move;

/**
 *
 * @author Dominik
 */
public class Model {

    private static Model instance;
    private GameManager gamemanager;
    private int macroboardId;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void setGameManagerHvH() {
        this.gamemanager = new GameManager(new GameState(new GameField()));
    }

    public void setGameManageHvB() {
        this.gamemanager = new GameManager(new GameState(new GameField()), new MCBot());
    }

    public boolean playMove(int x, int y) {
        IMove move = new Move(x, y);
        return gamemanager.UpdateGame(move);
    }

    public boolean playMove() {
        return gamemanager.UpdateGame();
    }

    public int getCurrentPlayer() {
        return gamemanager.getCurrentPlayer();
    }

    public Boolean UpdateGame(IMove move) {
        return gamemanager.UpdateGame(move);
    }

    public void setNewMicroboard(int newActiveMacroBoard) {
        this.macroboardId = newActiveMacroBoard;
    }

    public ObservableList<Integer> getMacroboardWins() {
        return gamemanager.getMacroboardWins();
    }

    public int getFieldState(int x, int y) {
        return gamemanager.getFieldState(x, y);
    }

}
