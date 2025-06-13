package analyze;

public class IntegerToBinary {
    public static void main(String[] args) {
        System.out.println(convert(8));
        System.out.println(convert(13));
        System.out.println(convert(-11));
        System.out.println(convert(0));
    }

    public static String convert (int i) {
        if (i < 0) {
            return "Algorithm doesn't work with negative numbers, sorry";
        }

        String result = "";
        do {
            if (i % 2 == 0) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            i >>= 1;
        } while (i != 0);
        return result;
    }
}
