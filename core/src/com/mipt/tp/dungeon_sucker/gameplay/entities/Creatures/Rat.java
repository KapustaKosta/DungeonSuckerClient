package com.mipt.tp.dungeon_sucker.gameplay.entities.Creatures;

import com.mipt.tp.dungeon_sucker.Weapons.WeaponsForEnemies.RatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Rat extends Creature {
  RatClaws weapon;
  public Rat(int health, int power, int weight, boolean isHostile, HauntedRoom place, String name) {
    super(health, power, weight, isHostile, place, name);
    this.weapon = new RatClaws(this.power, this.isHostile);
    this.name = name;
  }

  public void makeMove() {
    System.out.println("RAT IS MOVING");
    this.weapon.use(this.place);
  }
}
