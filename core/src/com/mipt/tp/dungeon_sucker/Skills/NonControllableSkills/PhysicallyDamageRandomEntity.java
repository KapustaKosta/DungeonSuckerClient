package com.mipt.tp.dungeon_sucker.Skills.NonControllableSkills;

import InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import java.util.Random;

public class PhysicallyDamageRandomEntity extends Skill {
  public PhysicallyDamageRandomEntity(Weapon weapon, int damage, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.damage = damage;
    this.description = "Deal " + this.damage + " to the random entity";
  }

  public void use(Room room) {
    System.out.println("choosing guy to punch");
    Entity[] enemies = new Entity[room.amountOfHostileEntities + room.amountOfFriendlyEntities];
    int maxIndex = room.amountOfHostileEntities + room.amountOfFriendlyEntities;
    if (room.amountOfFriendlyEntities >= 0) {
      System.arraycopy(room.friendlyEntities, 0, enemies, 0, room.amountOfFriendlyEntities);
    }
    if (room.amountOfHostileEntities >= 0) {
      System.arraycopy(room.hostileEntities, 0, enemies, room.amountOfFriendlyEntities, room.amountOfHostileEntities);
    }
    int index = new Random().nextInt(maxIndex);
    Entity enemy = enemies[index];
    while (!enemy.isAlive) {
      index = new Random().nextInt(maxIndex);
      enemy = enemies[index];
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(this.damage, "Physical");
  }
}
