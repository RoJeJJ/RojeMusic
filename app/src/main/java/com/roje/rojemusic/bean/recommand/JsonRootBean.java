package com.roje.rojemusic.bean.recommand;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonRootBean {
    @SerializedName("hasTaste")
    private boolean hasTaste;
    @SerializedName("code")
    private int code;
    @SerializedName("category")
    private int category;
    @SerializedName("result")
    private List<Result> result;
    public void setHasTaste(boolean hasTaste) {
         this.hasTaste = hasTaste;
     }
     public boolean getHasTaste() {
         return hasTaste;
     }

    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setCategory(int category) {
         this.category = category;
     }
     public int getCategory() {
         return category;
     }

    public void setResult(List<Result> result) {
         this.result = result;
     }
     public List<Result> getResult() {
         return result;
     }

}