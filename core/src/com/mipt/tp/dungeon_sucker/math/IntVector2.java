package com.mipt.tp.dungeon_sucker.math;

public class IntVector2 {
  public int x;
  public int y;
  public IntVector2() {
    this.x = 0;
    this.y = 0;
  }
  public IntVector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public IntVector2(IntVector2 point) {
    this.x = point.x;
    this.y = point.y;
  }

  public void moveX(int deltaX)
  {
    x += deltaX;
  }

  public void moveY(int deltaY)
  {
    y += deltaY;
  }

  @Override
  public String toString()
  {
    return "x: " + this.x + ", y: " + this.y;
  }
}
