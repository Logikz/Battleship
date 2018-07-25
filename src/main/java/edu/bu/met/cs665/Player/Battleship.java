package edu.bu.met.cs665.Player;

public class Battleship extends Ship {

  public Battleship() {
    this.health = 4;
  }

  @Override
  public int getLength() {
    return 4;
  }

  @Override
  public String toString() {
    return "Battleship";
  }
}
