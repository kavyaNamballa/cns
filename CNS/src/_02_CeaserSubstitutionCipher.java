
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class _02_CeaserSubstitutionCipher {

    public static String[] plaintextIntoCipher(
            String text,
            Map<Character, Integer> mp
    ) {
        String res[] = new String[text.length()];
        int arr[] = { 101, 116, 97, 111, 105 };
        char arr1[] = { 'e', 't', 'a', 'o', 'i' };
        int maxValue = Collections.max(mp.values());
        int Char = 0;
        for (char c : mp.keySet()) {
            if (maxValue == mp.get(c)) {
                Char = c;
                break;
            }
        }
        System.out.println((char)Char);
        for (int k = 0; k < 5; k++) {
            int move = Math.abs(arr[k] - Char); //E -> 69
            StringBuffer sb = new StringBuffer("");
            for (char ch : text.toCharArray()) {
                if (ch != arr1[k]) {
                    int temp = ch;
                    int temp1 = (((temp + move) % 97) % 26) + 97;
                    sb.append((char) temp1);
                } else sb.append(arr1[k]);
            }
            res[k] = sb.toString();
        }

        return res;
    }

    public static Map<Character, Integer> frequencyFind(String ciphertext) {
        Map<Character, Integer> mp = new HashMap<>();
        for (char i : ciphertext.toCharArray()) mp.put(
                i,
                mp.getOrDefault(i, 0) + 1
        );
        return mp;
    }

    public static void main(String[] args) {
        Map<Character, Integer> mp;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the cipherText");
        String plaintext = "btjnqmfnfttbhf";
        mp = frequencyFind(plaintext.toLowerCase());
        for (Map.Entry<Character, Integer> e : mp.entrySet())
            System.out.println("Key: " + e.getKey() + " Value: " + e.getValue());
        String res[] = new String[5];
        res = plaintextIntoCipher(plaintext, mp);
        for (int i = 0; i < 5; i++) {
            System.out.println(res[i]);
        }
    }
}
