package com.donnu.laba3;

public class Product implements MarketableToPutStore {
    private String brand;
    private String type;

    @Override
    public void SetBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public void SetType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void PutOnStore(Store store) {
        store.PutOnStore(this);
    }
}