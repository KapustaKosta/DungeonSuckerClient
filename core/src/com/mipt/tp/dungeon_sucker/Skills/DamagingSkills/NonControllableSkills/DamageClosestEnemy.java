package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;

public class DamageClosestEnemy extends DamagingSkill {
  boolean isUsedByHostile;
  public DamageClosestEnemy(Weapon weapon, int damage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
    this.description = "Deal " + this.damage + " to the closest enemy";
  }

  public void use(Room room) {
    Entity[] enemies;
    int index;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
      index = room.amountOfFriendlyEntities - 1;
    } else {
      enemies = room.hostileEntities;
      index = 0;
    }
    System.out.println(Arrays.toString(enemies));
    Entity enemy = enemies[index];
    while (!enemy.isAlive) {
      if(isUsedByHostile && index > 0){
        --index;
      }else if(!this.isUsedByHostile && index < room.amountOfHostileEntities - 1){
        ++index;
      }
      enemy = enemies[index];
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(this.damage, this.type);
  }
}
