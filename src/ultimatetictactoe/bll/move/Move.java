package ultimatetictactoe.bll.move;

/**
 * Represents a move in the game (WIP!!!).
 * @author sebok
 */
public class Move implements IMove
{
    int x;
    int y;

    public Move(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    
    @Override
    public int getX()
    {
        return this.x;
    }

    @Override
    public int getY()
    {
        return this.y;
    }
    
}
