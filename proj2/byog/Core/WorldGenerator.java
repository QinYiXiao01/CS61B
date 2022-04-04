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
        addRoom(world, new Random(Integer.parseInt(seed)));
        /*Random roomNumSeed = new Random(200148);
        int roomNum = RandomUtils.uniform(roomNumSeed, 8);
        for (int i = 0; i < roomNum; i++) {
            addRoom(world, new Random(Integer.parseInt(seed)));
        }
         */
        return world;
    }


    /**
     * 传进要编辑的世界，一次只生成一个房间
     * @param world
     */
    public void addRoom(TETile[][] world, Random roomSeed) {
        int x = roomSeed.nextInt(width); // RandomUtils.uniform(roomSeed, width);
        int y = roomSeed.nextInt(height);
        int xLength = roomSeed.nextInt(2, 10);
        int yLength = roomSeed.nextInt(2, 10);
        Room room = new Room(x, y, xLength, yLength);
        room.outAdjust();

        x = room.getX();
        y = room.getY();
        xLength = room.getxLength();
        yLength = room.getyLength();

        // 如果重叠了，就退出方法
        if (room.isOverlap(world)) {
            return;
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
    }
}
