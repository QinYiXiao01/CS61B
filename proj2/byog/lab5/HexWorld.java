package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public static void main(String[] args) {
        // 初始化界面
        TERenderer renderer = new TERenderer();
        renderer.initialize(WIDTH, HEIGHT);

        // 初始化方块
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(3, 1, 30, 30, world);
        renderer.renderFrame(world);


    }

    /**
     * 画单个六边形
     */
    public static void addHexagon(int s, int num, int x, int y, TETile[][] world) {
        int width = s * 3 - 2;
        int height = s * 2;
        // TETile[][] singleHexagon = new TETile[width][height];
        for (int i = x; i <= x + height ; i++) {
            for (int j = y; j <= y + width ; j++) {
                world[i][j] = getType(num);
                addBlank(world, i, j, s); // 添加空格
            }
        }
    }

    /**
     * 为矩形添加空格使其成为六边形
     * @param world
     * @param i
     * @param j
     * @param s
     */
    public static void addBlank(TETile[][] world, int i, int j, int s) {
        // 若此格子在中间两行，啥也不加
//        if (i == s || i == s - 1) {
//            return;
//        }
        // 除了中间两行，剩下的距离中间元素较远的，都置空
        if (Math.abs(j - s) > i + 1) {
            world[i][j] = Tileset.NOTHING;
        }

        world[25][25] = Tileset.NOTHING;
    }

    /**
     * 返回瓷砖的种类
     * @param num
     * @return
     */
    public static TETile getType(int num) {
        switch (num) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.MOUNTAIN;
            case 3:
                return Tileset.GRASS;
            default:
                return Tileset.NOTHING;
        }
    }

//    public void drawHexagon(TETile[][] world, int x, int y, int s) {
//        int width = s *
//        for (int i = x; i < x + width; i++) {
//            for (int j = y; j < y + height; j++) {
//                world[i][j] = hexagon[i][j];
//            }
//        }
//    }
}
