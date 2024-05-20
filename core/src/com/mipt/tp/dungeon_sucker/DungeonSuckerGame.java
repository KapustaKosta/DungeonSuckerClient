package com.mipt.tp.dungeon_sucker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Character;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.UI.Interface;
import com.mipt.tp.dungeon_sucker.UI.InventoryWindow;
import com.mipt.tp.dungeon_sucker.UI.MainWindow;
import com.mipt.tp.dungeon_sucker.UI.MapWindow;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.RaritySet;
import com.mipt.tp.dungeon_sucker.gameplay.items.Artifacts.ArtifactsForBoth.Masteries.HammerMastery;
import com.mipt.tp.dungeon_sucker.gameplay.items.Item;
import com.mipt.tp.dungeon_sucker.gameplay.items.Weapons.WeaponsForBoth.Club;
import com.mipt.tp.dungeon_sucker.gameplay.level.Level;
import com.mipt.tp.dungeon_sucker.gameplay.level.Map;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.DFSMapGenerator;
import com.mipt.tp.dungeon_sucker.gameplay.level.logic.MapGenerator;
import com.mipt.tp.dungeon_sucker.helper.Constants;
import com.mipt.tp.dungeon_sucker.math.IntVector2;

public class DungeonSuckerGame extends ApplicationAdapter {

    private static final Color BACKGROUND = Color.valueOf("#222222");
    private Character character;

    private Interface anInterface;

    public static boolean allowToChangeRoom = false;

    private void update() throws InterruptedException {
        if (allowToChangeRoom) character.getInput();
    }

    @Override
    public void create() {
        if (Constants.TEST_FIGHT) {
            FightTry.aboba();
            FightTry.generateWeapons();
            return;
        }

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

        generateGame(mapGenerator);
    }

    private void generateGame(MapGenerator mapGenerator)
    {
        Level level = new Level(mapGenerator, 10, 10);
        Map startMap = level.getMap();
        MapWindow mapWindow = new MapWindow(new IntVector2(0, 25), new IntVector2(10, 15), level);

        DungeonMasster dungeonMasster = DungeonMasster.getInstance();
        InventoryWindow inventoryWindow = new InventoryWindow(new IntVector2(0, 15),
                new IntVector2(10, 0), 4, 4);

        IntVector2 characterPosition = new IntVector2(startMap.spawn.getPosition().x, startMap.spawn.getPosition().y);
        character = new Character(characterPosition, new Texture("knight.png"), level, 25, 50, inventoryWindow);
        character.maxHealth = character.health;
        character.master = dungeonMasster;
        character.mapTexture = new Texture("character.png");
        startMap.spawn.friendlyEntities = new Entity[]{character};

        Item exampleItem = new Item();
        exampleItem.name = "knife";
        exampleItem.texture = new Texture("knife.png");
        inventoryWindow.addItem(exampleItem);

        MainWindow mainWindow = new MainWindow(new IntVector2(10, 25), new IntVector2(42, 0),
                startMap.spawn);
        mainWindow.setCurrentEntityIndicator(character);

        anInterface = new Interface(mapWindow, inventoryWindow, mainWindow);
        Gdx.input.setInputProcessor(anInterface);

        Club club = new Club(1, 1, "Bat", RaritySet.Common);
        club.getObtained(character);
        new HammerMastery().getObtained(character);
        character.setActiveWeapon(club);
        character.addOnMoveListener(args -> {
            Room nowRoom = startMap.getRoom(args[1], args[0]);
            nowRoom.master = dungeonMasster;
            character.place.extract(character, false);
            nowRoom.insert(character, false);
            for (Entity entity : nowRoom.hostileEntities) {
                if (entity != null) {
                    dungeonMasster.add(0, entity);
                }
            }
            if(character.isFighting) {
                character.showFightingButtons();
            }
            nowRoom.setOnTryAgain(args1 -> {
                generateGame(mapGenerator);
            });
            mainWindow.setRoom(nowRoom);
            character.place = nowRoom;
        });
        dungeonMasster.add(character.weight, character);
        DungeonMasster.getInstance().move();
    }

    @Override
    public void render() {
        try {
            this.update();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (Constants.TEST_FIGHT) {
            return;
        }
        ScreenUtils.clear(BACKGROUND);
        anInterface.drawInLibGDX();
        character.drawInLibGDX();
    }
}
