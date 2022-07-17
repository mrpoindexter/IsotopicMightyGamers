
package IsotopicMightyGamers;

import java.sql.Date;

public class Delman {
    
    String D_Name,D_Addr,D_email;
    int D_Phone,Salary,D_ID,deliveries;
    Date JoiningDate;
    float  NID;

    public Delman(int D_ID, String D_Name, String D_Addr, int D_Phone, int Salary, String D_email, Date JoiningDate, float NID, int deliveries) {
        this.D_Name = D_Name;
        this.D_Addr = D_Addr;
        this.D_Phone = D_Phone;
        this.Salary = Salary;
        this.D_ID = D_ID;
        this.D_email = D_email;
        this.JoiningDate = JoiningDate;
        this.NID = NID;
        this.deliveries = deliveries;
    }

    public int getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(int deliveries) {
        this.deliveries = deliveries;
    }

    public float getNID() {
        return NID;
    }

    public void setNID(float NID) {
        this.NID = NID;
    }
    
    public Date getJoiningDate() {
        return JoiningDate;
    }

    public void setJoiningDate(Date JoiningDate) {
        this.JoiningDate = JoiningDate;
    }

    public String getD_email() {
        return D_email;
    }

    public void setD_email(String D_email) {
        this.D_email = D_email;
    }

    public String getD_Name() {
        return D_Name;
    }

    public void setD_Name(String D_Name) {
        this.D_Name = D_Name;
    }

    public String getD_Addr() {
        return D_Addr;
    }

    public void setD_Addr(String D_Addr) {
        this.D_Addr = D_Addr;
    }

    public int getD_Phone() {
        return D_Phone;
    }

    public void setD_Phone(int D_Phone) {
        this.D_Phone = D_Phone;
    }

    public int getD_ID() {
        return D_ID;
    }

    public void setD_ID(int D_ID) {
        this.D_ID = D_ID;
    }
    

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int Salary) {
        this.Salary = Salary;
    }
    
    public Delman(String D_Name, int D_ID,String D_email) {
        this.D_Name = D_Name;
        this.D_ID = D_ID;
        this.D_email = D_email;
    }
    
    
    
}
