package lesson4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class lesson4 {
    private static final int SIZE = 9;
    private static final int DOTS_FOR_WIN = 7;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static Scanner scanner = new Scanner(System.in);
    private static Random rand = new Random();
    private static char[][] map;

    public static void main(String[] args) {
        boolean endGame = false;
        // начало
        initMap();
        printMap();
        while (!endGame) {
            humanTurn();
            printMap();
            endGame = checkEndGame();
            if (!endGame) {
                aiTurn();
                printMap();
                endGame = checkEndGame();
            }
        }
        System.out.println("Игра закончена");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты в формате \"X Y\": ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && map[x][y] == DOT_EMPTY;
    }

    private static void aiTurn() {
        Integer[] selectMove;
        ArrayList<Integer[]> moves = getMovesWithoutLoss();
        int position = moves.size() > 1 ? rand.nextInt(moves.size()) : 0;
        selectMove = moves.get(position);
        map[selectMove[0]][selectMove[1]] = DOT_O;
        System.out.printf("Ход компьютера: %d %d %n", selectMove[0] + 1, selectMove[1] + 1);
    }

    // получить ходы, не ведущие к явному проигрышу
    private static ArrayList<Integer[]> getMovesWithoutLoss() {
        ArrayList<Integer[]> wins = new ArrayList<>();
        ArrayList<Integer[]> losses = new ArrayList<>();
        ArrayList<Integer[]> others = new ArrayList<>();
        // пройти по пустым полям
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    Integer[] pair = new Integer[2];
                    pair[0] = i;
                    pair[1] = j;
                    // проверить потенциальную победу
                    if (isWinLossPositionAi(i, j, true)) {
                        // выигрышная позиция приоритетна
                        wins.add(pair);
                        break;
                    }
                    // проверить потенциальное поражение для блокировки хода человека
                    if (isWinLossPositionAi(i, j, false)) {
                        losses.add(pair);
                    }
                    others.add(pair);
                }
            }
            if (wins.size() > 0) {
                break;
            }
        }
        ArrayList<Integer[]> result;
        if (wins.size() > 0) {
            result = wins;
        } else if (losses.size() > 0) {
            result = losses;
        } else {
            result = others;
        }
        return result;
    }

    private static boolean isWinLossPositionAi(int x, int y, boolean checkWin) {
        map[x][y] = checkWin ? DOT_O : DOT_X;
        boolean[] lossWin = isWinLossPositionHuman(x, y);
        map[x][y] = DOT_EMPTY;
        return (lossWin[checkWin ? 1 : 0]);
    }

    // проверить позицию на выигрыш или проигрыш для игрока-человека
    private static boolean[] isWinLossPositionHuman(int x, int y) {
        boolean[] result = new boolean[]{false, false};
        // проверить по горизонтали
        result = checkHorizontal(x, y);
        if (!result[0] && !result[1]) {
            // проверить по вертикали
            result = checkVertical(x, y);
        }
        // проверить по диагоналям
        if (!result[0] && !result[1]) {
            result = checkDiagonalLeftToRight(x, y);
        }
        if (!result[0] && !result[1]) {
            result = checkDiagonalRightToLeft(x, y);
        }
        return result;
    }

    private static boolean[] checkHorizontal(int x, int y) {
        boolean[] result = new boolean[]{false, false};
        int start = y - Math.min(y, DOTS_FOR_WIN - 1);
        int end = y + Math.min(SIZE - 1 - y, DOTS_FOR_WIN - 1);
        // порциями по победному количеству проверить непрерывность равенства символов
        while (end - start >= DOTS_FOR_WIN - 1) {
            result[0] = true;
            result[1] = true;
            for (int i = start; i < start + DOTS_FOR_WIN; i++) {
                if (map[x][i] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    break;
                }
                result[0] = result[0] && map[x][i] == DOT_X;
                result[1] = result[1] && map[x][i] == DOT_O;
            }
            if (result[0] || result[1]) {
                break;
            }
            start++;
        }
        return result;
    }

    private static boolean[] checkVertical(int x, int y) {
        boolean[] result = new boolean[]{false, false};
        int start = x - Math.min(x, DOTS_FOR_WIN - 1);
        int end = x + Math.min(SIZE - 1 - x, DOTS_FOR_WIN - 1);
        while (end - start >= DOTS_FOR_WIN - 1) {
            result[0] = true;
            result[1] = true;
            for (int i = start; i < start + DOTS_FOR_WIN; i++) {
                if (map[i][y] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    break;
                }
                result[0] = result[0] && map[i][y] == DOT_X;
                result[1] = result[1] && map[i][y] == DOT_O;
            }
            if (result[0] || result[1]) {
                break;
            }
            start++;
        }
        return result;
    }

    // проверить диагональ на увеличение y слева направо (x++, y++)
    private static boolean[] checkDiagonalLeftToRight(int x, int y) {
        boolean[] result = new boolean[]{false, false};
        int minStart = Math.min(x, y);
        int minEnd = Math.min(SIZE - 1 - x, SIZE - 1 - y);
        // начальная позиция для проверки слева сверху по диагонали без выхода за пределы
        int startX = x - Math.min(minStart, DOTS_FOR_WIN - 1);
        int startY = y - Math.min(minStart, DOTS_FOR_WIN - 1);
        int endX = x + Math.min(minEnd, DOTS_FOR_WIN - 1);
        int endY = y + Math.min(minEnd, DOTS_FOR_WIN - 1);
        while (endX - startX >= DOTS_FOR_WIN - 1 && endY - startY >= DOTS_FOR_WIN - 1) {
            result[0] = true;
            result[1] = true;
            for (int i = startX, j = startY;
                 i <= startX + DOTS_FOR_WIN - 1 && j <= startY + DOTS_FOR_WIN - 1;
                 i++, j++) {
                if (map[i][j] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    break;
                }
                result[0] = result[0] && map[i][j] == DOT_X;
                result[1] = result[1] && map[i][j] == DOT_O;
            }
            if (result[0] || result[1]) {
                break;
            }
            startX++;
            startY++;
        }
        return result;
    }

    // проверить диагональ на уменьшение y справо налево (x++, y--)
    private static boolean[] checkDiagonalRightToLeft(int x, int y) {
        boolean[] result = new boolean[]{false, false};
        int minStart = Math.min(x, SIZE - 1 - y);
        int minEnd = Math.min(SIZE - 1 - x, y);
        // начальная позиция для проверки справа сверху по диагонали без выхода за пределы
        int startX = x - Math.min(minStart, DOTS_FOR_WIN - 1);
        int startY = y + Math.min(minStart, DOTS_FOR_WIN - 1);
        int endX = x + Math.min(minEnd, DOTS_FOR_WIN - 1);
        int endY = y - Math.min(minEnd, DOTS_FOR_WIN - 1);
        endX = Math.min(endX, SIZE - 1);
        endY = Math.max(endY, 0);
        while (endX - startX >= DOTS_FOR_WIN - 1 && startY - endY >= DOTS_FOR_WIN - 1) {
            result[0] = true;
            result[1] = true;
            for (int i = startX, j = startY;
                 i <= startX + DOTS_FOR_WIN - 1 && j >= startY - DOTS_FOR_WIN + 1;
                 i++, j--) {
                if (map[i][j] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    break;
                }
                result[0] = result[0] && map[i][j] == DOT_X;
                result[1] = result[1] && map[i][j] == DOT_O;
            }
            if (result[0] || result[1]) {
                break;
            }
            startX++;
            startY--;
        }
        return result;
    }

    private static boolean checkEndGame() {
        boolean result = false, availableEmpty = false;
        boolean[] winLoss;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    availableEmpty = true;
                } else {
                    winLoss = isWinLossPositionHuman(i, j);
                    if (winLoss[0]) {
                        System.out.println("Победил человек");
                    } else if (winLoss[1]) {
                        System.out.println("Победил Искуственный Интеллект");
                    }
                    result = winLoss[0] || winLoss[1];
                }
                if (result) {
                    break;
                }
            }
            if (result) {
                break;
            }
        }
        if (!result && !availableEmpty) {
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }
}