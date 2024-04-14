package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Skill {
  public int lastPower;
  public int power;
  protected String description;

  public void use(Room room) {
    this.lastPower = this.power;
  }

  public void setPower(int power) {
    this.lastPower = this.power;
    this.power = power;
  }
}
