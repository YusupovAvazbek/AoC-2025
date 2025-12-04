import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class day3 {
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
    public Integer partOne(){
        Integer result = 0;
        List<String> banks = new ArrayList<>();
        readFileData("D:\\projects\\AoC2025\\inputs\\day3_1.txt", banks);
        for  (int i = 0; i < banks.size(); i++){
            int maxJolts = getMaxJoltage(banks.get(i));
            result += maxJolts;
        }
        return result;
    }
    public Long partTwo(){
        Long result = 0L;
        List<String> banks = new ArrayList<>();
        readFileData("D:\\projects\\AoC2025\\inputs\\day3_2.txt", banks);
        for  (int i = 0; i < banks.size(); i++){
            Long maxJolts = Long.valueOf(getMaxJoltagePartTwo(banks.get(i)));
            result += maxJolts;
        }
        return result;
    }
    public static int getMaxJoltage(String bank) {
        int maxJolts = 0;
        for (int i = 0; i < bank.length() - 1; i++) {

            String firstDigit = String.valueOf(bank.charAt(i));
            for  (int j = i + 1; j < bank.length(); j++) {
                String secondDigit = String.valueOf(bank.charAt(j));
                String jolts = firstDigit + secondDigit;
                maxJolts = Math.max(maxJolts, Integer.parseInt(jolts));
            }

        }

        return maxJolts;
    }
    public static String getMaxJoltagePartTwo(String bank) {
        int n = bank.length();
        int digitsNeeded = 12;

        if (n < digitsNeeded) {
            return bank;
        }

        StringBuilder result = new StringBuilder();
        int startIndex = 0;

        for (int i = 0; i < digitsNeeded; i++) {
            char maxDigit = '0';
            int maxIndex = startIndex;

            int remainingDigits = digitsNeeded - i - 1;
            int searchLimit = n - remainingDigits;
            
            for (int j = startIndex; j < searchLimit; j++) {
                if (bank.charAt(j) > maxDigit) {
                    maxDigit = bank.charAt(j);
                    maxIndex = j;
                }
            }

            result.append(maxDigit);
            startIndex = maxIndex + 1;
        }

        return result.toString();
    }
}
