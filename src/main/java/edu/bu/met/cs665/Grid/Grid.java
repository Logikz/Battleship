package edu.bu.met.cs665.Grid;

import java.awt.Point;

public class Grid implements Cloneable{
  private Cell[][] grid;
  private final int width;
  private final int height;

  Grid(int width, int height) {
    grid = new WaterCell[width][height];
    this.width = width;
    this.height = height;
  }


  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public Cell getCellType(Point point) {
    if(point.x > width || point.y > height || point.x < 0 || point.y < 0){
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
}
