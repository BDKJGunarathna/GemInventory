package OrderManagement;

public class Customer {
    private int cId;
    private String cFName;
    private String cLName;
    private String cTelephone;

    public Customer(int cId, String cFName, String cLName,  String cTelephone) {
        this.cId = cId;
        this.cFName = cFName;
        this.cLName = cLName;
        this.cTelephone = cTelephone;
    }

    public Customer(int cId){
        this.cId = cId;
    }

    public int getCId() {
        return cId;
    }

    public String getCFName() {
        return cFName;
    }

    public String getCLName() {
        return cLName;
    }

    public String getCTelephone() {
        return cTelephone;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setcFName(String cFName) {
        this.cFName = cFName;
    }

    public void setcLName(String cLName) {
        this.cLName = cLName;
    }

    public void setcTelephone(String cTelephone) {
        this.cTelephone = cTelephone;
    }
}
