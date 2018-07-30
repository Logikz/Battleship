package edu.bu.met.cs665.Grid;

import edu.bu.met.cs665.Player.Ship;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Grid implements Cloneable{
  private Cell[][] grid;
  private final int width;
  private final int height;
  private List<Ship> ships;

  Grid(int width, int height) {
    grid = new Cell[width][height];
    this.width = width;
    this.height = height;
    for(int i = 0; i < width; ++i){
      for(int j = 0; j < height; ++j){
        grid[i][j] = new WaterCell();
      }
    }
    ships = new ArrayList<>();
  }


  int getWidth() {
    return width;
  }

  int getHeight() {
    return height;
  }

  public Cell getCellType(Point point) {
    if(point.x >= width || point.y >= height || point.x < 0 || point.y < 0){
      return null;
    }
    return grid[point.x][point.y];
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public void setCellType(Point point, Cell cell) {
    grid[point.x][point.y] = cell;
  }

  void addShip(Ship ship, Collection<Point> location) {
    location.forEach(point -> setCellType(point, new ShipCell()));
    ships.add(ship);
  }

  public List<Ship> getShips() {
    return ships;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    char cellSeparator = '|';

    // Print the column header
    stringBuilder.append(' ');
    stringBuilder.append(cellSeparator);
    for(int i = 0; i < width; ++i){
      stringBuilder.append((char) (i+65));
      stringBuilder.append(cellSeparator);
    }
    stringBuilder.append('\n');

    int rowLength = stringBuilder.length();
    stringBuilder.append(new String(new char[rowLength]).replace("\0", "="));
    stringBuilder.append('\n');

    for(int i = 0; i < height; ++i){
      stringBuilder.append(i + 1);
      stringBuilder.append(cellSeparator);
      for(int j = 0; j < width; ++j){
        stringBuilder.append(grid[i][j].displayCell());
        stringBuilder.append(cellSeparator);
      }
      stringBuilder.append('\n');
      stringBuilder.append(new String(new char[rowLength]).replace("\0", "="));
      stringBuilder.append('\n');
    }

    return stringBuilder.toString();
  }

  public void removeShip(Ship ship) {
    ships.remove(ship);
  }
}
