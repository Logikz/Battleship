package edu.bu.met.cs665.Player;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

public abstract class Ship {
  Collection<Point> cells = new ArrayList<>();
  int health;

  public abstract int getLength();
  public void setLocation(Collection<Point> points){
    this.cells = points;
  }
  public  boolean containsPoint(Point point){
    return cells.contains(point);
  }
  public boolean hit(){
    --health;
    return health <= 0;
  }
}
