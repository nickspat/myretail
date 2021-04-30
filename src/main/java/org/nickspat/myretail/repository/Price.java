package org.nickspat.myretail.repository;

import org.springframework.data.annotation.Id;

public class Price {
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public float getValue() {
        return value;
    }
    public void setValue(float value) {
        this.value = value;
    }
    public String getCurrencyType() {
        return currencyType;
    }
    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }
    @Id private String id;
    String productId;
    public String getProductId() {
        return productId;
    }
    public void setProductId(String productId) {
        this.productId = productId;
    }
    float value;
    String currencyType;

    public Price() {}

  public Price(String productID, float value, String currencyType) {
    this.productId = productID;
    this.value = value;
    this.currencyType = currencyType;
  }

  @Override
  public String toString() {
    return String.format(
        "Price[id=%s, productId=%s, value='%.2f', currencyType='%s']",
        id, productId, value, currencyType);
  }
}
