package com.mipt.tp.dungeon_sucker.Skills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Arrays;
import java.util.Random;

public class NonControllablePhysicallyDamageOneEntity extends Skill {
  boolean isUsedByHostile;

  public NonControllablePhysicallyDamageOneEntity(Weapon weapon, int damage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
  }

  public void use(HauntedRoom room) {
    System.out.println("choosing guy to punch");
    Entity[] enemies;
    int maxindex;
    if (this.isUsedByHostile) {
      enemies = room.friendlyEntities;
      maxindex = room.amountOfFriendlyEntities;
    } else {
      enemies = room.hostileEntities;
      maxindex = room.amountOfHostileEntities;
    }
    System.out.println(Arrays.toString(enemies));
    int index = new Random().nextInt(maxindex);
    Entity enemy = enemies[index];
    System.out.println("chosen index " + index);
    while (!enemy.isAlive) {
      index = new Random().nextInt(maxindex);
      enemy = enemies[index];
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(this.damage, "Physical");
  }
}