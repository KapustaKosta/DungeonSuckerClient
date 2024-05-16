package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class Creature extends Entity {

    protected boolean isSummoned = false;
    public int indexOfSkillToBeUsed;
    protected String moveMessage = "";

    public Creature(int health, int power, int weight, boolean isHostile, Room place, String name) {
        super(health, weight, place, name);
        this.power = power;
        this.isHostile = isHostile;
        this.isSummoned = false;
        this.isFighting = false;
    }

    public void makeMove(Action doAfterMove) {
        if (this.isFighting) {
            if (this.isSummoned) {
                System.out.println(this.moveMessage);
                this.weapon.useByCreature(this.place, indexOfSkillToBeUsed, doAfterMove);
            } else {
                System.out.println(this.name + "is summoning");
                this.summon(doAfterMove);
            }
        }
        super.makeMove(doAfterMove);
    }

    private void summon(Action action) {
        this.isSummoned = true;
        action.run();
    }

    public void setPlace(Room room) {
        this.place = room;
    }

    public void damage(Entity who, Damage damage, String type) {
        who.getDamaged(damage);
    }

    public void die() {
        if (this.isHostile) {
            for (int i = 0; i < this.place.amountOfFriendlyEntities; ++i) {
                if (this.place.friendlyEntities[i] != null) {
                    this.place.friendlyEntities[i].obtainExp(this.experiencePerKill);
                }
            }
        }
        super.die();
    }

    public int startMove() {
        if (this.isSummoned) {
            int index = new Random().nextInt(this.weapon.skills.length);
            this.indexOfSkillToBeUsed = index;
            if (this.weapon.creatureSkills[index] == null) {
                return -1;
            }
            return this.weapon.creatureSkills[index].identifier;
        }
        return -1;
    }
}
