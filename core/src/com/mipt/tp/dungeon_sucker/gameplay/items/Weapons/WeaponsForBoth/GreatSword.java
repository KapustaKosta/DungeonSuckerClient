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
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.GreatSwordConfig;

public class GreatSword extends Weapon {
    public GreatSword(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.type = WeaponTypes.sword;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.strengthScale = GreatSwordConfig.BASE_STRENGTH_SCALE;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        }
    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash,
                this.element, true, GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element,
                true, GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                GreatSwordConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element,
                true, GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                GreatSwordConfig.FIRST_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.SECOND_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.THIRD_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                GreatSwordConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                GreatSwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                GreatSwordConfig.FIRST_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.SECOND_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                GreatSwordConfig.THIRD_COEFFICIENT_IN_SECOND_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS, this.holder.isHostile));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.strengthScale *= GreatSwordConfig.COEFFICIENT_TO_STRENGTH_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.strengthScale *= GreatSwordConfig.COEFFICIENT_TO_STRENGTH_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.strengthScale *= GreatSwordConfig.COEFFICIENT_TO_STRENGTH_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.strengthScale *= GreatSwordConfig.COEFFICIENT_TO_STRENGTH_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.strengthScale *= GreatSwordConfig.COEFFICIENT_TO_STRENGTH_IF_LEGENDARY;
            this.vigorScale = GreatSwordConfig.VIGOR_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
        }
    }
}
