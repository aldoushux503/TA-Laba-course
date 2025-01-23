-- Insert into Product
INSERT INTO Product (name, price, description) 
VALUES 
('Laptop', 1200.99, 'Gaming Laptop'),
('Smartphone', 799.99, 'High-end smartphone'),
('Mouse', 25.99, 'Wireless Mouse'),
('Keyboard', 49.99, 'Mechanical Keyboard'),
('Monitor', 199.99, '24-inch Full HD Monitor');

-- Insert into Category
INSERT INTO Category (name, parent_category) 
VALUES 
('Electronics', NULL),
('Computers', 1),
('Accessories', 2),
('Displays', 1);

-- Link Products to Categories
INSERT INTO Product_category (category_id, product_id) 
VALUES 
(1, 1), 
(2, 1), 
(3, 3), 
(4, 5);

-- Insert Users
INSERT INTO User (first_name, last_name, email, phone_number, password_hash) 
VALUES 
('John', 'Doe', 'john.doe@example.com', '1234567890', 'hashed_password123'),
('Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'secure_hash456'),
('Alice', 'Johnson', 'alice.johnson@example.com', '4567891230', 'hash789');

-- Insert Orders
INSERT INTO `Order` (discount, total, created_at, order_status_id, user_id) 
VALUES 
(50, 1150, '2025-01-20 10:00:00', 1, 1),
(20, 780, '2025-01-21 12:00:00', 2, 2),
(0, 120, '2025-01-22 14:00:00', 3, 3);

-- Insert Products into Orders (Order_product)
INSERT INTO Order_product (price_at_order, quantity, order_id, product_id) 
VALUES 
(1200.99, 1, 1, 1),
(799.99, 1, 2, 2),
(49.99, 2, 3, 4);

-- Insert into Shipping
INSERT INTO Shipping (tracking_number, shipping_carrier, shipped_at, estimated_delivery, shipping_status_id, order_id, adress_id, carrier_id) 
VALUES 
('TRK123456789', 'DHL', '2025-01-21 10:00:00', '2025-01-25', 1, 1, 1, 1),
('TRK987654321', 'FedEx', '2025-01-22 12:00:00', '2025-01-26', 2, 2, 2, 2);


-- Insert into Payment
INSERT INTO Payment (created_at, updated_at, payment_method_id, user_id, order_id) 
VALUES 
('2025-01-20 11:00:00', NULL, 1, 1, 1),
('2025-01-21 13:00:00', NULL, 2, 2, 2);

-- Insert into Payment_method
INSERT INTO Payment_method (name) 
VALUES 
('Credit Card'), 
('PayPal'), 
('Bank Transfer');

-- Insert into Role
INSERT INTO Role (name) 
VALUES 
('Admin'),
('Customer');

-- Assign Roles to Users
INSERT INTO User_role (user_id, role_id) 
VALUES 
(1, 1),
(2, 2),
(3, 2);

-- Insert into Address
INSERT INTO Address (city, street, zip_code, user_id) 
VALUES 
('New York', '5th Avenue', '10001', 1),
('San Francisco', 'Market Street', '94103', 2),
('Los Angeles', 'Sunset Boulevard', '90001', 3);

-- Insert into Shipping_status
INSERT INTO Shipping_status (status_name) 
VALUES 
('Pending'),
('Shipped'),
('Delivered');

-- Insert into Carrier
INSERT INTO Carrier (name, contact_number, email, website) 
VALUES 
('DHL', '+1-800-123-456', 'support@dhl.com', 'https://dhl.com'),
('FedEx', '+1-800-654-321', 'support@fedex.com', 'https://fedex.com'),
('UPS', '+1-800-987-654', 'support@ups.com', 'https://ups.com');




-- 10 statements for updating. ----------------------------------

-- Update Product price
UPDATE Product SET price = 999.99 WHERE product_id = 1;

-- Update Category name
UPDATE Category SET name = 'Laptops' WHERE category_id = 2;

-- Update User phone number
UPDATE User SET phone_number = '1112223333' WHERE user_id = 1;

-- Update Address details
UPDATE Address SET city = 'Los Angeles', zip_code = '90001' WHERE adress_id = 1;

-- Update Order status
UPDATE `Order` SET order_status_id = 2 WHERE order_id = 1;

-- Update Role name
UPDATE Role SET name = 'Super Admin' WHERE role_id = 1;

-- Update Product description
UPDATE Product SET description = 'Updated Gaming Laptop' WHERE product_id = 1;

-- Update Shipping tracking number
UPDATE Shipping SET tracking_number = 'NEW123456789' WHERE shipping_id = 1;

-- Update Payment method name
UPDATE Payment_method SET name = 'Credit Card Updated' WHERE payment_method_id = 1;

-- Update User email
UPDATE User SET email = 'new.email@example.com' WHERE user_id = 2;


-- 10 statements for deletions.
-- Delete a product
DELETE FROM Product WHERE product_id = 2;

-- Delete a category
DELETE FROM Category WHERE category_id = 2;

-- Delete a user
DELETE FROM User WHERE user_id = 2;

-- Delete an order
DELETE FROM `Order` WHERE order_id = 1;

-- Delete a role
DELETE FROM Role WHERE role_id = 2;

-- Delete an address
DELETE FROM Address WHERE adress_id = 2;

-- Delete a shipping record
DELETE FROM Shipping WHERE shipping_id = 1;

-- Delete a payment
DELETE FROM Payment WHERE payment_id = 1;

-- Delete a product review
DELETE FROM Product_review WHERE review_id = 1;

-- Delete a product category link
DELETE FROM Product_category WHERE category_id = 1 AND product_id = 1;

-- 5 alter table. ----------------------------------
-- Add a new column to Product table
ALTER TABLE Product ADD COLUMN stock_quantity INT DEFAULT 0;

-- Modify the length of email in User table
ALTER TABLE User MODIFY email VARCHAR(100);

-- Add a unique constraint to Address table
ALTER TABLE Address ADD CONSTRAINT unique_address UNIQUE (city, street, zip_code);

-- Add a new index to Order table
ALTER TABLE `Order` ADD INDEX idx_created_at (created_at);

-- Drop an unused index from Product table
ALTER TABLE Product DROP INDEX product_id_UNIQUE;


-- 1 big statement to join all tables in the database. and 5 statements with left, right, inner, outer joins. ----------------------------------

SELECT 
    o.order_id AS order_id,
    u.first_name AS user_first_name,
    u.last_name AS user_last_name,
    u.email AS user_email,
    p.name AS product_name,
    p.price AS product_price,
    c.name AS category_name,
    s_status.status_name AS shipping_status,
    s.tracking_number AS shipping_tracking_number,
    s.shipped_at AS shipping_date,
    s.estimated_delivery AS estimated_delivery_date,
    pay.created_at AS payment_date,
    paym.name AS payment_method,
    os.status_name AS order_status,
    a.city AS delivery_city,
    a.street AS delivery_street,
    a.zip_code AS delivery_zip
FROM `Order` o
JOIN User u ON o.user_id = u.user_id
JOIN Order_product op ON o.order_id = op.order_id
JOIN Product p ON op.product_id = p.product_id
JOIN Product_category pc ON p.product_id = pc.product_id
JOIN Category c ON pc.category_id = c.category_id
JOIN Shipping s ON o.order_id = s.order_id
JOIN Shipping_status s_status ON s.shipping_status_id = s_status.shipping_status_id
JOIN Payment pay ON o.order_id = pay.order_id
JOIN Payment_method paym ON pay.payment_method_id = paym.payment_method_id
JOIN Order_status os ON o.order_status_id = os.order_status_id
JOIN Address a ON s.adress_id = a.adress_id;



-- Inner Join
SELECT p.name, c.name AS category_name
FROM Product p
INNER JOIN Product_category pc ON p.product_id = pc.product_id
INNER JOIN Category c ON pc.category_id = c.category_id;

-- Left Join
SELECT u.first_name, o.order_id
FROM User u
LEFT JOIN `Order` o ON u.user_id = o.user_id;

-- Right Join
-- Inner Join
SELECT u.first_name, o.order_id
FROM User u
INNER JOIN `Order` o ON u.user_id = o.user_id;

-- Left Join
SELECT u.first_name, a.city
FROM User u
LEFT JOIN Address a ON u.user_id = a.user_id;

-- Right Join
SELECT p.name, r.rating
FROM Product p
RIGHT JOIN Product_review r ON p.product_id = r.product_id;

-- Full Outer Join (simulated with UNION)
SELECT u.first_name, o.order_id
FROM User u
LEFT JOIN `Order` o ON u.user_id = o.user_id
UNION
SELECT u.first_name, o.order_id
FROM User u
RIGHT JOIN `Order` o ON u.user_id = o.user_id;

-- Left Join with filtering
SELECT u.first_name, a.city
FROM User u
LEFT JOIN Address a ON u.user_id = a.user_id
WHERE a.city IS NOT NULL;



-- 7 statements with aggregate functions and group by and without having. ----------------------------------
-- Count orders per user
SELECT u.user_id, COUNT(o.order_id) AS total_orders
FROM User u
JOIN `Order` o ON u.user_id = o.user_id
GROUP BY u.user_id;

-- Total sales per product
SELECT p.name, SUM(op.price_at_order * op.quantity) AS total_sales
FROM Product p
JOIN Order_product op ON p.product_id = op.product_id
GROUP BY p.name;

-- Average product rating
SELECT p.name AS product_name, COALESCE(AVG(r.rating), 0) AS avg_rating
FROM Product p
LEFT JOIN Product_review r ON p.product_id = r.product_id
GROUP BY p.product_id, p.name;


-- Count products per category
SELECT c.name, COUNT(pc.product_id) AS total_products
FROM Category c
JOIN Product_category pc ON c.category_id = pc.category_id
GROUP BY c.name;

-- Total payments per method
SELECT pm.name AS payment_method, SUM(o.total) AS total_payments FROM Payment p
JOIN Payment_method pm ON p.payment_method_id = pm.payment_method_id
JOIN `Order` o ON p.order_id = o.order_id
GROUP BY pm.name;


-- Count users with addresses
SELECT u.user_id, COUNT(a.adress_id) AS total_addresses
FROM User u
LEFT JOIN Address a ON u.user_id = a.user_id
GROUP BY u.user_id;

-- Total shipping by carrier
SELECT cr.name, COUNT(s.shipping_id) AS total_shipments
FROM Carrier cr
JOIN Shipping s ON cr.carrier_id = s.carrier_id
GROUP BY cr.name;



-- 7 statements with aggregate functions and group by and with having. ----------------------------------
-- Products with total sales over $1000
SELECT p.name, SUM(op.price_at_order * op.quantity) AS total_sales
FROM Product p
JOIN Order_product op ON p.product_id = op.product_id
GROUP BY p.name
HAVING total_sales > 1000;


-- Payment methods used at least once
SELECT pm.name, COUNT(p.payment_id) AS usage_count
FROM Payment p
JOIN Payment_method pm ON p.payment_method_id = pm.payment_method_id
GROUP BY pm.name
HAVING usage_count > 0;

-- Cities with more than 1 user
SELECT a.city, COUNT(u.user_id) AS total_users
FROM Address a
JOIN User u ON a.user_id = u.user_id
GROUP BY a.city
HAVING total_users > 1;

-- Categories with at least 1 product
SELECT c.name AS category_name, COUNT(pc.product_id) AS total_products
FROM Category c
JOIN Product_category pc ON c.category_id = pc.category_id
GROUP BY c.name
HAVING total_products > 0;

-- Carriers with at least 1 shipment
SELECT cr.name AS carrier_name, COUNT(s.shipping_id) AS total_shipments
FROM Carrier cr
JOIN Shipping s ON cr.carrier_id = s.carrier_id
GROUP BY cr.name
HAVING total_shipments > 0;

-- Cities with at least 1 user
SELECT a.city AS city_name, COUNT(u.user_id) AS total_users
FROM Address a
JOIN User u ON a.user_id = u.user_id
GROUP BY a.city
HAVING total_users > 0;

-- Retrieve total orders, total items ordered, and total amount spent for each user who has made at least one order

SELECT 
    u.first_name AS user_name,
    u.email AS user_email,
    COUNT(DISTINCT o.order_id) AS total_orders,
    SUM(op.quantity) AS total_items_ordered,
    SUM(o.total) AS total_spent
FROM User u
JOIN `Order` o ON u.user_id = o.user_id
JOIN Order_product op ON o.order_id = op.order_id
GROUP BY u.user_id
HAVING total_spent > 0;

