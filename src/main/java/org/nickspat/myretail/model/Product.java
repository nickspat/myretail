package org.nickspat.myretail.model;

public class Product {

    String id;
    String name;
    ProductPrice current_price;
    
    public class ProductPrice{
        public float getValue() {
            return value;
        }
        public void setValue(float value) {
            this.value = value;
        }
        float value;
        String currencyCode;
        public String getCurrencyCode() {
            return currencyCode;
        }
        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }
    
        
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

    public ProductPrice getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(ProductPrice current_price) {
        this.current_price = current_price;
    }

    
}
