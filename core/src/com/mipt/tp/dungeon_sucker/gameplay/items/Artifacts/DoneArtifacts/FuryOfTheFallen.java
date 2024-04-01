package com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.DoneArtifacts;

import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;

public class FuryOfTheFallen extends Artifact {

  public FuryOfTheFallen() {
    super();
    this.weight = 2;
    this.description = "Makes you respawn after you die, but only once";
  }

  public void triggerByDeath() {
    System.out.println("I, " + this.holder.name + " RESPAWN");
    if (!this.holder.isAlive) {
      this.holder.isAlive = true;
      this.holder.health = 50;
      this.getLost();
    }
    // Можно изменить метод использования, но уже и так норм
  }
}
