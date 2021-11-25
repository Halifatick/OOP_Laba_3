package com.donnu.laba3;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(public Product PutOnStore(Product))")
    public void BeforePutOnStore() {
        System.out.println("BeforePutOnStore: попытка добавления нового товара на склад!");
    }

    @AfterReturning(pointcut = "execution(public Product PutOnStore(Product))", returning = "product")
    public void AfterReturningPutOnStore(Product product) {
        System.out.printf("AfterReturningPutOnStore: добавлен новый товар на склад - %s %s\n", product.getType(), product.getBrand());
    }

    @AfterThrowing(pointcut = "execution(public Product GetProductByIndex(int))", throwing = "exception")
    public void AfterThrowingGetProductByIndex(Exception exception) {
        System.out.printf("AfterThrowingGetProductByIndex: логирую исключение %s\n", exception.getMessage());
    }

    @After("execution(public Product GetProductByIndex(int))")
    public void AfterGetProductByIndex() {
        System.out.println("AfterGetProductByIndex: метод отработал");
    }

    @Around("execution(public Product GetProductByIndex(int))")
    public Object AroundGetProductByIndex(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("Сигнатура метода GetProductByIndex: %s\n", methodSignature);
        System.out.printf("Метод = %s\n", methodSignature.getMethod());
        System.out.printf("Название метода: %s\n", methodSignature.getName());
        System.out.printf("Возвращаемый тип: %s\n", methodSignature.getReturnType());
        if (methodSignature.getName().equals("GetProductByIndex")) {
            System.out.printf("Выполняется Advice @Around для метода %s\n", methodSignature.getName());
        }
        System.out.println("AroundGetProductByIndex: попытка взять товар со склада");
        Object targetMethodResult = null;
        Object[] args = proceedingJoinPoint.getArgs();
        try {
            targetMethodResult = proceedingJoinPoint.proceed(); //Вызов метода GetProductByIndex
            System.out.printf("Товар с индексом - %s взят со склада\n", args[0].toString());
        } catch (Exception exception) {
            if (args.length > 0) {
                System.out.printf("Нет товара на складе под индексом - %s\n", args[0].toString());
            }
            System.out.println(exception.getMessage());
        }
        System.out.printf("Метод %s завершил работу!\n", methodSignature.getName());
        return targetMethodResult;
    }
}