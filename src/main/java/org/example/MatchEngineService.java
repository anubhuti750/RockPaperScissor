package org.example;

import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import static org.example.Choice.*;
import static org.example.MatchStatus.*;

public class MatchEngineService implements GameEngine {

    public Vector<Player> createPlayer() {
        /*one time call
        1. create 4 player here and assign then data
        * */
        Player p1,p2,p3,p4;

        p1=new Player();
        p2=new Player();
        p3=new Player();
        p4=new Player();

        p1.setId(1);
        p2.setId(2);
        p3.setId(3);
        p4.setId(4);

        p1.setName("Asha");
        p2.setName("Raj");
        p3.setName("Pinky");
        p4.setName("Rohan");

        Vector<Player> players = new Vector<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        return players;
    }

    public Vector<MatchEngine> createMatchEngine(){
        Vector<MatchEngine> matchEngines = new Vector<>();
        //inform someone if needed
        return matchEngines;
    }

    @Override
    public void startGame(){
        /*
        1.play game 50 times
            2.get 2 player for each matcha
            3.get both player choice for each match
            4.decide the winner for each match
            5.save choice to respective player
            6.save data after match for each match on matchEngine
        7.print 50 match score
        */

        Vector<Player> players = this.createPlayer();
        Vector<MatchEngine> matchEngines = this.createMatchEngine();

        for(int i=0;i<50;i++){

            Player player1 = this.getPlayerForMatch(players);
            Player player2 = this.getPlayerForMatch(players);

            while(player1.getId() == player2.getId()){
                player2 = this.getPlayerForMatch(players);
            }
            Choice player1Choice= this.getPlayerChoiceForMatch();
            Choice player2Choice= this.getPlayerChoiceForMatch();

            int winnerId = this.getWinnerForMatch(player1Choice,player2Choice);

            int winnerPlayerId = 0;
            if(winnerId == 1){
                winnerPlayerId = player1.getId();
            }else if(winnerId == 2){
                winnerPlayerId = player2.getId();
            }

            int matchId = i+1;
            this.savePlayerScoreForMatch(player1,player2,player1Choice,winnerPlayerId,matchId);
            this.savePlayerScoreForMatch(player2,player1,player2Choice,winnerPlayerId,matchId);

            this.saveMatchDataInMatchEngine(player1,player2,matchId,winnerPlayerId,matchEngines);
        }

        this.printMatchData(matchEngines);
        this.printEachPlayerWinCount(players);

    }

    public void savePlayerScoreForMatch(Player player,Player enemy,Choice choice,int winnerPlayerId,int matchId){
        MatchScore matchScore = new MatchScore();
        matchScore.setChoice(choice);
        matchScore.setEnemy(enemy);
        matchScore.setMatchId(matchId);
        MatchStatus matchStatus = TIE;
        if(winnerPlayerId == player.getId()){
            matchStatus = WIN;
        }else if(winnerPlayerId == enemy.getId()){
            matchStatus = LOSE;
        }
        matchScore.setMatchStatus(matchStatus);

        Vector<MatchScore> matchScoreVector = player.getMatchScoreList();
        matchScoreVector.add(matchScore);

    }

    public int getWinnerForMatch(Choice choice1,Choice choice2){
        int winnerId = 0;
        if(choice1==choice2){
            return  winnerId;
        }
        if(choice1.equals(ROCK) && choice2 == PAPER){
            winnerId = 2;
        }
        else if(choice1.equals(PAPER) && choice2.equals(ROCK)){
            winnerId=1;
        }
        else if(choice1.equals(ROCK) && choice2.equals(SCISSOR)){
            winnerId=1;
        }
        else if(choice1.equals(SCISSOR) && choice2.equals(ROCK)){
            winnerId=2;
        }
        else if(choice1.equals(PAPER) && choice2.equals(SCISSOR)){
            winnerId=2;
        }
        else if(choice1.equals(SCISSOR) && choice2.equals(PAPER)){
            winnerId=1;
        }
        return winnerId;
    }

    public Choice getPlayerChoiceForMatch(){
        int choiceId = ThreadLocalRandom.current().nextInt(0, 3);
        Choice[] choiceArray = Choice.values();
        return choiceArray[choiceId];
    }

    public Player getPlayerForMatch(Vector<Player> players){
        int playerIndexId = ThreadLocalRandom.current().nextInt(0, 4);
        Player player = players.get(playerIndexId);
        return player;
    }

    public void saveMatchDataInMatchEngine(Player player1,Player player2,int matchId,int winnerId,Vector<MatchEngine> matchEngineVector){
        MatchEngine matchEngine = new MatchEngine();
        matchEngine.setPlayerOne(player1);
        matchEngine.setPlayerTwo(player2);
        matchEngine.setMatchId(matchId);
        matchEngine.setWinnerId(winnerId);

        matchEngineVector.add(matchEngine);
    }

    public void printMatchData(Vector<MatchEngine> matchEngines){
        for(MatchEngine matchEngine:matchEngines){
            System.out.println("Match Id "+matchEngine.getMatchId());
            System.out.println("Player 1 id : "+matchEngine.getPlayerOne().getId()+
                    " | Player 2 id : "+matchEngine.getPlayerTwo().getId());

            Vector<MatchScore> matchScoreList = matchEngine.getPlayerOne().getMatchScoreList();
            for(MatchScore matchScore:matchScoreList){
                if(matchScore.getMatchId() == matchEngine.getMatchId()){
                    System.out.println("Player 1 choice ::"+matchScore.getChoice());
                }
            }
            Vector<MatchScore> matchScoreListPlayer2 = matchEngine.getPlayerTwo().getMatchScoreList();
            for(MatchScore matchScore:matchScoreListPlayer2){
                if(matchScore.getMatchId() == matchEngine.getMatchId()){
                    System.out.println("Player 2 choice ::"+matchScore.getChoice());
                }
            }
            if(matchEngine.getWinnerId()==0){
                System.out.println("Match is tie.");
            }
            else
            System.out.println("Player Id of winner :: "+matchEngine.getWinnerId());
        }

    }

    void printEachPlayerWinCount(Vector<Player> players){
        for(Player player: players){
            Vector<MatchScore> matchScoreVector = player.getMatchScoreList();
            int winCount = 0;
            for(MatchScore matchScore: matchScoreVector){
                if(matchScore.getMatchStatus().equals(WIN)){
                    winCount++;
                }
            }
            System.out.println("Player id "+player.getId()+", name "+player.getName()+
                    " win count "+winCount);
        }
    }
}
