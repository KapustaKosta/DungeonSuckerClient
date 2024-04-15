package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;

public class Artifact extends Item {
  public boolean triggerableByBeingDamaged;
  protected String description;
  protected int effectiveness;
  public boolean mustBeRemoved = false;
  public boolean triggerableByDeath = false;

  public void triggerByBeingDamaged(Damage damage) {
  }

  public void triggerByDeath() {
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.artifacts.add(this);
  }

  public void getLost() {
    this.holder.weight -= this.weight;
    this.holder = null;
  }

}
