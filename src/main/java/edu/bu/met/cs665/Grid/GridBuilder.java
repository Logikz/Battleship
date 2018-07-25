package edu.bu.met.cs665.Grid;

import edu.bu.met.cs665.Player.Ship;
import java.awt.Point;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class GridBuilder {


  private final int width;
  private final int height;

  public GridBuilder(int width, int height){
    this.width = width;
    this.height =  height;
  }

  public Grid buildBasicGrid(){
    return new Grid(width, height);
  }
  
  public Grid buildGridWithRandomShips(List<Ship> ships){
    Grid grid = new Grid(width, height);
    for(Ship ship: ships){
      int length = ship.getLength();

      Stack<Point> location = findLocation(grid, length);
      ship.setLocation(location);
      grid.addShip(ship, location);
    }

    return grid;
  }

  private Stack<Point> findLocation(Grid grid, int length) {
    Stack<Point> points = new Stack<>();
    Random random = new Random();

    while(points.size() != length){
      // pick a random point
      int x = random.nextInt(grid.getWidth());
      int y = random.nextInt(grid.getHeight());

      // pick a random orientation
      boolean vertical = random.nextBoolean();

      // try to place it recursively
      placePointRecursive(grid, new Point(x, y), vertical, points, length);
    }

    return points;
  }

  private void placePointRecursive(Grid grid, Point point, boolean vertical, Stack<Point> points, int length) {
    Cell cellType = grid.getCellType(point);
    if(cellType == null){
      return;
    }
    if (cellType instanceof WaterCell && points.size() < length && !points.contains(point)) {
      points.push(point);
      if (vertical) {
        //try a new point in a vertical direction
        placePointRecursive(grid, new Point(point.x, point.y + 1), vertical, points, length);
        placePointRecursive(grid, new Point(point.x, point.y - 1), vertical, points, length);
      } else {
        //try a new point in a horizontal direction
        placePointRecursive(grid, new Point(point.x +1, point.y), vertical, points, length);
        placePointRecursive(grid, new Point(point.x -1, point.y), vertical, points, length);
      }
    }
  }

}
