package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemyAndTwoClosest;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.ClubConfig;

public class Club extends Weapon {

  public Club(int level, int damage, String name, RaritySet rarity) {
    super(3);
    this.type = WeaponTypes.hammer;
    this.power = damage * level;
    this.level = level;
    this.name = name;
    this.strengthScale = ClubConfig.BASE_STRENGTH_SCALE;
    this.rarity = rarity;
    this.weight = 5;
    this.recountScales();
  }

  public void getObtained(Entity holder) {
    super.getObtained(holder);
    this.generateSkill(
        new DamageOneEntity(this, this.power, DamageTypeSet.Smash, this.element, true,
                ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
    this.generateSkill(
        new DamageThreeEntities(this, this.power, DamageTypeSet.Smash, this.element, true,
                ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                ClubConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                ClubConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                ClubConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));
    this.generateSkill(
        new DamageThreeEntities(this, this.power, DamageTypeSet.Smash, this.element, true,
                ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                ClubConfig.FIRST_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                ClubConfig.SECOND_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                ClubConfig.THIRD_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));

    this.generateSkillForCreature(new DamageRandomEnemy(
        this, this.power, DamageTypeSet.Smash, this.element, true,
            ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE, this.holder.isHostile));
    this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
        this, this.power, DamageTypeSet.Smash, this.element, true,
            ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
            ClubConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            ClubConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            ClubConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            this.holder.isHostile));
    this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
        this, this.power, DamageTypeSet.Smash, this.element, true,
            ClubConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
            ClubConfig.FIRST_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            ClubConfig.SECOND_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            ClubConfig.THIRD_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
            this.holder.isHostile));
  }

  private void recountScales() {
    if (this.rarity == RaritySet.Poor) {
      this.strengthScale *= ClubConfig.COEFFICIENT_TO_STRENGTH_IF_POOR;
    }
    if (this.rarity == RaritySet.Uncommon) {
      this.strengthScale *= ClubConfig.COEFFICIENT_TO_STRENGTH_IF_UNCOMMON;
    }
    if (this.rarity == RaritySet.Rare) {
      this.strengthScale *= ClubConfig.COEFFICIENT_TO_STRENGTH_IF_RARE;
    }
    if (this.rarity == RaritySet.Epic) {
      this.strengthScale *= ClubConfig.COEFFICIENT_TO_STRENGTH_IF_EPIC;
    }
    if (this.rarity == RaritySet.Legendary) {
      this.strengthScale *= ClubConfig.COEFFICIENT_TO_STRENGTH_IF_LEGENDARY;
      this.dexterityScale = ClubConfig.DEXTERITY_SCALE_IF_LEGENDARY;
      this.power = this.power * 3 / 2;
      this.weight = this.weight * 3 / 2;
    }
  }
}
