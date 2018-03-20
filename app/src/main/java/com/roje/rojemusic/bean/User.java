package com.roje.rojemusic.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("loginType")
    private int loginType;
    @SerializedName("code")
    private int code;
    @SerializedName("account")
    private Account account;
    @SerializedName("bindings")
    private List<Binding> bindings;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @SerializedName("profile")
    private Profile profile;

    public List<Binding> getBindings() {
        return bindings;
    }

    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }
}
