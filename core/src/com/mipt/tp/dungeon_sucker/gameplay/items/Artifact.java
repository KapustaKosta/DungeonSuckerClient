package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;

public class Artifact extends Item{
  protected String description;
  protected int effectiveness;
  protected int weight;

  protected void triggerByBeingDamaged(Damage damage) {
  }
  protected void triggerByDeath(){}

  protected void getObtained(Entity entity) {
    this.holder = entity;
    this.holder.weight += this.weight;
  }

  protected void getLost() {
    this.holder.weight -= this.weight;
    this.holder = null;
  }

}
