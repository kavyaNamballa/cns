import java.util.Scanner;

public class AzbyCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pl = sc.next();
        StringBuilder ct = new StringBuilder();
        for(char ch:pl.toCharArray()){
            if(ch>='A' && ch<='Z'){
                int id = ch-'A';
                ct.append((char)((25-id)+'A'));
            }else{
                int id = ch-'a';
                ct.append((char)((25-id)+'a'));
            }
        }
        System.out.println(ct);
    }
}
