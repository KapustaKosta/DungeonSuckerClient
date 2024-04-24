package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageOneRandomEnemyWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemyAndTwoClosest;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Scanner;

public class Dagger extends Weapon {
  public Dagger(int level, int damage, String name, RaritySet rarity) {
    super(3);
    this.power = damage * level;
    this.level = level;
    this.name = name;
    this.dexterityScale = 0.5;
    this.rarity = rarity;
    this.weight = 5;
    this.recountScales();
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    //public DamageThreeEntities(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element, true, 0.3));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.power, 0.75, DamageTypeSet.Point, this.element, true, 0.25, 1, 5, 3));
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3));
  }

  public void getObtained(Entity holder) {
    super.getObtained(holder);
    this.generateSkillForCreature(new DamageRandomEnemy(
        this, this.power, DamageTypeSet.Point, this.element, true, 0.3, this.holder.isHostile));
    this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
        this, this.power, 0.75, DamageTypeSet.Point, this.element, true,
        0.25, 1, 5, 3, this.holder.isHostile));
    this.generateSkillForCreature(new DamageRandomEnemy(
        this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, this.holder.isHostile));
  }

  private void recountScales() {
    if (this.rarity == RaritySet.Poor) {
      this.dexterityScale /= 1.25;
    }
    if (this.rarity == RaritySet.Uncommon) {
      this.dexterityScale *= 1.2;
    }
    if (this.rarity == RaritySet.Rare) {
      this.dexterityScale *= 1.4;
    }
    if (this.rarity == RaritySet.Epic) {
      this.dexterityScale *= 2;
    }
    if (this.rarity == RaritySet.Legendary) {
      this.dexterityScale *= 3;
      this.strengthScale = 1;
      this.power = this.power * 3 / 2;
      this.weight = this.weight * 3 / 2;
    }
  }

  public void use(Room room) {
    this.recount();
    this.recount();
    int index = getSkillIndex();
    System.out.println("Choose your skill");
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
  }
}
