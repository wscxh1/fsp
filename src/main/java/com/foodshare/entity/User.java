package com.foodshare.entity;

public class User {
    private Integer id;

    private String nickname;

    private String email;

    private Byte usertype;

    private Byte isreal;

    private String phone;

    private Byte isdeleted;

    private String pswd;

    private String salt;

    public User() {
    }

    public User(Integer id, String nickname, String email, Byte usertype, Byte isreal, String phone, Byte isdeleted, String pswd, String salt) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.usertype = usertype;
        this.isreal = isreal;
        this.phone = phone;
        this.isdeleted = isdeleted;
        this.pswd = pswd;
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public Byte getIsreal() {
        return isreal;
    }

    public void setIsreal(Byte isreal) {
        this.isreal = isreal;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Byte isdeleted) {
        this.isdeleted = isdeleted;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd == null ? null : pswd.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", usertype=" + usertype +
                ", isreal=" + isreal +
                ", phone='" + phone + '\'' +
                ", isdeleted=" + isdeleted +
                ", pswd='" + pswd + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}