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

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class DungeonSuckerGame extends ApplicationAdapter {

    private static final Color BACKGROUND = Color.valueOf("#222222");
    private Character character;
    private Interface anInterface;
    public static boolean allowToChangeRoom = false;
    private static HttpClient client;
    private boolean isSignIn = false;
    private static String login = "";
    private static String password = "";

    private void update() throws InterruptedException {
        if (allowToChangeRoom) character.getInput();
    }

    @Override
    public void create() {
        client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        while (!isSignIn) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write:\n0, if you want to log in to your account\n1, if you want to register");
            int input = scanner.nextInt();
            if (input == 0) {
                System.out.println("Enter your login");
                this.login = scanner.next().trim();
                System.out.println("Enter your password");
                this.password = scanner.next().trim();
                try {
                    isSignIn = signIn(this.login, this.password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!isSignIn){
                    System.out.println("Invalid login or password");
                }
            }
            if (input == 1) {
                System.out.println("Enter new login");
                String login = scanner.next().trim();
                System.out.println("Enter new password");
                String password = scanner.next().trim();
                System.out.println("Enter you email");
                String email = scanner.next().trim();
                boolean registrationResult = false;
                try {
                    registrationResult = register(login, password, email);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(registrationResult){
                    System.out.println("You are registered in the system!");
                }
            }
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

    private void generateGame(MapGenerator mapGenerator) {
        Level level = new Level(mapGenerator, 10, 10);
        Map startMap = level.getMap();
        MapWindow mapWindow = new MapWindow(new IntVector2(0, 25), new IntVector2(10, 15), level);

        DungeonMasster dungeonMasster = DungeonMasster.getInstance();
        InventoryWindow inventoryWindow = new InventoryWindow(new IntVector2(0, 15),
                new IntVector2(10, 0), 4, 4);

        IntVector2 characterPosition = new IntVector2(startMap.spawn.getPosition().x, startMap.spawn.getPosition().y);
        character = new Character(characterPosition, new Texture("knight.png"), level, -75, 50, inventoryWindow);
        character.maxHealth = character.health;
        character.master = dungeonMasster;
        character.mapTexture = new Texture("character.png");
        character.name = "Vasya";
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
            if (character.isFighting) {
                character.showFightingButtons();
            }
            nowRoom.setOnTryAgain(args1 -> {
                generateGame(mapGenerator);
            });
            mainWindow.setRoom(nowRoom);
            character.place = nowRoom;
        });
        System.out.println(character.getAllDataForMakingCreature());
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
        ScreenUtils.clear(BACKGROUND);
        anInterface.drawInLibGDX();
        character.drawInLibGDX();
    }

    private static boolean signIn(String login, String password) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/office/myOffice"))
                //.POST(HttpRequest.BodyPublishers.noBody())
                .header("LOGIN", login)
                .header("PASSWORD", password)
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().equals("You are in my office!");
    }

    private static boolean register(String login, String password, String email) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/register"))
                .POST(HttpRequest.BodyPublishers.noBody())
                .header("LOGIN", login)
                .header("PASSWORD", password)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body().equals("Пользователь успешно зарегистрирован");
    }

    public static void sendDeadPlayerData(String data) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/office/sendDeadPlayerData"))
                .POST(HttpRequest.BodyPublishers.ofString(data))
                .header("LOGIN", login)
                .header("PASSWORD", password)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
    }

    public static String receiveDeadPlayerData() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI("http://localhost:8080/office/receiveDeadPlayerData"))
                .header("LOGIN", login)
                .header("PASSWORD", password)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        return response.body();
    }
}
