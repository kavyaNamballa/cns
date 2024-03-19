import java.util.Arrays;

public class FreqAttackOnMonoAlph {
    static void attack(String ct, int n)
    {
        String []plaintext = new String[26];

        int freq[] = new int[26];
        int freqSorted[] = new int[26];
        int visited[] = new int[26];

        for (int i = 0; i < n; i++) {
            if (ct.charAt(i) != ' ') {
                freq[ct.charAt(i) - 'A']++;
            }
        }

        for (int i = 0; i < 26; i++) {
            freqSorted[i] = freq[i];
        }
        String T = "ETAOINSHRDLCUMWFGYPBVKJXQZ";

        Arrays.sort(freqSorted);
        reverse(freqSorted);

        for (int i = 0; i < 26; i++) {
            int ch = -1;
            for (int j = 0; j < 26; j++) {
                if (freqSorted[i] == freq[j] && visited[j] == 0) {
                    visited[j] = 1;
                    ch = j;
                    break;
                }
            }
            if (ch == -1)
                break;
            int x = T.charAt(i) - 'A';
            // possible shift
            x = x - ch;
            String curr = encrypt(ct,-x);
            plaintext[i] = curr;
        }
        for (int i = 0; i < 5; i++) {
            System.out.print(plaintext[i] +"\n");
        }
    }
    static void reverse(int a[]) {
        int i, n = a.length, t;
        for (i = 0; i < n / 2; i++) {
            t = a[i];
            a[i] = a[n - i - 1];
            a[n - i - 1] = t;
        }
    }

    public static void main(String[] args)
    {
//        String S = "EGGCURRYISTOOGOOOD";
        String S = "BTJNQMFNFTTBHF";
        int N = S.length();
        attack(S, N);

    }
    static String encrypt(String pt,int k){
        StringBuilder ct = new StringBuilder();
        for(char ch:pt.toCharArray()){
            ct.append((char)((ch-'A'+k+26)%26+'A'));
        }
        return ct.toString();
    }
}

