package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.*;

import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import java.util.Scanner;

public class PhysicallyDamageOneEntity extends Skill {
  public PhysicallyDamageOneEntity(Weapon weapon, int damage) {
    this.weapon = weapon;
    this.damage = damage;
  }

  public void use(Room room) {
    Entity[] entities;
    if (room.isHaunted) {
      entities = ((HauntedRoom) room).hostileEntities;
    } else {
      return;
    }
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    for (int i = 0; i < ((HauntedRoom) room).hostileEntities.length; ++i) {
      if (((Creature) ((HauntedRoom) room).hostileEntities[i]).isAlive) {
        System.out.println(i + 1 + ": " + ((Creature) ((HauntedRoom) room).hostileEntities[i]).name);
      }
    }
    int index = in.nextInt();
    entities[index - 1].getDamaged(this.damage, "Physical");
  }

  public String toString() {
    return "punch one enemy by your choice, dealing " + this.damage + " damage";
  }
}
