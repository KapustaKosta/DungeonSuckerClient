package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.DeadlineIsComing;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class IvanKalinin extends Creature {
  public IvanKalinin(int health, int power, int weight, boolean isHostile, Room place, String name) {
    super(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, true, place, "IVAN KALININ");
    this.weapon = new DeadlineIsComing();
    this.weapon.getObtained(this);
    this.description = "Ultimate reviewer";
  }
  public void summon(){
    this.strength = Integer.MAX_VALUE;
    this.dexterity = Integer.MAX_VALUE;
    this.intellect = Integer.MAX_VALUE;
  }
  public int startMove(){
    int index = new Random().nextInt(this.weapon.skills.length);
    this.indexOfSkillToBeUsed = index;
    return this.weapon.creatureSkills[index].identifier;
  }
  public void makeMove() {
    if (this.isSummoned && this.isFighting) {
      System.out.println("YOUR CODESTYLE SUCKS!");
      this.weapon.useByCreature(this.place, indexOfSkillToBeUsed);
    }
    super.makeMove();
  }
}
