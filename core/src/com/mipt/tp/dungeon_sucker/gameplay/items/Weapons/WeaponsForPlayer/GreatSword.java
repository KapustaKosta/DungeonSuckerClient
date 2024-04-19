package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForPlayer;

import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class GreatSword extends Weapon {
  public GreatSword(int damage, String name, RaritySet rarity) {
    super(3);
    this.power = damage;
    this.name = name;
    this.strengthScale = 0.8;
    this.rarity = rarity;
    this.recountScales();
    this.weight = 5;
    // public DamageOneEntity(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage)
    //public DamageThreeEntities(Weapon weapon, int damage, String type, String element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient) {
    this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3));
    this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, 0.5, 1, 0.5));
    this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, 0.75, 0.5, 0.75));
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
      this.vigorScale = 1;
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
