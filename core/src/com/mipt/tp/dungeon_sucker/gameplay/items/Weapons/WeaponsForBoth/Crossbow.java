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
    this.chargesForSkill[0] = 1;
    this.chargesForSkill[1] = 2;
  }

  // Todo: дублируемый код убрать
  // Todo: Сделать класс со static final полями, в которых будут настраиваться все значения (все числа ниже)
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

  // Todo: дублируемый код убрать
  // Todo: Сделать класс со static final полями, в которых будут настраиваться все значения (все числа ниже)
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

  // Todo: дублируемый код убрать
  public void use(Room room) {
    this.recount();
    getSkillIndexUntilPredicate(args -> this.skills[args[0]].use(room),
        value -> this.chargesForSkill[value] <= this.charges);
  }

  // Todo: дублируемый код убрать
  public void useByCreature(Room room, int indexOfSkillToBeUsed) {
    this.recount();
    int index = new Random().nextInt(this.skills.length);
    while (this.chargesForSkill[index] > this.charges) {
      index = new Random().nextInt(this.skills.length);
    }
    this.skills[index].use(room);
    this.recount();
  }
}
