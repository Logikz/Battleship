package edu.bu.met.cs665;

public class GameManager {

  private GameManager instance;

  private GameManager(){

  }

  public GameManager getInstance(){
    if(instance == null){
      instance = new GameManager();
    }
    return instance;
  }

  public void start(){
    // Get player 1 name
    // Get player 2 name

    // Create random maps for player 1 and player 2, gameEngine.init

    // Decide start player gameEngine.pickStartPlayer

    // while current player.isNotDead()

      // Show the player's grid

      // read input of shooting location

      // engine.shoot()

      // display result

      // get current player
  }
}
