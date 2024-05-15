package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth.PointResistance;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.FragileBody;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class Skeleton extends Creature {
    final int BASE_HEALTH = 3;
    final int BASE_POWER = 1;
    final int BASE_WEIGHT = 3;
    final int DEX_PER_LVL = 1;
    final int STR_PER_LVL = 2;
    final int VIG_PER_LVL = 1;

    public Skeleton(boolean isHostile, Room place, String name) {
        super(3, 1, 3, isHostile, place, name);
        this.weapon = new WeaponGenerator().generateWeapon(this.power);
        this.weapon.getObtained(this);
        new FragileBody().getObtained(this);
        new PointResistance().getObtained(this);
        this.name = name;
        this.experiencePerKill = 3;
        this.description = this.name + ", a skeleton, may hit you with its weapon, " + this.weapon.name;
        this.texture = new Texture("skeleton.png");
    }

    public Skeleton(int health, int power, int weight, boolean isHostile, Room place) {
        super(health, power, weight, isHostile, place, "Skeleton");
        this.weapon = new WeaponGenerator().generateWeapon(this.power);
        this.weapon.getObtained(this);
        new FragileBody().getObtained(this);
        new PointResistance().getObtained(this);
        this.name = "Skeleton";
        this.experiencePerKill = 3;
        this.description = this.name + ", a skeleton, may hit you with its weapon, " + this.weapon.name;
        this.texture = new Texture("skeleton.png");
    }

    public void summon() {
        this.strength = this.STR_PER_LVL * this.master.level;
        this.dexterity = this.DEX_PER_LVL * this.master.level;
        this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
        this.maxHealth = this.health;
        this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
        this.weapon.recount();
    }

    public void makeMove(Action doAfterMove) {
        System.out.println(this.isSummoned + " " + this.isFighting);
        if (this.isSummoned && this.isFighting) {
            System.out.println("Skeleton attacks");
            this.weapon.useByCreature(this.place, indexOfSkillToBeUsed, doAfterMove);
        } else doAfterMove.run();
        super.makeMove(doAfterMove);
    }
}
