
package IsotopicMightyGamers;

public class Cust {
    
    int Customer_ID, C_contact;
    String C_name, C_Address, C_Email;
    float TotalPoints;

    public String getC_Email() {
        return C_Email;
    }

    public void setC_Email(String C_Email) {
        this.C_Email = C_Email;
    }

    public float getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(float TotalPoints) {
        this.TotalPoints = TotalPoints;
    }
    
    public String getEmail() {
        return C_Email;
    }

    public void setEmail(String C_Email) {
        this.C_Email = C_Email;
    }

    public void setCustomer_ID(int Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public int getCustomer_ID() {
        return Customer_ID;
    }
    

    public int getC_contact() {
        return C_contact;
    }

    public void setC_contact(int C_contact) {
        this.C_contact = C_contact;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String C_name) {
        this.C_name = C_name;
    }

    public String getC_Address() {
        return C_Address;
    }

    public void setC_Address(String C_Address) {
        this.C_Address = C_Address;
    }

    public Cust(int Customer_ID, String C_name,String C_Address, int C_contact, String C_Email, float TotalPoints) {
        this.Customer_ID = Customer_ID;
        this.C_contact = C_contact;
        this.C_name = C_name;
        this.C_Address = C_Address;
        this.C_Email = C_Email;
        this.TotalPoints = TotalPoints;
    }
    
    public Cust(int Customer_ID, String C_name, String C_Email) {
        this.Customer_ID = Customer_ID;
        this.C_name = C_name;
        this.C_Email = C_Email;
    }

    public Cust(String C_name, int C_contact, String C_Address) {
        this.C_name = C_name;
        this.C_Address = C_Address;
        this.C_contact = C_contact;
    }
    
    public Cust(int Customer_ID)
    {
        this.Customer_ID = Customer_ID;
    }
    
}
