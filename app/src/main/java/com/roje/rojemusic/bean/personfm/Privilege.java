package com.roje.rojemusic.bean.personfm;

import com.google.gson.annotations.SerializedName;

public class Privilege {
    @SerializedName("id")
    private long id;
    @SerializedName("fee")
    private int fee;
    @SerializedName("payed")
    private int payed;
    @SerializedName("st")
    private int st;
    @SerializedName("pl")
    private long pl;
    @SerializedName("dl")
    private long dl;
    @SerializedName("sp")
    private int sp;
    @SerializedName("cp")
    private int cp;
    @SerializedName("subp")
    private int subp;
    @SerializedName("cs")
    private boolean cs;
    @SerializedName("maxbr")
    private long maxbr;
    @SerializedName("fl")
    private long fl;
    @SerializedName("toast")
    private boolean toast;
    @SerializedName("flag")
    private int flag;
    @SerializedName("preSell")
    private boolean preSell;
    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setFee(int fee) {
         this.fee = fee;
     }
     public int getFee() {
         return fee;
     }

    public void setPayed(int payed) {
         this.payed = payed;
     }
     public int getPayed() {
         return payed;
     }

    public void setSt(int st) {
         this.st = st;
     }
     public int getSt() {
         return st;
     }

    public void setPl(long pl) {
         this.pl = pl;
     }
     public long getPl() {
         return pl;
     }

    public void setDl(long dl) {
         this.dl = dl;
     }
     public long getDl() {
         return dl;
     }

    public void setSp(int sp) {
         this.sp = sp;
     }
     public int getSp() {
         return sp;
     }

    public void setCp(int cp) {
         this.cp = cp;
     }
     public int getCp() {
         return cp;
     }

    public void setSubp(int subp) {
         this.subp = subp;
     }
     public int getSubp() {
         return subp;
     }

    public void setCs(boolean cs) {
         this.cs = cs;
     }
     public boolean getCs() {
         return cs;
     }

    public void setMaxbr(long maxbr) {
         this.maxbr = maxbr;
     }
     public long getMaxbr() {
         return maxbr;
     }

    public void setFl(long fl) {
         this.fl = fl;
     }
     public long getFl() {
         return fl;
     }

    public void setToast(boolean toast) {
         this.toast = toast;
     }
     public boolean getToast() {
         return toast;
     }

    public void setFlag(int flag) {
         this.flag = flag;
     }
     public int getFlag() {
         return flag;
     }

    public void setPreSell(boolean preSell) {
         this.preSell = preSell;
     }
     public boolean getPreSell() {
         return preSell;
     }

}