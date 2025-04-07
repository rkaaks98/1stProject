package farmstory.dto;

import farmstory.DataTransferObject;

public class UserDTO implements DataTransferObject {
  private String id;
  private String password;
  private String name;
  private String nickname;
  private int point;
  private int level;
  private String email;
  private String phoneNum;
  private String zip;
  private String address;
  private String addressDetail;
  private String registerDate;
  private String leaveDate;

  public UserDTO() {
    this.point = 0;
    this.level = 0;
  }



  public String getId() {
    return id;
  }



  public void setId(String id) {
    this.id = id;
  }



  public String getPassword() {
    return password;
  }



  public void setPassword(String password) {
    this.password = password;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    this.name = name;
  }



  public String getNickname() {
    return nickname;
  }



  public void setNickname(String nickname) {
    this.nickname = nickname;
  }



  public int getPoint() {
    return point;
  }



  public void setPoint(int point) {
    this.point = point;
  }



  public int getLevel() {
    return level;
  }



  public void setLevel(int level) {
    this.level = level;
  }



  public String getEmail() {
    return email;
  }



  public void setEmail(String email) {
    this.email = email;
  }



  public String getPhoneNum() {
    return phoneNum;
  }



  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }



  public String getZip() {
    return zip;
  }



  public void setZip(String zip) {
    this.zip = zip;
  }



  public String getAddress() {
    return address;
  }



  public void setAddress(String address) {
    this.address = address;
  }


  public String getAddressDetail() {
    return addressDetail;
  }



  public void setAddressDetail(String addressDetail) {
    this.addressDetail = addressDetail;
  }



  public String getRegisterDate() {
    return registerDate;
  }



  public void setRegisterDate(String registerDate) {
    this.registerDate = registerDate;
  }



  public String getLeaveDate() {
    return leaveDate;
  }



  public void setLeaveDate(String leaveDate) {
    this.leaveDate = leaveDate;
  }


  @Override
  public String toString() {
    return "UserDTO [id=" + id + ", password=" + password + ", name=" + name + ", nickname="
        + nickname + ", point=" + point + ", level=" + level + ", email=" + email + ", phoneNum="
        + phoneNum + ", zip=" + zip + ", address=" + address + ", addressDetail=" + addressDetail
        + ", registerDate=" + registerDate + ", leaveDate=" + leaveDate + "]";
  }
}
