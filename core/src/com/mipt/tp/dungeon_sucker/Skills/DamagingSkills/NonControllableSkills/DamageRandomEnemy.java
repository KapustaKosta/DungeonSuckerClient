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

public class DamageRandomEnemy extends DamagingSkill {
    boolean isUsedByHostile;

    public DamageRandomEnemy(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile) {
        this.weapon = weapon;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.isUsedByHostile = isUsedByHostile;
        this.description = "Deal " + this.damage.totalDamage + " to a random enemy";
        this.type = type;
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
        System.out.println("punching " + enemy.name);
        enemy.getDamaged(new Damage(this.damage, this.lastPower, this.power));
        super.use(room, doAfterUse);
    }
}
