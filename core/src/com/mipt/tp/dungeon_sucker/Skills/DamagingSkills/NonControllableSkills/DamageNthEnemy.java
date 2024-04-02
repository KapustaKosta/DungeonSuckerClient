package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;

public class DamageNthEnemy extends DamagingSkill {
  int number;
  boolean isUsedByHostile;

  public DamageNthEnemy(Weapon weapon, int damage, boolean isUsedByHostile, int number, String type) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
    this.number = number;
    this.description = "Deal " + this.damage + " to enemy on place " + this.number + " if impossible, does nothing";
  }

  public void use(Room room) {
    Entity[] enemies;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
    } else {
      enemies = room.hostileEntities;
    }
    System.out.println(Arrays.toString(enemies));
    Entity enemy = enemies[this.number - 1];
    if (enemy != null && enemy.isAlive) {
      System.out.println("punching " + enemy.name);
      enemy.getDamaged(this.damage, this.type);
    } else {
      return;
    }
  }
}
