package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.Split;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.BatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class Slime extends Creature {
    final int BASE_HEALTH = 3;
    final int BASE_POWER = 1;
    final int BASE_WEIGHT = 3;
    final int DEX_PER_LVL = 2;
    final int STR_PER_LVL = 1;
    final int VIG_PER_LVL = 1;
    public int slimeLevel;

    public Slime(boolean isHostile, Room place, String name, int slimeLevel) {
        super(3 * (slimeLevel + 1), (slimeLevel + 1), 3, isHostile, place, name);
        this.name = name;
        this.experiencePerKill = 3;
        this.weapon = new BatClaws(this.power);
        this.weapon.getObtained(this);
        new Split().getObtained(this);
        this.slimeLevel = slimeLevel;
        this.description = this.name + ", a blob full of something gooey, looks like he's capable of dealing " + this.power + "of physical damage";
        this.texture = new Texture("slime.png");
        this.moveMessage = this.name + " is moving";
    }

    public Slime(int health, int power, int weight, boolean isHostile, Room place, int slimeLevel) {
        super(health * (slimeLevel + 1), power * (slimeLevel + 1), weight, isHostile, place, "Rat");
        this.slimeLevel = slimeLevel;
        this.name = "Blob";
        this.experiencePerKill = 3;
        this.weapon = new BatClaws(this.power);
        this.weapon.getObtained(this);
        new Split().getObtained(this);
        this.description = this.name + ", a blob full of something gooey, looks like he's capable of dealing " + this.power + "of physical damage";
        this.texture = new Texture("slime.png");
        this.moveMessage = this.name + " is moving";
    }

    public void summon(Action doAfterMove) {
        this.strength = this.STR_PER_LVL * this.master.level;
        this.dexterity = this.DEX_PER_LVL * this.master.level;
        this.maxHealth = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
        this.health = this.maxHealth;
        this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
        this.weapon = new BatClaws(this.power);
        this.weapon.getObtained(this);
        this.weapon.recount();
        super.summon(doAfterMove);
    }

}
