
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class TestSort {

    static class A {

        public A(final int a, final String s) {
            this.a = a;
            this.s = s;
        }

        int a;
        String s;
    }



    public static void main(String[] args) {
        final Random random = new Random();
        List<A> a = new ArrayList<A>();
        for (int i = 0; i < 1001001; ++i) {
            a.add(new A(random.nextInt(), "adfsdsf"));
        }
        final long l = System.currentTimeMillis();
        Collections.sort(a, new Comparator<A>() {
            @Override
            public int compare(final A o1, final A o2) {
                if (o1.a < o2.a)
                    return -1;
                else if (o1.a > o2.a)
                    return 1;
                else
                    return 0;
            }
        });
        System.out.println(System.currentTimeMillis() - l);
    }
}
