package maxParlinStr1032;

import java.util.Scanner;

/**
 * Created by hz on 2017/6/7.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        //char [][] str = new char[num][];
        char [] str ;
        for (int i = 0; i < num; i++) {
          str = in.next().toCharArray();
            System.out.println(getMaxParliStrCntManacher(str));
        }

    }

    /*private static int getMaxParliStrCntOddOrEven(char[] chars) {
        if (chars.length <= 1) return 1;
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            //search for centered by i - 1, i
            int low = i - 1, high = i;
            maxLen = getMaxLen(chars,low, high, maxLen);

            low = i - 1; high = i + 1;
            maxLen = getMaxLen(chars,low, high, maxLen);
        }
        return maxLen;
    }

    private static int getMaxLen(char[] chars, int low, int high, int maxLen) {
        while (low >= 0 && high < chars.length && chars[low] == chars[high]) {
            low--;
            high++;
        }
        if (high - low - 1 > maxLen) {
            maxLen = high - low - 1;
        }
        return maxLen;
    }*/

    private static int getMaxParliStrCntManacher(char[] originChars) {
        char[] chars = preProcess(originChars);
        int [] p = new int[chars.length];//p array in manacher's algorithm
        int id = 0, mx = 0;//id is the cur max mid index, mx is the max right(first different)
        int maxP = 0;
        for(int i = 1; i < chars.length - 1/*the last real char in origin*/; i++) {
            //get the min parli str num of mid
            p[i] = mx > i?Math.min(p[2*id-i], mx - i):1;
            while(chars[i + p[i]] == chars[i - p[i]]) p[i]++;
            if (i + p[i] > mx) {
                mx = i + p[i];
                id = i;
            }
            if (p[i] > maxP) {//search p for maxLen
                maxP = p[i];
            }
        }

      return maxP - 1;
    }

    private static char[] preProcess(char[] originChars) {
        StringBuffer sb = new StringBuffer();
        sb.append("$"); sb.append("#");
        for (char ch : originChars) {
            sb.append(ch);sb.append("#");
        }
        sb.append("^");
        return  sb.toString().toCharArray();
    }

  /*  private static int getMaxParliStrCntDp(char[] chars) {
        if(chars.length <= 1) return 1;
        boolean [][] dp = new boolean[chars.length][chars.length];
        //int posLeft = 0, posRight = 0;//java是每个变量单独初始化
        int maxParliStrLength = 0;
        dp[0][0] = true;
        for (int i = 1; i < chars.length; i++) {
            dp[i][i] = true;
            dp[i][i-1] = true;//for k==2 to use;
        }

        for (int k = 2; k <= chars.length; k++) {//k是子串长度
            for (int i = 0; i <= chars.length - k; i++) {//i是长度为k的子串的起始位置
                if (chars[i] == chars[i + k - 1]  && dp[i + 1][i+k-2]) {
                    dp[i][i + k - 1] = true;
          *//*          if (posRight - posLeft + 1 < k) {
                        posLeft = i;
                        posRight = i + k - 1;
                    }*//*
                      if (maxParliStrLength < k) maxParliStrLength = k;
                }
            }
        }
        return maxParliStrLength;
    }*/
}
