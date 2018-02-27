/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bll.game;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import ultimatetictactoe.bll.field.IField;

/**
 *
 * @author Dominik
 */
public class GameState implements IGameState {

    private IField field;
    private final IntegerProperty moveCount = new SimpleIntegerProperty();
    private final IntegerProperty roundCount = new SimpleIntegerProperty();

    public GameState() {
    }

    public GameState(IField field) {
        this.field = field;
        field.clearBoard();
    }

    @Override
    public IField getField() {
        return field;
    }

    @Override
    public int getMoveNumber() {
        return this.moveCount.get();
    }

    @Override
    public void setMoveNumber(int moveNumber) {
        this.moveCount.set(moveNumber);
    }

    @Override
    public int getRoundNumber() {
        return roundCount.get();
    }

    @Override
    public void setRoundNumber(int roundNumber) {
        this.roundCount.set(roundNumber);
    }

    public IntegerProperty roundCountProperty() {
        return roundCount;
    }

    public IntegerProperty moveCountProperty() {
        return moveCount;
    }

}
