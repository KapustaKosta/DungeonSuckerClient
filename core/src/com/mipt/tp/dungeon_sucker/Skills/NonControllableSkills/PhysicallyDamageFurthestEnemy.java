package com.mipt.tp.dungeon_sucker.Skills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;

public class PhysicallyDamageFurthestEnemy extends Skill {
  boolean isUsedByHostile;
  public PhysicallyDamageFurthestEnemy(Weapon weapon, int damage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
    this.description = "Deal " + this.damage + " to the furthest enemy";
  }

  public void use(Room room) {
    Entity[] enemies;
    int index;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
      index = 0;
    } else {
      enemies = room.hostileEntities;
      index = room.amountOfHostileEntities - 1;
    }
    System.out.println(Arrays.toString(enemies));
    Entity enemy = enemies[index];
    while (!enemy.isAlive) {
      if(isUsedByHostile && index < room.amountOfFriendlyEntities-1){
        ++index;
      }else if(!this.isUsedByHostile && index > 0){
        --index;
      }
      enemy = enemies[index];
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(this.damage, "Physical");
  }
}
