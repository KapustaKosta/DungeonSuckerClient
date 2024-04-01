package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Random;
import java.util.Scanner;

public class PhysicallyDamageOneEntityWithCrit extends PhysicallyDamageOneEntity {
  private final int divider;
  int numerator;
  int criticalDamage;
  public PhysicallyDamageOneEntityWithCrit(Weapon weapon, int damage, int numerator, int divider, double multiplier) {
    super(weapon, damage);
    this.criticalDamage = (int) (this.damage * multiplier);
    this.divider = divider;
    this.numerator = numerator;
    this.description = "Deals " + this.damage + " damage to enemy by your choice, has " + numerator + "/" + divider + "chance to deal" + (this.damage * multiplier) + " damage instead";
  }
  public void use(Room room) {
    int a = new Random().nextInt(this.divider);
    int damage = this.damage;
    if(a < numerator){
      damage = this.criticalDamage;
    }
    Entity[] entities;
    if (room.isHaunted) {
      entities = ((HauntedRoom) room).hostileEntities;
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
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length-1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    index = Math.min(Math.max(index - 1, 0), entities.length-1);
    entities[index].getDamaged(damage, "Physical");
  }
}
