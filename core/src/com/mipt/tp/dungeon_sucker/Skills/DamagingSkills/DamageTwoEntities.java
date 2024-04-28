package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class DamageTwoEntities extends DamagingSkill {
  double firstCoefficient;
  double secondCoefficient;
  Damage firstDamage;
  Damage secondDamage;

  public DamageTwoEntities(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient) {
    this.weapon = weapon;
    this.firstCoefficient = firstCoefficient;
    this.secondCoefficient = secondCoefficient;
    this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
    this.description = "deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy by your choice." +
        " Also if possible deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy before him and " +
        this.damage.totalDamage * this.secondCoefficient + " damage to enemy right after him";
    this.type = type;
    this.firstDamage = this.damage.copy();
    this.firstDamage.totalDamage = (int) (this.firstCoefficient * this.firstDamage.totalDamage);
    this.firstDamage.defaultDamage = (int) (this.firstCoefficient * this.firstDamage.defaultDamage);
    this.firstDamage.elementDamage = (int) (this.firstCoefficient * this.firstDamage.elementDamage);
    this.secondDamage = this.damage.copy();
    this.secondDamage.totalDamage = (int) (this.secondCoefficient * this.secondDamage.totalDamage);
    this.secondDamage.defaultDamage = (int) (this.secondCoefficient * this.secondDamage.defaultDamage);
    this.secondDamage.elementDamage = (int) (this.secondCoefficient * this.secondDamage.elementDamage);
  }

  public void use(Room room) {
    if (!room.isHaunted) {
      return;
    }
    Entity[] entities = room.hostileEntities;
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    for (int i = 0; i < (room).hostileEntities.length; ++i) {
      if (room.hostileEntities[i] != null) {
        if (room.hostileEntities[i].isAlive) {
          System.out.println(i + 1 + ": " + ((room.hostileEntities[i])).name + ": "
              + ((room).hostileEntities[i]).health + " hp");
        }
      }
    }
    int index = in.nextInt();
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length - 1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    if (index <= 0) {
      entities[0].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      if (entities.length > 1) {
        if (entities[1] != null) {
          entities[1].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
        }
      }
    } else if (index < entities.length - 1) {
      if (entities[index - 1] != null) {
        entities[index - 1].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      }
      if (entities[index] != null) {
        entities[index].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
      }
    } else if (index == entities.length - 1) {
      if (entities[index - 1] != null) {
        entities[index - 1].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      }
      if (entities[index] != null) {
        entities[index].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
      }
    } else if (index > entities.length - 1) {
      if (entities[index] != null) {
        entities[index].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      }
    }
    super.use(room);
  }

  public String toString() {
    return "hit two enemies (one by your choice and one right after thee, dealing "
        + this.firstDamage.totalDamage + " and "
        + this.secondDamage.totalDamage +
        " to first, and second, respectively. BaseDamage is " + this.damage;
  }
}
