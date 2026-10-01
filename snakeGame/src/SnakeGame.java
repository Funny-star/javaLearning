import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class SnakeGame {
    // 游戏常量
    private static final int WIDTH = 20;
    private static final int HEIGHT = 15;

    // 方向枚举
    private enum Direction { UP, DOWN, LEFT, RIGHT, STOP }

    // 游戏状态
    private LinkedList<int[]> snake;  // 蛇身坐标 [x, y]
    private int[] food;               // 食物坐标 [x, y]
    private Direction dir;
    private int score;
    private boolean gameOver;
    private Random random;

    public SnakeGame() {
        random = new Random();
        resetGame();
    }

    public void resetGame() {
        snake = new LinkedList<>();
        // 初始化蛇：水平放置3节
        for (int i = 0; i < 3; i++) {
            snake.add(new int[]{WIDTH / 2 - i, HEIGHT / 2});
        }
        dir = Direction.RIGHT;
        score = 0;
        gameOver = false;
        spawnFood();
    }

    private void spawnFood() {
        boolean valid;
        do {
            food = new int[]{random.nextInt(WIDTH), random.nextInt(HEIGHT)};
            valid = true;
            for (int[] segment : snake) {
                if (segment[0] == food[0] && segment[1] == food[1]) {
                    valid = false;
                    break;
                }
            }
        } while (!valid);
    }

    private void clearScreen() {
        // 清屏（通过打印大量换行）
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    private void draw() {
        clearScreen();

        // 绘制顶部边框
        System.out.print("+");
        for (int i = 0; i < WIDTH * 2; i++) System.out.print("-");
        System.out.println("+");

        // 绘制游戏区域
        for (int y = 0; y < HEIGHT; y++) {
            System.out.print("|");
            for (int x = 0; x < WIDTH; x++) {
                boolean printed = false;

                // 检查是否是蛇身
                for (int i = 0; i < snake.size(); i++) {
                    int[] segment = snake.get(i);
                    if (segment[0] == x && segment[1] == y) {
                        if (i == 0) {
                            System.out.print("O ");  // 蛇头
                        } else {
                            System.out.print("o ");  // 蛇身
                        }
                        printed = true;
                        break;
                    }
                }

                // 检查是否是食物
                if (!printed && food[0] == x && food[1] == y) {
                    System.out.print("* ");
                    printed = true;
                }

                // 空白
                if (!printed) {
                    System.out.print("  ");
                }
            }
            System.out.println("|");
        }

        // 绘制底部边框
        System.out.print("+");
        for (int i = 0; i < WIDTH * 2; i++) System.out.print("-");
        System.out.println("+");

        // 显示信息
        System.out.println("分数: " + score + "  |  按 WASD 移动  |  按 Q 退出");
        System.out.println("提示：输入后按回车确认");
    }

    private void input() {
        Scanner scanner = new Scanner(System.in);
        // 非阻塞输入（Java 控制台不支持，所以使用线程）
        // 这里简化：在游戏循环中阻塞等待输入
        try {
            if (System.in.available() > 0) {
                char key = (char) System.in.read();
                key = Character.toLowerCase(key);
                processKey(key);
            }
        } catch (Exception e) {
            // 忽略异常
        }
    }

    // 用于多线程输入的键盘处理
    private void processKey(char key) {
        switch (key) {
            case 'w': if (dir != Direction.DOWN) dir = Direction.UP; break;
            case 's': if (dir != Direction.UP) dir = Direction.DOWN; break;
            case 'a': if (dir != Direction.RIGHT) dir = Direction.LEFT; break;
            case 'd': if (dir != Direction.LEFT) dir = Direction.RIGHT; break;
            case 'q': gameOver = true; break;
        }
    }

    private void update() {
        if (gameOver) return;

        // 计算新蛇头位置
        int[] head = snake.getFirst();
        int newX = head[0];
        int newY = head[1];

        switch (dir) {
            case UP:    newY--; break;
            case DOWN:  newY++; break;
            case LEFT:  newX--; break;
            case RIGHT: newX++; break;
            case STOP:  return;
        }

        // 检查是否吃到食物
        boolean ateFood = (newX == food[0] && newY == food[1]);

        // 移动蛇
        snake.addFirst(new int[]{newX, newY});
        if (!ateFood) {
            snake.removeLast();
        } else {
            score++;
            spawnFood();
        }

        // 碰撞检测
        // 撞墙
        if (newX < 0 || newX >= WIDTH || newY < 0 || newY >= HEIGHT) {
            gameOver = true;
            return;
        }

        // 撞自身（从第2节开始检查）
        for (int i = 1; i < snake.size(); i++) {
            int[] segment = snake.get(i);
            if (segment[0] == newX && segment[1] == newY) {
                gameOver = true;
                return;
            }
        }
    }

    private void showGameOver() {
        clearScreen();
        System.out.println("==================================");
        System.out.println("         游 戏 结 束！          ");
        System.out.println("         最终分数: " + score);
        System.out.println("==================================");
        System.out.println("     按 R 重新开始，按 Q 退出    ");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("r")) {
                resetGame();
                return;
            } else if (input.equals("q")) {
                System.exit(0);
            }
        }
    }

    // 键盘监听线程（用于非阻塞输入）
    private class KeyListener extends Thread {
        @Override
        public void run() {
            Scanner scanner = new Scanner(System.in);
            while (!gameOver) {
                try {
                    if (scanner.hasNext()) {
                        String input = scanner.nextLine().trim();
                        if (input.length() > 0) {
                            processKey(input.charAt(0));
                        }
                    }
                } catch (Exception e) {
                    break;
                }
            }
            scanner.close();
        }
    }

    public void run() {
        // 启动键盘监听线程
        KeyListener keyListener = new KeyListener();
        keyListener.setDaemon(true);
        keyListener.start();

        while (true) {
            // 绘制游戏
            draw();

            // 更新游戏逻辑
            update();

            // 检查游戏是否结束
            if (gameOver) {
                showGameOver();
                continue;
            }

            // 控制速度
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SnakeGame game = new SnakeGame();
        game.run();
    }
}
