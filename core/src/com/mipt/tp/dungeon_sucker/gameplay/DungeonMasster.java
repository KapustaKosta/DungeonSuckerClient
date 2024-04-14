package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;

import java.util.LinkedList;

public class DungeonMasster {

  long time = 0;
  public LinkedList<StepOrder> orderOfSteps;

  public DungeonMasster() {
    this.orderOfSteps = new LinkedList<>();
  }

  public void add(long timeOfStep, Entity entity) {
    entity.master = this;
    if (this.orderOfSteps.isEmpty()) {
      this.orderOfSteps.addLast(new StepOrder(timeOfStep, entity));
      return;
    } else if (this.orderOfSteps.getFirst().timeOfStep >= timeOfStep) {
      this.orderOfSteps.addFirst(new StepOrder(timeOfStep, entity));
      return;
    } else if (this.orderOfSteps.getLast().timeOfStep <= timeOfStep) {
      this.orderOfSteps.addLast(new StepOrder(timeOfStep, entity));
      return;
    }
    for (int i = 0; i < this.orderOfSteps.size() - 1; ++i) {
      if (this.orderOfSteps.get(i).timeOfStep <= timeOfStep
          && this.orderOfSteps.get(i + 1).timeOfStep >= timeOfStep) {
        this.orderOfSteps.add(i + 1, new StepOrder(timeOfStep, entity));
        return;
      }
    }
  }

  public void move() {
    while (true) {
      //System.out.println(this.orderOfSteps.size());
      if (this.orderOfSteps.getFirst().entity.isAlive) {
        this.time = this.orderOfSteps.getFirst().timeOfStep;
        this.orderOfSteps.getFirst().entity.makeMove();
        Class<Creature> creatureClass = Creature.class;
        if (this.orderOfSteps.getFirst().entity.getClass() == creatureClass) {
          this.add((this.orderOfSteps.getFirst().entity).weight
              + this.orderOfSteps.getFirst().timeOfStep, this.orderOfSteps.getFirst().entity);
        } else {
          this.add((this.orderOfSteps.getFirst().entity).weight
              + this.orderOfSteps.getFirst().timeOfStep, this.orderOfSteps.getFirst().entity);
        }
        this.orderOfSteps.removeFirst();
      } else {
        this.orderOfSteps.removeFirst();
      }
    }
  }
}
