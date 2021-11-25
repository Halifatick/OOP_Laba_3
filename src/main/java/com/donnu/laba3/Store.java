package com.donnu.laba3;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component("Store_Bean")
@Scope("singleton")
@EnableAspectJAutoProxy
public class Store {
    private ArrayList<Product> products = new ArrayList<Product>();

    public Product PutOnStore(Product product) {
        products.add(product);
        return product;
    }

    public ArrayList<Product> GetAllProducts() {
        return this.products;
    }

    public void DeleteProductByIndex(int Index) {
        try {
            String Name_Brand = this.products.get(Index).getBrand();
            this.products.remove(Index);
            System.out.printf("Товар бренда %s был изъят со склада!", Name_Brand);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void DeleteAllProduct() {
        this.products.clear();
        System.out.println("Все товары удалены!");
    }

    public Product GetProductByIndex(int Index) {
        return this.products.get(Index);
    }

    public void GetNameOfAllProductsFromStore() {
        for (int i = 0; i < this.products.size(); i++) {
            System.out.println(this.products.get(i).getBrand());
        }
    }
}
