package edu.bu.met.cs665.Engine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.bu.met.cs665.Grid.Grid;
import edu.bu.met.cs665.Grid.GridBuilder;
import edu.bu.met.cs665.Grid.ShipCell;
import edu.bu.met.cs665.Grid.WaterCell;
import edu.bu.met.cs665.Player.Cruiser;
import edu.bu.met.cs665.Player.Player;
import java.awt.Point;
import java.util.Collections;
import org.junit.jupiter.api.Test;

public class EngineTest {

  int TEST_WIDTH = 2;
  int TEST_HEIGHT = 2;

  @Test
  void testPlayerSwitch() {
    Grid basicGrid = new GridBuilder(1, 1).buildBasicGrid();
    Player p1 = new Player(basicGrid, basicGrid, "p1");
    Player p2 = new Player(basicGrid, basicGrid, "p2");

    GameEngine engine = new GameEngine(p1, p2);

    assertEquals(engine.getCurrentPlayer(), p1);
    assertEquals(engine.getOpponent(), p2);
    engine.shoot(new Point(0, 0));
    assertEquals(engine.getCurrentPlayer(), p2);
    assertEquals(engine.getOpponent(), p1);
  }

  @Test
  void testHit() {
    Cruiser cruiser = new Cruiser();

    Grid shipGrid = new GridBuilder(TEST_WIDTH, TEST_HEIGHT)
        .buildGridWithRandomShips(Collections.singletonList(cruiser));

    Point shipPoint = null;
    for (int i = 0; i < TEST_WIDTH && shipPoint == null; ++i) {
      for (int j = 0; j < TEST_HEIGHT && shipPoint == null; ++j) {
        if (shipGrid.getCellType(new Point(i, j)) instanceof ShipCell) {
          shipPoint = new Point(i, j);
        }
      }
    }

    Player p1 = new Player(new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(),
        new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(), "p1");
    Player p2 = new Player(new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(),
        shipGrid, "p2");

    GameEngine engine = new GameEngine(p1, p2);

    Result res = engine.shoot(shipPoint);
    assertEquals(res, Result.HIT);

  }

  @Test
  void testMiss() {
    Cruiser cruiser = new Cruiser();

    Grid shipGrid = new GridBuilder(TEST_WIDTH, TEST_HEIGHT)
        .buildGridWithRandomShips(Collections.singletonList(cruiser));

    Point waterPoint = null;
    for (int i = 0; i < TEST_WIDTH && waterPoint == null; ++i) {
      for (int j = 0; j < TEST_HEIGHT && waterPoint == null; ++j) {
        if (shipGrid.getCellType(new Point(i, j)) instanceof WaterCell) {
          waterPoint = new Point(i, j);
        }
      }
    }

    Player p1 = new Player(new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(),
        new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(), "p1");
    Player p2 = new Player(new GridBuilder(TEST_WIDTH, TEST_HEIGHT).buildBasicGrid(),
        shipGrid, "p2");

    GameEngine engine = new GameEngine(p1, p2);

    Result res = engine.shoot(waterPoint);
    assertEquals(res, Result.MISS);

  }
}
