package org.example;

import java.util.Vector;

public class Player {

    private String name;
    private int id;
    private Vector<MatchScore> matchScoreList = new Vector<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector<MatchScore> getMatchScoreList() {
        return matchScoreList;
    }

    public void setMatchScoreList(Vector<MatchScore> matchScoreList) {
        this.matchScoreList = matchScoreList;
    }
}


