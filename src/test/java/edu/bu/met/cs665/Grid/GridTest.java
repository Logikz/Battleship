package edu.bu.met.cs665.Grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.bu.met.cs665.Player.Battleship;
import edu.bu.met.cs665.Player.Cruiser;
import edu.bu.met.cs665.Player.Destroyer;
import edu.bu.met.cs665.Player.Ship;
import java.awt.Point;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GridTest {

  @Test
  void testGridWithShip() {
    Grid grid = new Grid(2, 2);

    for (int i = 0; i < 2; ++i) {
      for (int j = 0; j < 2; ++j) {
        Point currentLoc = new Point(i, j);
        assertTrue(grid.getCellType(currentLoc) instanceof WaterCell);
      }
    }

    Cruiser cruiser = new Cruiser();
    List<Point> points = Arrays.asList(new Point(0, 0), new Point(0, 1));
    cruiser.setLocation(points);
    grid.addShip(cruiser, points);

    for (int i = 0; i < 2; ++i) {
      for (int j = 0; j < 2; ++j) {
        Point currentLoc = new Point(i, j);
        if (points.contains(currentLoc)) {
          assertTrue(grid.getCellType(currentLoc) instanceof ShipCell);
        } else {
          assertTrue(grid.getCellType(currentLoc) instanceof WaterCell);
        }
      }
    }
  }

  @Test
  void testGridBuilder() {
    List<Ship> ships = Arrays.asList(new Battleship(), new Cruiser(), new Destroyer());
    Grid grid = new GridBuilder(5, 5).buildGridWithRandomShips(ships);

    assertEquals(ships, grid.getShips());


  }
}
