/**
 * Created by hz on 2017/6/7.
 */
public class BeautifulCode {
    private static boolean isPowerOfTwo(int val) {
        return (val & -val) == val;
    }
}
