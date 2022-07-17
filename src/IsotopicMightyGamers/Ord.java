/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsotopicMightyGamers;

import java.sql.Date;

/**
 *
 * @author Poindexter
 */
public class Ord {
    
    int product_id, order_id, customer_id, DeliveryCharge,D_ID;
    float points;
    Date order_date, delivery_date;

    public float getPoints() {
        return points;
    }

    public void setPoints(float points) {
        this.points = points;
    }
    
    public int getD_ID() {
        return D_ID;
    }

    public void setD_ID(int D_ID) {
        this.D_ID = D_ID;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getDeliveryCharge() {
        return DeliveryCharge;
    }

    public void setDeliveryCharge(int DeliveryCharge) {
        this.DeliveryCharge = DeliveryCharge;
    }

    

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Date getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(Date delivery_date) {
        this.delivery_date = delivery_date;
    }

    public Ord(int product_id, int order_id, int customer_id, Date order_date, Date delivery_date, int DeliveryCharge, int D_ID, float points) {
        this.product_id = product_id;
        this.order_id = order_id;
        this.customer_id = customer_id;
        this.DeliveryCharge = DeliveryCharge;
        this.order_date = order_date;
        this.delivery_date = delivery_date;
        this.D_ID = D_ID;
        this.points = points;
    }
    
    public Ord( int order_id, Date delivery_date, int DeliveryCharge) {
        this.order_id = order_id;
        this.DeliveryCharge = DeliveryCharge;
        this.delivery_date = delivery_date;
    }
    
    public Ord( int order_id, Date order_date, float points) {
        this.order_id = order_id;
        this.points = points;
        this.order_date = order_date;
    }
   
}
