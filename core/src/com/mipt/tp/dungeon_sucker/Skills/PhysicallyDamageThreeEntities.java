package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.gameplay.*;

import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import java.util.Scanner;

public class PhysicallyDamageThreeEntities extends Skill {
  double firstCoefficient;
  double secondCoefficient;
  double thirdCoefficient;

  public PhysicallyDamageThreeEntities(Weapon weapon,int damage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.weapon = weapon;
    this.firstCoefficient = firstCoefficient;
    this.secondCoefficient = secondCoefficient;
    this.thirdCoefficient = thirdCoefficient;
    this.damage = damage;
  }

  public void use(Room room) {
    if(!room.isHaunted){
      return;
    }
    Entity[] entities = ((HauntedRoom) room).hostileEntities;
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    int index = in.nextInt();
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
