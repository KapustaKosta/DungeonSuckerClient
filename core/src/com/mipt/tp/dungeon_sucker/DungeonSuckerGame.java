package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.gameplay.Room;

public class DungeonSuckerGame extends ApplicationAdapter {
	private static final Color BACKGROUND = Color.valueOf("#222222");
	private Room room;
	
	@Override
	public void create () {
		Texture texture = new Texture("room.png");
		room = new Room(new Vector2(10, 10), texture);
	}

	@Override
	public void render () {
		ScreenUtils.clear(BACKGROUND);
		room.draw();
	}
}
