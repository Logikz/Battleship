package edu.bu.met.cs665.Player;

public class Cruiser extends Ship {

  public Cruiser() {
    this.health = 2;
  }

  @Override
  public int getLength() {
    return 2;
  }

  @Override
  public String toString() {
    return "Cruiser";
  }
}
