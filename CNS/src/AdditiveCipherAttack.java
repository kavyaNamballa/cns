import java.util.*;

public class AdditiveCipherAttack {

    // Function to perform a letter frequency attack on an additive cipher
    static List<String> decryptAdditiveCipher(String ciphertext, int topPlaintexts) {
        // Initialize a list to store the possible plaintexts
        List<String> possiblePlaintexts = new ArrayList<>();

        // Iterate over possible additive keys (0 to 25)
        for (int key = 0; key < 26; key++) {
            // Decrypt the ciphertext using the current key
            StringBuilder plaintext = new StringBuilder();
            for (char ch : ciphertext.toCharArray()) {
                if (Character.isLetter(ch)) {
                    // Decrypt each letter by subtracting the key
                    char decryptedChar = (char) (((ch - 'A' - key + 26) % 26) + 'A');
                    plaintext.append(decryptedChar);
                } else {
                    // Append non-letter characters as they are
                    plaintext.append(ch);
                }
            }
            // Add the decrypted plaintext to the list
            possiblePlaintexts.add(plaintext.toString());
        }

        // Sort the possible plaintexts by English letter frequency
        possiblePlaintexts.sort((s1, s2) -> {
            // Calculate frequencies of letters in each plaintext
            int[] freq1 = calculateLetterFrequencies(s1);
            int[] freq2 = calculateLetterFrequencies(s2);
            // Compare frequencies to determine likelihood
            return compareFrequencies(freq2, freq1); // Sort in descending order
        });

        // Return the top 'topPlaintexts' plaintexts
        return possiblePlaintexts.subList(0, Math.min(topPlaintexts, possiblePlaintexts.size()));
    }

    // Function to calculate letter frequencies in a string
    static int[] calculateLetterFrequencies(String text) {
        int[] frequencies = new int[26];
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                frequencies[Character.toUpperCase(ch) - 'A']++;
            }
        }
        return frequencies;
    }

    // Function to compare frequencies of two texts
    static int compareFrequencies(int[] freq1, int[] freq2) {
        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return freq1[i] - freq2[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        // Example usage
        String ciphertext = "WKLVLVDOOHJLDQW";
        int topPlaintexts = 5;

        // Perform additive cipher attack
        List<String> possiblePlaintexts = decryptAdditiveCipher(ciphertext, topPlaintexts);

        // Print the top possible plaintexts
        System.out.println("Top " + topPlaintexts + " possible plaintexts:");
        for (String plaintext : possiblePlaintexts) {
            System.out.println(plaintext);
        }
    }
}
