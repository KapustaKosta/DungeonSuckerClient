package com.mipt.tp.dungeon_sucker.gameplay.items;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

public abstract class Artifact extends Item {
  protected String description;
  protected int effectiveness;
  public boolean mustBeRemoved = false;
  public boolean triggerableByDeath = false;
  protected boolean triggerableByEnteringRoom = false;
  public boolean triggerableByBeingDamaged = false;

  public abstract void triggerByBeingDamaged(Damage damage);

  public void triggerByEnteringRoom(Room room) {
  }

  public void triggerByDeath() {
  }

  public void getObtained(Entity entity) {
    super.getObtained(entity);
    this.holder.artifacts.add(this);
  }

  public void getLost() {
    this.holder.baseWeight -= this.weight;
    this.holder.recountWeight();
    this.holder.makeFictionalMove();
    for (int i = 0; i < this.holder.artifacts.size(); ++i) {
      if (this == this.holder.artifacts.get(i)) {
        this.holder.artifacts.remove(i);
        break;
      }
    }
    super.getLost();
    this.holder.recountWeapon();
    this.holder = null;
  }

}
