package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class FuryOfTheFallen extends Artifact {
  int weight = 2;
  public FuryOfTheFallen(){
    super();
    this.description = "Makes you respawn after you die, but only once";
  }
  public void triggerByDeath(){
    System.out.println("I, " + this.holder.name + " RESPAWN");
    this.holder.isAlive =true;
    this.holder.health = 50;
    getLost();
  }
}
