import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        String pt = "gayathriredy";
        int[][] key = new int[][]{
                {6, 24, 1, 13},
                {13, 16, 10, 23},
                {20, 17, 15, 7},
                {2, 5, 21, 18}
        };
//        String ct = encrypt("gfg",new int[][]{{6,24,1},{13,16,10},{20,17,15}});
//        String temp = decrypt(ct,new int[][]{{6,24,1},{13,16,10},{20,17,15}});
        String ct = encrypt(pt,key);
        System.out.println("Encryption: "+ct);
//        System.out.println(temp);
        System.out.println("Decryption: "+decrypt(ct,key));
    }
    static String encrypt(String pt,int[][] key){
        int l = key.length;
        StringBuilder ct = new StringBuilder();
        for(int i=0;i<pt.length();i=i+l){
            int[] ptArr = new int[l];
            for (int j = i; j < (i+l); j++) {
                ptArr[j-i] = pt.charAt(j)-'a';
            }

            int[] ctArr = new int[l];
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    ctArr[j] += (ptArr[k]*key[k][j]);
                }
            }

            for (int j = 0; j < l; j++) {
                int temp = ctArr[j]%26+'a';
                ct.append((char)temp);
            }
        }
        return ct.toString();
    }
    static String decrypt(String ct,int[][] key){
        int l = key.length;
        StringBuilder pt = new StringBuilder();
        for (int i = 0; i < ct.length(); i=i+l) {
            int[] ctArr = new int[l];
            for (int j = i; j <(i+l); j++) {
                ctArr[j-i] = ct.charAt(j)-'a';
            }
            int[] ptArr = new int[l];
            int[][] cofact = getCofactorMatrix(key);

            int det = 0;
            for (int j = 0; j < l; j++) {
                det += key[j][0]*cofact[j][0];
            }
            // do mod 26 to the det
            det = det%26;
            if(det<0) det = det+26;

            // finding adjoint matrix
            transpose(cofact);

            // do mod 26 for the transpose of cofactor matrix (adjoint matrix)
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    cofact[j][k] = cofact[j][k]%26;
                    if(cofact[j][k]<0) cofact[j][k] += 26;
                }
            }

            // do mod 26 to the 1/det also
            det = mulInverse(det,26);

            // multiply det with adjoint matrix to get inverse of a matrix
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    cofact[j][k] = (cofact[j][k]*det)%26;
                }
            }

            // finding plain text matrix
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    ptArr[j] += (ctArr[k]*cofact[k][j]);
                }
            }

            for (int j = 0; j < l; j++) {
                int temp = ptArr[j]%26+'a';
                pt.append((char)temp);
            }

        }
        return pt.toString();
    }
    static int mulInverse(int a,int n){
        for (int i = 1; i < n; i++) {
            if((a*i)%n==1) return i;
        }
        return -1;
    }
    static void transpose(int[][] mat){
        for (int i = 0; i < mat.length; i++) {
            for (int j = i+1; j < mat.length; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }
    public static int[][] getCofactorMatrix(int[][] mat) {
        int n = mat.length;
        int[][] cofactor = new int[n][n];
        if(n==2){
            cofactor[0][0] = mat[1][1];
            cofactor[1][1] = mat[0][0];
            cofactor[0][1] = -mat[1][0];
            cofactor[1][0] = -mat[0][1];
            return cofactor;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofactor[i][j] = getCofactor(mat, i, j) * (int) Math.pow(-1, i + j);
            }
        }

        return cofactor;
    }

    public static int getCofactor(int[][] mat, int row, int col) {
        int n = mat.length;
        int[][] subMatrix = new int[n - 1][n - 1];
        int subRow = 0, subCol;

        for (int i = 0; i < n; i++) {
            if (i == row) {
                continue;
            }
            subCol = 0;
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    continue;
                }
                subMatrix[subRow][subCol++] = mat[i][j];
            }
            subRow++;
        }

        return determinant(subMatrix);
    }

    public static int determinant(int[][] matrix) {
        int n = matrix.length;

        if (n == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }

        int det = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            int[][] subMatrix = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                for (int k = 0, l = 0; k < n; k++) {
                    if (k == i) {
                        continue;
                    }
                    subMatrix[j - 1][l++] = matrix[j][k];
                }
            }
            det += sign * matrix[0][i] * determinant(subMatrix);
            sign = -sign;
        }

        return det;
    }

}