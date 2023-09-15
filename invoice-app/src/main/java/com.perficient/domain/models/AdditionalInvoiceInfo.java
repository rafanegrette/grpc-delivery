package models;

public class AdditionalInvoiceInfo {
  private final Long userId;
  private final String userName;
  private final String userEmail;

  public AdditionalInvoiceInfo(Long userId, String userName, String userEmail) {
    this.userId = userId;
    this.userName = userName;
    this.userEmail = userEmail;
  }

  public Long getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserEmail() {
    return userEmail;
  }
}

