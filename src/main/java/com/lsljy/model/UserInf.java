package com.lsljy.model;

public class UserInf {
    private String userId;//用户编号
    private String headPhoto;//头像编号
    private String petName;//昵称
    private String sex;//性别
    private String phone;//电话
    private String workZw;//职位
    private String adress;//地址
    private String regiTime;//注册时间
    private String modTime;//修改时间
    private String delTime;//删除时间
    private String delInd;//删除标志-1是0否
    private String openAccInd;//开通账户标志1开0关
    private String password;//开通账户标志1开0关
    private String email;//开通账户标志1开0关


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWorkZw(String workZw) {
        this.workZw = workZw;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setRegiTime(String regiTime) {
        this.regiTime = regiTime;
    }

    public void setModTime(String modTime) {
        this.modTime = modTime;
    }

    public void setDelTime(String delTime) {
        this.delTime = delTime;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public void setOpenAccInd(String openAccInd) {
        this.openAccInd = openAccInd;
    }


    public String getUserId() {
        return userId;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public String getPetName() {
        return petName;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getWorkZw() {
        return workZw;
    }

    public String getAdress() {
        return adress;
    }

    public String getRegiTime() {
        return regiTime;
    }

    public String getModTime() {
        return modTime;
    }

    public String getDelTime() {
        return delTime;
    }

    public String getDelInd() {
        return delInd;
    }

    public String getOpenAccInd() {
        return openAccInd;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
