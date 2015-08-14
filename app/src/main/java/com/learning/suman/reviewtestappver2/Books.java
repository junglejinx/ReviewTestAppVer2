package com.learning.suman.reviewtestappver2;

public class Books {

    private int _id;
    private String _bookname;
    private String _authorname;
    private String _genre;
    private String _language;
    private String _date;

    public Books(){

    }

    public Books( String _bookname, String _authorname, String _genre, String _language, String _date) {

        this._bookname = _bookname;
        this._authorname = _authorname;
        this._genre = _genre;
        this._language = _language;
        this._date=_date;
    }

    public String get_genre() {
        return _genre;
    }

    public String get_language() {
        return _language;
    }

    public void set_genre(String _genre) {
        this._genre = _genre;
    }

    public void set_language(String _language) {
        this._language = _language;
    }

    public void set_bookname(String _bookname) {
        this._bookname = _bookname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_id() {
        return _id;
    }

    public String get_bookname() {
        return _bookname;
    }

    public String get_authorname() {
        return _authorname;
    }

    public void set_authorname(String _authorname) {
        this._authorname = _authorname;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}

