import java.util.ArrayList;

/**
 * Created by hz on 2017/6/7.
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i <50; i ++) {
            System.out.println(i + " " + (i & -i));
        }
    }
}