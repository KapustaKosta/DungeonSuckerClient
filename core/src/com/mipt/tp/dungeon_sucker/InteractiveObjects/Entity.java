package com.mipt.tp.dungeon_sucker.InteractiveObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.Damage;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifact;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapon;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.ArrayList;

public class Entity extends InteractiveObject implements Drawable {
  // Базовые статы из РПГ. Ловкость для каких-нибудь рапир, сила для булав, мудрость для магии
  public int vigor; // +hp
  public int power; // -time per move
  public int strength; // +dmg of some weapons
  public int dexterity; // +dmg of some other weapons
  public int intellect; // +dmg of other weapons
  public int faith; // you guessed it, +dmg of other weapons
  // +dmg can intersect
  protected Weapon weapon;
  public DungeonMasster master;
  public int health;
  public int maxHealth;
  public int physicalArmor;
  public int magicalArmor;
  public boolean isAlive = true;
  protected IntVector2 levelPosition;
  protected Vector2 position;
  protected Level level;
  private Texture texture;
  private SpriteBatch batch;
  public int weight;
  public Room place;
  public String name;
  public ArrayList<Item> items;
  public ArrayList<Artifact> artifacts;
  public boolean isHostile;

  public Entity(int health, int weight, Room place, String name) {
    this.maxHealth = health;
    this.health = health;
    this.weight = weight;
    this.place = place;
    this.name = name;
  }

  public Entity(IntVector2 position, Texture texture, Level level) {
    this.levelPosition = position;
    this.position = new Vector2(position.x * Constants.cellSize, position.y * Constants.cellSize);
    this.texture = texture;
    this.batch = new SpriteBatch();
    this.level = level;
  }

  @Override
  public void draw() {
    batch.begin();
    batch.draw(texture, position.x, position.y);
    batch.end();
  }

  public void updateRealPosition() {
    this.position = new Vector2(levelPosition.x * Constants.cellSize, levelPosition.y * Constants.cellSize);
  }

  public void getDamaged(Damage damage) {
    damage = damage.copy();
    this.triggerArtifactsByDamage(damage);
    int dmgDealt = damage.totalDamage;
    this.health -= dmgDealt;
    System.out.println(this.name + " got damaged. Current health: " + this.health);
    if (this.health <= 0) {
      this.die();
    }
    this.removeExtraArtifacts();
    this.weapon.recount();
  }

  public void makeMove() {
    this.weapon.recount();
  }

  public void die() {
    this.isAlive = false;
    this.triggerArtifactsByDeath();
    this.removeExtraArtifacts();
    if (this.isHostile) {
      this.place.checkHostileAlive();
    } else {
      this.place.checkFriendlyAlive();
    }
  }

  private void triggerArtifactsByDeath() {
    for (int i = 0; i < this.artifacts.size(); ++i) {
      if (this.artifacts.get(i).triggerableByDeath) {
        this.artifacts.get(i).triggerByDeath();
      }
    }
  }

  private void removeExtraArtifacts() {
    int amountOfArtifactsToRemove = 0;
    for (int i = 0; i - amountOfArtifactsToRemove < this.artifacts.size(); ++i) {
      if (this.artifacts.get(i - amountOfArtifactsToRemove).mustBeRemoved) {
        this.artifacts.get(i - amountOfArtifactsToRemove).getLost();
        ++amountOfArtifactsToRemove;
      }
    }
  }

  private void triggerArtifactsByDamage(Damage damage) {
    for (int i = 0; i < this.artifacts.size(); ++i) {
      if (this.artifacts.get(i).triggerableByBeingDamaged) {
        this.artifacts.get(i).triggerByBeingDamaged(damage);
      }
    }
  }

  public void decreaseMaxHP(int value) {
    if (this.maxHealth <= value) {
      this.die();
    } else if (this.health <= value) {
      this.health = 1;
      this.maxHealth -= value;
    } else {
      this.maxHealth -= value;
      this.health -= value;
      // Добавить метод отрисовки здоровья после каждого хода/действия
    }
  }

  public void setActiveWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  public void recountWeapon() {
    this.weapon.recount();
  }
}
