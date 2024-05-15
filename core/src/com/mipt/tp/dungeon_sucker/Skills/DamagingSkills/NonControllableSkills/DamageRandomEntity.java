package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills.NonControllableSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class DamageRandomEntity extends DamagingSkill {
    public DamageRandomEntity(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage) {
        this.weapon = weapon;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.description = "Deal " + this.damage + " to the random entity";
        this.type = type;
    }

    public void use(Room room, Action doAfterUse) {
        System.out.println("choosing guy to punch");
        Entity[] enemies = new Entity[room.amountOfHostileEntities + room.amountOfFriendlyEntities];
        int maxIndex = room.amountOfHostileEntities + room.amountOfFriendlyEntities;
        if (room.amountOfFriendlyEntities >= 0) {
            System.arraycopy(room.friendlyEntities, 0, enemies, 0, room.amountOfFriendlyEntities);
        }
        if (room.amountOfHostileEntities >= 0) {
            System.arraycopy(room.hostileEntities, 0, enemies, room.amountOfFriendlyEntities, room.amountOfHostileEntities);
        }
        int index = new Random().nextInt(maxIndex);
        Entity enemy = enemies[index];
        while (enemy == null || !enemy.isAlive) {
            index = new Random().nextInt(maxIndex);
            enemy = enemies[index];
        }
        System.out.println("punching " + enemy.name);
        enemy.getDamaged(new Damage(this.damage, this.lastPower, this.power));
        super.use(room, doAfterUse);
    }
}
