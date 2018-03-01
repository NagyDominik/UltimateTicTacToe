/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bll.field;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import ultimatetictactoe.bll.move.IMove;

/**
 *
 * @author Dominik
 */
public class GameField implements IField {

    private String[][] board = new String[9][9];
    private String[][] macroBoard = new String[3][3];
    private List<IMove> moves = new ArrayList();
    
    public void initialize(URL url, ResourceBundle rb) {
        clearBoard();
    }
    
    @Override
    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[j][i] = IField.EMPTY_FIELD;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                macroBoard[j][i] = IField.AVAILABLE_FIELD;
            }
        }
    }

    @Override
    public List<IMove> getAvailableMoves() {
        return moves;
    }

    @Override
    public String getPlayerId(int column, int row) {
        return board[row][column];
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] != IField.EMPTY_FIELD) {
                    isEmpty = false;
                    break;
                }
            }
        }
        return isEmpty;
    }

    @Override
    public boolean isFull() {
        boolean isFull = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == IField.EMPTY_FIELD) {
                    isFull = false;
                    break;
                }
            }
        }
        return isFull;
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y) {
        //return macroBoard[x][y] == AVAILABLE_FIELD;
        return true;
    }

    @Override
    public String[][] getBoard() {
        return board;
    }

    @Override
    public String[][] getMacroboard() {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board) {
        this.board = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard) {
        this.macroBoard = macroboard;
    }

    public void updateBoard(int x, int y) {
        board[x][y] = "X";
    }
    
}
