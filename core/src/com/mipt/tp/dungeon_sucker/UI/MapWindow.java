package com.mipt.tp.dungeon_sucker.UI;

import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class MapWindow extends Window {
    private Level level;

    public MapWindow(IntVector2 topLeftPoint,
                     IntVector2 bottomRightPoint, Level level) {
        super(topLeftPoint, bottomRightPoint);
        this.level = level;
        IntVector2 anchor = new IntVector2(topLeftPoint.x, bottomRightPoint.y);
        level.setAnchor(anchor);
    }

    @Override
    public void draw() {
        drawBoard();
        level.draw();
    }
}
