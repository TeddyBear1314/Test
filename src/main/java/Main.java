import java.util.*;

/**
 * Created by hz on 2017/6/20.
 */
public class Main {
 /*   private static final int N =100005;
    long [] bit = new long[N << 2];
    int [] a = new int[N];
    int [] m = new int[N];
    int [] r = new int[N];
    int [] bit2 = new int[N << 2];
    ArrayList<Integer> [] Lt = new ArrayList[N];
    ArrayList<Integer> [] Rt = new ArrayList[N];
    ArrayList<Integer> [] op = new ArrayList[N];
    ArrayList<Integer> b = new ArrayList<Integer>();
    Map<Integer, Integer> s = new HashMap<Integer, Integer>();*/
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < n; i++) {
            int s, m, r;
            s = in.nextInt();
            m = in.nextInt();
            r = in.nextInt();
            st.init(i, s, m, r);
        }
        int m = in.nextInt();
        long sum = 0;
        for (int i = 0; i < m; i++) {
            int t, l, r;
            t = in.nextInt();
            l = in.nextInt();
            r = in.nextInt();
            l--;r--;
            st.recover(l, r, t);
            sum += st.query(l, r);
            st.clean(l, r, t);
        }
        System.out.println(sum);

    }

    private static class SegmentTree {
        int size;
        Node[] tree;

        public SegmentTree(int size) {
            this.size = size;
            tree = new Node[size << 2];
           for (int i = 0; i <(size << 2);i++) {
               tree[i] =new Node();
           }
        }
        public long query(int start, int end) {return query(start, end, 1, 0, size - 1);}
        public void recover(int start, int end, int time) {recover(start, end, time, 1, 0, size-1);}
        public void clean(int start, int end, int time) {clean(start, end, time, 1, 0, size - 1);}
        public void init(int index, int s, int m, int r) {init(index, s, m, r, 1, 0, size - 1);}

        int lson(int x) {return x << 1;}
        int rson(int x) {return (x << 1)|1;}
        long query(int start, int end, int root, int left, int right) {
            if (start == left && end == right) {
                return tree[root].sum;
            }
            int mid = left + (right - left)/2;
            if (end <= mid)
                return query(start, end, lson(root), left, mid);
            if (start > mid)
                return query(start, end, rson(root), mid + 1, right);
            return query(start, mid, lson(root), left, mid) + query(mid + 1, end, rson(root), mid + 1, right);
        }
        void recover(int start, int end, int time, int root, int left, int right) {
          if (start == left && end == right && left == right) {
              long tmp = tree[root].sum + (time - tree[root].t) * tree[root].r;
              tree[root].sum = Math.min(tmp, (long)tree[root].m);
          } else {
              int mid = left + (right - left)/2;
              if (end <= mid)
                  recover(start, end, time, lson(root), left, mid);
              else if (start > mid)
                  recover(start, end, time, rson(root), mid + 1, right);
              else {
                  recover(start, mid, time, lson(root), left, mid);
                  recover(mid + 1, end, time, rson(root), mid + 1, right);
              }
              tree[root].sum = tree[lson(root)].sum + tree[rson(root)].sum;
          }
        }
        void clean(int start, int end, int time, int root, int left, int right) {
           if (start == left && end == right && left == right) {
               tree[root].sum = 0;
               tree[root].t = time;
           } else {
               int mid = left + (right - left)/2;
               if (end <= mid)
                   clean(start, end, time, lson(root), left, mid);
               else if (start > mid)
                   clean(start, end, time, rson(root), mid + 1, right);
               else {
                   clean(start, mid, time, lson(root), left, mid);
                   clean(mid + 1, end, time, rson(root), mid + 1, right);
               }
               tree[root].sum = tree[lson(root)].sum + tree[rson(root)].sum;
           }
        }
        void init(int index, int s, int m, int r, int root, int left, int right) {
         if (left == right && left == index) {
             tree[root].sum = s;
             tree[root].m = m;
             tree[root].r = r;
         } else {
             int mid = left + (right - left)/2;
             if (index <= mid)
                 init(index, s, m, r, lson(root), left, mid);
             else
                 init(index, s, m, r, rson(root), mid + 1, right);
             tree[root].sum = tree[lson(root)].sum + tree[rson(root)].sum;
         }
        }

    }

    private static class Node {
        long sum;
        int m, r, t;
    }
}
