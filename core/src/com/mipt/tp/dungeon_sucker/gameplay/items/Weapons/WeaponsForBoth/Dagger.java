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
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.helper.WeaponConfis.DaggerConfig;
import com.mipt.tp.dungeon_sucker.itemSpriteGenerator.ItemSpriteGenerator;

public class Dagger extends Weapon {

    public Dagger(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.classID = 3;
        this.type = WeaponTypes.knife;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.dexterityScale = DaggerConfig.BASE_DEXTERITY_SCALE;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        this.texture = ItemSpriteGenerator.generateTexture(this);
    }

    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Point, this.element,
                true, DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE));
        this.generateSkill(new DamageOneEntityWithCrit(this, this.power,
                DaggerConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT, DamageTypeSet.Point,
                this.element, true,
                DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                DaggerConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                DaggerConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                DaggerConfig.CRIT_MULTIPLIER));
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash,
                this.element, true, DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Point, this.element, true, DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE, this.holder.isHostile));
        this.generateSkillForCreature(new DamageOneRandomEnemyWithCrit(
                this, this.power, DaggerConfig.COEFFICIENT_TO_CRIT_IF_NO_CRIT, DamageTypeSet.Point, this.element, true,
                DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE,
                DaggerConfig.NUMERATOR_OF_CHANCE_TO_CRIT,
                DaggerConfig.DIVIDER_OF_CHANCE_TO_CRIT,
                DaggerConfig.CRIT_MULTIPLIER, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Slash, this.element, true, DaggerConfig.PERCENT_OF_ELEMENTAL_DAMAGE, this.holder.isHostile));
    }

    private void recountScales() {
        if (this.rarity == RaritySet.Poor) {
            this.dexterityScale *= DaggerConfig.COEFFICIENT_TO_DEXTERITY_IF_POOR;
        }
        if (this.rarity == RaritySet.Uncommon) {
            this.dexterityScale *= DaggerConfig.COEFFICIENT_TO_DEXTERITY_IF_UNCOMMON;
        }
        if (this.rarity == RaritySet.Rare) {
            this.dexterityScale *= DaggerConfig.COEFFICIENT_TO_DEXTERITY_IF_RARE;
        }
        if (this.rarity == RaritySet.Epic) {
            this.dexterityScale *= DaggerConfig.COEFFICIENT_TO_DEXTERITY_IF_EPIC;
        }
        if (this.rarity == RaritySet.Legendary) {
            this.dexterityScale *= DaggerConfig.COEFFICIENT_TO_DEXTERITY_IF_LEGENDARY;
            this.strengthScale = DaggerConfig.STRENGTH_SCALE_IF_LEGENDARY;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
        }
    }
}
