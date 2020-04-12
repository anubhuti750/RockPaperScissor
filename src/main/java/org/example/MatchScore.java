package org.example;

public class MatchScore {
    private int matchId;
    private Player enemy;
    private MatchStatus matchStatus;
    private Choice choice;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public Player getEnemy() {
        return enemy;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(MatchStatus matchStatus) {
        this.matchStatus = matchStatus;
    }

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }
}
