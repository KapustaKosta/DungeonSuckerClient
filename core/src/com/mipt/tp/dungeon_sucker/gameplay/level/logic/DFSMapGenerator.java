package com.mipt.tp.dungeon_sucker.gameplay.level.logic;

import com.badlogic.gdx.math.Vector2;
import com.mipt.tp.dungeon_sucker.UI.texturePacks.RoomsTexturesPack;
import com.mipt.tp.dungeon_sucker.gameplay.level.roomTypes.*;
import com.mipt.tp.dungeon_sucker.gameplay.level.*;
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
    if (roomType > 0 && roomType < 6) {
      rooms[coordinates.y][coordinates.x] = new HauntedRoom(roomPosition,
          roomsTexturesPack.hauntedRoomTexture);
    }
    if (roomType >= 6 && roomType < 9) {
      rooms[coordinates.y][coordinates.x] = new PeaceRoom(roomPosition,
          roomsTexturesPack.peaceRoomTexture);
    }
    if (roomType >= 9 && roomType <= 10) {
      rooms[coordinates.y][coordinates.x] = new PeaceRoom(roomPosition,
          roomsTexturesPack.shopTexture);
    }
  }

  private boolean isCoordinatesOnMap(IntVector2 coordinates, Room[][] map) {
    if (coordinates.x < 0 || coordinates.x >= map[0].length) {
      return false;
    }
    if (coordinates.y < 0 || coordinates.y >= map.length) {
      return false;
    }
    return true;
  }
}
