package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Creature extends Entity{
  protected boolean isSummoned = false;
  public Creature(int health, int power, int weight, boolean isHostile, Room place, String name) {
    super(health, weight, place, name);
    this.strength = power;
    this.isHostile = isHostile;
  }

  public void makeMove() {
    if (this.isSummoned) {
      super.makeMove();
    } else {
      this.summon();
    }
  }

  private void summon() {
    throw new NotImplementedException();
  }

  public void setPlace(Room room) {
    this.place = room;
  }

  public void damage(Entity who, Damage damage, String type) {
    who.getDamaged(damage);
  }

}
