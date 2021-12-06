package com.example;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import org.apache.commons.beanutils.MethodUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * 反射测试
 */
public class ReflectTest {

    interface Person {

    }

    interface Person2 {

    }

    static class Tom implements Person, Person2 {
        @Override
        public String toString() {
            return "BBB";
        }
    }

    public void add(int p) {
        System.out.println(p);
    }

    private void add(Tom p) {
        System.out.println("private add x: " + p);
    }



    @Test
    public void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Tom tom = new Tom();
        Object[] args = new Object[] {
                tom };
        Class<?> target = this.getClass();
        String methodName = "add";
        Method method = getDeclaredMethod(target, methodName, args);
        System.out.println("method: " + method);
        System.out.println("xxx");
        System.out.println("result: " + method.invoke(this, args));
    }

    private Method getDeclaredMethod(Class<?> target, String methodName, Object[] args) throws NoSuchMethodException {
        Class<?>[] argsTypes = Arrays.stream(args)
                .map((Function<Object, Class<?>>) o -> o != null ? o.getClass() : null).toArray(Class<?>[]::new);
        Method method;
        try {
            method = target.getDeclaredMethod(methodName, argsTypes);
        } catch (NoSuchMethodException e) {
            method = MethodUtils.getMatchingAccessibleMethod(target, methodName, argsTypes);
        }
        if (Objects.isNull(method)) {
            throw new NoSuchMethodException(target.getName() + "#" + methodName);
        }
        method.setAccessible(true);
        return method;
    }

}
