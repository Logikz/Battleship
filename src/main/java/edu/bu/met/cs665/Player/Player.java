package edu.bu.met.cs665.Player;

import edu.bu.met.cs665.Engine.Result;
import edu.bu.met.cs665.Grid.Cell;
import edu.bu.met.cs665.Grid.Grid;
import edu.bu.met.cs665.Grid.HitCell;
import edu.bu.met.cs665.Grid.MissCell;
import edu.bu.met.cs665.Grid.ShipCell;
import java.awt.Point;
import java.util.concurrent.atomic.AtomicReference;

public class Player {
  private Grid enemyGrid;
  private Grid myGrid;
  private String name;

  public Player(Grid enemyGrid, Grid myGrid, String name) {
    this.enemyGrid = enemyGrid;
    this.myGrid = myGrid;
    this.name = name;
  }

  public Result ask(Point point){
    // check my grid to see if it has a ship or not
    Cell cellType = myGrid.getCellType(point);

    if(cellType instanceof ShipCell){
      AtomicReference<Ship> sunkShip = new AtomicReference<>();
      //figure out which ship it is to hit
      myGrid.getShips().stream().filter(ship -> ship.containsPoint(point)).forEach(ship -> {
        if (ship.hit(point)) {
          System.out.println("You sunk my " + ship.toString());
          sunkShip.set(ship);
        }
      });

      if (sunkShip.get() != null) {
        myGrid.removeShip(sunkShip.get());
      }

      return Result.HIT;
    }
    return Result.MISS;
  }

  public boolean hasShips() {
    return !myGrid.getShips().isEmpty();
  }

  public String showEnemyGrid() {
    return enemyGrid.toString();
  }

  public void markGrid(Point location, Result result) {
    switch (result){
      case HIT:
        enemyGrid.setCellType(location, new HitCell());
        break;
      case MISS:
        enemyGrid.setCellType(location, new MissCell());
        break;
    }
  }

  public String getName() {
    return name;
  }
}
