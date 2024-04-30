package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class Skill {
  public int lastPower = 1;
  public int power = 1;
  public int identifier;
  protected String description;

  public void use(Room room) {
    this.lastPower = this.power;
  }

  public void setPower(int power) {
    this.lastPower = this.power;
    this.power = power;
  }
}
