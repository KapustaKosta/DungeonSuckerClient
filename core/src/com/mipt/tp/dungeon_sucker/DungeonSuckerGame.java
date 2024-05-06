package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Rat;
import com.mipt.tp.dungeon_sucker.UI.Interface;
import com.mipt.tp.dungeon_sucker.UI.InventoryWindow;
import com.mipt.tp.dungeon_sucker.UI.MainWindow;
import com.mipt.tp.dungeon_sucker.UI.MapWindow;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.DFSMapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.MapGenerator;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class DungeonSuckerGame extends ApplicationAdapter {

    private static final Color BACKGROUND = Color.valueOf("#222222");
    private Character character;
    private Room room;

    private Interface anInterface;


    private void update() {
        character.getInput();
    }

    @Override
    public void create() {
        RoomsTexturesPack texturesPack = new RoomsTexturesPack();
        texturesPack.emptyRoomTexture = new Texture("emptyRoom.png");
        texturesPack.hauntedRoomTexture = new Texture("room.png");
        texturesPack.exitRoomTexture = new Texture("exitRoom.png");
        texturesPack.oasisTexture = new Texture("room.png");
        texturesPack.peaceRoomTexture = new Texture("room.png");
        texturesPack.spawnTexture = new Texture("spawn.png");
        texturesPack.shopTexture = new Texture("room.png");
        texturesPack.shopTexture = new Texture("room.png");
        MapGenerator mapGenerator = new DFSMapGenerator(texturesPack);

        Level level = new Level(mapGenerator, 10, 10);
        MapWindow mapWindow = new MapWindow(new IntVector2(0, 25), new IntVector2(10, 15), level);

        InventoryWindow inventoryWindow = new InventoryWindow(new IntVector2(0, 15), new IntVector2(10, 0), 4, 4);
        Item exampleItem = new Item();
        exampleItem.name = "knife";
        exampleItem.texture = new Texture("knife.png");
        inventoryWindow.addItem(exampleItem);

        Room exampleRoom = new Room();
        Character exampleCharacter = new Character(15, 100, "Vasya", null);
        exampleCharacter.texture = new Texture("knight.png");
        Creature[] exampleHostileEntities = new Creature[3];
        exampleHostileEntities[0] = new Rat(
                true,
                exampleRoom,
                "rat1");
        exampleHostileEntities[1] = new Rat(
                true,
                exampleRoom,
                "rat2");
        exampleHostileEntities[2] = new Rat(
                true,
                exampleRoom,
                "rat3");
        exampleRoom.hostileEntities = exampleHostileEntities;
        exampleRoom.friendlyEntities = new Character[]{exampleCharacter};
        MainWindow mainWindow = new MainWindow(new IntVector2(10, 25), new IntVector2(42, 0), exampleRoom, new Vector2(400, 250), new Vector2(700, 250));
        mainWindow.setCurrentEntityIndicator(exampleCharacter);

        anInterface = new Interface(mapWindow, inventoryWindow, mainWindow);
        Gdx.input.setInputProcessor(anInterface);

        IntVector2 characterPosition = new IntVector2(level.getMap().spawn.getPosition().x, level.getMap().spawn.getPosition().y);
        this.character = new Character(characterPosition, new Texture("character.png"), level, 1, 1);

    /* Texture texture = new Texture("room.png");
    room = new Room(new IntVector2(10, 10), texture, new DungeonMasster());*/
        // FightTry.aboba();
        // FightTry.generateWeapons();
    }

    @Override
    public void render() {
        this.update();
        ScreenUtils.clear(BACKGROUND);
        anInterface.draw();
        character.draw();
    }
}
