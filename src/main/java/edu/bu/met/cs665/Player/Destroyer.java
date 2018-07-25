package edu.bu.met.cs665.Player;

public class Destroyer extends Ship {
  public Destroyer() {
    this.health = 3;
  }

  @Override
  public int getLength() {
    return 3;
  }

  @Override
  public String toString() {
    return "Destroyer";
  }
}
