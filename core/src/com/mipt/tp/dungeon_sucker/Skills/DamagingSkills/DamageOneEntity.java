package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageOneEntity extends DamagingSkill {
    public DamageOneEntity(Weapon weapon, int damage, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage) {
        this.weapon = weapon;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage, damage);
        this.description = "Deals " + this.damage.totalDamage + " damage to enemy by your choice";
        this.type = type;
    }


    public void use(Room room) {
        Entity[] entities;
        if (room.isHaunted) {
            entities = room.hostileEntities;
        } else {
            return;
        }
        int index = chooseVictimToAttack(room);
        while (entities[Math.min(Math.max(index, 0), entities.length - 1)] == null || !entities[Math.min(Math.max(index, 0), entities.length - 1)].isAlive) {
            System.out.println("do not play with dead!");
            System.out.println("choose another one");
            index = chooseVictimToAttack(room);
        }
        index = Math.min(Math.max(index, 0), entities.length - 1);
        entities[index].getDamaged(new Damage(this.damage, this.lastPower, this.power));
        super.use(room);
    }

    public String toString() {
        return "punch one enemy by your choice, dealing " + this.damage + " damage";
    }
}
