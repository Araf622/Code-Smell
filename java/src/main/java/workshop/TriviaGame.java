package workshop;

import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList<String> players = new ArrayList<String>();
    ArrayList<Integer> places = new ArrayList<Integer>();
    ArrayList<Integer> purses = new ArrayList<Integer>();
    ArrayList<Boolean> inPenaltyBox = new ArrayList<Boolean>();

    LinkedList popQuestions = new LinkedList();
    LinkedList scienceQuestions = new LinkedList();
    LinkedList sportsQuestions = new LinkedList();
    LinkedList rockQuestions = new LinkedList();

    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (int i = 0; i < 50; i++) {
            popQuestions.addLast("Pop Question " + i);
            scienceQuestions.addLast(("Science Question " + i));
            sportsQuestions.addLast(("Sports Question " + i));
            rockQuestions.addLast("Rock Question " + i);
        }
    }

    public boolean isPlayable() {
        return (players.size() >= 2);
    }

    public boolean addPlayerInfo(String playerName) {
        players.add(playerName);
        places.add(0);
        purses.add(0);
        inPenaltyBox.add(false);
        System.out.println(playerName + " was added");
        System.out.println("They are player number " + players.size());
        return true;
    }


    public void roll(int roll) {
        System.out.println(players.get(currentPlayer) + " is the current player");
        System.out.println("They have rolled a " + roll);

        if (inPenaltyBox.get(currentPlayer)) {
           insidePenaltyBox(roll);
        } else {
            outsidePenaltyBox(roll);
        }

    }

    public void insidePenaltyBox(int roll){
        if (roll % 2 != 0) {
            isGettingOutOfPenaltyBox = true;

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
            outsidePenaltyBox(roll);
        } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
            isGettingOutOfPenaltyBox = false;
        }
    }

    public void outsidePenaltyBox(int roll){
        places.add(places.get(currentPlayer) + roll);
        if (places.get(currentPlayer) > 11) places.add(places.get(currentPlayer) - 12);

        System.out.println(players.get(currentPlayer)
                + "'s new location is "
                + places.get(currentPlayer));
        System.out.println("The category is " + currentCategory());
        askQuestion();
    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            System.out.println(popQuestions.removeFirst());
        if (currentCategory() == "Science")
            System.out.println(scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            System.out.println(sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            System.out.println(rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places.get(currentPlayer)%4==0) return "Pop";
        else if (places.get(currentPlayer)%4==1) return "Science";
        else if (places.get(currentPlayer)%4==2) return "Sports";
        return "Rock";
    }

    public boolean wasCorrectlyAnswered(){
        if (inPenaltyBox.get(currentPlayer)) {
            if (isGettingOutOfPenaltyBox) {
               return correctAnswer();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {
            return correctAnswer();
        }
    }

    public boolean correctAnswer(){
        System.out.println("Answer was correct!!!!");
        purses.set(currentPlayer,currentPlayer+1);
        System.out.println(players.get(currentPlayer)
                + " now has "
                + purses.get(currentPlayer)
                + " Gold Coins.");

        boolean winner = didPlayerWin();
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }


    private boolean didPlayerWin() {
        return !(purses.get(currentPlayer) == 6);
    }

}