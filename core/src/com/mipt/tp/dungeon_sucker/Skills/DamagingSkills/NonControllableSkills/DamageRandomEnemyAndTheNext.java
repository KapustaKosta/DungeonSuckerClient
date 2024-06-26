package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;
import java.util.Random;

public class DamageRandomEnemyAndTheNext extends DamagingSkill {
    boolean isUsedByHostile;
    double firstCoefficient;
    double secondCoefficient;
    Damage firstDamage;
    Damage secondDamage;

    public DamageRandomEnemyAndTheNext(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, double firstCoefficient, double secondCoefficient, double thirdCoefficient, boolean isUsedByHostile) {
        this.weapon = weapon;
        this.isUsedByHostile = isUsedByHostile;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.description = "deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy by your choice." +
                " Also if possible deals " + this.damage.totalDamage * this.secondCoefficient + " damage to enemy before him and " +
                this.damage.totalDamage * this.secondCoefficient + " damage to enemy right after him";
        this.type = type;
        this.firstDamage = this.damage.copy();
        this.firstDamage.totalDamage = (int) (this.firstCoefficient * this.firstDamage.totalDamage);
        this.firstDamage.defaultDamage = (int) (this.firstCoefficient * this.firstDamage.defaultDamage);
        this.firstDamage.elementDamage = (int) (this.firstCoefficient * this.firstDamage.elementDamage);
        this.secondDamage = this.damage.copy();
        this.secondDamage.totalDamage = (int) (this.secondCoefficient * this.secondDamage.totalDamage);
        this.secondDamage.defaultDamage = (int) (this.secondCoefficient * this.secondDamage.defaultDamage);
        this.secondDamage.elementDamage = (int) (this.secondCoefficient * this.secondDamage.elementDamage);
    }

    public void use(Room room, Action doAfterUse) {
        System.out.println("choosing guy to punch");
        Entity[] enemies;
        int maxIndex;
        if (this.isUsedByHostile) {
            enemies = room.friendlyEntities;
            maxIndex = room.amountOfFriendlyEntities;
        } else {
            enemies = room.hostileEntities;
            maxIndex = room.amountOfHostileEntities;
        }
        System.out.println(Arrays.toString(enemies));
        int index = new Random().nextInt(maxIndex);
        Entity enemy = enemies[index];
        System.out.println("chosen index " + index);
        while (enemy == null || !enemy.isAlive) {
            index = new Random().nextInt(maxIndex);
            enemy = enemies[index];
        }
        if (index > 0) {
            Entity enemyBefore = enemies[index - 1];
            if (enemyBefore != null) {
                enemyBefore.getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
            }
        }
        System.out.println("punching " + enemy.name);
        enemy.getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
        super.use(room, doAfterUse);
    }

    public String toString() {
        return "hit three enemies (one by your choice and the next one, dealing "
                + this.firstDamage.totalDamage + ", "
                + this.secondDamage.totalDamage +
                " to first and second of them, respectively. BaseDamage is " + this.damage;
    }
}
