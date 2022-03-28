package lesson4;

import java.util.*;

public class lesson4 {
    private static final int SIZE = 5;
    private static final int DOTS_FOR_WIN = 4;
    private static final char DOT_EMPTY = '•';
    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();
    private static char[][] map;
    private static boolean existsEmpty;

    public static void main(String[] args) {
        boolean endGame = false;
        int countMoves = 1;// проверять только после соответствующего количества ходов
        initMap();
        printMap();
        while (!endGame) {
            humanTurn();
            printMap();
            if (countMoves >= DOTS_FOR_WIN) {
                endGame = checkEndGame();
            }
            if (!endGame) {
                aiTurn(countMoves);
                printMap();
                if (countMoves >= DOTS_FOR_WIN) {
                    endGame = checkEndGame();
                }
            }
            countMoves++;
        }
        System.out.println("Игра закончена");
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            Arrays.fill(map[i], DOT_EMPTY);
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
            System.out.print("Введите координаты в формате \"Строка Столбец\": ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE && map[x][y] == DOT_EMPTY;
    }

    private static void aiTurn(int countMoves) {
        Integer[] selectMove;
        List<Integer[]> moves = getMovesWithoutLoss(countMoves);
        int position = moves.size() > 1 ? rand.nextInt(moves.size()) : 0;
        selectMove = moves.get(position);
        map[selectMove[0]][selectMove[1]] = DOT_O;
        System.out.printf("Ход компьютера: Строка: %d Столбец: %d %n", selectMove[0] + 1, selectMove[1] + 1);
    }

    // получить ходы, не ведущие к явному проигрышу
    private static ArrayList<Integer[]> getMovesWithoutLoss(int countMoves) {
        ArrayList<Integer[]> wins = new ArrayList<>();
        ArrayList<Integer[]> priority = new ArrayList<>();
        ArrayList<Integer[]> losses = new ArrayList<>();
        ArrayList<Integer[]> others = new ArrayList<>();
        // пройти по пустым полям
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    Integer[] pair = new Integer[]{i, j};
                    // проверить потенциальную победу после количества ходов для победы
                    if (countMoves >= DOTS_FOR_WIN && checkPositionAi(i, j, DOT_O)) {
                        // выигрышная позиция приоритетна
                        wins.add(pair);
                        return wins;
                    }
                    // проверить потенциальное поражение для блокировки хода человека
                    // для следующего хода человека
                    if (countMoves + 1 >= DOTS_FOR_WIN && checkPositionAi(i, j, DOT_X)) {
                        losses.add(pair);
                    } else if (isPriorityPositionAi(i, j)) {
                        priority.add(pair);
                    } else {
                        others.add(pair);
                    }
                }
            }
        }
        if (!losses.isEmpty()) {
            return losses;
        }
        if (!priority.isEmpty()) {
            return priority;
        }
        return others;
    }

    // определить соседство хода игрока-человека
    private static boolean isPriorityPositionAi(int x, int y) {
        if (x > 0 && map[x - 1][y] == DOT_X) {
            return true;
        }
        if (x < SIZE - 1 && map[x + 1][y] == DOT_X) {
            return true;
        }
        if (y > 0 && map[x][y - 1] == DOT_X) {
            return true;
        }
        if (y < SIZE - 1 && map[x][y + 1] == DOT_X) {
            return true;
        }
        if (x > 0 && y > 0 && map[x - 1][y - 1] == DOT_X) {
            return true;
        }
        if (x < SIZE - 1 && y < SIZE - 1 && map[x + 1][y + 1] == DOT_X) {
            return true;
        }
        if (x < SIZE - 1 && y > 0 && map[x + 1][y - 1] == DOT_X) {
            return true;
        }
        if (x > 0 && y < SIZE - 1 && map[x - 1][y + 1] == DOT_X) {
            return true;
        }
        return false;
    }

    private static boolean checkPositionAi(int x, int y, char symbol) {
        map[x][y] = symbol;
        boolean[] lossWin = isWinLossPositionHuman(x, y);
        map[x][y] = DOT_EMPTY;
        return (lossWin[symbol == DOT_O ? 1 : 0]);
    }

    // проверить позицию на выигрыш или проигрыш для игрока-человека
    private static boolean[] isWinLossPositionHuman(int x, int y) {
        boolean[] result;
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
        boolean[] result = new boolean[2];
        int start = y - Math.min(y, DOTS_FOR_WIN - 1);
        int end = y + Math.min(SIZE - 1 - y, DOTS_FOR_WIN - 1);
        // порциями по победному количеству проверить непрерывность равенства символов
        while (end - start >= DOTS_FOR_WIN - 1) {
            result[0] = result[1] = true;
            for (int i = start; i < start + DOTS_FOR_WIN; i++) {
                if (map[x][i] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    existsEmpty = true;
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
        boolean[] result = new boolean[2];
        int start = x - Math.min(x, DOTS_FOR_WIN - 1);
        int end = x + Math.min(SIZE - 1 - x, DOTS_FOR_WIN - 1);
        while (end - start >= DOTS_FOR_WIN - 1) {
            result[0] = result[1] = true;
            for (int i = start; i < start + DOTS_FOR_WIN; i++) {
                if (map[i][y] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    existsEmpty = true;
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
        boolean[] result = new boolean[2];
        int minStart = Math.min(x, y);
        int minEnd = Math.min(SIZE - 1 - x, SIZE - 1 - y);
        // начальная позиция для проверки слева сверху по диагонали без выхода за пределы
        int startX = x - Math.min(minStart, DOTS_FOR_WIN - 1);
        int startY = y - Math.min(minStart, DOTS_FOR_WIN - 1);
        int endX = x + Math.min(minEnd, DOTS_FOR_WIN - 1);
        int endY = y + Math.min(minEnd, DOTS_FOR_WIN - 1);
        while (endX - startX >= DOTS_FOR_WIN - 1 && endY - startY >= DOTS_FOR_WIN - 1) {
            result[0] = result[1] = true;
            for (int i = startX, j = startY; i <= startX + DOTS_FOR_WIN - 1 && j <= startY + DOTS_FOR_WIN - 1; i++, j++) {
                if (map[i][j] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    existsEmpty = true;
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
            result[0] = result[1] = true;
            for (int i = startX, j = startY; i <= startX + DOTS_FOR_WIN - 1 && j >= startY - DOTS_FOR_WIN + 1; i++, j--) {
                if (map[i][j] == DOT_EMPTY) {
                    result[0] = false;
                    result[1] = false;
                    existsEmpty = true;
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
        boolean result = false;
        boolean[] winLoss;
        // сбросить показатель наличия свободных позиций
        existsEmpty = false;
        winLoss = checkEndHorizontal();
        if (!winLoss[0] && !winLoss[1]) {
            winLoss = checkEndVertical();
        }
        if (!winLoss[0] && !winLoss[1]) {
            winLoss = checkEndDiagonal();
        }
        if (winLoss[0]) {
            System.out.println("Победил человек");
            result = true;
        } else if (winLoss[1]) {
            System.out.println("Победил Искусственный Интеллект");
            result = true;
        } else if (!existsEmpty) {
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }

    private static boolean[] checkEndHorizontal() {
        boolean[] result = new boolean[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j += DOTS_FOR_WIN) {
                result = checkHorizontal(i, j);
                if (result[0] || result[1]) {
                    break;
                }
            }
            if (result[0] || result[1]) {
                break;
            }
        }
        return result;
    }

    private static boolean[] checkEndVertical() {
        boolean[] result = new boolean[2];
        for (int i = 0; i < SIZE; i += DOTS_FOR_WIN) {
            for (int j = 0; j < SIZE; j++) {
                result = checkVertical(i, j);
                if (result[0] || result[1]) {
                    break;
                }
            }
            if (result[0] || result[1]) {
                break;
            }
        }
        return result;
    }

    private static boolean[] checkEndDiagonal() {
        boolean[] result = new boolean[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result = checkDiagonalLeftToRight(i, j);
                if (result[0] || result[1]) {
                    break;
                }
                result = checkDiagonalRightToLeft(i, j);
                if (result[0] || result[1]) {
                    break;
                }
            }
            if (result[0] || result[1]) {
                break;
            }
        }
        return result;
    }
}