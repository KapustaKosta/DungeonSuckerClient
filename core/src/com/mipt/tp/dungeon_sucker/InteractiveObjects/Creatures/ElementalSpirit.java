package com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures;

import com.badlogic.gdx.graphics.Texture;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsFirBoth.*;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.FireVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.FrostVulnerability;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForEnemies.LightVulnerability;
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
    this.description =
        this.name + ", basically a winged rat, may also bite you, dealing " + this.power
            + "of elemental damage";
    this.texture = ElementSet.getTexture(element);
  }

  public ElementalSpirit(int health, int power, int weight, boolean isHostile, Room place,
      ElementSet element) {
    super(health, power, weight, isHostile, place, "Elemental Spirit");
    this.name = "Elemental spirit";
    this.element = element;
    this.experiencePerKill = 3;
    this.weapon = new ElementalPower(this.power);
    this.weapon.getObtained(this);
    this.addElementTraits();
    this.description =
        this.name + ", basically a winged rat, may also bite you, dealing " + this.power
            + "of elemental damage";
    this.texture = ElementSet.getTexture(element);
  }

  private void addElementTraits() {
    switch (this.element) {
/*            case Light: {
                new LightResistance().getObtained(this);
                new DarkVulnerability().getObtained(this);
            }*/
      case Stone: {
        new DarkResistance().getObtained(this);
        new LightVulnerability().getObtained(this);
      }
      case Fire: {
        new FireResistance().getObtained(this);
        new FrostVulnerability().getObtained(this);
      }
      case Water: {
        new WaterResistance().getObtained(this);
        new FireVulnerability().getObtained(this);
      }
      case Nature: {
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
    this.weapon = new ElementalPower(this.power);
    this.weapon.getObtained(this);
    this.weapon.recount();
  }

  public void makeMove(Action doAfterMove) {
    System.out.println(this.isSummoned + " " + this.isFighting);
    if (this.isSummoned && this.isFighting) {
      System.out.println("Elemental Spirit IS MOVING");
      this.weapon.useByCreature(this.place, indexOfSkillToBeUsed, doAfterMove);
    } else {
      doAfterMove.run();
    }
    super.makeMove(doAfterMove);
  }
}
