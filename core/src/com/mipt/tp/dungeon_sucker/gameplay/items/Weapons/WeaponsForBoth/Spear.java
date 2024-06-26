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
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.SpearConfig;
import com.mipt.tp.dungeon_sucker.itemSpriteGenerator.ItemSpriteGenerator;

public class Spear extends Weapon {
    public Spear(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.classID = 6;
        this.type = WeaponTypes.polearm;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.dexterityScale = SpearConfig.BASE_DEXTERITY_SCALE;
        this.strengthScale = SpearConfig.BASE_STRENGTH_SCALE;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        this.texture = ItemSpriteGenerator.generateTexture(this);
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point,
                this.element, true, SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageOneEntityWithCrit(this, this.power, SpearConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT,
                DamageTypeSet.Point, this.element, true, SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SpearConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                SpearConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                SpearConfig.CRIT_MULTIPLIER));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Point,
                this.element, true, SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SpearConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                SpearConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                SpearConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Point, this.element, true,
                SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                this.holder.isHostile));
        this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
                this, this.power, 0.75, DamageTypeSet.Point,
                this.element, true, SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SpearConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                SpearConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                SpearConfig.CRIT_MULTIPLIER,
                this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Point, this.element, true,
                SpearConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SpearConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                SpearConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                SpearConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS, this.holder.isHostile));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_POOR;
            this.dexterityScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_UNCOMMON;
            this.dexterityScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_RARE;
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_EPIC;
            this.dexterityScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.strengthScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_LEGENDARY;
            this.dexterityScale *= SpearConfig.COEFFICIENT_TO_EXISTING_STATS_IF_LEGENDARY;
            this.vigorScale = SpearConfig.VIGOR_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
        }
    }
}
