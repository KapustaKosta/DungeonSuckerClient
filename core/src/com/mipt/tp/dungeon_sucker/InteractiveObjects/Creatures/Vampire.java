package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.ElementalResistances.DarkResistance;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.LightVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.PointVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.VampireClaws;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class Vampire extends Creature {
    final int BASE_HEALTH = 3;
    final int BASE_POWER = 1;
    final int BASE_WEIGHT = 3;
    final int DEX_PER_LVL = 3;
    final int STR_PER_LVL = 3;
    final int VIG_PER_LVL = 1;

    public Vampire(boolean isHostile, Room place, String name) {
        super(3, 1, 3, isHostile, place, name);
        this.weapon = new VampireClaws(this.power);
        this.weapon.getObtained(this);
        new LightVulnerability().getObtained(this);
        new PointVulnerability().getObtained(this);
        new DarkResistance().getObtained(this);
        this.name = name;
        this.experiencePerKill = 3;
        this.description = this.name + ", a noble vampire";
        this.texture = new Texture("vampire.png");
        this.moveMessage = this.name + " is moving";
    }

    public Vampire(int health, int power, int weight, boolean isHostile, Room place) {
        super(health, power, weight, isHostile, place, "Vampire");
        this.weapon = new VampireClaws(this.power);
        this.weapon.getObtained(this);
        new LightVulnerability().getObtained(this);
        new PointVulnerability().getObtained(this);
        new DarkResistance().getObtained(this);
        this.name = "Vampire";
        this.experiencePerKill = 3;
        this.description = this.name + ", a noble vampire";
        this.texture = new Texture("vampire.png");
        this.moveMessage = this.name + " is moving";
    }

    public void summon(Action doAfterMove) {
        this.strength = this.STR_PER_LVL * this.master.level;
        this.dexterity = this.DEX_PER_LVL * this.master.level;
        this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
        this.power = this.master.level;
        this.maxHealth = this.health;
        this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
        this.weapon.recount();
        super.summon(doAfterMove);
    }
}
