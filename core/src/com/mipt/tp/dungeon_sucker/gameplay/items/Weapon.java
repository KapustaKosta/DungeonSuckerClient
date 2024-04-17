package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Weapon extends Item {
  protected RaritySet rarity;
  int level;
  protected ElementSet element;
  protected int weight;
  protected Skill[] skills;
  protected int amountOfSkills = 0;
  protected double strengthScale;
  protected double dexterityScale;
  protected double faithScale;
  protected double intellectScale;
  protected int power;

  public Weapon(int numberOfSkills) {
    this.skills = new Skill[numberOfSkills];
  }

  public void useSkill(int index, Room room) {
    this.skills[index].use(room);
  }

  protected void generateSkill(Skill skill) {
    this.skills[this.amountOfSkills++] = skill;
  }

  public void use(Room place) {
    throw new NotImplementedException();
  }

  public void use(HauntedRoom room) {
  }

  public void recount() {
    int effectiveness = (int) (this.power * (
        this.holder.strength * this.strengthScale
        + this.holder.dexterity * this.dexterityScale
        + this.holder.faith * this.faithScale
        + this.holder.intellect * this.intellectScale
            + 1));
    for (int i = 0; i < this.amountOfSkills; ++i) {
      this.skills[i].setPower(effectiveness);
    }
  }
}
