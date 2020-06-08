package state;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.UnsupportedEncodingException;

/**
 * @author mawt
 * @description
 * @date 2020/1/6
 */
public class wait {

    //对象锁
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        //[\u89c6\u9177IM],\u60a8\u7684\u9a8c\u8bc1\u7801\u4e3a
        System.out.println(decodeUnicode("\\u89c6\\u9177IM\\u60a8\\u7684\\u9a8c\\u8bc1\\u7801\\u4e3a"));
        //    System.out.println(new String("[\\u89c6\\u9177IM],\\u60a8\\u7684\\u9a8c\\u8bc1\\u7801\\u4e3a".getBytes("ISO-8859-1"), "utf-8"));

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println(1);
                    //当前线程调用对象的wait()方法，当前线程释放对象锁，进入等待队列WAITING
                    lock.wait();
                    System.out.println(2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        Thread.sleep(2000);

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                //WAITING
                System.out.println(thread.getState().name());
                lock.notifyAll();

            }
        });
        thread1.start();
    }

    //Unicode转中文
    public static String decodeUnicode(final String unicode) {
        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 0; i < hex.length; i++) {

            try {
                // 汉字范围 \u4e00-\u9fa5 (中文)
                if (hex[i].length() >= 4) {//取前四个，判断是否是汉字
                    String chinese = hex[i].substring(0, 4);
                    try {
                        int chr = Integer.parseInt(chinese, 16);
                        boolean isChinese = isChinese((char) chr);
                        //转化成功，判断是否在  汉字范围内
                        if (isChinese) {//在汉字范围内
                            // 追加成string
                            string.append((char) chr);
                            //并且追加  后面的字符
                            String behindString = hex[i].substring(4);
                            string.append(behindString);
                        } else {
                            string.append(hex[i]);
                        }
                    } catch (NumberFormatException e1) {
                        string.append(hex[i]);
                    }

                } else {
                    string.append(hex[i]);
                }
            } catch (NumberFormatException e) {
                string.append(hex[i]);
            }
        }

        return string.toString();
    }

    /**
     * 判断是否为中文字符
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

}
