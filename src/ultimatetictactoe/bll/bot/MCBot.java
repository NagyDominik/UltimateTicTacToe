/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.bll.bot;

import java.util.List;
import java.util.Random;
import ultimatetictactoe.bll.game.IGameState;
import ultimatetictactoe.bll.move.IMove;

/**
 *
 * @author sebok
 */
public class MCBot implements IBot
{
    
    /**
     * Play a move on the given state.
     * @param state
     * @return 
     */
    @Override
    public IMove doMove(IGameState state)
    {
        Random r = new Random();
        List<IMove> validMoves = state.getField().getAvailableMoves();
        return validMoves.get(r.nextInt(validMoves.size()));
    }
    
    public String getBotName()
    {
        return "MCBot";
    }
}
