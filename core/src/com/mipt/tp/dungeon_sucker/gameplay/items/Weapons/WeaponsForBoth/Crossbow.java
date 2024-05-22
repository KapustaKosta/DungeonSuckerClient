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
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.CrossbowConfig;
import com.mipt.tp.dungeon_sucker.itemSpriteGenerator.ItemSpriteGenerator;

public class Crossbow extends ChargableWeapon {
    public int chargesPerChargeUse = 1;

    public Crossbow(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.classID = 2;
        this.type = WeaponTypes.ranged;
        this.chargesForSkill = new int[3];
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.dexterityScale = CrossbowConfig.BASE_DEXTERITY_SCALE;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        this.chargesForSkill[0] = CrossbowConfig.CHARGES_FOR_FIRST_SKILL;
        this.chargesForSkill[1] = CrossbowConfig.CHARGES_FOR_SECOND_SKILL;
        this.texture = ItemSpriteGenerator.generateTexture(this);
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element,
                false, CrossbowConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageOneEntityWithCrit(this, this.power,
                CrossbowConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT,
                DamageTypeSet.Point, this.element, false,
                CrossbowConfig.PERCENT_OF_ELEMENTAL_DAMAGE, CrossbowConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                CrossbowConfig.DIVIDER_OF_CHANCE_TO_CRIT, CrossbowConfig.CRIT_MULTIPLIER));
        this.generateSkill(new ChargeWeapon(this, 1));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Point, this.element, false,
                CrossbowConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                this.holder.isHostile));
        this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
                this, this.power, CrossbowConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT,
                DamageTypeSet.Point, this.element, false,
                CrossbowConfig.PERCENT_OF_ELEMENTAL_DAMAGE, CrossbowConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                CrossbowConfig.DIVIDER_OF_CHANCE_TO_CRIT, CrossbowConfig.CRIT_MULTIPLIER,
                this.holder.isHostile));
        this.generateSkillForCreature(new ChargeWeapon(this, 1));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.dexterityScale *= CrossbowConfig.COEFFICIENT_TO_DEXTERITY_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.dexterityScale *= CrossbowConfig.COEFFICIENT_TO_DEXTERITY_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.dexterityScale *= CrossbowConfig.COEFFICIENT_TO_DEXTERITY_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.dexterityScale *= CrossbowConfig.COEFFICIENT_TO_DEXTERITY_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.dexterityScale *= CrossbowConfig.COEFFICIENT_TO_DEXTERITY_IF_LEGENDARY;
            this.strengthScale = CrossbowConfig.STRENGTH_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
            this.chargesPerChargeUse = 2;
            this.skills[2] = new ChargeWeapon(this, this.chargesPerChargeUse);
        }
    }
}
