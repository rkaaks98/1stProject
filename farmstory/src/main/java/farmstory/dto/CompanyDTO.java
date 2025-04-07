package farmstory.dto;

import farmstory.DataTransferObject;

public class CompanyDTO implements DataTransferObject {
  private int id;
  private String companyName;
  private String managerName;
  private String contact;
  private String address;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getManagerName() {
    return managerName;
  }

  public void setManagerName(String managerName) {
    this.managerName = managerName;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "CompanyDTO [id=" + id + ", companyName=" + companyName + ", managerName=" + managerName
        + ", contact=" + contact + ", address=" + address + "]";
  }
}
