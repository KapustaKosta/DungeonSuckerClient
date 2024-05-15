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

public class DamageFurthestEnemy extends DamagingSkill {
    // Todo: rework choosing who to damage.
    boolean isUsedByHostile;

    public DamageFurthestEnemy(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile) {
        this.weapon = weapon;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.isUsedByHostile = isUsedByHostile;
        this.description = "Deal " + this.damage.totalDamage + " to the furthest enemy";
        this.type = type;
    }

    public void use(Room room, Action doAfterUse) {
        Entity[] enemies;
        int index;
        if (this.isUsedByHostile) {
            enemies = room.friendlyEntities;
            index = 0;
        } else {
            enemies = room.hostileEntities;
            index = room.amountOfHostileEntities - 1;
        }
        System.out.println(Arrays.toString(enemies));
        Entity enemy = enemies[index];
        while (!enemy.isAlive) {
            if (isUsedByHostile && index < room.amountOfFriendlyEntities - 1) {
                ++index;
            } else if (!this.isUsedByHostile && index > 0) {
                --index;
            }
            enemy = enemies[index];
        }
        System.out.println("punching " + enemy.name);
        enemy.getDamaged(new Damage(this.damage, this.lastPower, this.power));
        super.use(room, doAfterUse);
    }
}
