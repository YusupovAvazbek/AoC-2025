import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class day2 {
    public static void readFileData(String filePath, List<String> str) {
        File inputFile = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (String part : parts) {
                    str.add(part.trim());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing file", e);
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                throw new RuntimeException("Error closing resources", e);
            }
        }
    }

    public Long partOne() {
        List<String> ranges = new ArrayList<>();
        Long sum = 0L;
        readFileData("D:\\projects\\AoC2025\\inputs\\day2_1.txt", ranges);

        for (int i = 0; i < ranges.size(); i++) {
            String[] parts = ranges.get(i).split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long id = start; id <= end; id++) {
                if (isInvalidID(id)) {
                    sum += id;
                }
            }
        }

        return sum;
    }

    public Long partTwo() {

        List<String> ranges = new ArrayList<>();
        Long sum = 0L;
        readFileData("D:\\projects\\AoC2025\\inputs\\day2_2.txt", ranges);

        for (int i = 0; i < ranges.size(); i++) {
            String[] parts = ranges.get(i).split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long id = start; id <= end; id++) {
                if (isInvalidIDPartTwo(id)) {
                    sum += id;
                }
            }
        }

        return sum;
    }

    public static boolean isInvalidID(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();

        if (len % 2 != 0) {
            return false;
        }

        int halfLen = len / 2;
        String firstHalf = idStr.substring(0, halfLen);
        String secondHalf = idStr.substring(halfLen);

        return firstHalf.equals(secondHalf);
    }

    public static boolean isInvalidIDPartTwo(long id) {
        String idStr = String.valueOf(id);
        int len = idStr.length();
        for (int patternLen = 1; patternLen <= len / 2; patternLen++) {
            if (len % patternLen == 0) {
                String pattern = idStr.substring(0, patternLen);
                boolean isRepeated = true;

                int repeatCount = len / patternLen;
                if (repeatCount >= 2) {
                    for (int i = 0; i < repeatCount; i++) {
                        int start = i * patternLen;
                        int end = start + patternLen;
                        if (!idStr.substring(start, end).equals(pattern)) {
                            isRepeated = false;
                            break;
                        }
                    }
                    if (isRepeated) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
