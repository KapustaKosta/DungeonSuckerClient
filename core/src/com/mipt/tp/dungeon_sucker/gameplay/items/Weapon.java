package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Scanner;

public class Weapon extends Item {
  protected RaritySet rarity;
  int level;
  protected ElementSet element;
  protected int weight;
  public Skill[] skills;
  protected int amountOfSkills = 0;
  protected double strengthScale = 0;
  protected double dexterityScale = 0;
  protected double faithScale = 0;
  protected double intellectScale = 0;
  protected double vigorScale = 0;
  protected int power;

  public Weapon(int numberOfSkills) {
    this.skills = new Skill[numberOfSkills];
  }

  public void useSkill(int index, Room room) {
    this.skills[index].use(room);
  }

  public void generateSkill(Skill skill) {
    this.skills[this.amountOfSkills++] = skill;
  }

  public void use(Room place) {
    throw new NotImplementedException();
  }

  public void recount() {
    int effectiveness = (int) (this.power * (
        this.holder.strength * this.strengthScale
            + this.holder.dexterity * this.dexterityScale
            + this.holder.faith * this.faithScale
            + this.holder.intellect * this.intellectScale
            + this.holder.vigor * this.vigorScale
            + 1));
    for (int i = 0; i < this.amountOfSkills; ++i) {
      this.skills[i].setPower(effectiveness);
    }
  }

  public int getSkillIndex() {
    // переписать выбор, когда подкрутите доступ из экрана
    System.out.println("Choose your skill");
    for (int i = 0; i < this.skills.length; ++i) {
      System.out.println(i + 1 + ": " + skills[i].toString());
    }
    Scanner in = new Scanner(System.in);
    int index = in.nextInt() - 1;
    return index;
  }
}
