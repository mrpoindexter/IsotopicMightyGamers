/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

public class User {
    
    String U_Name,Username,U_Password,Email;
    int Phone;

    public String getU_Name() {
        return U_Name;
    }

    public void setU_Name(String U_Name) {
        this.U_Name = U_Name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getU_Password() {
        return U_Password;
    }

    public void setU_Password(String U_Password) {
        this.U_Password = U_Password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public User(String U_Name, String Username, String U_Password, String Email, int Phone) {
        this.U_Name = U_Name;
        this.Username = Username;
        this.U_Password = U_Password;
        this.Email = Email;
        this.Phone = Phone;
    }
    
    public User(String Email, String U_Password)
    {
        this.Email = Email;
        this.U_Password = U_Password;
    }
    
    public User(String U_Name)
    {
        this.U_Name = U_Name;
    }
    
}
