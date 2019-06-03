package com.example.demo.Util;


import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 王国超 2019/06/02
 * Jpa 序列化过滤类
 */
public class SimplePropertyFilter implements PropertyPreFilter {
    Set<String> set = new HashSet<>();//存储出现过的类名 , 类名不能重复
    LinkedList<String> rootClassName = new LinkedList<>();//存储数组当前类名 , 只有最后一个才能重复出现

    @Override
    public boolean apply(JSONSerializer serializer, Object object, String name) {
        String className = object.getClass().getName();
        String s = serializer.toString();
        set.add(className);
        int i1 = s.lastIndexOf("[");
        int i2 = s.lastIndexOf("]");
        /**
         * 如果[]空对象忽略 , 否则[ 出现的时候存为当前类名 . ] 出现释放当前类
         * 只有当最后一个对象类名的时候才可以重复出现
         */
        if (!(i1 == i2 - 1)) {
            if (i1 >= 0 && i1 == s.length() - 1) {                                          //出现 [ 存储当前对象
                boolean contains = rootClassName.contains(className);
                if (!contains) {
                    rootClassName.add(className);
                }
            }
            if (i2 > 0 && i2 == s.length() - 1) {                                           //出现 ] 移除当前类名
                set.remove(rootClassName.getLast());
                rootClassName.removeLast();
            }
        }
        Object value = null;
        try {
            Field field = object.getClass().getDeclaredField(name);
            Type type = field.getGenericType();
            String typeName = type.getTypeName();
            for (String s1 : set) {
                if (s1 != null && typeName.indexOf(s1) >= 0) {
                    if (rootClassName.size() == 0) {                                        //没有当前类名是不加载
                        return false;
                    }
                    if (typeName.indexOf(rootClassName.getLast()) < 0) {                    //等于最后一个当前类名不加载
                        return false;
                    }
                    if (!className.equals(rootClassName.getLast())) {                      //等于正在使用的类不加载
                        return false;
                    }
                }
            }
            field.setAccessible(true);
            value = field.get(object);
        } catch (Exception e) {
            return true;
        }
        /**
         * 序列化的时候忽略懒加载
         */
        if (value instanceof HibernateProxy) {
            LazyInitializer lazyInitializer = ((HibernateProxy) value).getHibernateLazyInitializer();
            if (lazyInitializer.isUninitialized()) {
                return false;
            }
        } else if (value instanceof PersistentCollection) {
            PersistentCollection collection = (PersistentCollection) value;
            if (!collection.wasInitialized()) {
                return false;
            }
            Object val = collection.getValue();
            if (val == null) {
                return false;
            }
        }
        return true;
    }
}
