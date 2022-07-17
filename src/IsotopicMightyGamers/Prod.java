
package IsotopicMightyGamers;

import java.sql.Date;

public class Prod {
    int Product_ID, Price,Warranty, Costing, quantity;
    String Product_Name,Brand,Country;
    Date Shipping_Date;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(int Product_ID) {
        this.Product_ID = Product_ID;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public int getWarranty() {
        return Warranty;
    }

    public void setWarranty(int Warranty) {
        this.Warranty = Warranty;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public Date getShipping_Date() {
        return Shipping_Date;
    }

    public void setShipping_Date(Date Shipping_Date) {
        this.Shipping_Date = Shipping_Date;
    }

    public int getCosting() {
        return Costing;
    }

    public void setCosting(int Costing) {
        this.Costing = Costing;
    } 

    public Prod(int Product_ID, String Product_Name, int Price,String Brand, int Warranty,Date Shipping_Date, String Country, int Costing, int quantity) {
        this.Product_ID = Product_ID;
        this.Price = Price;
        this.Warranty = Warranty;
        this.Product_Name = Product_Name;
        this.Brand = Brand;
        this.Country = Country;
        this.Shipping_Date = Shipping_Date;
        this.Costing = Costing;
        this.quantity = quantity;
    }
    
   public Prod(String Product_Name, int Price, int Warranty, int Product_ID) {
        this.Price = Price;
        this.Warranty = Warranty;
        this.Product_Name = Product_Name;
        this.Product_ID = Product_ID;
    } 
   
   public Prod(int Price) {
        this.Price = Price;
    }
   
   public Prod(int Product_ID, int Warranty)
   {
       this.Product_ID = Product_ID;
       this.Warranty = Warranty;
   }
    
}
