package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Arrays;
import java.util.Random;

public class DamageOneRandomEnemyWithCrit extends DamageRandomEnemy {
    private final int divider;
    int numerator;
    Damage criticalDamage;

    public DamageOneRandomEnemyWithCrit(Weapon weapon, int damage, double coefficient, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, int numerator, int divider, double multiplier, boolean isUsedByHostile) {
        super(weapon, (int) (damage * coefficient), type, element, isMelee, percentOfElementDamage, isUsedByHostile);
        this.criticalDamage = this.damage.copy();
        this.criticalDamage.elementDamage *= (int) multiplier;
        this.criticalDamage.defaultDamage *= (int) multiplier;
        this.criticalDamage.totalDamage *= (int) multiplier;
        this.divider = divider;
        this.numerator = numerator;
        this.description = "Deals " + this.damage.totalDamage + " damage to enemy by your choice, has " + numerator + "/" + divider + "chance to deal" + this.criticalDamage.totalDamage + " damage instead";
    }

    public void use(Room room, Action doAfterUse) {
        int a = new Random().nextInt(this.divider);
        Damage damage = this.damage;
        if (a < numerator) {
            damage = this.criticalDamage;
        }
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
        System.out.println("punching " + enemy.name);
        enemy.getDamaged(new Damage(damage, this.lastPower, this.power));
        super.use(room, doAfterUse);
    }
}

