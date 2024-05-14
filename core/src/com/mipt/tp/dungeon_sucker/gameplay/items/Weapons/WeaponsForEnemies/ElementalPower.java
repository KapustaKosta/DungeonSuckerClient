package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.ElementalSpirit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;

public class ElementalPower extends Weapon {

  int damage;

  public ElementalPower(int power) {
    super(power);
    this.dexterityScale = 0.3;
    this.strengthScale = 0.2;
    this.damage = power;
    this.name = "Pure energy of " + this.element;
  }

  public void getObtained(ElementalSpirit holder) {
    super.getObtained(holder);
    this.element = holder.element;
    this.generateSkillForCreature(
        new DamageRandomEnemy(this, this.damage, DamageTypeSet.Magic, this.element, true, 1,
            this.holder.isHostile));
    this.generateSkillForCreature(
        new DamageRandomEnemy(this, this.damage, DamageTypeSet.Magic, this.element, true, 1,
            this.holder.isHostile));
    this.generateSkillForCreature(
        new DamageRandomEnemy(this, this.damage, DamageTypeSet.Magic, this.element, false, 1,
            this.holder.isHostile));
  }
}
