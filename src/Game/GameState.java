// src/Game/GameState.java
package Game;

public class GameState {
    private static GameState instance;
    private String lastMoveFrom;
    private String lastMoveTo;
    private boolean isMyTurn;
    private String winner;

    private GameState() {
        lastMoveFrom = null;
        lastMoveTo = null;
        isMyTurn = false;
        winner = null;
    }

    public static synchronized GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public synchronized String getLastMove() {
        if (lastMoveFrom == null || lastMoveTo == null) {
            return "null";
        }
        String move = lastMoveFrom + "->" + lastMoveTo;
        return move;
    }

    public synchronized void setLastMove(String from, String to) {
        this.lastMoveFrom = from;
        this.lastMoveTo = to;
    }

    public synchronized void clearLastMove() {
        this.lastMoveFrom = null;
        this.lastMoveTo = null;
    }

    public synchronized boolean isMyTurn() {
        return isMyTurn;
    }

    public synchronized void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public synchronized String getWinner() {
        return winner;
    }

    public synchronized void setWinner(String winner) {
        this.winner = winner;
    }
}