package kmp1015;

import java.util.Scanner;

/**
 * Created by hz on 2017/6/6.
 */
public class Main {
    private static final int MAX_ORI_CNT = 1000000;
    private static final int MAX_PAT_CNT = 10000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        int [] res = new int[num];
        for(int i = 0; i < num; i++) {
            char[] pattern = in.next().toCharArray();
            char[] string = in.next().toCharArray();
            res[i] = new Pair(pattern, string).getMatchNum();
        }
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
    private static final class Pair {
        private char [] pattern;
        private char [] string;
        private int[] next;

        public Pair(char[] pattern, char[] string) {
            this.pattern = pattern;
            this.string = string;
            next = getNext();
        }

        public int getMatchNum() {
            int res = 0;
            int k = -1;
            for(int i = 0; i < string.length; i++) {
                while (k > -1 && string[i] != pattern[k + 1]) {
                    k = next[k];
                }
                if (string[i] == pattern[k + 1]) {
                    k = k + 1;
                    if (k == pattern.length - 1)  {res ++;k = next[k];/*if matched, for the next match*/}
                }

            }
            return res;
        }
        private int[] getNext() {
            int[] next = new int[pattern.length];
            next[0] = -1;
            int k = -1;//record the last next[]
            for(int i = 1; i < next.length; i++) {
                while (k > -1 && pattern[i] != pattern[k + 1]) {
                    k = next[k];
                }
                    if (pattern[i] == pattern[k + 1]) k = k + 1;

                next[i] = k;
            }
            return next;
        }


    }

}
