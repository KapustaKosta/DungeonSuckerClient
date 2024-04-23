package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class DamageRandomEnemyAndTwoClosest extends DamagingSkill {
  boolean isUsedByHostile;
  double firstCoefficient;
  double secondCoefficient;
  double thirdCoefficient;
  Damage firstDamage;
  Damage secondDamage;
  Damage thirdDamage;

  public DamageRandomEnemyAndTwoClosest(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient, boolean isUsedByHostile) {
    this.weapon = weapon;
    this.isUsedByHostile = isUsedByHostile;
    this.firstCoefficient = firstCoefficient;
    this.secondCoefficient = secondCoefficient;
    this.thirdCoefficient = thirdCoefficient;
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
    this.thirdDamage = this.damage.copy();
    this.thirdDamage.totalDamage = (int) (this.thirdCoefficient * this.thirdDamage.totalDamage);
    this.thirdDamage.defaultDamage = (int) (this.thirdCoefficient * this.thirdDamage.defaultDamage);
    this.thirdDamage.elementDamage = (int) (this.thirdCoefficient * this.thirdDamage.elementDamage);

  }

  public void use(Room room) {System.out.println("choosing guy to punch");
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
    if(index > 0){
      Entity enemyBefore = enemies[index-1];
      enemyBefore.getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
    }if(index < maxIndex - 1){
      Entity enemyAfter = enemies[index+1];
      enemyAfter.getDamaged(new Damage(this.thirdDamage, this.lastPower, this.power));
    }
    System.out.println("punching " + enemy.name);
    enemy.getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
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
