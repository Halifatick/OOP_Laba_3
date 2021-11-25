package com.donnu.laba3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class OopLaba3Application {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        Jacket jacket = context.getBean("Jacket_Bean", Jacket.class);
        Dress dress = context.getBean("Dress_Bean", Dress.class);
        T_Shirt t_shirt = context.getBean("T_Shirt_Bean", T_Shirt.class);
        Store store = context.getBean("Store_Bean", Store.class);
        for (int i = 0; i < 40; i++)
        {
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------");
        jacket.SetType("Куртка");
        jacket.SetBrand("Stone Island");
        jacket.PutOnStore(store);
        dress.SetType("Платье");
        dress.SetBrand("Sherri Hill");
        dress.PutOnStore(store);
        t_shirt.SetType("Футболка");
        t_shirt.SetBrand("Louis Vuitton");
        t_shirt.PutOnStore(store);
        try {
            store.GetProductByIndex(3);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        try {
            store.GetProductByIndex(1);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < 5; i++)
        {
            System.out.println();
        }
        context.close();
    }
}
