package aqs;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mawt
 * @description
 * @date 2020/6/8
 */
public class TestReentrantReadWriteLock {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

    }

    public void readMethod() {
        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                readLock.lock();
                try {
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
                    Thread.sleep(3000);
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            });
            thread.start();
        }
    }

    public void writeMethod() {
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                writeLock.lock();
                try {
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
                    Thread.sleep(3000);
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            });
            thread.start();
        }
    }


}
