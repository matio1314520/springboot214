package introspect;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * @author mawt
 * @description
 * @date 2020/6/15
 */
public class TestIntrospect {

    private static class User {

        private Long id;

        private String username;

        private int age;

        public String lovers;

        private static String sex;

        public static String address;

        public String getUsername() {
            return username;
        }
    }

    public static void main(String[] args) {
        try {
            Class clazz = Class.forName("introspect.TestIntrospect$User");
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            PropertyDescriptor[] pros = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor pro : pros) {
                System.out.println("name = " + pro.getName());

                //获取set方法
                Method writeMethod = pro.getWriteMethod();

                //获取get方法
                Method readMethod = pro.getReadMethod();

            }

            MethodDescriptor[] mdps = beanInfo.getMethodDescriptors();
            for (MethodDescriptor md : mdps) {
                Method method = md.getMethod();
                ParameterDescriptor[] pds = md.getParameterDescriptors();



            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

}
