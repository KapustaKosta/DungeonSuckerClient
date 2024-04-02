package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;
import java.util.Random;

public class NonControllableDamageOneEnemy extends DamagingSkill {
  boolean isUsedByHostile;

  public NonControllableDamageOneEnemy(Weapon weapon, int damage, boolean isUsedByHostile, String type) {
    this.weapon = weapon;
    this.damage = damage;
    this.isUsedByHostile = isUsedByHostile;
    this.description = "Deal " + this.damage + " to a random enemy";
    this.type = type;
  }

  public void use(Room room) {
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
    enemy.getDamaged(this.damage, this.type);
  }
}
