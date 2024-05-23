# hello-world
This is my first repository to learn Github
Now i am trying to learn to commit a change with a new branch in github 
MY names Raja kumar singh and I am a computer science sophomore


-- Use the grocery_ecommerce database
USE grocery_ecommerce;

-- Insert one admin
INSERT INTO Users (username, email, password_hash, first_name, last_name, address, phone_number, is_admin)
VALUES 
('admin_user', 'admin@example.com', 'hashedpassword123', 'Admin', 'User', '123 Admin Street', '1234567890', TRUE);

-- Insert five customers
INSERT INTO Users (username, email, password_hash, first_name, last_name, address, phone_number)
VALUES 
('customer1', 'customer1@example.com', 'hashedpassword123', 'John', 'Doe', '123 Main Street', '2345678901'),
('customer2', 'customer2@example.com', 'hashedpassword123', 'Jane', 'Doe', '456 Main Street', '3456789012'),
('customer3', 'customer3@example.com', 'hashedpassword123', 'Jim', 'Beam', '789 Main Street', '4567890123'),
('customer4', 'customer4@example.com', 'hashedpassword123', 'Jack', 'Daniels', '101 Main Street', '5678901234'),
('customer5', 'customer5@example.com', 'hashedpassword123', 'Jill', 'Stark', '202 Main Street', '6789012345');

-- Insert three categories
INSERT INTO Categories (category_name, description)
VALUES 
('Fruits', 'Fresh and organic fruits'),
('Vegetables', 'Fresh and organic vegetables'),
('Dairy', 'Milk, cheese, and other dairy products');

-- Insert fifteen products
INSERT INTO Products (product_name, description, price, stock_quantity, category_id, image_url)
VALUES 
('Apple', 'Fresh red apples', 1.20, 100, 1, 'url_to_image_apple'),
('Banana', 'Ripe yellow bananas', 0.50, 200, 1, 'url_to_image_banana'),
('Orange', 'Juicy oranges', 1.10, 150, 1, 'url_to_image_orange'),
('Strawberry', 'Sweet strawberries', 2.50, 80, 1, 'url_to_image_strawberry'),
('Grapes', 'Fresh grapes', 3.00, 120, 1, 'url_to_image_grapes'),
('Carrot', 'Fresh carrots', 1.00, 100, 2, 'url_to_image_carrot'),
('Broccoli', 'Organic broccoli', 2.00, 90, 2, 'url_to_image_broccoli'),
('Spinach', 'Fresh spinach leaves', 1.50, 70, 2, 'url_to_image_spinach'),
('Potato', 'Organic potatoes', 0.80, 200, 2, 'url_to_image_potato'),
('Tomato', 'Fresh tomatoes', 1.00, 150, 2, 'url_to_image_tomato'),
('Milk', 'Organic whole milk', 2.00, 50, 3, 'url_to_image_milk'),
('Cheese', 'Cheddar cheese', 5.00, 40, 3, 'url_to_image_cheese'),
('Yogurt', 'Natural yogurt', 1.20, 80, 3, 'url_to_image_yogurt'),
('Butter', 'Organic butter', 3.50, 60, 3, 'url_to_image_butter'),
('Cream', 'Fresh cream', 2.50, 70, 3, 'url_to_image_cream');
