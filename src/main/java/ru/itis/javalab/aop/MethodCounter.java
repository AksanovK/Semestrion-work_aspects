package ru.itis.javalab.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import ru.itis.javalab.repositories.MethodsRepository;
import sun.reflect.generics.repository.MethodRepository;

import java.util.*;

@Aspect
@Component
@Configurable
public class MethodCounter {
    Map<String, Integer> map = new HashMap<>();

    @Autowired
    MethodsRepository methodsRepository;

    @Around(value = "execution(* ru.itis.javalab.controllers.RegController.*(..))")
    public Object addCountToMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = joinPoint.proceed();
        String name = joinPoint.getSignature().getName();
        System.out.println(name);
        if (map.containsKey(name)) {
            Integer a = map.get(name);
            a += 1;
            map.put(name, a);
 //           methodsRepository.enlargeCount(name, a);
        } else {
            map.put(name, 1);
 //           methodsRepository.save(name, 1);
        }
        int max = Collections.max(map.values());
        List<String> keys = new ArrayList<>();
        for (String str : map.keySet()) {
            if (map.get(str) == max) {
                keys.add(str);
            }
        }
        methodsRepository.delete();
        for (String str : keys) {
            methodsRepository.save(str, map.get(str));
        }
        return res;
    }
}
