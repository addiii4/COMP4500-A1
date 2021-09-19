package assignment1;

import java.lang.reflect.Array;

public class Test {
    public static void main(String[] args) {
        String studentNumber = "984633369452";
        String studentNumber0 = "984653812752";

        char[] sniChars = new char[studentNumber.length()];
        sniChars = studentNumber.toCharArray();
        int[] d = new int[studentNumber.length()];

        for (int i = 0; i < studentNumber.length(); i++) {
            d[i] = Integer.parseInt(String.valueOf(sniChars[i]));
        }

        for(int i=2; i<12; i++) {
            if(d[i] == d[i-1]) {
                d[i] = (d[i] + 3)%10;
            }
        }

        for (int i: d) {
            System.out.print(i);
        }

        String sni = "984636369452";



    }
}
