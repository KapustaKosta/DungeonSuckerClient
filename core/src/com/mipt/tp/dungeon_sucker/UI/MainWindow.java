package com.mipt.tp.dungeon_sucker.UI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.UI.Buttons.ButtonsGroupUI;
import com.mipt.tp.dungeon_sucker.UI.text.FixedSysFont;
import com.mipt.tp.dungeon_sucker.UI.text.Text;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class MainWindow extends Window {

    private Room room;
    private int leftBorderMargin = 10;
    private int bottomBorderMargin = 300;
    private int spaceBetweenFriendlyAndHostile = 250;
    private int minSpaceBetweenFriendlyAndHostile = 100;
    private SpriteBatch[] friendlyEntitiesBatches;
    private SpriteBatch[] hostileEntitiesBatches;
    private int[] friendlyEntitiesTextureWidths;
    private int[] hostileEntitiesTextureWidths;
    private float scale = 1;
    private final static int friendlyEntitiesOffset = 10;
    private final static int hostileEntitiesOffset = 15;
    private Text[] friendlyEntitiesHealth;
    private Text[] hostileEntitiesHealth;
    private final static int healthIndent = 30;
    private final Texture currentEntityIndicator = new Texture("currentEntityIndicator.png");
    private final static int currentEntityIndicatorIndent = 50;
    private Batch currentEntityIndicatorBatch;
    private Vector2 currentEntityIndicatorPosition;

    private ButtonsGroupUI buttonsGroupUI;

    public MainWindow(IntVector2 topLeftPoint,
                      IntVector2 bottomRightPoint, Room room) {
        super(topLeftPoint, bottomRightPoint);
        this.currentEntityIndicatorBatch = new SpriteBatch();
        this.buttonsGroupUI = new ButtonsGroupUI(new Texture("buttonHolder.png"));
        setRoom(room);
    }

    public void setRoom(Room room) {
        this.room = room;
        this.currentEntityIndicatorBatch = new SpriteBatch();

        friendlyEntitiesBatches = new SpriteBatch[room.friendlyEntities.length];
        hostileEntitiesBatches = new SpriteBatch[room.hostileEntities.length];
        friendlyEntitiesHealth = new Text[room.friendlyEntities.length];
        hostileEntitiesHealth = new Text[room.hostileEntities.length];
        friendlyEntitiesTextureWidths = new int[room.friendlyEntities.length];
        hostileEntitiesTextureWidths = new int[room.hostileEntities.length];

        float totalEntitiesWidth = leftBorderMargin;
        for (int i = 0; i < friendlyEntitiesBatches.length; i++) {
            if (room.friendlyEntities[i] == null) continue;
            totalEntitiesWidth += room.friendlyEntities[i].texture.getWidth();
            if (i != friendlyEntitiesBatches.length - 1) totalEntitiesWidth += friendlyEntitiesOffset;
        }
        totalEntitiesWidth += spaceBetweenFriendlyAndHostile;
        for (int i = 0; i < hostileEntitiesBatches.length; i++) {
            if (room.hostileEntities[i] == null) continue;
            totalEntitiesWidth += room.hostileEntities[i].texture.getWidth();
            if (i != hostileEntitiesBatches.length - 1) totalEntitiesWidth += hostileEntitiesOffset;
        }
        if (totalEntitiesWidth > width) {
            float delta = totalEntitiesWidth - width;
            totalEntitiesWidth -= spaceBetweenFriendlyAndHostile;
            spaceBetweenFriendlyAndHostile = Integer.max(minSpaceBetweenFriendlyAndHostile, (int) (spaceBetweenFriendlyAndHostile - delta));
            totalEntitiesWidth += spaceBetweenFriendlyAndHostile;
            delta = totalEntitiesWidth - width;
            if (delta >= 0) {
                scale = width / totalEntitiesWidth;
            }
        }

        iterateOnEntitiesAndDoAction(args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            friendlyEntitiesBatches[index] = new SpriteBatch();
            friendlyEntitiesHealth[index] = new Text(FixedSysFont.getInstance(),
                    getHealthBar(room.friendlyEntities[index]),
                    new Vector2(x,
                            bottomLeftPoint.y + bottomBorderMargin + room.friendlyEntities[index].texture.getHeight() * scale
                                    + healthIndent * scale));
        }, args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            hostileEntitiesBatches[index] = new SpriteBatch();
            if (room.hostileEntities[index] != null) {
                hostileEntitiesHealth[index] = new Text(FixedSysFont.getInstance(),
                        getHealthBar(room.hostileEntities[index]),
                        new Vector2(x,
                                bottomLeftPoint.y + bottomBorderMargin + room.hostileEntities[index].texture.getHeight() * scale
                                        + healthIndent * scale));
            }
        });
    }

    public void setCurrentEntityIndicator(Entity entity) {
        iterateOnEntitiesAndDoAction(args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            if (entity.equals(room.friendlyEntities[index])) {
                currentEntityIndicatorPosition = new Vector2(x,
                        bottomLeftPoint.y + bottomBorderMargin + room.friendlyEntities[index].texture.getHeight() * scale
                                + currentEntityIndicatorIndent * scale);
            }
        }, args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            if (entity.equals(room.friendlyEntities[index])) {
                currentEntityIndicatorPosition = new Vector2(x,
                        bottomLeftPoint.y + bottomBorderMargin + room.friendlyEntities[index].texture.getHeight() * scale
                                + currentEntityIndicatorIndent * scale);
            }
        });
    }

    private static String getHealthBar(Entity entity) {
        return entity.health + "/" + entity.maxHealth;
    }

    @Override
    public void drawInLibGDX() {
        drawBoard();

        iterateOnEntitiesAndDoAction(args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            if (room.friendlyEntities[index] != null) {
                friendlyEntitiesBatches[index].begin();
                friendlyEntitiesBatches[index].draw(room.friendlyEntities[index].texture,
                        x, bottomLeftPoint.y + bottomBorderMargin, room.friendlyEntities[index].texture.getWidth() * scale, room.friendlyEntities[index].texture.getHeight() * scale);
                friendlyEntitiesBatches[index].end();
            }

            if (friendlyEntitiesHealth[index] != null && room.friendlyEntities[index] != null) {
                friendlyEntitiesHealth[index].setText(getHealthBar(room.friendlyEntities[index]));
                friendlyEntitiesHealth[index].drawInLibGDX();
            }
        }, args -> {
            float x = (float) args[0];
            int index = (Integer) args[1];
            if (room.hostileEntities[index] != null) {
                hostileEntitiesBatches[index].begin();
                hostileEntitiesBatches[index].draw(room.hostileEntities[index].texture,
                        x, bottomLeftPoint.y + bottomBorderMargin, room.hostileEntities[index].texture.getWidth() * scale, room.hostileEntities[index].texture.getHeight() * scale);
                hostileEntitiesBatches[index].end();
            }

            if (hostileEntitiesHealth[index] != null && room.hostileEntities[index] != null) {
                hostileEntitiesHealth[index].setText(getHealthBar(room.hostileEntities[index]));
                hostileEntitiesHealth[index].drawInLibGDX();
            }
        });

        if (currentEntityIndicatorPosition != null) {
            currentEntityIndicatorBatch.begin();
            currentEntityIndicatorBatch.draw(currentEntityIndicator, currentEntityIndicatorPosition.x,
                    currentEntityIndicatorPosition.y);
            currentEntityIndicatorBatch.end();
        }

        buttonsGroupUI.drawInLibGDX();
    }

    public void iterateOnEntitiesAndDoAction(ObjectAction doWithFriendly, ObjectAction doWithHostile) {
        float lastX = bottomLeftPoint.x + leftBorderMargin * scale;
        for (int i = 0; i < friendlyEntitiesBatches.length; i++) {
            int textureWidth = 0;
            if (room.friendlyEntities[i] != null) {
                textureWidth = (int) (room.friendlyEntities[i].texture.getWidth() * scale);
                friendlyEntitiesTextureWidths[i] = textureWidth;
            } else {
                textureWidth = friendlyEntitiesTextureWidths[i];
            }

            doWithFriendly.run(lastX, i);

            lastX += textureWidth;
            if (i != friendlyEntitiesBatches.length - 1) lastX += friendlyEntitiesOffset * scale;
        }
        lastX += spaceBetweenFriendlyAndHostile;
        for (int i = 0; i < hostileEntitiesBatches.length; i++) {
            int textureWidth = 0;
            if (room.hostileEntities[i] != null) {
                textureWidth = (int) (room.hostileEntities[i].texture.getWidth() * scale);
                hostileEntitiesTextureWidths[i] = textureWidth;
            } else {
                textureWidth = hostileEntitiesTextureWidths[i];
            }

            doWithHostile.run(lastX, i);

            lastX += textureWidth;
            if (i != hostileEntitiesBatches.length - 1) lastX += hostileEntitiesOffset * scale;
        }
    }

    public void onMouseClick(Entity hostileEntity) {
        System.out.println(hostileEntity.name);
    }

    public void onMouseClick(int x, int y) {
        if (!isPointInWindow(x, y)) {
            return;
        }

        iterateOnEntitiesAndDoAction(args -> {
        }, args -> {
            float xPos = (float) args[0];
            int index = (Integer) args[1];
            if (room.hostileEntities[index] != null && x >= xPos && x <= xPos + hostileEntitiesTextureWidths[index]
                    && y >= bottomLeftPoint.y + bottomBorderMargin
                    && y <= bottomLeftPoint.y + bottomBorderMargin + room.hostileEntities[index].texture.getHeight() * scale) {
                onMouseClick(room.hostileEntities[index]);
            }
        });
        buttonsGroupUI.click(x, y);
    }
}
