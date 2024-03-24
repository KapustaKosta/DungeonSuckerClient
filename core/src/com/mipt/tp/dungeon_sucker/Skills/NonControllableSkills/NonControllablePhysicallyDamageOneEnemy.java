package com.mipt.tp.dungeon_sucker.Skills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Arrays;
import java.util.Random;

public class NonControllablePhysicallyDamageOneEnemy extends Skill {
  boolean isUsedByHostile;

  public NonControllablePhysicallyDamageOneEnemy(Weapon weapon, int damage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
    this.description = "Deal " + this.damage + " to a random enemy";
  }

  public void use(HauntedRoom room) {
    System.out.println("choosing guy to punch");
    Entity[] enemies;
    int maxIndex;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
      maxIndex = room.amountOfFriendlyEntities;
    } else {
      enemies = room.hostileEntities;
      maxIndex = room.amountOfHostileEntities;
    }
    System.out.println(Arrays.toString(enemies));
    int index = new Random().nextInt(maxIndex);
    Entity enemy = enemies[index];
    System.out.println("chosen index " + index);
    while (!enemy.isAlive) {
      index = new Random().nextInt(maxIndex);
      enemy = enemies[index];
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(this.damage, "Physical");
  }
}
