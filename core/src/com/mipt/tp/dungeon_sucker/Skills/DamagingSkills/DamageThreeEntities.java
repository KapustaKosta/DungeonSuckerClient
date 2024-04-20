package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class DamageThreeEntities extends DamagingSkill {
  double firstCoefficient;
  double secondCoefficient;
  double thirdCoefficient;
  Damage firstDamage;
  Damage secondDamage;
  Damage thirdDamage;

  public DamageThreeEntities(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.weapon = weapon;
    this.firstCoefficient = firstCoefficient;
    this.secondCoefficient = secondCoefficient;
    this.thirdCoefficient = thirdCoefficient;
    this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
    this.description = "deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy by your choice." +
        " Also if possible deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy before him and " +
        this.damage.totalDamage * this.secondCoefficient + " damage to enemy right after him";
    this.type = type;
    this.firstDamage = this.damage.copy();
    this.firstDamage.totalDamage = (int)(this.firstCoefficient * this.firstDamage.totalDamage);
    this.firstDamage.defaultDamage = (int)(this.firstCoefficient * this.firstDamage.defaultDamage);
    this.firstDamage.elementDamage = (int)(this.firstCoefficient * this.firstDamage.elementDamage);
    this.secondDamage = this.damage.copy();
    this.secondDamage.totalDamage = (int)(this.secondCoefficient * this.secondDamage.totalDamage);
    this.secondDamage.defaultDamage = (int)(this.secondCoefficient * this.secondDamage.defaultDamage);
    this.secondDamage.elementDamage = (int)(this.secondCoefficient * this.secondDamage.elementDamage);
    this.thirdDamage = this.damage.copy();
    this.thirdDamage.totalDamage = (int)(this.thirdCoefficient  * this.thirdDamage.totalDamage);
    this.thirdDamage.defaultDamage = (int)(this.thirdCoefficient * this.thirdDamage.defaultDamage);
    this.thirdDamage.elementDamage = (int)(this.thirdCoefficient * this.thirdDamage.elementDamage);

  }

  public void use(Room room) {
    if (!room.isHaunted) {
      return;
    }
    Entity[] entities = room.hostileEntities;
    Scanner in = new Scanner(System.in);
    System.out.println("choose enemy to punch");
    for (int i = 0; i < (room).hostileEntities.length; ++i) {
      if ((room).hostileEntities[i].isAlive) {
        System.out.println(i + 1 + ": " + ((room).hostileEntities[i]).name + ": "
            + ((room).hostileEntities[i]).health + " hp");
      }
    }
    int index = in.nextInt();
    while (!entities[Math.min(Math.max(index - 1, 0), entities.length - 1)].isAlive) {
      System.out.println("do not play with dead!");
      System.out.println("choose another one");
      index = in.nextInt();
    }
    if (index <= 0) {
      entities[0].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
      if (entities.length > 1) {
        entities[1].getDamaged(new Damage(this.thirdDamage, this.lastPower, this.power));
      }
    } else if (index < entities.length - 1) {
      entities[index - 1].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      entities[index].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
      entities[index + 1].getDamaged(new Damage(this.thirdDamage, this.lastPower, this.power));
    } else if (index == entities.length - 1) {
      entities[index - 1].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
      entities[index].getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
    } else if (index > entities.length - 1) {
      entities[index].getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
    }
    super.use(room);
  }

  public String toString() {
    return "hit three enemies (one by your choice and two surrounding him, dealing "
        + this.firstDamage.totalDamage + ", "
        + this.secondDamage.totalDamage + " and "
        + this.thirdDamage.totalDamage +
        " to first, second and third of them, respectively. BaseDamage is " + this.damage;
  }
}
