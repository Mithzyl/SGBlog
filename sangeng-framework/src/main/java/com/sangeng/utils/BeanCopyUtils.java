package com.sangeng.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    public static <V> V copyBean(Object source, Class<V> clazz){
        // 声明目标对象类型
        V result = null;
        try{
            // 利用clazz反射创建目标对象
            result = clazz.newInstance();
            // copy属性
            BeanUtils.copyProperties(source, result);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    // copy列表
    public static <O, V> List<V> copyBeanList(List<O> sourceList, Class<V> clazz){
        return sourceList.stream().map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
