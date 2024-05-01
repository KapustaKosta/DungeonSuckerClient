package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.DarkVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.FireVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.FrostVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.LightVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.BatClaws;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForEnemies.ElementalPower;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.Random;

public class ElementalSpirit extends Creature {
  final int BASE_HEALTH = 3;
  final int BASE_POWER = 1;
  final int BASE_WEIGHT = 3;
  final int DEX_PER_LVL = 2;
  final int STR_PER_LVL = 1;
  final int VIG_PER_LVL = 1;
  public final ElementSet element;

  public ElementalSpirit(boolean isHostile, Room place, String name, ElementSet element) {
    super(3, 1, 3, isHostile, place, name);
    this.name = name;
    this.element = element;
    this.experiencePerKill = 3;
    this.weapon = new ElementalPower(this.power);
    this.weapon.getObtained(this);
    this.addElementTraits();
    this.description = this.name + ", basically a winged rat, may also bite you, dealing " + this.power + "of elemental damage";
  }

  public ElementalSpirit(int health, int power, int weight, boolean isHostile, Room place, ElementSet element) {
    super(health, power, weight, isHostile, place, "Rat");
    this.name = "Bat";
    this.element = element;
    this.experiencePerKill = 3;
    this.weapon = new ElementalPower(this.power);
    this.weapon.getObtained(this);
    this.addElementTraits();
    this.description = this.name + ", basically a winged rat, may also bite you, dealing " + this.power + "of elemental damage";
  }

  private void addElementTraits() {
    switch (this.element) {
      case Light: {
        new LightResistance().getObtained(this);
        new DarkVulnerability().getObtained(this);
      }
      case Dark: {
        new DarkResistance().getObtained(this);
        new LightVulnerability().getObtained(this);
      }
      case Fire: {
        new FireResistance().getObtained(this);
        new FrostVulnerability().getObtained(this);
      }
      case Frost: {
        new FrostResistance().getObtained(this);
        new FireVulnerability().getObtained(this);
      }
      case Poison: {
        new PoisonResistance().getObtained(this);
      }
      case None: {
      }
    }
  }

  public void summon() {
    this.strength = this.STR_PER_LVL * this.master.level;
    this.dexterity = this.DEX_PER_LVL * this.master.level;
    this.health = this.BASE_HEALTH + this.VIG_PER_LVL * this.master.level;
    this.maxHealth = this.health;
    this.experiencePerKill = (int) (Math.sqrt(this.master.level) * this.experiencePerKill);
    this.weapon = new BatClaws(this.power);
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
      System.out.println("BAT IS MOVING");
      this.weapon.useByCreature(this.place, indexOfSkillToBeUsed);
    }
    super.makeMove();
  }
}
