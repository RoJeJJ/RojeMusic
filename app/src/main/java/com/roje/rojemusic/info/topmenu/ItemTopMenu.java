package com.roje.rojemusic.info.topmenu;


public class ItemTopMenu extends BaseTopMenu {
    private String title;
    private int icnRes;
    private int group;

    public ItemTopMenu(boolean login,String title, int icnRes, int group) {
        super.setLogin(login);
        this.title = title;
        this.icnRes = icnRes;
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcnRes() {
        return icnRes;
    }

    public void setIcnRes(int icnRes) {
        this.icnRes = icnRes;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }
}
