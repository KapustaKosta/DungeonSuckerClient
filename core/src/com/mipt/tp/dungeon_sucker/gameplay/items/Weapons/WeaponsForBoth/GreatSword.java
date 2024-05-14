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

public class GreatSword extends Weapon {
    public GreatSword(int level, int damage, String name, RaritySet rarity) {
        super(3);
        this.type = WeaponTypes.sword;
        this.power = damage * level;
        this.level = level;
        this.name = name;
        this.strengthScale = 0.8;
        this.rarity = rarity;
        this.weight = 5;
        this.recountScales();
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, 0.5, 1, 0.5));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element, true, 0.3, 0.75, 0.5, 0.75));
    }

    // Todo: дублируемый код убрать
    // Todo: Сделать класс со static final полями, в которых будут настраиваться все значения (все числа ниже)
    public void getObtained(Entity holder) {
        super.getObtained(holder);
        this.generateSkill(new DamageOneEntity(this, this.power, DamageTypeSet.Slash,
                this.element, true, 0.3));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element,
                true, 0.3, 0.5, 1, 0.5));
        this.generateSkill(new DamageThreeEntities(this, this.power, DamageTypeSet.Slash, this.element,
                true, 0.3, 0.75, 0.5, 0.75));

        this.generateSkillForCreature(new DamageRandomEnemy(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                0.3, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                0.3, 0.5, 1, 0.5, this.holder.isHostile));
        this.generateSkillForCreature(new DamageRandomEnemyAndTwoClosest(
                this, this.power, DamageTypeSet.Slash, this.element, true,
                0.3, 0.75, 0.5, 0.75, this.holder.isHostile));
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
            this.vigorScale = 1;
            this.power = this.power * 3 / 2;
            this.weight = this.weight * 3 / 2;
        }
    }

    // Todo: дублируемый код убрать
    public void use(Room room) {
        this.recount();
        getSkillIndex(args -> {
            this.skills[args[0]].use(room);
            this.recount();
        });
    }
}
