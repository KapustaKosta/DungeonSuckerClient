package com.mipt.tp.dungeon_sucker.gameplay.level;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Chest;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mipt.tp.dungeon_sucker.UI.Drawable;
import com.mipt.tp.dungeon_sucker.gameplay.generators.ArtifactGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.generators.WeaponGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class Room implements Drawable {
  public Chest chest = new Chest(0, this);
  public DungeonMasster master;
  protected boolean isCleared;
  public Entity[] friendlyEntities = new Entity[8];
  public Entity[] hostileEntities = new Entity[8];
  private IntVector2 levelPosition;
  private Texture texture;
  private SpriteBatch batch;
  public int amountOfFriendlyEntities = 0;
  public boolean isHaunted = false;
  public int amountOfHostileEntities = 0;

  protected boolean isLocked = false;
  public int level;
  public int threatLevel = 0; // 0 - just a room, 1 - fighting encounter, 2 - elite encounter, 3 - Boss encounter

  public Room(IntVector2 levelPosition, Texture texture) {
    this.levelPosition = levelPosition;
    this.texture = texture;
    this.batch = new SpriteBatch();
    this.isCleared = true;
  }

  public Room(IntVector2 levelPosition, Texture texture, DungeonMasster master) {
    this.levelPosition = levelPosition;
    this.texture = texture;
    this.batch = new SpriteBatch();
    this.master = master;
    this.isCleared = true;
  }

  public Room() {
    this.levelPosition = null;
  }

  public Room(IntVector2 intVector2, Texture texture, Creature[] creatures, DungeonMasster dm) {
    this(intVector2, texture, dm);
    for (Creature creature : creatures) {
      this.insert(creature, creature.isHostile);
    }
    this.isCleared = false;
    this.isHaunted = true;
  }

  public Room(DungeonMasster dungeonMasster) {
    this.master = dungeonMasster;
  }

  @Override
  public void draw() {
    batch.begin();
    batch.draw(texture, levelPosition.x * Constants.CELL_SIZE, levelPosition.y * Constants.CELL_SIZE);
    batch.end();
  }

  public void drawWithAnchor(IntVector2 anchor) {
    batch.begin();
    batch.draw(texture, (levelPosition.x + anchor.x) * Constants.CELL_SIZE, (levelPosition.y + anchor.y) * Constants.CELL_SIZE);
    batch.end();
  }

  public IntVector2 getPosition() {
    return levelPosition;
  }

  public boolean isLocked() {
    return isLocked;
  }

  public void insert(Entity entity, boolean isHostile) {
    if (isHostile) {
      for (int i = 0; i < this.amountOfHostileEntities; ++i) {
        if (hostileEntities[i] != null && !this.hostileEntities[i].isAlive) {
          this.hostileEntities[i] = entity;
          entity.place = this;
          entity.positionInRoom = i;
          this.master.add(0, entity);
          return;
        }
      }
      if (this.amountOfHostileEntities < this.hostileEntities.length) {
        this.hostileEntities[this.amountOfHostileEntities++] = entity;
        entity.place = this;
        entity.positionInRoom = this.amountOfHostileEntities - 1;
        this.master.add(0, entity);
      }
      return;
    }
    entity.place = this;
    for (int i = 0; i < this.amountOfFriendlyEntities; ++i) {
      if (friendlyEntities[i] != null && !this.friendlyEntities[i].isAlive) {
        this.friendlyEntities[i] = entity;
        entity.positionInRoom = i;
        this.master.add(0, entity);
        return;
      }
    }
    if (this.amountOfFriendlyEntities < this.friendlyEntities.length) {
      this.friendlyEntities[this.amountOfFriendlyEntities++] = entity;
      entity.positionInRoom = this.amountOfFriendlyEntities - 1;
      this.master.add(0, entity);
    }
  }

  public void checkHostileAlive() {
    boolean battleMustEnd = true;
    for (int i = 0; i < this.amountOfHostileEntities; ++i) {
      if (this.hostileEntities[i] != null && this.hostileEntities[i].isAlive) {
        battleMustEnd = false;
        System.out.println("Battle shall continue");
      }
    }
    if (battleMustEnd) {
      System.out.println("No enemies in room");
      this.isCleared = true;
      this.isHaunted = false;
      this.addItemToChest(this.threatLevel);
      for (int i = 0; i < this.amountOfFriendlyEntities; ++i) {
        this.friendlyEntities[i].isFighting = false;
      }
    }
  }

  public void checkFriendlyAlive() {
    boolean battleMustEnd = true;
    for (int i = 0; i < this.amountOfFriendlyEntities; ++i) {
      if (this.friendlyEntities[i].isAlive) {
        battleMustEnd = false;
      }
    }
    if (battleMustEnd) {
      System.out.println("Room was taken by evil");
    }
  }

  public void addItemToChest(int threatLevel) {
    switch (threatLevel) {
      case 0:
        return;
      case 1: {
        chest.add(new WeaponGenerator().generateWeapon(this.level));
        return;
      }
      case 2: {
        for (int i = 0; i < 3; ++i) {
          chest.add(new WeaponGenerator().generateWeapon(this.level));
        }
        return;
      }
      case 3: {
        for (int i = 0; i < 4; ++i) {
          chest.add(new WeaponGenerator().generateWeapon(this.level));
        }
        for (int i = 0; i < 4; ++i) {
          chest.add(new ArtifactGenerator().generateArtifact());
        }
        return;
      }
    }
  }

  public void addItemToChest(Item item) {
    this.chest.add(item);
  }

  public void clearCorpses() {
    for (int i = 0; i < this.friendlyEntities.length; ++i) {
      if (this.friendlyEntities[i] != null && !this.friendlyEntities[i].isAlive) {
        this.friendlyEntities[i] = null;
      }
    }
    for (int i = 0; i < this.hostileEntities.length; ++i) {
      if (this.hostileEntities[i] != null && !this.hostileEntities[i].isAlive) {
        this.hostileEntities[i] = null;
      }
    }
  }
}
