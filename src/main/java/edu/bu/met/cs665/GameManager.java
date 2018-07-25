package edu.bu.met.cs665;

import edu.bu.met.cs665.Engine.GameEngine;
import edu.bu.met.cs665.Engine.Result;
import edu.bu.met.cs665.Grid.GridBuilder;
import edu.bu.met.cs665.Player.Battleship;
import edu.bu.met.cs665.Player.Cruiser;
import edu.bu.met.cs665.Player.Destroyer;
import edu.bu.met.cs665.Player.Player;
import edu.bu.met.cs665.Player.Ship;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GameManager {

  private static GameManager instance;

  private GameManager(){

  }

  public static GameManager getInstance(){
    if(instance == null){
      instance = new GameManager();
    }
    return instance;
  }

  public void start(){
    int width = 5;
    int height = 5;
    Scanner scanner = new Scanner(System.in);
    GridBuilder gridBuilder = new GridBuilder(width, height);

    System.out.println("Welcome to Battleship.");
    // Get player 1 name
    System.out.print("What is player 1's name?");
    String name = scanner.nextLine();
    Player p1 = new Player(gridBuilder.buildBasicGrid(),
        gridBuilder.buildGridWithRandomShips(Arrays.asList(new Battleship(), new Destroyer(), new Cruiser())),
        name);

    // Get player 2 name
    System.out.print("What is player 2's name?");
    name = scanner.nextLine();
    Player p2 = new Player(gridBuilder.buildBasicGrid(),
        gridBuilder.buildGridWithRandomShips(Arrays.asList(new Battleship(), new Destroyer(), new Cruiser())),
        name);

    GameEngine gameEngine = new GameEngine(p1, p2);

    // Decide start player gameEngine.pickStartPlayer
    Player currentPlayer = gameEngine.pickStartPlayer();

    // while current player.isNotDead()
    while(currentPlayer.hasShips()){
      clearScreen();
      // Show the player's grid
      System.out.println(currentPlayer.getName() + "'s turn");
      System.out.print(currentPlayer.showEnemyGrid());

      // read input of shooting location
      Point point = getLocation(scanner, width, height);
      // engine.shoot()

      Result result = gameEngine.shoot(point);
      // display result
      switch(result){
        case HIT:
          System.out.println("HIT!");
          break;
        case MISS:
          System.out.println("MISS!");
          break;
      }

      System.out.println("Press enter when the next player is ready");
      scanner.nextLine();

      // get current player
      currentPlayer = gameEngine.getCurrentPlayer();
    }


  }

  private Point getLocation(Scanner scanner, int width, int height) {
    System.out.print("Where would you like to shoot?");

    while(true){
      String location = scanner.nextLine();
      location = location.toUpperCase();
      if(location.length() != 2){
        System.out.println("Please input a valid coordinate, such as A4");
      } else {
        int column = ((int)location.charAt(0)) - 65; // Ascii beginning for 'A'
        if(column < 0 || column > width){
          System.out.println("Please input a valid column");
        } else {
          int row = ((int)location.charAt(1)) - 49; // Ascii beginning for '1'
          if(row < 0 || row > height){
            System.out.println("Please input a valid row");
          }else {
            return new Point(row, column);
          }
        }

      }
    }
  }

  // stolen from https://stackoverflow.com/questions/2979383/java-clear-the-console
  private void clearScreen() {
      System.out.print("\033[H\033[2J");
      System.out.flush();
  }
}
