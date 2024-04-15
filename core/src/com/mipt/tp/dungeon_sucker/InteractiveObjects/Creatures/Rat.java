package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.RatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

public class Rat extends Creature {
  final int BASE_HEALTH = 3;
  final int BASE_POWER = 1;
  final int BASE_WEIGHT = 3;
  final int DEX_PER_LVL = 2;
  final int STR_PER_LVL = 1;
  final int VIG_PER_LVL = 1;
  public Rat(boolean isHostile, HauntedRoom place, String name) {
    super(3, 1, 3, isHostile, place, name);
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
  public void summon(){
    this.strength = this.STR_PER_LVL * this.master.level;
    this.dexterity = this.DEX_PER_LVL * this.master.level;
    this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
    this.weapon.recount();
  }

  public void makeMove() {
    if (this.isSummoned) {
      System.out.println("RAT IS MOVING");
      this.weapon.use(this.place);
    }
    super.makeMove();
  }
}
