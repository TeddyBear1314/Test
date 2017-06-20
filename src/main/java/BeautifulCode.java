/**
 * Created by hz on 2017/6/7.
 */
public class BeautifulCode {
    private static boolean isPowerOfTwo(int val) {
        return (val & -val) == val;
    }
    private static int rson(int x) {
        return (x << 1)|1;
    }
}
