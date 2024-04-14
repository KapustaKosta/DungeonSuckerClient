package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;

public class Artifact extends Item{
  protected String description;
  protected int effectiveness;

  protected void triggerByBeingDamaged(Damage damage) {
  }
  protected void triggerByDeath(){}

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.artifacts.add(this);
  }

  protected void getLost() {
    this.holder.weight -= this.weight;
    this.holder = null;
  }

}
