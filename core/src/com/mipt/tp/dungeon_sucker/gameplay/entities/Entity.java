package com.mipt.tp.dungeon_sucker.gameplay.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.HauntedRoom;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

import java.util.Objects;

public class Entity implements Drawable {
  public int health;
  int maxHealth;
  public int physicalArmor;
  protected int magicalArmor;
  public boolean isAlive = true;
  protected IntVector2 levelPosition;
  protected Vector2 position;
  protected Level level;
  private Texture texture;
  private SpriteBatch batch;
  public int weight;
  public Room place;
  public String name;
  // Базовые статы из РПГ. Ловкость для каких-нибудь рапир, сила для булав, мудрость для магии

  public Entity(int health, int weight, HauntedRoom place, String name) {
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

  public void makeMove() {
  }

  public void die() {
    this.isAlive = false;
  }
}
