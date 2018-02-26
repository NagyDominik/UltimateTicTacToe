/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoe.be;

import ultimatetictactoe.bll.bot.IBot;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
