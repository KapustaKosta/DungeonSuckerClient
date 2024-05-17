package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntityWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageOneRandomEnemyWithCrit;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.SwordConfig;
import com.mipt.tp.dungeon_sucker.itemSpriteGenerator.ItemSpriteGenerator;

public class Sword extends Weapon {

    public Sword(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.type = WeaponTypes.sword;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.rarity = rarity;
        this.strengthScale = SwordConfig.BASE_STRENGTH_SCALE;
        this.dexterityScale = SwordConfig.BASE_DEXTERITY_SCALE;
        this.weight = 5;
        this.recountScales();
        this.texture = ItemSpriteGenerator.generateTexture(this);
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash,
                this.element, true, SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageOneEntityWithCrit(this, this.power, SwordConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT,
                DamageTypeSet.Slash, this.element, true, SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SwordConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                SwordConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                SwordConfig.CRIT_MULTIPLIER));
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point,
                this.element, true, SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE_FOR_THIRD_SKILL));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE, this.holder.isHostile));
        this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
                this, this.power, SwordConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT, DamageTypeSet.Slash, this.element, true,
                SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                SwordConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                SwordConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                SwordConfig.CRIT_MULTIPLIER, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Point, this.element, true,
                SwordConfig.PERCENT_OF_ELEMENTAL_DAMAGE_FOR_THIRD_SKILL, this.holder.isHostile));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_POOR;
            this.dexterityScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_UNCOMMON;
            this.dexterityScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_RARE;
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_EPIC;
            this.dexterityScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.strengthScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_LEGENDARY;
            this.dexterityScale *= SwordConfig.COEFFICIENT_TO_EXISTING_STATS_IF_LEGENDARY;
            this.intellectScale = SwordConfig.INTELLECT_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
        }
    }
}
