package edu.bu.met.cs665.Grid;

import edu.bu.met.cs665.Player.Ship;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GridBuilder {

  private Grid baseGrid;

  public GridBuilder(int width, int height){
    baseGrid = new Grid(width, height);
  }

  public Grid buildBasicGrid(){
    try {
      return (Grid) baseGrid.clone();
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public Grid buildGridWithRandomShips(List<Ship> ships){
    try {
      Grid grid = (Grid) baseGrid.clone();
      for(Ship ship: ships){
        int length = ship.getLength();

        Stack<Point> location = placeOnGrid(length);
        location.stream().forEach(point -> grid.setCellType(point, new ShipCell()));
      }
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return null;
  }

  private Stack<Point> placeOnGrid(int length) {
    Stack<Point> points = new Stack<>();
    Random random = new Random();

    while(points.size() != length){
      // pick a random point
      int x = random.nextInt(baseGrid.getWidth());
      int y = random.nextInt(baseGrid.getHeight());

      // pick a random orientation
      boolean vertical = random.nextBoolean();

      // try to place it recursively
      placePointRecursive(new Point(x, y), vertical, points, length);
    }

    return points;
  }

  private void placePointRecursive(Point point, boolean vertical, Stack<Point> points, int length) {
    Cell cellType = baseGrid.getCellType(point);
    if(cellType == null){
      return;
    }
    if (cellType instanceof WaterCell && points.size() < length) {
      points.push(point);
      if (vertical) {
        //try a new point in a vertical direction
        placePointRecursive(new Point(point.x, point.y + 1), vertical, points, length);
        placePointRecursive(new Point(point.x, point.y - 1), vertical, points, length);
      } else {
        //try a new point in a horizontal direction
        placePointRecursive(new Point(point.x +1, point.y), vertical, points, length);
        placePointRecursive(new Point(point.x -1, point.y), vertical, points, length);
      }
    }
  }

}
