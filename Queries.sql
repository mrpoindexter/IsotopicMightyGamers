create database MacroGameZone

USE MacroGameZone

create table signup
(
	U_Name varchar(255) not null,
	Username varchar(255) not null,
	U_Password varchar(255) not null,
	Email varchar(255) not null
)

create table Customer
(
	Customer_ID int not null primary key,
	C_Name varchar(255) not null,
	C_address varchar(255) not null,
	C_contact int not null,
	C_Email varchar(255) not null
)

create table Product
(
	Product_ID int not null primary key,
	Product_Name varchar(255) not null,
	Price int not null,
	Brand varchar(255),
	Warranty int not null,
	Shipping_Date Date not null,
	Country varchar(255) not null,
	PurchaseCost int
)


create table DeliveryMan
(
	D_ID int not null primary key,
	D_Name varchar(255) not null,
	D_Addr varchar(255) not null,
	D_Phone int not null,
	Salary int not null,
	D_email varchar(255),
	JoiningDate Date not null,
	NID varchar(255) not null
)


create table Orders
(
	Product_ID int not null foreign key references Product(Product_ID),
	order_id int not null primary key,
	Customer_ID int not null foreign key references Customer(Customer_ID),
	Order_Date Date not null,
	Delivery_Date Date not null,
	DeliveryCharge int not null,
	D_ID int not null foreign key references DeliveryMan(D_ID),
	points float
)

Select * From signup
Select * From Customer
Select * From Product
Select * From Orders
Select * From DeliveryMan


SELECT C_Name,C_contact,order_id,Delivery_Date,DeliveryCharge,DeliveryMan.D_ID,Price,D_email,D_Name
FROM Customer Right JOIN Orders ON Customer.Customer_ID = Orders.Customer_ID
Left JOIN Product ON Product.Product_ID = Orders.Product_ID
Left JOIN DeliveryMan ON Orders.D_ID = DeliveryMan.D_ID ORDER BY Customer.Customer_ID;


Select Customer.Customer_ID, C_Name, C_Email, Product_Name, Price, Warranty, Product.Product_ID, order_id 
From Customer Right join Orders on Customer.Customer_ID = Orders.Customer_ID 
Left Join Product on Product.Product_ID = Orders.Product_ID 
ORDER BY Customer.Customer_ID;


Select Price, DeliveryCharge, Order_Date, Count(Customer_ID) as 'Today Sales'
From Orders 
Right Join Product
on Orders.Product_ID = Product.Product_ID
WHERE Order_Date = '2022-06-18' 
GROUP BY Order_Date,Price,DeliveryCharge


Select * from Orders where Order_Date = '2022-06-18'

Select SUM(Price) as 'Total Price',SUM(DeliveryCharge) as 'Total Charge' From Product Right Join Orders on Product.Product_ID = Orders.Product_id

select count(Product_ID) as 'Purchased Items' from Product where Shipping_Date > '2022-06-11' and Shipping_Date < '2022-06-20'

select SUM(PurchaseCost) as 'Cost of purchases' from Product where Shipping_Date > '2022-01-01' and Shipping_Date < '2022-06-19'

select count(Orders.Customer_ID) as 'Total Sales' from Customer Right Join Orders on Customer.Customer_ID = Orders.Customer_ID


select count(order_id) as 'Sales', SUM(Price) as 'Sales price' from Orders Right Join Customer on Orders.Customer_ID = Customer.Customer_ID Right Join Product  on Product.Product_ID = Orders.Product_ID where Order_Date >  and Order_Date <

select  Product_Name From Product Right Join Orders on Product.Product_ID = Orders.Product_ID Group by Product_Name,Order_Date having Order_Date > '2022-06-16' and Order_Date < '2022-06-19'  order by count(Orders.Product_ID) desc  

select Brand from Product Right Join Orders on Product.Product_ID = Orders.Product_ID Group by Brand,Order_Date having Order_Date > '' and Order_Date < '' order by count(Product.Brand) desc

select D_Name from DeliveryMan Right Join Orders on Orders.D_ID = DeliveryMan.D_ID where Order_Date > '2022-01-01' and Order_Date < '2022-06-24' Group by D_Name order by count(Orders.D_ID) desc

select count(Orders.Customer_ID) as 'Total Sales' from Customer Right Join Orders on Customer.Customer_ID = Orders.Customer_ID where Order_Date > '' and Order_Date < ''

select  C_Name From Customer Right Join Orders on Customer.Customer_ID = Orders.Customer_ID Group by C_Name,Order_Date Having Order_Date > '' and Order_Date < '' order by count(Orders.Customer_ID) desc


ALTER TABLE Customer
ADD	CONSTRAINT C_U_con UNIQUE (C_contact)

ALTER TABLE Customer
ADD	CONSTRAINT C_U_ema UNIQUE (C_Email)

ALTER TABLE DeliveryMan
ADD	CONSTRAINT D_U_EM UNIQUE (D_email)

ALTER TABLE DeliveryMan
ADD	CONSTRAINT D_U_ni UNIQUE (NID)

ALTER TABLE DeliveryMan
ADD	CONSTRAINT D_U_pho UNIQUE (D_Phone)

Alter table signup drop column Phone

Drop database MacroGameZone

Alter Table DeliveryMan add NID varchar(255) not null

Alter Table Orders add points float

Select SUM(points) from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID

Alter Table Customer add points float

Alter Table Customer drop column points

Select SUM(points) from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID

Select Customer.*,SUM(points) as 'Total Points' from Customer left join Orders on Customer.Customer_ID = Orders.Customer_ID group by Customer.Customer_ID, Customer.C_address, Customer.C_contact,Customer.C_Name, Customer.C_Email order by [Total Points] asc

Select SUM(points) as 'Total Points' from Customer join Orders on Customer.Customer_ID = Orders.Customer_ID where Customer.Customer_ID = 1052

Select Customer.Customer_ID, C_Name, C_Email, Product_Name, Price, Warranty, Product.Product_ID, order_id, Order_Date, Points as 'Total Points' From Customer Right join Orders on Customer.Customer_ID = Orders.Customer_ID Left Join Product on Product.Product_ID = Orders.Product_ID group by Customer.Customer_ID, Customer.C_Name, Customer.C_Email, Product_Name, Price, Warranty, Product.Product_ID, order_id, Order_Date, Orders.points ORDER BY Customer.Customer_ID;
        
SELECT C_Name,C_contact,C_address,order_id,Delivery_Date,DeliveryCharge,DeliveryMan.D_ID,Price,D_email,D_Name FROM Customer Right JOIN Orders ON Customer.Customer_ID = Orders.Customer_ID Left JOIN Product ON Orders.Product_ID = Product.Product_ID Left JOIN DeliveryMan ON Orders.D_ID = DeliveryMan.D_ID ORDER BY Orders.Delivery_Date asc;

Alter Table Orders drop column destination 

Alter Table Product add quantity int

Update Product set quantity = 1 where Product_ID = 2046

Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone order by [deliveries] desc

Select DeliveryMan.D_ID, D_Name, D_Addr, D_Phone, Salary, D_Email, JoiningDate, NID, count(Orders.D_ID) as 'deliveries' from orders right join DeliveryMan on Orders.D_ID = DeliveryMan.D_ID where DeliveryMan.D_ID = 5241 group by DeliveryMan.D_Addr, DeliveryMan.D_email, DeliveryMan.D_ID, DeliveryMan.D_Name, DeliveryMan.JoiningDate, DeliveryMan.NID, DeliveryMan.Salary, DeliveryMan.D_Phone


SELECT D_Name, CONVERT(int,ROUND(DATEDIFF(hour,JoiningDate,GETDATE())/8766.0,0)) AS 'Years worked' from DeliveryMan order by [Years Worked] desc
    