package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;

public class StepOrder {
  long timeOfStep;
  Entity entity;
  public StepOrder(long timeOfStep, Entity creature){
    this.entity = creature;
    this.timeOfStep = timeOfStep;
}
}
