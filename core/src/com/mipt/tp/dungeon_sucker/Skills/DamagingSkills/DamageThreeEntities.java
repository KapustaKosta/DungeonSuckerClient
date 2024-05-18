package com.mipt.tp.dungeon_sucker.Skills.DamagingSkills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.Skills.DamagingSkill;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.DamageTypeSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class DamageThreeEntities extends DamagingSkill {

    double firstCoefficient;
    double secondCoefficient;
    double thirdCoefficient;
    Damage firstDamage;
    Damage secondDamage;
    Damage thirdDamage;

    public DamageThreeEntities(Weapon weapon, int damage, DamageTypeSet type, ElementSet element,
                               boolean isMelee, double percentOfElementDamage, double firstCoefficient,
                               double secondCoefficient, double thirdCoefficient) {
        this.weapon = weapon;
        this.firstCoefficient = firstCoefficient;
        this.secondCoefficient = secondCoefficient;
        this.thirdCoefficient = thirdCoefficient;
        this.damage = new Damage(this.weapon.holder, type, element, isMelee, percentOfElementDamage,
                damage);
        this.description = "deals " + this.damage.totalDamage * this.secondCoefficient
                + " damage to enemy by your choice." +
                " Also if possible deals " + this.damage.totalDamage * this.secondCoefficient
                + " damage to enemy before him and " +
                this.damage.totalDamage * this.secondCoefficient + " damage to enemy right after him";
        this.type = type;
        this.firstDamage = this.damage.copy();
        this.firstDamage.totalDamage = (int) (this.firstCoefficient * this.firstDamage.totalDamage);
        this.firstDamage.defaultDamage = (int) (this.firstCoefficient * this.firstDamage.defaultDamage);
        this.firstDamage.elementDamage = (int) (this.firstCoefficient * this.firstDamage.elementDamage);
        this.secondDamage = this.damage.copy();
        this.secondDamage.totalDamage = (int) (this.secondCoefficient * this.secondDamage.totalDamage);
        this.secondDamage.defaultDamage = (int) (this.secondCoefficient
                * this.secondDamage.defaultDamage);
        this.secondDamage.elementDamage = (int) (this.secondCoefficient
                * this.secondDamage.elementDamage);
        this.thirdDamage = this.damage.copy();
        this.thirdDamage.totalDamage = (int) (this.thirdCoefficient * this.thirdDamage.totalDamage);
        this.thirdDamage.defaultDamage = (int) (this.thirdCoefficient * this.thirdDamage.defaultDamage);
        this.thirdDamage.elementDamage = (int) (this.thirdCoefficient * this.thirdDamage.elementDamage);

    }

    // Todo: дублируемый код убрать
    public void use(Room room, Action doAfterUse) {
        if (!room.isHaunted) {
            return;
        }
        Entity[] entities = room.hostileEntities;

        chooseVictimToAttackUntilPredicate(room,
                args -> {
                    int index = args[0];
                    useByIndex(index, room, doAfterUse);
                },
                value -> !(entities[Math.min(Math.max(value, 0), entities.length - 1)] == null
                        || !entities[Math.min(Math.max(value, 0), entities.length - 1)].isAlive));
    }

    // Todo: дублируемый код убрать
    private void useByIndex(int index, Room room, Action doAfterUse) {
        Entity[] entities = room.hostileEntities;

        if (index <= 0) {
            if (entities[0] != null) {
                Entity entity1 = null;
                if (entities.length > 1 && entities[1] != null) {
                    entity1 = entities[1];
                }
                Entity entity0 = entities[0];
                entity0.getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
                if (entity1 != null) {
                    entity1.getDamaged(new Damage(this.thirdDamage, this.lastPower, this.power));
                }
            }
        } else if (index < entities.length - 1) {
            Entity entity0 = null;
            Entity entity1 = null;
            Entity entity2 = null;
            if (entities[index - 1] != null) {
                entity0 = entities[index - 1];
            }
            if (entities[index] != null) {
                entity1 = entities[index];
            }
            if (entities[index + 1] != null) {
                entity2 = entities[index + 1];
            }
            if (entity0 != null) {
                entity0.getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
            }
            if (entity1 != null) {
                entity1.getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
            }
            if (entity2 != null) {
                entity2.getDamaged(new Damage(this.thirdDamage, this.lastPower, this.power));
            }
        } else if (index == entities.length - 1) {
            Entity entity0 = null;
            Entity entity1 = null;
            if (entities[index - 1] != null) {
                entity0 = entities[index - 1];
            }
            if (entities[index] != null) {
                entity1 = entities[index - 1];
            }
            if (entity0 != null) {
                entity0.getDamaged(new Damage(this.firstDamage, this.lastPower, this.power));
            }
            if (entity1 != null) {
                entity1.getDamaged(new Damage(this.secondDamage, this.lastPower, this.power));
            }
        } else if (index > entities.length - 1) {
            if (entities[index - 1] != null) {
                entities[entities.length - 1].getDamaged(
                        new Damage(this.firstDamage, this.lastPower, this.power));
            }
        }
        super.use(room, doAfterUse);
    }

    public String toString() {
        return "hit three enemies (one\nby your choice and two\nsurrounding him,\ndealing "
                + this.firstDamage.totalDamage + ", "
                + this.secondDamage.totalDamage + " and "
                + this.thirdDamage.totalDamage +
                " to first, second and\nthird of them, respectively.\nBaseDamage is " + this.damage;
    }
}
