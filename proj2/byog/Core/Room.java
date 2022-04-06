package byog.Core;


import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {
    private int x; // 中心点位置
    private int y;
    private int xLength;
    private int yLength;

    public Room(int x, int y, int xLength, int yLength) {
        this.x = x;
        this.y = y;
        this.xLength = xLength;
        this.yLength = yLength;
        outAdjust();
    }

    /**
     * 判断是否是房间的边界
     * @param testX
     * @param testY
     * @return
     */
    public boolean isEdge(int testX, int testY) {
        if (Math.abs(testX - x) == xLength / 2 ||
                Math.abs(testY - y) == yLength / 2) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否重叠
     * @return
     */
    public boolean isOverlap(TETile[][] world) {
        for (int i = x - xLength / 2; i <= x + xLength / 2; i++) {
            for (int j = y - yLength / 2; j <= y + yLength / 2 ; j++) {
                // 一旦出现非空格，就说明重叠了
                if (world[i][j] != Tileset.NOTHING) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 如果出界了进行调整
     */
    public void outAdjust() {
        int balanceX = x - xLength / 2;
        int balanceY = y - yLength / 2;
        if (balanceX < 0) {
            this.x -= balanceX; // 若x坐标出去了屏幕(< 0), 就加回来
        }
        if (balanceY < 0) {
            this.y -= balanceY;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getxLength() {
        return xLength;
    }

    public int getyLength() {
        return yLength;
    }
}
