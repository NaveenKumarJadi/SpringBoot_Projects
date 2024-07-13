+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Spring Boot Application with Payment Gateway:
+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Prerequisite:

1. Spring Boot
2. Data JPA
3. Thymeleaf
4. Java Script
5. Razorpay Account
		key ID: rzp_test_RAZORPAYKEYID
		key Secret: RAZOR1pay2KEY3secret

Step 1: Login into Razorpay website with your credentials
Step 2: Generate API Keys in test mode and download them
Step 3: Create Spring boot application
			1)web-starter
			2)data-jpa
			3)mysql-driver
			4)thymeleaf
			5)devtools
			6)razorpay-java

++++++++++++++++++++++++++++++++++++++++++++++
SQL queries:
++++++++++++++++++++++++++++++++++++++++++++++
create database ecomm;
use ecomm;
show tables;
select * from student_orders;
truncate table student_orders;

++++++++++++++++++++++++++++++++++++++++++++++

GET: http://localhost:8080/
