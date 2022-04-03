package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"你可以的!", "你能行!",
            "不错!", "牛逼啊!", "冲冲冲!",
            "简单!", "太叼了!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        MemoryGame game = new MemoryGame(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        this.rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String randString = "";
        for (int i = 0; i < n; i++) {
            int randNum = rand.nextInt(27);
            char randChar = CHARACTERS[randNum];
            randString += String.valueOf(randChar); // 效率最高的转换方法，要记住
        }
        return randString;
    }

    public void drawFrame(String s) {
        //TODO: Take the string and display it in the center of the screen (成功/失败等信息)
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Monaco", Font.BOLD, 30));
        StdDraw.text(width / 2, height / 2, s);
        StdDraw.show();

        if (!gameOver) {
            StdDraw.setFont(new Font("Monaco", Font.BOLD, 20));
            StdDraw.textLeft(1, height - 1, "round:" + round);
            if (playerTurn) {
                StdDraw.textLeft(width / 2, height - 1, "输入！");
            } else {
                StdDraw.textLeft(width / 2, height - 1, "仔细看！");
            }
            StdDraw.show();
        }
    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for (int i = 0; i < letters.length(); i++) {
            StdDraw.pause(500);
            char letter = letters.charAt(i);
            drawFrame(String.valueOf(letter));
            StdDraw.pause(1000);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        String inputString = "";
        drawFrame(inputString); // 打印输入字符串

        while (inputString.length() < n) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                inputString += String.valueOf(c);
                drawFrame(inputString);
            }
            continue;
        }
        StdDraw.pause(500);
        return inputString;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        gameOver = false;
        round = 1;
        //TODO: Establish Game loop
        while (!gameOver) {
            playerTurn = false;
            // 生成随机字符串
            String randString = generateRandomString(round);
            // 在屏幕上打印局数信息
            drawFrame("第" + round + "轮 加油！");
            StdDraw.pause(1500);
            // 依次打印出每个字符
            flashSequence(randString);
            // 开始监听玩家键入
            playerTurn = true;
            String inputString = solicitNCharsInput(round);
            if (inputString.equals(randString)) {
                drawFrame("恭喜你答对 进入下一轮");
                round++;
            } else {
                drawFrame("很遗憾你答错 游戏结束");
                gameOver = true;
            }

        }
    }

}
