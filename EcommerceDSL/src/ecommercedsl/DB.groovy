/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ecommercedsl
import java.sql.*; 
import groovy.sql.Sql 

/**
 *
 * @author ASUS X202E
 */
class DB {
    def sql = Sql.newInstance('jdbc:mysql://localhost:3306/ecommerce_groovy', 'root', '', 'com.mysql.jdbc.Driver')
        
    def checkLogin(username,password) {
        def rows = sql.rows("SELECT * FROM user WHERE username='"+username+"' AND password='"+password+"'");
        if (rows.isEmpty()) return false;
        else return true;
    }
    
    def register(user) {
        def params = [user.username, user.email, user.password, user.name, user.address]
        def query = "INSERT INTO user (username,email,password,name,address) VALUES (?,?,?,?,?)";
        
        try {
            sql.execute query,params;
            println("User "+ user.username +" successfully registered"); 
        } catch(Exception ex) {
            println("Registration failed")
        }
    }
    
    def getProductSeller(product_id) {
        def row = sql.firstRow("SELECT seller FROM product WHERE id="+product_id);
        if (row==null) return null;
        else return row.seller;
    }
    
    def addProduct(info) {
        def params = [info.seller, info.name, info.price, info.description]
        def query = "INSERT INTO product (seller,name,price,description) values (?,?,?,?)";
        try {
            sql.execute query,params;
            println("Product "+ info.name +" successfully added"); 
        } catch(Exception ex) {
            println("Add Product failed")
        }
    }
    
    def addTransaction(info) {
        if (getProductSeller(info.product)==null) println "Product does not exist"
        else {
            def params = [info.buyer,seller,info.product,info.quantity]
            def query = "INSERT INTO transaction (buyer,seller,product,quantity) values (?,?,?,?)"
            try {
                sql.execute query,params;
                println("Transaction successful"); 
            } catch(Exception ex) {
                println("Transaction failed")
            }
        }
    }
    
    def addReview(info) {
        def params = [info.product, info.reviewer, info.rating, info.content]
        def query = "INSERT INTO review (product,reviewer,rating,content) values (?,?,?,?)";
        try {
            sql.execute query,params;
            println("Review to Product_ID "+ info.product +" successfully added"); 
        } catch(Exception ex) {
            println("Add review failed")
        }
    }
   
    def getPurchases(username) {
        println "Purchases of "+username
        println sql.rows("SELECT date,seller,product,quantity from transaction where buyer='"+username+"' ORDER BY date DESC")
    }
    
    def getSales(username) {
        println "Sales of "+username
        println sql.rows("SELECT date,buyer,product,quantity from transaction where seller='"+username+"' ORDER BY date DESC")
    }
    
    
    
}

