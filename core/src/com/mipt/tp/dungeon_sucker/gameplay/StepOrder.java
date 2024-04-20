package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;

public class StepOrder {
  public long timeOfStep;
  public Entity entity;
  public StepOrder(long timeOfStep, Entity entity){
    this.entity = entity;
    this.timeOfStep = timeOfStep;
}
}
