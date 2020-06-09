package reorder;

/**
 * @author mawt
 * @description
 * @date 2020/6/9
 */
public class 重排序Test {

    static int x = 0, y = 0;
    static int a = 0, b = 0;

    static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            test();
            if (x == 0 && y == 0) {
                System.out.println("执行了" + count + "次");
                break;
            }
        }
    }

    private static void test() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            a = 1;
            x = b;
        });
        Thread thread2 = new Thread(() -> {
            b = 1;
            y = a;
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        count++;

        if (x == 0 && y == 0) {
            System.out.println("执行了" + count + "次");
        } else {
        //    test();
        //    System.out.println("(" + x + "," + y + ")");
        //    Thread.sleep(500);
        }

    }

}
