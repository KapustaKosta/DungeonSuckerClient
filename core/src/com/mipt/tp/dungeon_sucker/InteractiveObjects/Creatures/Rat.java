package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.BatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.RatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class Rat extends Creature {
    final int BASE_HEALTH = 3;
    final int BASE_POWER = 1;
    final int BASE_WEIGHT = 3;
    final int DEX_PER_LVL = 2;
    final int STR_PER_LVL = 1;
    final int VIG_PER_LVL = 1;

    public Rat(boolean isHostile, Room place, String name) {
        super(3, 1, 3, isHostile, place, name);
        this.name = name;
        this.experiencePerKill = 3;
        this.weapon = new BatClaws(this.power);
        this.weapon.getObtained(this);
        this.description = this.name + ", a rat that crawls everywhere, may bite you, dealing " + this.power + "of physical damage";
        this.texture = new Texture("rat.png");
        this.moveMessage = this.name + " is moving";
    }

    public Rat(int health, int power, int weight, boolean isHostile, Room place) {
        super(health, power, weight, isHostile, place, "Rat");
        this.name = "Rat";
        this.experiencePerKill = 3;
        this.weapon = new BatClaws(this.power);
        this.weapon.getObtained(this);
        this.description = this.name + ", a rat that crawls everywhere, may bite you, dealing  " + this.power + "of physical damage";
        this.texture = new Texture("rat.png");
        this.moveMessage = this.name + " is moving";
    }

    public void summon(Action doAfterMove) {
        this.strength = this.STR_PER_LVL * this.master.level;
        this.dexterity = this.DEX_PER_LVL * this.master.level;
        this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
        this.maxHealth = this.health;
        this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
        this.weapon = new RatClaws(this.power);
        this.weapon.getObtained(this);
        this.weapon.recount();
        super.summon(doAfterMove);
    }
}
