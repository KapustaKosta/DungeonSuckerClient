package com.mipt.tp.dungeon_sucker.gameplay.level.logic;

import com.mipt.tp.dungeon_sucker.InteractiveObjects.Chest;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creature;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Bat;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.ElementalSpirit;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.IvanKalinin;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Mimic;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Rat;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Skeleton;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Slime;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Creatures.Vampire;
import com.mipt.tp.dungeon_sucker.InteractiveObjects.Entity;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.DungeonMasster;
import com.mipt.tp.dungeon_sucker.gameplay.generators.Sets.ElementSet;
import com.mipt.tp.dungeon_sucker.gameplay.level.Map;
import com.mipt.tp.dungeon_sucker.gameplay.level.Room;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.*;
import com.mipt.tp.dungeon_sucker.math.IntVector2;
import com.mipt.tp.dungeon_sucker.math.RandomNumGenerator;

public class DFSMapGenerator extends MapGenerator {

    private Room[][] rooms;
    private boolean[][] visited;
    private int counter;
    private int targetRoomCount;

    public DFSMapGenerator(
            RoomsTexturesPack roomsTexturesPack) {
        super(roomsTexturesPack);
    }

    @Override
    public Map generate(Map map, int targetRoomCount) {
        this.rooms = map.rooms;
        this.visited = new boolean[rooms.length][rooms[0].length];
        this.counter = 0;
        this.targetRoomCount = targetRoomCount;

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                IntVector2 position = new IntVector2(j, i);
                rooms[i][j] = new EmptyRoom(position, roomsTexturesPack.emptyRoomTexture);
            }
        }

        int from = rooms.length / 3;
        int to = (rooms.length / 3) * 2;
        int y = RandomNumGenerator.generateFromRange(from, to);
        from = rooms[0].length / 3;
        to = (rooms[0].length / 3) * 2;
        int x = RandomNumGenerator.generateFromRange(from, to);
        dfs(new IntVector2(x, y));

        Spawn spawn = null;
        ExitRoom exitRoom = null;

        boolean found = false;
        for (int i = rooms.length - 1; i >= 0; i--) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (!(rooms[i][j] instanceof EmptyRoom)) {
                    IntVector2 position = new IntVector2(j, i);
                    spawn = new Spawn(position, roomsTexturesPack.spawnTexture);
                    rooms[i][j] = spawn;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        found = false;
        for (int i = 0; i < rooms.length; i++) {
            for (int j = rooms[0].length - 1; j >= 0; j--) {
                if (!(rooms[i][j] instanceof EmptyRoom)) {
                    IntVector2 position = new IntVector2(j, i);
                    exitRoom = new ExitRoom(position, roomsTexturesPack.exitRoomTexture);
                    rooms[i][j] = exitRoom;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        return new Map(rooms, spawn, exitRoom);
    }

    public void dfs(IntVector2 coordinates) {
        counter++;
        visited[coordinates.y][coordinates.x] = true;
        spawnRandomRoom(coordinates);
        int[] deltaX = new int[]{-1, 0, 1, 0};
        int[] deltaY = new int[]{0, 1, 0, -1};
        int[] randomOrder = new int[]{0, 1, 2, 3};
        ArrayTools.shuffleArray(randomOrder);
        for (Integer i : randomOrder) {
            IntVector2 newCoordinates = new IntVector2(coordinates.x + deltaX[i],
                    coordinates.y + deltaY[i]);
            if (isCoordinatesOnMap(newCoordinates, rooms) && counter <= targetRoomCount) {
                dfs(newCoordinates);
            }
        }
    }

    public void spawnRandomRoom(IntVector2 coordinates) {
        IntVector2 roomPosition = new IntVector2(coordinates.x, coordinates.y);
        int roomType = RandomNumGenerator.generateFromRange(0, 10);
        if (roomType == 0) {
            rooms[coordinates.y][coordinates.x] = new Oasis(roomPosition, roomsTexturesPack.oasisTexture);
        }
        if (roomType > 0 && roomType < 8) {
            Room room = generateHauntedRoom(roomPosition);
            rooms[coordinates.y][coordinates.x] = room;
            room.level = 1;
            room.chest = new Chest(room.level, room);
        }
        if (roomType >= 8 && roomType < 9) {
            rooms[coordinates.y][coordinates.x] = new PeaceRoom(roomPosition,
                    roomsTexturesPack.peaceRoomTexture);
        }
        if (roomType >= 9 && roomType <= 10) {
            rooms[coordinates.y][coordinates.x] = new PeaceRoom(roomPosition,
                    roomsTexturesPack.shopTexture);
        }
    }

    private HauntedRoom generateHauntedRoom(IntVector2 position) {
        HauntedRoom result = new HauntedRoom(position, roomsTexturesPack.hauntedRoomTexture);
        int enemiesCount = RandomNumGenerator.generateFromRange(1, 7);
        result.hostileEntities = new Entity[enemiesCount];
        result.master = DungeonMasster.getInstance();
        for (int i = 0; i < enemiesCount; i++) {
            int creatureIndex = RandomNumGenerator.generateFromRange(0, 6);
            switch (creatureIndex) {
                // Todo: mimic и генерация chest
                case 0: {
                    result.insert(new Bat(true, result, "Bat" + (i + 1)), true);
                    break;
                }
                case 1: {
                    result.insert(new ElementalSpirit(true, result, "Elemental spirit" + (i + 1), ElementSet.getRandom()), true);
                    break;
                }
                case 2: {
                    // result.insert(new IvanKalinin(result), true);
                    break;
                }
                case 3: {
                    result.insert(new Rat(true, result, "Rat" + (i + 1)), true);
                    break;
                }
                case 4: {
                    result.insert(new Skeleton(true, result, "Skeleton" + (i + 1)), true);
                    break;
                }
                case 5: {
                    result.insert(new Slime(true, result, "Slime" + (i + 1), RandomNumGenerator.generateFromRange(0, 3)), true);
                    break;
                }
                case 6: {
                    result.insert(new Vampire(true, result, "Vampire" + (i + 1)), true);
                    break;
                }
            }
        }
        result.isHaunted = true;
        return result;
    }

    private boolean isCoordinatesOnMap(IntVector2 coordinates, Room[][] map) {
        if (coordinates.x < 0 || coordinates.x >= map[0].length) {
            return false;
        }
        return coordinates.y >= 0 && coordinates.y < map.length;
    }
}
