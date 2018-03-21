package com.roje.rojemusic.bean.privatecontent;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PriContentRootBean {
    @SerializedName("code")
    private int code;
    @SerializedName("name")
    private String name;
    @SerializedName("result")
    private List<PriContResult> result;
    public void setCode(int code) {
         this.code = code;
     }
     public int getCode() {
         return code;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setResult(List<PriContResult> result) {
         this.result = result;
     }
     public List<PriContResult> getResult() {
         return result;
     }

}