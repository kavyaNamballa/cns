import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FreqAttackOnAdditiveCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        String ct = "WKLVLVDOOHJLDQW";
        String ct = encrypt("EGGCURRYISTOOGOOOD",2);
        System.out.println("possible plain texts: ");
        List<String> li = new ArrayList<>();
        for(int i=1;i<26;i++){
            StringBuilder pl = new StringBuilder();
            for(char ch:ct.toCharArray()){
                if(ch>='A' && ch<='Z'){
                    int id = ch-'A';
                    pl.append((char)((id-i+26)%26+'A'));
                }else{
                    int id = ch-'a';
                    pl.append((char)((id-i+26)%26+'a'));
                }
            }
            li.add(pl.toString());
        }
        li.sort((s1, s2) -> {
            int[] freq1 = calculateLetterFrequencies(s1);
            int[] freq2 = calculateLetterFrequencies(s2);
            return compareFrequencies(freq2, freq1);
        });

        //for top 10
        for(int i=0;i<20;i++){
            System.out.println(li.get(i));
        }

    }
    static String encrypt(String pt,int k){
        StringBuilder ct = new StringBuilder();
        for(char ch:pt.toCharArray()){
            ct.append((char)((ch-'A'+k)%26+'A'));
        }
        return ct.toString();
    }
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
}

