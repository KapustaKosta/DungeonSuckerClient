package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.UI.Buttons.Button;
import com.mipt.tp.dungeon_sucker.UI.Buttons.ButtonsGroup;
import com.mipt.tp.dungeon_sucker.gameplay.Action;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.WeaponTypes;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.ArrayList;

public abstract class Weapon extends Item {

  public WeaponTypes type;
  protected RaritySet rarity;
  public int level;
  protected ElementSet element;
  public Skill[] skills;
  public Skill[] creatureSkills;
  protected int amountOfSkills = 0;
  public double strengthScale = 0;
  public double dexterityScale = 0;
  public double faithScale = 0;
  public double intellectScale = 0;
  protected double vigorScale = 0;
  public int power;
  private int amountOfCreatureSkills = 0;
  private ArrayList<Action> listeners = new ArrayList<>();

  public Weapon(int numberOfSkills) {
    this.skills = new Skill[numberOfSkills];
    this.creatureSkills = new Skill[numberOfSkills];
  }

  public void useSkill(int index, Room room, Action doAfterUse) {
    this.skills[index].use(room, doAfterUse);
  }

  public void generateSkill(Skill skill) {
    this.skills[(this.amountOfSkills++) % this.skills.length] = skill;
  }

  public void generateSkillForCreature(Skill skill) {
    this.creatureSkills[(this.amountOfCreatureSkills++) % this.skills.length] = skill;
  }

  public void use(Room room, Action doAfterUse) {
    this.recount();
    getSkillIndex(args -> {
      System.out.println(this.skills[args[0]].toString());
      this.skills[args[0]].use(room, doAfterUse);
      this.recount();
    });
  }

  public void useByCreature(Room room, int indexOfSkillToBeUsed, Action doAfterUse) {
    this.recount();
    System.out.println(indexOfSkillToBeUsed + " " + this.amountOfCreatureSkills);
    if (indexOfSkillToBeUsed < this.amountOfCreatureSkills) {
      (creatureSkills[indexOfSkillToBeUsed]).use(room, doAfterUse);
    }
    else {
      doAfterUse.run();
    }
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

  public void getSkillIndex(Action listener) {
    ButtonsGroup.getInstance().clear();
    ButtonsGroup.getInstance().setArticle("Choose skill:");
    for (int i = 0; i < this.skills.length; ++i) {
      final int index = i;
      ButtonsGroup.getInstance()
          .addButton(new Button(skills[i].toString(), args -> this.onGetSkillIndex(index)));
    }
    listeners.add(listener);
  }

  public void getSkillIndexUntilPredicate(Action listener, Predicate predicate) {
    ButtonsGroup.getInstance().clear();
    ButtonsGroup.getInstance().setArticle("Choose your skill:");
    for (int i = 0; i < this.skills.length; ++i) {
      final int index = i;
      ButtonsGroup.getInstance()
          .addButton(new Button(skills[i].toString(),
              args -> this.onGetSkillIndexWithPredicate(index, predicate)));
    }
    listeners.add(listener);
  }

  public void onGetSkillIndexWithPredicate(int index, Predicate predicate) {
    if (!predicate.valueFits(index)) {
      getSkillIndexUntilPredicate(null, predicate);
      return;
    }
    for (Action listener : listeners) {
      if (listener != null) {
        listener.run(index);
      }
    }
    listeners.clear();
  }


  public void onGetSkillIndex(int index) {
    for (Action listener : listeners) {
      listener.run(index);
    }
  }

  public void setName(String s) {
    this.name = s;
  }
}

