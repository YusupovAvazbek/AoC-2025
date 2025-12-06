import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day5 {

    public static void readFileData(String filePath, List<long[]> ranges, List<Long> ingredientIds) {
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            boolean isRangeSection = true;
            for (String line : lines) {
                line = line.trim();

                if (line.isEmpty()) {
                    isRangeSection = false;
                    continue;
                }
                if (isRangeSection) {
                    String[] parts = line.split("-");
                    long start = Long.parseLong(parts[0]);
                    long end = Long.parseLong(parts[1]);
                    ranges.add(new long[]{start, end});
                } else {
                    long id = Long.parseLong(line);
                    ingredientIds.add(id);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }

    public Long partOne() {
        List<long[]> ranges = new ArrayList<>();
        List<Long> numbers = new ArrayList<>();
        readFileData("D:\\projects\\AoC2025\\inputs\\day5_1.txt", ranges, numbers);
        long count = 0;
        for (long id : numbers) {
            if (isFresh(id, ranges)) {
                count++;
            }
        }
        return count;
    }

    public Long partTwo() {
        List<long[]> ranges = new ArrayList<>();
        List<Long> numbers = new ArrayList<>();
        readFileData("D:\\projects\\AoC2025\\inputs\\day5_1.txt", ranges, numbers);

        List<long[]> mergedRanges = mergeRanges(ranges);

        long totalFreshIds = 0;
        for (long[] range : mergedRanges) {
            totalFreshIds += (range[1] - range[0] + 1);
        }

        return totalFreshIds;
    }

    public static List<long[]> mergeRanges(List<long[]> ranges) {
        if (ranges.isEmpty()) return ranges;

        List<long[]> sortedRanges = new ArrayList<>(ranges);
        Collections.sort(sortedRanges, (a, b) -> Long.compare(a[0], b[0]));

        List<long[]> merged = new ArrayList<>();
        merged.add(new long[]{sortedRanges.get(0)[0], sortedRanges.get(0)[1]});

        for (int i = 1; i < sortedRanges.size(); i++) {
            long[] current = sortedRanges.get(i);
            long[] last = merged.get(merged.size() - 1);

            if (current[0] <= last[1] + 1) {
                last[1] = Math.max(last[1], current[1]);
            } else {
                merged.add(new long[]{current[0], current[1]});
            }
        }

        return merged;
    }

    public static boolean isFresh(long id, List<long[]> ranges) {
        for (long[] range : ranges) {
            if (id >= range[0] && id <= range[1]) {
                return true;
            }
        }
        return false;
    }
}