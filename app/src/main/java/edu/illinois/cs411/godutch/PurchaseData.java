package edu.illinois.cs411.godutch;


class PurchaseData {

    private int purchaseId;
    private String date, storeAddress, userName;
    private double tax;

    public PurchaseData(int purchaseId, String date, String storeAddress, String userName, double tax) {
        this.purchaseId = purchaseId;
        this.date = date;
        this.storeAddress = storeAddress;
        this.userName = userName;
        this.tax = tax;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public String getDate() {
        return date;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getUserName() {
        return userName;
    }

    public double getTax() {
        return tax;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }
}
