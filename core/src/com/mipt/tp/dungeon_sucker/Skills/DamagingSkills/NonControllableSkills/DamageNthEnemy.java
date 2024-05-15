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

public class DamageNthEnemy extends DamagingSkill {
    int number;
    boolean isUsedByHostile;

    public DamageNthEnemy(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile, int number) {
        this.weapon = weapon;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.isUsedByHostile = isUsedByHostile;
        this.number = number;
        this.description = "Deal " + this.damage.totalDamage + " to enemy on place " + this.number + " if impossible, does nothing";
    }

    public DamageNthEnemy(Weapon weapon, Damage damage, boolean isUsedByHostile, int i) {
        new DamageNthEnemy(this.weapon, damage.totalDamage, damage.type, damage.element, damage.isMelee, damage.percentOfElementDamage, isUsedByHostile, i);
    }

    public void use(Room room, Action doAfterUse) {
        Entity[] enemies;
        if (this.isUsedByHostile) {
            enemies = room.friendlyEntities;
        } else {
            enemies = room.hostileEntities;
        }
        System.out.println(Arrays.toString(enemies));
        Entity enemy = enemies[this.number - 1];
        if (enemy != null && enemy.isAlive) {
            System.out.println("punching " + enemy.name);
            enemy.getDamaged(new Damage(this.damage, this.lastPower, this.power));
            super.use(room, doAfterUse);
        } else {
            super.use(room, doAfterUse);
        }
    }
}
