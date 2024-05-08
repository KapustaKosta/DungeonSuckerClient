package com.mipt.tp.dungeon_sucker.UI.text;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FixedSysFont {
    private static BitmapFont font;

    public static BitmapFont getInstance() {
        if (font != null) return font;
        font = new BitmapFont(Gdx.files.internal("fixedsys.fnt"), false);
        return font;
    }
}
