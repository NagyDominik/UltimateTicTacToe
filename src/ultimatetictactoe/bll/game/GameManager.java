package ultimatetictactoe.bll.game;

import ultimatetictactoe.bll.bot.IBot;
import ultimatetictactoe.bll.field.IField;
import ultimatetictactoe.bll.move.IMove;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager {

    /**
     * Three different game modes.
     */
    public enum GameMode {
        HumanVsHuman,
        HumanVsComputer,
        ComputerVsComputer
    }

    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState) {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot) {
        this.currentState = currentState;
        mode = GameMode.HumanVsComputer;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2) {
        this.currentState = currentState;
        mode = GameMode.ComputerVsComputer;
        this.bot = bot;
        this.bot2 = bot2;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame(IMove move) {
        //Verify the new move
        if (!VerifyMoveLegality(move)) {
            return false;
        }

        int x = move.getX();
        int y = move.getY();

        //Update the currentState
        UpdateBoard(move);
        UpdateMacroboard(move);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;

        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean UpdateGame() {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsComputer && currentPlayer == 1) {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return UpdateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNewActiveMicroboard(int id) {
        int x = getMacroX(id);
        int y = getMacroY(id);
        String newActiveBoard[][] = new String[3][3];
        newActiveBoard[x][y] = IField.AVAILABLE_FIELD;
        currentState.getField().setMacroboard(newActiveBoard);
    }

    private Boolean VerifyMoveLegality(IMove move) {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        System.out.println("Not currently checking move validity actual board");
        Boolean legal = true;
        if (currentState.getField().getBoard()[move.getX()][move.getY()] != IField.EMPTY_FIELD) {
            legal = false;
        }
        if (!currentState.getField().isInActiveMicroboard(move.getX(), move.getY())) {
            legal = false;
        }
        return legal;
    }

    private void UpdateBoard(IMove move) {
        currentState.getField().getBoard()[move.getX()][move.getY()] = "X";
    }

    private void UpdateMacroboard(IMove move) {
        currentState.getField().getMacroboard()[1][1] = "X";
    }

    private int getMacroX(int id) {
        switch (id) {
            case 0:
            case 1:
            case 2:
                return 0;
            case 3:
            case 4:
            case 5:
                return 1;
            case 6:
            case 7:
            case 8:
                return 2;
            default:
                return -1;
        }
    }

    private int getMacroY(int id) {
        switch (id) {
            case 0:
            case 3:
            case 6:
                return 0;
            case 1:
            case 4:
            case 7:
                return 1;
            case 2:
            case 5:
            case 8:
                return 2;
            default:
                return -1;
        }
    }

    
    private Boolean checkMacroBoardWin(String[][] macroBoard, int col, int row, String playerSymbol)
    {
        for(int i = 0; i < 3; i++){
            if(macroBoard[col][i] != playerSymbol)
                break;
            if(i == 2){
                return true;
            }
        }
        
        for(int i = 0; i < 3; i++){
            if(macroBoard[i][row] != playerSymbol)
                break;
            if(i == 2){
                return true;
            }
        }
        
        if(col == row){
            //we're on a diagonal
            for(int i = 0; i < 3; i++){
                if(macroBoard[i][i] != playerSymbol)
                    break;
                if(i == 2){
                    return true;
                }
            }
        }
        
        if(col + row == 2){
            for(int i = 0; i < 3; i++){
                if(macroBoard[i][(2)-i] != playerSymbol)
                    break;
                if(i == 2){
                    return true;
                }
            }
        }
        
        return false;
    }
}
