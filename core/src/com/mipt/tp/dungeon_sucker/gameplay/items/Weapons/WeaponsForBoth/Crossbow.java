package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.ChargeWeapon;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageOneRandomEnemyWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.ChargableWeapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class Crossbow extends ChargableWeapon {

  public Crossbow(int level, int damage, String name, RaritySet rarity) {
    super(3);
    this.type = WeaponTypes.ranged;
    this.chargesForSkill = new int[3];
    this.power = damage * level;
    this.level = level;
    this.name = name;
    this.dexterityScale = 0.5;
    this.rarity = rarity;
    this.weight = 5;
    this.recountScales();
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    //public DamageThreeEntities(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.chargesForSkill[0] = 1;
    this.chargesForSkill[1] = 2;
  }

  public void getObtained(Entity holder) {
    super.getObtained(holder);
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element,
        false, 0.3));
    this.generateSkill(new DamageOneEntityWithCrit(this, this.power, 0.75,
        DamageTypeSet.Point, this.element, false,
        0.3, 1, 5, 3));
    this.generateSkill(new ChargeWeapon(this, 1));

    this.generateSkillForCreature(new DamageRandomEnemy(
        this, this.power, DamageTypeSet.Point, this.element, false, 0.3, this.holder.isHostile));
    this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
        this, this.power, 0.75, DamageTypeSet.Point, this.element, false,
        0.3, 1, 5, 3, this.holder.isHostile));
    this.generateSkillForCreature(new ChargeWeapon(this, 1));
  }

  private void recountScales() {
    if (this.rarity == RaritySet.Poor) {
      this.strengthScale /= 1.25;
    }
    if (this.rarity == RaritySet.Uncommon) {
      this.strengthScale *= 1.2;
    }
    if (this.rarity == RaritySet.Rare) {
      this.strengthScale *= 1.4;
    }
    if (this.rarity == RaritySet.Epic) {
      this.strengthScale *= 2;
    }
    if (this.rarity == RaritySet.Legendary) {
      this.strengthScale *= 3;
      this.dexterityScale = 1;
      this.power = this.power * 3 / 2;
      this.weight = this.weight * 3 / 2;
      this.skills[2] = new ChargeWeapon(this, 2);
    }
  }

  public void use(Room room) {
    this.recount();
    int index = getSkillIndex();
    while (this.chargesForSkill[index] > this.charges) {
      index = getSkillIndex();
    }
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
  }

  public void useByCreature(Room room, int indexOfSkillToBeUsed) {
    this.recount();
    int index = new Random().nextInt(this.skills.length);
    System.out.println("Choosing your skill");
    while (this.chargesForSkill[index] > this.charges) {
      index = new Random().nextInt(this.skills.length);
    }
    System.out.println(this.skills[index].getClass());
    this.skills[index].use(room);
    this.recount();
  }
}
