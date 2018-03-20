package com.roje.rojemusic.bean.login;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonRootBean {
    @SerializedName("loginType")
    private int loginType;
    @SerializedName("code")
    private int code;
    @SerializedName("account")
    private Account account;
    @SerializedName("profile")
    private Profile profile;
    @SerializedName("bindings")
    private List<Bindings> bindings;
    public void setLoginType(int loginType) {
        this.loginType = loginType;
    }
    public int getLoginType() {
        return loginType;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public Account getAccount() {
        return account;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public Profile getProfile() {
        return profile;
    }

    public void setBindings(List<Bindings> bindings) {
        this.bindings = bindings;
    }
    public List<Bindings> getBindings() {
        return bindings;
    }
}
