package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageAllEnemies extends DamagingSkill {
    boolean isUsedByHostile;

    public DamageAllEnemies(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, boolean isUsedByHostile) {
        this.weapon = weapon;
        this.power = damage;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.isUsedByHostile = isUsedByHostile;
        this.description = "Deal " + this.damage.totalDamage + " to all enemies in room";
        this.type = type;
    }

    public void use(Room room) {
        Entity[] enemies;
        if (this.isUsedByHostile) {
            enemies = room.friendlyEntities;
        } else {
            enemies = room.hostileEntities;
        }
        for (int i = 0; i < enemies.length; ++i) {
            new DamageNthEnemy(this.weapon, new Damage(this.damage, this.lastPower, this.power), this.isUsedByHostile, i).use(room);
        }
        super.use(room);
    }
}
