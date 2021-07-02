package com.qif.id.paragraph.Model;

public class NotifModel {
    public String ID;
    String title;

    public String getNotif_date_form() {
        return notif_date_form;
    }

    public void setNotif_date_form(String notif_date_form) {
        this.notif_date_form = notif_date_form;
    }

    String notif_date_form;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc_title() {
        return desc_title;
    }

    public void setDesc_title(String desc_title) {
        this.desc_title = desc_title;
    }

    public String getNotif_date() {
        return notif_date;
    }

    public void setNotif_date(String notif_date) {
        this.notif_date = notif_date;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

    String desc_title;
    String notif_date;
    String is_active;
}
