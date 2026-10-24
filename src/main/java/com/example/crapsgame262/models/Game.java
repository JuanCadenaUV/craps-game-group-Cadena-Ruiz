package com.example.crapsgame262.models;

/**
 * Craps game logic. It knows nothing about the view: the controller rolls
 * through {@link #roll()} and reads the state with the getters.
 */
public class Game {

    /** Outcome of a single roll. */
    public enum Result {
        /** The player won the current game. */
        WIN,
        /** The player lost the current game. */
        LOSS,
        /** First roll that set a point; the game continues. */
        POINT_ESTABLISHED,
        /** A roll after the point that was neither the point nor 7; the game continues. */
        CONTINUE
    }

    private final Dice dice1 = new Dice();
    private final Dice dice2 = new Dice();

    /** Sum of the last roll, 0 if there is none yet. */
    private int lastRoll;

    /** Current point, 0 while no point is established (come-out roll). */
    private int point;

    private int gamesWon;
    private int gamesLost;

    /**
     * Rolls both dice and applies the Craps rules to the result.
     *
     * @return the outcome of this roll
     */
    public Result roll() {
        dice1.roll();
        dice2.roll();
        return evaluate(dice1.getValue() + dice2.getValue());
    }

    /**
     * Applies the Craps rules to a roll total. Public so the rules can be
     * tested without depending on random dice.
     *
     * @param total sum of the two dice, between 2 and 12
     * @return the outcome of this roll
     */
    public Result evaluate(int total) {
        lastRoll = total;

        if (point == 0) {
            return comeOutRoll(total);
        }
        return pointRoll(total);
    }

    private Result comeOutRoll(int total) {
        if (total == 7 || total == 11) {
            gamesWon++;
            return Result.WIN;
        }
        if (total == 2 || total == 3 || total == 12) {
            gamesLost++;
            return Result.LOSS;
        }
        point = total;
        return Result.POINT_ESTABLISHED;
    }

    private Result pointRoll(int total) {
        if (total == point) {
            point = 0;
            gamesWon++;
            return Result.WIN;
        }
        if (total == 7) {
            point = 0;
            gamesLost++;
            return Result.LOSS;
        }
        return Result.CONTINUE;
    }

    /** Clears the score and starts over. */
    public void reset() {
        lastRoll = 0;
        point = 0;
        gamesWon = 0;
        gamesLost = 0;
    }

    public Dice getDice1() {
        return dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public int getLastRoll() {
        return lastRoll;
    }

    public int getPoint() {
        return point;
    }

    public boolean hasPoint() {
        return point != 0;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }
}
