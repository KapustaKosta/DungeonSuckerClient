package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class Creature extends Entity {
    protected boolean isSummoned = false;
    public int indexOfSkillToBeUsed;

    public Creature(int health, int power, int weight, boolean isHostile, Room place, String name) {
        super(health, weight, place, name);
        this.power = power;
        this.isHostile = isHostile;
        this.isSummoned = false;
    }

    public void makeMove() {
        if (this.isSummoned) {
            super.makeMove();
        } else {
            this.summon();
        }
    }

    private void summon() {
        this.isSummoned = true;
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

}
