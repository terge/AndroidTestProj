package me.terge.androidtest.utils;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class ReflectionUtils {
    public static <T> T create(Class<T> cls, Object... args) {
        return create(cls, resolveArgsTypes(args), args);
    }

    public static <T> T create(Class<T> cls, Class<?>[] types, Object... args) {
        try {
            Constructor<T> ctr = cls.getDeclaredConstructor(types);
            ctr.setAccessible(true);
            return ctr.newInstance(args);
        } catch (Throwable e) {
            //Check.d(e);
            return null;
        }
    }

    // Getter

    public static Method getMethod(Class<?> targetClass, String methodName, Class<?>... types) {
        try {
            Method method = targetClass.getDeclaredMethod(methodName, types);
            method.setAccessible(true);
            return method;
        } catch (Throwable e) {
            //Check.d(e);
            return null;
        }
    }

    public static Field getField(Class<?> targetClass, String fieldName) {
        try {
            Field field = targetClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (Throwable e) {
            if (targetClass != null) {
                targetClass = targetClass.getSuperclass();
                if (targetClass != null && targetClass != Object.class) {
                    return getField(targetClass, fieldName);
                }
            }
            //Check.d(e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object target, String... fieldNames) {
        try {
            Field field;
            Class<?> cls = target.getClass();
            for (String fieldName : fieldNames) {
                field = getField(cls, fieldName);
                if (field != null) {
                    return (T) field.get(target);
                }
            }
        } catch (Throwable e) {
            //Check.d(e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object target, Field field) {
        try {
            if (field != null) {
                field.setAccessible(true);
                return (T) field.get(target);
            }
        } catch (Throwable e) {
            //Check.d(e);
        }
        return null;
    }

    public static void setFieldValue(Object target, Object... fieldNameValuePairs) {
        //Check.d(fieldNameValuePairs.length % 2 == 0);
        try {
            Field field;
            Class<?> cls = target.getClass();
            for (int i = 0; i < fieldNameValuePairs.length; i += 2) {
                field = getField(cls, (String) fieldNameValuePairs[i]);
                if (field != null) {
                    field.set(target, fieldNameValuePairs[i + 1]);
                }
            }
        } catch (Throwable e) {
            //Check.d(e);
        }
    }

    public static boolean setField(Object target, Field field, Object val) {
        boolean ret = false;
        try {
            field.setAccessible(true);
            field.set(target, val);
            ret = true;
        } catch (Throwable e) {
            //Check.d(e);
        }
        return ret;
    }

    public static Field getField(Object target, String... fieldNames) {
        try {
            Field field;
            Class<?> cls = target.getClass();
            for (String fieldName : fieldNames) {
                field = getField(cls, fieldName);
                if (field != null) {
                    return field;
                }
            }
        } catch (Throwable e) {
            //Check.d(e);
        }
        return null;
    }

    // Invoke

    public static Object invokeStatic(String className, String methodName, Object... args) {
        return invokeStatic(className, methodName, resolveArgsTypes(args), args);
    }

    public static Object invokeStatic(String className, String methodName, Class<?>[] argTypes, Object... args) {
        try {
            return invokeStatic(Class.forName(className), methodName, argTypes, args);
        } catch (Throwable e) {
            //Check.d(e);
            return null;
        }
    }

    public static Object invokeStatic(Class<?> classType, String methodName, Object... args) {
        return invokeStatic(classType, methodName, resolveArgsTypes(args), args);
    }

    public static Object invokeStatic(Class<?> classType, String methodName, Class<?>[] argTypes, Object... args) {
        try {
            Object obj= invoke(null, classType, methodName, argTypes, args);
            Log.e("teg","reflection obj:"+obj);
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object invokeStatic(Method method, Object... args) {
        return invoke(null, method, args);
    }

    /**
     * @param args
     *            (Note:there must be a clear distinction between int type and
     *            float, double, etc.)
     */
    public static Object invoke(Object obj, String methodName, Object... args) {
        return invoke(obj, obj.getClass(), methodName, resolveArgsTypes(args), args);
    }

    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            return method.invoke(obj, args);
        } catch (Throwable e) {
            //Check.d(e);
            return null;
        }
    }

    public static Object invoke(Object obj, Class<?> targetClass, String methodName, Class<?>[] argTypes, Object... args) {
        try {
            Method method = targetClass.getDeclaredMethod(methodName, argTypes);
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Throwable e) {
            //Check.d(e);
            return null;
        }
    }

    /** Construct a object by class name */
    public static Object construct(String className, Object... args) {
        try {
            Class<?> clazz = Class.forName(className);
            Constructor<?> constructor = clazz.getConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance(args);
        } catch (Exception e) {
            //Check.d(e);
        }
        return null;
    }

    /**
     * @return resolve primitive type for all primitive wrapper types.
     */
    public static Class<?> rawType(Class<?> type) {
        if (type.equals(Boolean.class)) {
            return boolean.class;
        }
        else if (type.equals(Integer.class)) {
            return int.class;
        }
        else if (type.equals(Float.class)) {
            return float.class;
        }
        else if (type.equals(Double.class)) {
            return double.class;
        }
        else if (type.equals(Short.class)) {
            return short.class;
        }
        else if (type.equals(Long.class)) {
            return long.class;
        }
        else if (type.equals(Byte.class)) {
            return byte.class;
        }
        else if (type.equals(Character.class)) {
            return char.class;
        }

        return type;
    }

    private static Class<?>[] resolveArgsTypes(Object... args) {
        Class<?>[] types = null;
        if (args != null && args.length > 0) {
            types = new Class<?>[args.length];
            for (int i = 0; i < args.length; ++i) {
                types[i] = rawType(args[i].getClass());
            }
        }
        return types;
    }

}
