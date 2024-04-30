package com.mipt.tp.dungeon_sucker.Skills;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Bat;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Rat;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.Skill;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.CreaturesToSummon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public class SummonAlly extends Skill {
  private final CreaturesToSummon creatureToSummon;
  private final boolean createsHostile;
  private final DungeonMasster master;
  private final Entity user;

  public SummonAlly() {
    super();
    this.identifier = 1;
    this.power = 1;
    this.lastPower = 1;
    this.creatureToSummon = CreaturesToSummon.Rat;
    this.createsHostile = false;
    this.master = null;
    this.user = null;
  }

  public SummonAlly(int power, CreaturesToSummon creaturesToSummon, Entity holder) {
    super();
    this.identifier = 1;
    this.power = power;
    this.lastPower = power;
    this.creatureToSummon = creaturesToSummon;
    this.createsHostile = holder.isHostile;
    this.master = holder.master;
    this.user = holder;
  }

  public void use(Room room) {
    System.out.println("SUMMON!");
    switch (this.creatureToSummon) {
      case Bat: {
        room.insert(new Bat(1, this.power, 3, this.createsHostile, room), this.createsHostile);
        break;
      }
      case Rat: {
        room.insert(new Rat(1, this.power, 3, this.createsHostile, room), this.createsHostile);
        break;
      }
    }
  }
}
