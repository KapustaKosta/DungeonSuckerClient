package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.mipt.tp.dungeon_sucker.gameplay.entities.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import java.util.Objects;
import java.util.Random;

public class Creature extends Entity {
  protected boolean isHostile;
  protected int power;
  protected Weapon weapon;

  public Creature(int health, int power, int weight, boolean isHostile, HauntedRoom place, String name) {
    super(health, weight, place, name);
    this.power = power;
    this.isHostile = isHostile;
  }

  public void makeMove() {
    if (this.isHostile) {
      Entity[] victims = this.place.friendlyEntities;
      Entity victim = victims[new Random().nextInt(this.place.amountOfFriendlyEntities)];
      this.damage(victim, this.power, this.name);
    } else {
      Entity[] enemies = this.place.hostileEntities;
      Entity enemy = enemies[new Random().nextInt(enemies.length)];
      this.damage(enemy, this.power, this.name);
    }
  }

  public void setPlace(HauntedRoom room) {
    this.place = room;
  }

  public void damage(Entity who, int damage, String type) {
    who.getDamaged(damage, type);
  }

  public void getDamaged(int damage, String type) {
    if (Objects.equals(type, "Magic")) {
      damage = Math.max(0, damage - magicalArmor);
    } else {
      damage = Math.max(0, damage - physicalArmor);
    }
    this.health -= damage;
    System.out.println(this.name + " got damaged. Current health: " + this.health);
    if (this.health <= 0) {
      this.die();
    }
  }
}