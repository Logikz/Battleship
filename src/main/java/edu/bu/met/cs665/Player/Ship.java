package edu.bu.met.cs665.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Ship {

  private Collection<Point> cells = new ArrayList<>();
  int health;

  public abstract int getLength();
  public void setLocation(Collection<Point> points){
    this.cells = points;
  }

  boolean containsPoint(Point point) {
    return cells.contains(point);
  }

  boolean hit(Point point) {
    cells.remove(point);
    --health;
    return health <= 0;
  }
}
