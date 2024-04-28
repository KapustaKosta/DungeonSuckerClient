package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Upgrader;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;

public class DungeonMasster {

  long time = 0;
  public LinkedList<StepOrder> orderOfSteps;
  public int level = 0;

  public DungeonMasster() {
    this.orderOfSteps = new LinkedList<>();
    this.orderOfSteps.add(new StepOrder(100, new Upgrader(1, 100, new Room(this), "GodObject")));
  }

  public void add(long timeOfStep, Entity entity) {
    entity.master = this;
    for (int i = 0; i < this.orderOfSteps.size(); ++i) {
      if (this.orderOfSteps.get(i).entity == entity) {
        this.orderOfSteps.remove(i);
        break;
      }
    }
    if (this.orderOfSteps.isEmpty()) {
      this.orderOfSteps.addLast(new StepOrder(timeOfStep, entity));
      return;
    } else if (this.orderOfSteps.getFirst().timeOfStep > timeOfStep) {
      this.orderOfSteps.addFirst(new StepOrder(timeOfStep, entity));
      return;
    } else if (this.orderOfSteps.getLast().timeOfStep < timeOfStep) {
      this.orderOfSteps.addLast(new StepOrder(timeOfStep, entity));
      return;
    }
    for (int i = 0; i < this.orderOfSteps.size() - 1; ++i) {
      if ((this.orderOfSteps.get(i).timeOfStep <= timeOfStep
          && this.orderOfSteps.get(i + 1).timeOfStep > timeOfStep)||
          (this.orderOfSteps.get(i).timeOfStep <= timeOfStep
          && this.orderOfSteps.get(i + 1).timeOfStep == timeOfStep && entity.isCharacter)) {
        this.orderOfSteps.add(i + 1, new StepOrder(timeOfStep, entity));
        return;
      }
    }
  }

  // вынести в update
  public void move() {
    while (true) {
      if (this.orderOfSteps.getFirst().entity.isAlive) {
        this.time = Math.max(this.time, this.orderOfSteps.getFirst().timeOfStep);
        this.orderOfSteps.getFirst().entity.makeMove();
        Class<Creature> creatureClass = Creature.class;
        if (this.orderOfSteps.getFirst().entity.getClass() == creatureClass) {
          this.add((this.orderOfSteps.getFirst().entity).weight
              + this.time, this.orderOfSteps.getFirst().entity);
        } else {
          this.add((this.orderOfSteps.getFirst().entity).weight
              + this.time, this.orderOfSteps.getFirst().entity);
        }
      } else {
        this.orderOfSteps.removeFirst();
      }
    }
  }
}
