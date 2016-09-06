package com.iuiu_library.huzykamz.iuiulibrary.model;

import android.graphics.Bitmap;

import com.iuiu_library.huzykamz.iuiulibrary.Science;

/**
 * Created by HUZY_KAMZ on 5/25/2016.
 */
public class ScienceModel {

    private String author;
    private String description;
    private String bookname;
    private Bitmap bitmap;
    private String photo;
    private String faculty;
    private String BookId;


    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
}
