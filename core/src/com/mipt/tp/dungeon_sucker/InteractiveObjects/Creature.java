package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

public class Creature extends Entity {
  protected int power;

  public Creature(int health, int power, int weight, boolean isHostile, HauntedRoom place, String name) {
    super(health, weight, place, name);
    this.power = power;
    this.isHostile = isHostile;
  }

  public void makeMove() {
    throw new NotImplementedException();
  }

  public void setPlace(HauntedRoom room) {
    this.place = room;
  }

  public void damage(Entity who, Damage damage, String type) {
    who.getDamaged(damage);
  }

}
