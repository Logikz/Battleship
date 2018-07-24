package edu.bu.met.cs665.Player;

import edu.bu.met.cs665.Engine.Result;
import edu.bu.met.cs665.Grid.Grid;

public class Player {
  private Grid enemyGrid;
  private Grid myGrid;
  private String name;

  public Player(Grid enemyGrid, Grid myGrid, String name) {
    this.enemyGrid = enemyGrid;
    this.myGrid = myGrid;
    this.name = name;
  }

  public Result ask(int x, int y){
    // check my grid to see if it has a ship or not

    return Result.MISS;
  }
}
