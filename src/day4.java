import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class day4 {

    private static final int[] DR = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static final int[] DC = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static int partOne() {
        char[][] grid = readFileData("D:\\projects\\AoC2025\\inputs\\day4_1.txt");
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '@' && countRolls(grid, i, j) < 4) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int partTwo() {
        char[][] grid = readFileData("D:\\projects\\AoC2025\\inputs\\day4_1.txt");
        int totalRemoved = 0;

        boolean hasRemoved = true;

        while (hasRemoved) {
            hasRemoved = false;
            List<int[]> toRemove = new ArrayList<>();

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '@' && countRolls(grid, i, j) < 4) {
                        toRemove.add(new int[]{i, j});
                    }
                }
            }

            if (!toRemove.isEmpty()) {
                hasRemoved = true;
                for (int[] pos : toRemove) {
                    grid[pos[0]][pos[1]] = '.';
                }
                totalRemoved += toRemove.size();
            }
        }

        return totalRemoved;
    }

    private static int countRolls(char[][] grid, int row, int col) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        for (int d = 0; d < 8; d++) {
            int nr = row + DR[d];
            int nc = col + DC[d];

            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == '@') {
                count++;
                if (count >= 4) return count;
            }
        }

        return count;
    }

    private static char[][] readFileData(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            char[][] grid = new char[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                grid[i] = lines.get(i).toCharArray();
            }
            return grid;
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}