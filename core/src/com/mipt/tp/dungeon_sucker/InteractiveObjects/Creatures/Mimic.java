package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Chest;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.generators.ItemGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.MimicClaws;

import java.util.Random;

public class Mimic extends Creature {
    Chest chest;
    final int BASE_HEALTH = 10;
    final int BASE_POWER = 5;
    final int BASE_WEIGHT = 5;
    final int DEX_PER_LVL = 2;
    final int STR_PER_LVL = 5;
    final int VIG_PER_LVL = 3;

    public Mimic(Chest chest, boolean isHostile) {
        //  public Creature(int health, int power, int weight, boolean isHostile, Room place, String name) {
        super(10, 5, 5, isHostile, chest.room, "Mimic");
        this.experiencePerKill = 10;
        this.chest = chest;
        this.weapon = new MimicClaws(this.power);
        this.weapon.getObtained(this);
        this.description = this.name + ", it was looking just like a chest, I promise";
    }

    public void summon() {
        this.strength = this.STR_PER_LVL * this.master.level;
        this.dexterity = this.DEX_PER_LVL * this.master.level;
        this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
        this.maxHealth = this.health;
        this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
        this.weapon = new MimicClaws(this.power);
        this.weapon.getObtained(this);
        this.weapon.recount();
    }

    public int startMove() {
        int index = new Random().nextInt(this.weapon.skills.length);
        this.indexOfSkillToBeUsed = index;
        return this.weapon.creatureSkills[index].identifier;
    }

    public void makeMove() {
        if (this.isSummoned && this.isFighting) {
            System.out.println("MIMIC IS MOVING");
            this.weapon.useByCreature(this.place, indexOfSkillToBeUsed);
        }
        super.makeMove();
    }

    public void die() {
        for (int i = 0; i < this.chest.inventory.size(); ++i) {
            this.place.chest.add(this.chest.inventory.get(i));
        }
        this.place.chest.add(new ItemGenerator().generateItem(this.master.level));
        this.place.chest.add(new ItemGenerator().generateItem(this.master.level));
        super.die();
    }
}
