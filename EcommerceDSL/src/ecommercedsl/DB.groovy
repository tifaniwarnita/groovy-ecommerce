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
        def params = ['${user.username}', '${user.email}', '${user.password}', '${user.name}', '${user.address}']
        def query = "INSERT INTO user (username,email,password,name,address) VALUES (?,?,?,?,?)";
        try {
            sql.execute query,params;
            assert sql.updateCount == 1
            println("User "+${user.username}+" successfully registered"); 
        } catch(Exception ex) {
            println("Registration failed")
        }
    }
    
    def getProductSeller(product_id) {
        return sql.rows("SELECT seller FROM product WHERE id="+product_id)[0];
    }
    
    def addProduct(info) {
        //def params = etc
        def query = "INSERT INTO product (seller,name,price,description) values (?,?,?,?)";
        //sql.execute query,params;
    }
    
    def addTransaction(info) {
        //getproductseller
        //hitung totalprice
        //def params
        //insert into transaction (buyer,seller,product,quantity,total_price) values (?,?,?,?,?)
        //sql.execute query,params
    }
    
    def addReview(info) {
        //def params
        //insert into review (product,reviewer,rating,content) values (?,?,?,?)
        //sql.execute query,params
    }
   
    def getPurchases() {
        //select * from transaction where buyer=username
        //pake sql.eachRow
    }
    
    def getSales() {
        //select * from transaction where seller=username
        //pake sql.eachRow
    }
}

