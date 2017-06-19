/**
 * Created by hz on 2017/6/16.
 */
public class Main {
    //f[i][j]代表的是开头为j的i位数不含62或4
    //f[0][0] = 1;//只要f[0][0]就可以统一初始化
    //for i = 1 ~ 7
    // for j = 0 ~ 9
    //  for k = 0 ~ 9
    //    if j != 4 && !(j == 6 && k ==2)
    //       f[i][j] += f[i-1][k]

    //digit[i]代表n从右到左第i位是什么，len是n有多少位
    //for i = len ~ 1//枚举哪一位<n的对应位
    //for j = 0 ~ digit[i] - 1//枚举这一位的数值,比digit[i]小
    // if j != 4 && !(j != 2 && digit[i + 1] != 6)
    // ans += f[i][j]//合法情况
    // if(digit[i] == 4 || (digit[i] == 2 && digit[i + 1] == 6)break;
    private static final int MAX_LEN = 21;
    private static final int DIGIT_NUM = 20;
    private static final int MAX_SUM_RANGE = 400;
    private Node [][][] dp = new Node[MAX_LEN][DIGIT_NUM][MAX_SUM_RANGE];
    //from low to high, digit of the number
    private int [] bits = new int[MAX_LEN];
    //base for every digit
    private long [] base = new long[MAX_LEN];
    public static void main(String[] args) {

    }

    Node dfs(int len, int digit, boolean begin_zero, boolean limit, int sum) {
        Node t = new Node();
        //check argument
        if (len <= 0 || len >= 20 || digit < 0 || digit >19 || sum < -200 || sum >= 200)
            return t;
        //如果当前的结果已被计算过了，直接返回，记忆化搜索
        if (!limit && dp[len][digit + (begin_zero? 0 : 10)][sum + 200].num != -1)
            return dp[len][digit + (begin_zero?0:10)][sum + 200];
        //对于len=1,直接讨论返回
        if (len == 1) {
            if (digit != sum) {
                return t;
            } else {
                t.num = 1;t.sum =sum;
                return t;
            }
        }
        //开始枚举下一位的数字
        int end = limit?bits[len - 2] : 9;
        int newsum = digit - sum ; 9

        

    }
    private static class Node {
        // the sum of 交错和
        private long sum;
        //满足条件的个数
        private long num;

        public Node() {
         /*   sum = 0;
            num = 0;*/
        }
    }
}
