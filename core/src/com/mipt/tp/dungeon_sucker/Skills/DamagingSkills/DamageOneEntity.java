package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Scanner;

public class DamageOneEntity extends DamagingSkill {
  public DamageOneEntity(Weapon weapon, int damage, String type) {
    this.weapon = weapon;
    this.damage = damage;
    this.description = "Deals " + this.damage + " damage to enemy by your choice";
    this.type = type;
  }


  public void use(Room room) {
    Entity[] entities;
    if (room.isHaunted) {
      entities = room.hostileEntities;
    } else {
      return;
    }
    Scanner in = new Scanner(System.in);
    HauntedRoom hauntedRoom = (HauntedRoom) room;
    System.out.println("choose enemy to punch");
    for (int i = 0; i < (hauntedRoom).hostileEntities.length; ++i) {
      if (hauntedRoom.hostileEntities[i].isAlive) {
        System.out.println(i + 1 + ": " + ((hauntedRoom.hostileEntities[i])).name + ": "
            + ((hauntedRoom).hostileEntities[i]).health + " hp");
      }
    }
    int index = in.nextInt();
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length - 1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    index = Math.min(Math.max(index - 1, 0), entities.length - 1);
    entities[index].getDamaged(this.damage, "Physical");
  }

  public String toString() {
    return "punch one enemy by your choice, dealing " + this.damage + " damage";
  }
}