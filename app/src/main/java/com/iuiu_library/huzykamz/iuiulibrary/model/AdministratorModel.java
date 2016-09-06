package com.iuiu_library.huzykamz.iuiulibrary.model;

import android.graphics.Bitmap;

/**
 * Created by HUZY_KAMZ on 6/1/2016.
 */
public class AdministratorModel {

    private String name;
    private String bookname;
    private String photo;
    private Bitmap bitmap;
    private String regno;
    private String borrow_date;
    private String borrowing_days;
    private String initialdate;
    private String finaldate;


    public String getInitialdate() {
        return initialdate;
    }

    public void setInitialdate(String initialdate) {
        this.initialdate = initialdate;
    }

    public String getFinaldate() {
        return finaldate;
    }

    public void setFinaldate(String finaldate) {
        this.finaldate = finaldate;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getBorrowing_days() {
        return borrowing_days;
    }

    public void setBorrowing_days(String borrowing_days) {
        this.borrowing_days = borrowing_days;
    }
}
