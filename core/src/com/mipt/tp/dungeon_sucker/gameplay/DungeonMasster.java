package com.mipt.tp.dungeon_sucker.gameplay;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Upgrader;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;

import java.util.LinkedList;
import java.util.Objects;

public class DungeonMasster {

    long time = 0;
    public LinkedList<StepOrder> orderOfSteps;
    public int level = 0;

    private static DungeonMasster dungeonMasster;

    public static DungeonMasster getInstance() {
        if (dungeonMasster == null) {
            dungeonMasster = new DungeonMasster();
        }
        return dungeonMasster;
    }

    public DungeonMasster() {
        this.orderOfSteps = new LinkedList<>();
        this.orderOfSteps.add(new StepOrder(100, new Upgrader(1, 100, new Room(this), "GodObject")));
    }

    public void add(long timeOfStep, Entity entity) {
        System.out.println("adding " + entity.name + " " + timeOfStep);
        entity.master = this;
        System.out.println(entity.name + " " + timeOfStep);
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
                    && this.orderOfSteps.get(i + 1).timeOfStep > timeOfStep) ||
                    (this.orderOfSteps.get(i).timeOfStep <= timeOfStep
                            && this.orderOfSteps.get(i + 1).timeOfStep == timeOfStep && entity.isCharacter)) {
                this.orderOfSteps.add(i + 1, new StepOrder(timeOfStep, entity));
                return;
            }
        }
    }

    // вынести в update
    public void move() {
        System.out.println("I'm gonna move, I'm " + this.orderOfSteps.getFirst().entity.name);
        if (this.orderOfSteps.getFirst().entity.isAlive) {
            this.time = Math.max(this.time, this.orderOfSteps.getFirst().timeOfStep);
            this.orderOfSteps.getFirst().entity.startMove(args -> {
                System.out.println(this.orderOfSteps.getFirst().entity.name);
                if (Objects.equals(this.orderOfSteps.getFirst().entity.name, "GodObject")) {
                    System.out.println("CURRENT LEVEL IS " + this.level);
                }
                System.out.println("NUMBER OF ENTITIES = " + this.orderOfSteps.size());
                this.orderOfSteps.getFirst().entity.makeMove(brgs -> {
                    System.out.println(this.orderOfSteps.getFirst().entity.name + " has moved");
                    Class<Creature> creatureClass = Creature.class;
                    if (this.orderOfSteps.getFirst().entity.getClass() == creatureClass) {
                        this.add((this.orderOfSteps.getFirst().entity).weight
                                + this.time, this.orderOfSteps.getFirst().entity);
                    } else {
                        this.add((this.orderOfSteps.getFirst().entity).weight
                                + this.time, this.orderOfSteps.getFirst().entity);
                    }
                    this.move();
                });
            });
        } else {
            System.out.println("O-o-o-o-o-ops, I guess, I'm too dead do move");
            this.orderOfSteps.removeFirst();
            this.move();
        }
    }
}
