package com.example.barcode06_10_19;

public class User {
    private String qrinfo;
    private String userid;
    private String username;
    private String useremail;
    private String usermobileno;
    private String postingdate;
    private String note;

    public User() {

    }

    public User(String userid, String username, String useremail, String usermobileno, String qrinfo, String postingdate, String note) {
        this.userid = userid;
        this.username = username;
        this.useremail = useremail;
        this.usermobileno = usermobileno;
        this.qrinfo = qrinfo;
        this.postingdate = postingdate;
        this.note = note;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPostingdate() {
        return postingdate;
    }

    public void setPostingdate(String postingdate) {
        this.postingdate = postingdate;
    }

    public String getQrinfo() {
        return qrinfo;
    }

    public void setQrinfo(String qrinfo) {
        this.qrinfo = qrinfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUsermobileno() {
        return usermobileno;
    }

    public void setUsermobileno(String usermobileno) {
        this.usermobileno = usermobileno;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

