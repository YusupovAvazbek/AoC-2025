import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class day1 {
    public static void readFileData(String filePath, List<String> str) {
        File inputFile = new File(filePath);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
            String line;
            while ((line = br.readLine()) != null) {
                str.add(line);

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

    public Integer partOne() {
        Integer position = 50;
        Integer cntZero = 0;
        List<String> str = new ArrayList<>();

        readFileData("D:\\projects\\AoC2025\\inputs\\day1_1.txt", str);

        for (int i = 0; i < str.size(); i++) {
            String direction = str.get(i).substring(0, 1);
            Integer step = Integer.parseInt(str.get(i).substring(1));
            System.out.println(direction + ":" + String.valueOf(step));

            if (direction.equals("L")) {
                position = (position - step) % 100;
                if (position < 0) {
                    position = position + 100;
                }

            } else if (direction.equals("R")) {
                position = (position + step) % 100;
            }
            if (position == 0) cntZero++;

        }

        return cntZero;
    }

    public Integer partTwo() {
        Integer position = 50;
        Integer cntZero = 0;
        List<String> str = new ArrayList<>();

        readFileData("D:\\projects\\AoC2025\\inputs\\day1_2.txt", str);

        for (int i = 0; i < str.size(); i++) {
            String direction = str.get(i).substring(0, 1);
            Integer step = Integer.parseInt(str.get(i).substring(1));
            System.out.println(direction + ":" + String.valueOf(step));


            if (direction.equals("L")) {
                for (int j = 1; j <= step; j++) {
                    position--;
                    if (position < 0) {
                        position = 99;
                    }
                    if (position == 0) {
                        cntZero++;
                    }
                }
            } else if (direction.equals("R")) {
                for (int j = 1; j <= step; j++) {
                    position++;
                    if (position >= 100) {
                        position = 0;
                    }
                    if (position == 0) {
                        cntZero++;
                    }
                }
            }
        }

        return cntZero;
    }
}
