package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class DamageOneEntityWithCrit extends DamageOneEntity {
    private final int divider;
    int numerator;
    Damage criticalDamage;

    public DamageOneEntityWithCrit(Weapon weapon, int damage, double coefficient, DamageTypeSet type, ElementSet element, boolean isMelee, double percentOfElementDamage, int numerator, int divider, double multiplier) {
        super(weapon, (int) (damage * coefficient), type, element, isMelee, percentOfElementDamage);
        this.criticalDamage = this.damage.copy();
        this.criticalDamage.elementDamage *= (int) multiplier;
        this.criticalDamage.defaultDamage *= (int) multiplier;
        this.criticalDamage.totalDamage *= (int) multiplier;
        this.divider = divider;
        this.numerator = numerator;
        this.description = "Deals " + this.damage.totalDamage + " damage to enemy by your choice, has " + numerator + "/" + divider + "chance to deal" + this.criticalDamage.totalDamage + " damage instead";
    }

    public void use(Room room) {
        int a = new Random().nextInt(this.divider);
        Damage damage = this.damage;
        if (a < numerator) {
            damage = this.criticalDamage;
        }
        Entity[] entities;
        if (room.isHaunted) {
            entities = room.hostileEntities;
        } else {
            return;
        }
        int index = this.chooseVictimToAttack(room);
        while (entities[Math.min(Math.max(index, 0), entities.length - 1)] == null || !entities[Math.min(Math.max(index, 0), entities.length - 1)].isAlive) {
            System.out.println("do not play with dead!");
            System.out.println("choose another one");
            index = this.chooseVictimToAttack(room);
        }
        index = Math.min(Math.max(index, 0), entities.length - 1);
        entities[index].getDamaged(new Damage(damage, this.lastPower, this.power));
        super.use(room);
    }
}
