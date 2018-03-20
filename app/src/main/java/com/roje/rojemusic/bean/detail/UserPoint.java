package com.roje.rojemusic.bean.detail;


import com.google.gson.annotations.SerializedName;

public class UserPoint {
    @SerializedName("userId")
    private long userId;
    @SerializedName("balance")
    private int balance;
    @SerializedName("updateTime")
    private long updateTime;
    @SerializedName("version")
    private int version;
    @SerializedName("status")
    private int status;
    @SerializedName("blockBalance")
    private int blockBalance;
    public void setUserId(long userId) {
         this.userId = userId;
     }
     public long getUserId() {
         return userId;
     }

    public void setBalance(int balance) {
         this.balance = balance;
     }
     public int getBalance() {
         return balance;
     }

    public void setUpdateTime(long updateTime) {
         this.updateTime = updateTime;
     }
     public long getUpdateTime() {
         return updateTime;
     }

    public void setVersion(int version) {
         this.version = version;
     }
     public int getVersion() {
         return version;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setBlockBalance(int blockBalance) {
         this.blockBalance = blockBalance;
     }
     public int getBlockBalance() {
         return blockBalance;
     }

}