package com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.ChargeWeapon;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageOneEntity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.DamageThreeEntities;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemy;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills.DamageRandomEnemyAndTwoClosest;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.ChargableWeapon;
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.BowConfig;
import com.mipt.tp.dungeon_sucker.itemSpriteGenerator.ItemSpriteGenerator;

public class Bow extends ChargableWeapon {
    public int chargesPerChargeUse = 1;

    public Bow(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.chargesForSkill = new int[3];
        this.type = WeaponTypes.ranged;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.strengthScale = BowConfig.BASE_STRENGTH_SCALE;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        this.chargesForSkill[0] = BowConfig.CHARGES_FOR_FIRST_SKILL;
        this.chargesForSkill[1] = BowConfig.CHARGES_FOR_SECOND_SKILL;
        this.texture = ItemSpriteGenerator.generateTexture(this);
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point,
                this.element, false, BowConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Point, this.element,
                false, BowConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                BowConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                BowConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                BowConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS));
        this.generateSkill(new ChargeWeapon(this, this.chargesPerChargeUse));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Point, this.element, false,
                BowConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Point, this.element, false,
                BowConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                BowConfig.FIRST_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                BowConfig.SECOND_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                BowConfig.THIRD_COEFFICIENT_IN_FIRST_SKILL_THAT_ATTACKS_MULTIPLE_TARGETS,
                this.holder.isHostile));
        this.generateSkillForCreature(new ChargeWeapon(this, this.chargesPerChargeUse));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.strengthScale *= BowConfig.COEFFICIENT_TO_STRENGTH_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.strengthScale *= BowConfig.COEFFICIENT_TO_STRENGTH_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.strengthScale *= BowConfig.COEFFICIENT_TO_STRENGTH_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.strengthScale *= BowConfig.COEFFICIENT_TO_STRENGTH_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.strengthScale *= BowConfig.COEFFICIENT_TO_STRENGTH_IF_LEGENDARY;
            this.dexterityScale = BowConfig.DEXTERITY_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
            this.chargesPerChargeUse = 2;
            this.skills[2] = new ChargeWeapon(this, this.chargesPerChargeUse);
        }
    }
}
