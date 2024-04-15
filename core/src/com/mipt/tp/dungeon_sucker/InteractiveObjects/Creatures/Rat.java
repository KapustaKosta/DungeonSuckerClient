package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.RatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Rat extends Creature {
  public Rat(int health, int power, int weight, boolean isHostile, HauntedRoom place, String name) {
    super(health, power, weight, isHostile, place, name);
    this.weapon = new RatClaws(this.power, this.isHostile);
    this.name = name;
    this.description = this.name + ", a rat, that crawls everywhere, may bite you, dealing " + this.power + "of physical damage";
  }
  public Rat(int health, int power, int weight, boolean isHostile, HauntedRoom place) {
    super(health, power, weight, isHostile, place, "Rat");
    this.weapon = new RatClaws(this.power, this.isHostile);
    this.name = "Rat";
    this.description = this.name + ", that crawls everywhere, may bite you, dealing " + this.power + "of physical damage";
  }

  public void makeMove() {
    System.out.println("RAT IS MOVING");
    this.weapon.use(this.place);
    super.makeMove();
  }
}
