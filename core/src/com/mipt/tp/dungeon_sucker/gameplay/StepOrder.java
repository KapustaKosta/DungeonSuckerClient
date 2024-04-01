package com.mipt.tp.dungeon_sucker.gameplay;

import InteractiveObjects.Entity;

public class StepOrder {
  long timeOfStep;
  Entity entity;
  public StepOrder(long timeOfStep, Entity entity){
    this.entity = entity;
    this.timeOfStep = timeOfStep;
}
}
