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

    protected void summon(Action action) {
        this.isSummoned = true;
        this.makeFictionalMove();
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
//todo: make sure that shakeBeforeMoving() works correctly
    public void startMove(Action action) {
        if (this.isSummoned) {
            this.indexOfSkillToBeUsed = new Random().nextInt(this.weapon.skills.length);
        }
        this.shakeBeforeMoving();
        action.run();
    }
}
