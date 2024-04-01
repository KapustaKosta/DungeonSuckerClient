package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;

import java.util.Scanner;

public class PhysicallyDamageThreeEntities extends Skill {
  double firstCoefficient;
  double secondCoefficient;
  double thirdCoefficient;

  public PhysicallyDamageThreeEntities(Weapon weapon, int damage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.weapon = weapon;
    this.firstCoefficient = firstCoefficient;
    this.secondCoefficient = secondCoefficient;
    this.thirdCoefficient = thirdCoefficient;
    this.damage = damage;
    this.description = "deals " + this.damage * this.secondCoefficient + " damage to enemy by your choice." +
        " Also if possible deals " + this.damage * this.secondCoefficient + " damage to enemy before him and " +
        this.damage * this.secondCoefficient + " damage to enemy right after him";
  }

  public void use(Room room) {
    if (!room.isHaunted) {
      return;
    }
    Entity[] entities = ((HauntedRoom) room).hostileEntities;
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    HauntedRoom hauntedRoom = (HauntedRoom) room;
    for (int i = 0; i < (hauntedRoom).hostileEntities.length; ++i) {
      if ((hauntedRoom).hostileEntities[i].isAlive) {
        System.out.println(i + 1 + ": " + ((hauntedRoom).hostileEntities[i]).name + ": "
            + ((hauntedRoom).hostileEntities[i]).health + " hp");
      }
    }
    int index = in.nextInt();
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length - 1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    if (index <= 0) {
      entities[0].getDamaged((int) (this.damage * this.secondCoefficient), "Physical");
      if (entities.length > 1) {
        entities[1].getDamaged((int) (this.damage * this.thirdCoefficient), "Physical");
      }
    } else if (index < entities.length - 1) {
      entities[index - 1].getDamaged((int) (this.damage * this.firstCoefficient), "Physical");
      entities[index].getDamaged((int) (this.damage * this.secondCoefficient), "Physical");
      entities[index + 1].getDamaged((int) (this.damage * this.thirdCoefficient), "Physical");
    } else if (index == entities.length - 1) {
      entities[index - 1].getDamaged((int) (this.damage * this.firstCoefficient), "Physical");
      entities[index].getDamaged((int) (this.damage * this.secondCoefficient), "Physical");
    } else if (index > entities.length - 1) {
      entities[index].getDamaged((int) (this.damage * this.firstCoefficient), "Physical");
    }
  }

  public String toString() {
    return "hit three enemies (one by your choice and two surrounding him, dealing "
        + (int) (this.firstCoefficient * this.damage) + ", "
        + (int) (this.secondCoefficient * this.damage) + " and "
        + (int) (this.thirdCoefficient * this.damage) +
        " to first, second and third of them, respectively. BaseDamage is " + this.damage;
  }
}
