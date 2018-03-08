package ultimatetictactoe.bll.game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ultimatetictactoe.bll.bot.IBot;
import ultimatetictactoe.bll.field.GameField;
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

        //Update the currentState
        UpdateBoard(move);
        checkMacroBoardWin();

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
        if (mode == GameMode.HumanVsComputer) {
            IMove botMove = bot.doMove(currentState);
            return UpdateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.ComputerVsComputer) {
            assert (bot != null);
            assert (bot2 != null);
            IMove botMove = currentPlayer == 0 ? bot.doMove(currentState) : bot2.doMove(currentState);
            return UpdateGame(botMove);
        }
        return false;
    }

    /*public void setNewActiveMicroboard(int id) {
        int x = getMacroX(id);
        int y = getMacroY(id);
        String newActiveBoard[][] = new String[3][3];
        newActiveBoard[x][y] = IField.AVAILABLE_FIELD;
        currentState.getField().setMacroboard(newActiveBoard);
    }*/
    private Boolean VerifyMoveLegality(IMove move) {
        //Test if the move is legal   
        //NOTE: should also check whether the move is placed on an occupied spot.
        System.out.println("Checking move validity against macroboard available field");
        System.out.println("Not currently checking move validity actual board");
        Boolean legal = true;
        /*if (currentState.getField().getMacroboard()[move.getX() / 3][move.getY() / 3] != IField.AVAILABLE_FIELD) {
            legal = false;
        }*/
        if (currentState.getField().getBoard()[move.getX()][move.getY()] != IField.EMPTY_FIELD) {
            legal = false;
        }
        if (!currentState.getField().isInActiveMicroboard(move.getX(), move.getY())) {
            legal = false;
        }
        return legal;
    }

    private void UpdateBoard(IMove move) {
        GameField field = (GameField) currentState.getField();
        field.updateBoard(move.getX(), move.getY(), (byte) currentPlayer);
        //System.out.println(checkMicroboardWin());
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

    public Boolean checkMicroboardWin() {
        Boolean checkwin = false;
        String[][] board = currentState.getField().getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int ix = i * 3;
                int jx = j * 3;
                for (int k = 0; k < 3; k++) {
                    if (board[ix + k][jx] != IField.EMPTY_FIELD && board[ix + k][jx + 1] == board[ix + k][jx] && board[ix + k][jx + 2] == board[ix + k][jx]) {
                        setMacroWin(i, j);
                        checkwin = true;
                    }
                    if (board[jx][ix + k] != IField.EMPTY_FIELD && board[jx + 1][ix + k] == board[jx][ix + k] && board[jx + 2][ix + k] == board[jx][ix + k]) {
                        setMacroWin(i, j);
                        checkwin = true;
                    }
                }
                if (board[jx][ix] != IField.EMPTY_FIELD && board[jx + 1][ix + 1] == board[jx][ix] && board[jx + 2][ix + 2] == board[jx][ix]) {
                    setMacroWin(i, j);
                    checkwin = true;
                }
                if (board[jx + 2][ix] != IField.EMPTY_FIELD && board[jx + 1][ix + 1] == board[jx + 2][ix] && board[jx][ix + 2] == board[jx + 1][ix + 1]) {
                    setMacroWin(i, j);
                    checkwin = true;
                }
            }
        }
        return checkwin;
    }

    private void setMacroWin(int col, int row) {
        String[][] uboard = currentState.getField().getMacroboard();
        System.out.println(uboard[col][row]);
        if (uboard[col][row].equals(IField.AVAILABLE_FIELD)) {
            uboard[row][col] = String.valueOf(this.currentPlayer);
            currentState.getField().setMacroboard(uboard);
        }
    }

    //TODO: make this not ugly as fuck
    private Boolean checkMacroBoardWin() {
        String[][] macroBoard = currentState.getField().getMacroboard();
        for (int i = 0; i < 3; i++) {
            if (macroBoard[i][0] != null && macroBoard[i][1] == macroBoard[i][0] && macroBoard[i][2] == macroBoard[i][0]) {
                return true;
            }
            if (macroBoard[0][i] != null && macroBoard[i][1] == macroBoard[i][0] && macroBoard[i][2] == macroBoard[i][0]) {
                return true;
            }
        }
        if (macroBoard[1][1] != null && macroBoard[0][0] == macroBoard[1][1] && macroBoard[2][2] == macroBoard[1][1]) {
            return true;
        }
        if (macroBoard[1][1] != null && macroBoard[2][0] == macroBoard[1][1] && macroBoard[0][2] == macroBoard[1][1]) {
            return true;
        }
        return false;
    }

    public String[][] getMacroboardWins() {
        checkMicroboardWin();
        return currentState.getField().getMacroboard();
    }

    public int getFieldState(int x, int y) {
        return currentState.getField().getBoard()[x][y].equals("X") ? 0 : 1;
    }

}
