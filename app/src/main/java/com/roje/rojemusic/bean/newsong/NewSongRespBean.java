package com.roje.rojemusic.bean.newsong;
import java.util.List;


public class NewSongRespBean {

    private int code;
    private int category;
    private List<NewSongResult> result;
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

    public void setResult(List<NewSongResult> result) {
         this.result = result;
     }
     public List<NewSongResult> getResult() {
         return result;
     }

}