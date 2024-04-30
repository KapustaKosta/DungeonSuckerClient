package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.UI.text.FixedSysFont;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class MainWindow extends Window {

  private Room room;
  private Vector2 friendlyEntitiesPosition;
  private Vector2 hostileEntitiesPosition;
  private SpriteBatch[] friendlyEntitiesBatches;
  private SpriteBatch[] hostileEntitiesBatches;
  private final static int friendlyEntitiesOffset = 10;
  private final static int hostileEntitiesOffset = 15;
  private Text[] friendlyEntitiesHealth;
  private Text[] hostileEntitiesHealth;
  private final static int healthIndent = 30;
  private final Texture currentEntityIndicator = new Texture("currentEntityIndicator.png");
  private final static int currentEntityIndicatorIndent = 50;
  private Batch currentEntityIndicatorBatch;
  private Vector2 currentEntityIndicatorPosition;

  public MainWindow(IntVector2 topLeftPoint,
      IntVector2 bottomRightPoint, Room room, Vector2 friendlyEntitiesPosition,
      Vector2 hostileEntitiesPosition) {
    super(topLeftPoint, bottomRightPoint);
    this.room = room;
    this.friendlyEntitiesPosition = friendlyEntitiesPosition;
    this.hostileEntitiesPosition = hostileEntitiesPosition;
    this.currentEntityIndicatorBatch = new SpriteBatch();

    friendlyEntitiesBatches = new SpriteBatch[room.friendlyEntities.length];
    hostileEntitiesBatches = new SpriteBatch[room.hostileEntities.length];
    friendlyEntitiesHealth = new Text[room.friendlyEntities.length];
    hostileEntitiesHealth = new Text[room.hostileEntities.length];

    float lastX = friendlyEntitiesPosition.x;
    for (int i = 0; i < friendlyEntitiesBatches.length; i++) {
      friendlyEntitiesBatches[i] = new SpriteBatch();
      friendlyEntitiesHealth[i] = new Text(FixedSysFont.getInstance(),
          getHealthBar(room.friendlyEntities[i]),
          new Vector2(lastX,
              friendlyEntitiesPosition.y + room.friendlyEntities[i].texture.getHeight()
                  + healthIndent));
      lastX += room.friendlyEntities[i].texture.getWidth();
      lastX += friendlyEntitiesOffset;
    }
    lastX = hostileEntitiesPosition.x;
    for (int i = 0; i < hostileEntitiesBatches.length; i++) {
      hostileEntitiesBatches[i] = new SpriteBatch();
      hostileEntitiesHealth[i] = new Text(FixedSysFont.getInstance(),
          getHealthBar(room.hostileEntities[i]),
          new Vector2(lastX,
              hostileEntitiesPosition.y + room.hostileEntities[i].texture.getHeight()
                  + healthIndent));
      lastX += room.hostileEntities[i].texture.getWidth();
      lastX += hostileEntitiesOffset;
    }
  }

  public boolean setCurrentEntityIndicator(Entity entity) {
    float lastX = friendlyEntitiesPosition.x;
    for (int i = 0; i < friendlyEntitiesBatches.length; i++) {
      if (entity.equals(room.friendlyEntities[i])) {
        currentEntityIndicatorPosition = new Vector2(lastX,
            friendlyEntitiesPosition.y + room.friendlyEntities[i].texture.getHeight()
                + currentEntityIndicatorIndent);
        return true;
      }
      lastX += room.friendlyEntities[i].texture.getWidth();
      lastX += friendlyEntitiesOffset;
    }

    lastX = hostileEntitiesPosition.x;
    for (int i = 0; i < hostileEntitiesBatches.length; i++) {
      if (entity.equals(room.hostileEntities[i])) {
        currentEntityIndicatorPosition = new Vector2(lastX,
            hostileEntitiesPosition.y + room.hostileEntities[i].texture.getHeight()
                + currentEntityIndicatorIndent);
        return true;
      }
      lastX += room.hostileEntities[i].texture.getWidth();
      lastX += hostileEntitiesOffset;
    }

    return false;
  }

  private static String getHealthBar(Entity entity) {
    return entity.health + "/" + entity.maxHealth;
  }

  @Override
  public void draw() {
    drawBoard();

    // Отрисовка друзей
    float lastX = friendlyEntitiesPosition.x;
    for (int i = 0; i < friendlyEntitiesBatches.length; i++) {
      friendlyEntitiesBatches[i].begin();
      friendlyEntitiesBatches[i].draw(room.friendlyEntities[i].texture,
          lastX, friendlyEntitiesPosition.y);
      friendlyEntitiesBatches[i].end();
      lastX += room.friendlyEntities[i].texture.getWidth();
      lastX += friendlyEntitiesOffset;

      friendlyEntitiesHealth[i].setText(getHealthBar(room.friendlyEntities[i]));
      friendlyEntitiesHealth[i].draw();
    }

    // Отрисовка врагов
    lastX = hostileEntitiesPosition.x;
    for (int i = 0; i < hostileEntitiesBatches.length; i++) {
      hostileEntitiesBatches[i].begin();
      hostileEntitiesBatches[i].draw(room.hostileEntities[i].texture,
          lastX, hostileEntitiesPosition.y);
      hostileEntitiesBatches[i].end();
      lastX += room.hostileEntities[i].texture.getWidth();
      lastX += hostileEntitiesOffset;

      hostileEntitiesHealth[i].setText(getHealthBar(room.hostileEntities[i]));
      hostileEntitiesHealth[i].draw();
    }

    if (currentEntityIndicatorPosition != null) {
      currentEntityIndicatorBatch.begin();
      currentEntityIndicatorBatch.draw(currentEntityIndicator, currentEntityIndicatorPosition.x,
          currentEntityIndicatorPosition.y);
      currentEntityIndicatorBatch.end();
    }
  }

  public void onMouseClick(Entity hostileEntity) {
    System.out.println(hostileEntity.name);
  }

  public void onMouseClick(int x, int y) {
    if (!isPointInWindow(x, y)) {
      return;
    }
    float lastX = hostileEntitiesPosition.x;
    for (int i = 0; i < hostileEntitiesBatches.length; i++) {
      if (x >= lastX && x <= lastX + room.hostileEntities[i].texture.getWidth()
          && y >= hostileEntitiesPosition.y
          && y <= hostileEntitiesPosition.y + room.hostileEntities[i].texture.getHeight()) {
        onMouseClick(room.hostileEntities[i]);
      }
      lastX += room.hostileEntities[i].texture.getWidth();
      lastX += hostileEntitiesOffset;
    }
  }
}