package edu.bu.met.cs665.Engine;

import edu.bu.met.cs665.Player.Player;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {

  private List<Player> players;
  private int currentPlayer = 0;

  public GameEngine(Player p1, Player p2) {
    players = new ArrayList<>();
    players.add(p1);
    players.add(p2);
  }

  public Result shoot(Point location){
    // ask opponent location
    Player opponent = players.get((currentPlayer + 1) % players.size());
    Result result = opponent.ask(location);

    // update player state
    players.get(currentPlayer).markGrid(location, result);

    // change player order
    currentPlayer += (currentPlayer + 1) % players.size();

    // return result
    return result;
  }

  public Player pickStartPlayer(){
    Random r = new Random();

    currentPlayer = r.nextInt(players.size());
    return players.get(currentPlayer);
  }

  public Player getCurrentPlayer() {
    return players.get(currentPlayer);
  }
}
