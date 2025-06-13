package search;

public class FindClosestMatch {
    public static void main(String[] args) {
        String[] dictionary = {"cable", "banana", "gallon", "carrot", "goose", "crypt", "light",
                "yellow", "dolphin", "taboo", "coward", "glitch", "zebra", "question", "rainbow",
                "curriculum", "coffee", "unicorn", "length", "disco", "plywood", "whitewash",
                "silver", "ocean", "center", "standard", "crystal", "laser", "glass", "beginner"};
        System.out.println(findClosestMatch(dictionary, "grass"));
        System.out.println(findClosestMatch(dictionary, "plywodo"));
        System.out.println(findClosestMatch(dictionary, "gooe"));
        System.out.println(findClosestMatch(dictionary, "zeeeebra"));
    }

    static String findClosestMatch (String[] allWords, String input) {
        String result = null;
        int closestMatch = 0;
        int[][] table;

        for (String word : allWords) {
            table = new int[word.length() + 1][input.length() + 1];

            for (int i = 0; i < word.length() + 1; i++) {
                for (int j = 0; j < input.length() + 1; j++) {
                    if (i == 0 || j == 0) {
                        table[i][j] = 0;
                        continue;
                    }

                    if (word.charAt(i - 1) == input.charAt(j - 1)) {
                        table[i][j] = table[i - 1][j - 1] + 1;
                    } else {
                        table[i][j] = Math.max(table[i][j - 1], table[i - 1][j]);
                    }
                }
            }

            if (table[word.length()][input.length()] > closestMatch) {
                result = word;
                closestMatch = table[word.length()][input.length()];
            }
        }

        System.out.println(closestMatch);
        return result;
    }
}
