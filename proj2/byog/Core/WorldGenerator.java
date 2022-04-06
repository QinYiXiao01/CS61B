package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class WorldGenerator {
    private int width;
    private int height;
    private String seed;

    public WorldGenerator(int width, int height, String seed) {
        this.width = width;
        this.height = height;
        this.seed = seed;
    }

    public TETile[][] generateWorld() {
        TETile[][] world = new TETile[width][height];
        // 先为世界填充空白
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        // 添加房间
        addRooms(world, new Random(Integer.parseInt(seed)));
        return world;
    }


    /**
     * 传进要编辑的世界，之前想错了，应该是一下子生成所有的房间
     * @param blankWorld
     */
    public TETile[][] addRooms(TETile[][] blankWorld, Random roomSeed) {
        TETile[][] world = blankWorld;
        // 得到房间的总个数
        Random roomNumSeed = new Random(2001482);
        int roomNum = roomNumSeed.nextInt(20);
        for (int i = 0; i < roomNum; i++) {
            int x = roomSeed.nextInt(width);
            int y = roomSeed.nextInt(height);
            int xLength = roomSeed.nextInt(2, 10);
            int yLength = roomSeed.nextInt(2, 10);
            Room room = new Room(x, y, xLength, yLength);
            world = addSingleRoom(world, room);
        }
        return world;
    }

    /**
     * 添加一个房间
     * @param world
     * @param room
     * @return
     */
    public TETile[][] addSingleRoom(TETile[][] world, Room room) {
        int x = room.getX();
        int y = room.getY();
        int xLength = room.getxLength();
        int yLength = room.getyLength();
        // 如果重叠了，就退出方法
        if (room.isOverlap(world)) {
            return null;
        }
        // 没重叠，就创建一个房间
        for (int i = x - xLength / 2; i <= x + xLength / 2; i++) {
            for (int j = y - yLength / 2; j <= y + yLength / 2 ; j++) {
                if (room.isEdge(i, j)) {
                    world[i][j] = Tileset.WALL;
                }
                else {
                    world[i][j] = Tileset.FLOOR;
                }
            }
        }
        return world;
    }
}
