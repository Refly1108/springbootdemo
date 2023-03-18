package com.refly.spring_boot_test.model;

public class SetWishResponse {
  private boolean result;
  
  private Integer wishId;
  
  public SetWishResponse(boolean result, Integer wishId) {
    this.result = result;
    this.wishId = wishId;
  }
  
  public boolean isResult() {
    return this.result;
  }
  
  public void setResult(boolean result) {
    this.result = result;
  }
  
  public Integer getWishId() {
    return this.wishId;
  }
  
  public void setWishId(Integer wishId) {
    this.wishId = wishId;
  }
}