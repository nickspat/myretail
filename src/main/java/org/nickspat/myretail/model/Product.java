package org.nickspat.model;

public class Product {

    String id;
    String name;
    Price current_price;
    
    public class Price{
        public float getValue() {
            return value;
        }
        public void setValue(float value) {
            this.value = value;
        }
        float value;
        enum currency_code{USD};
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Price getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Price current_price) {
        this.current_price = current_price;
    }

    
}
