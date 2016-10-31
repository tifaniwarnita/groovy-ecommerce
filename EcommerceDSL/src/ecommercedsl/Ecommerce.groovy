/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ecommercedsl

import groovy.xml.MarkupBuilder


class Ecommerce {
    def dbConn = new DB()
    def loggedInUser = null;
    
    def sign(action) {
        [username: { username ->
            [email: { email ->
                [password : { pwd ->
                    [name : { name ->
                        [address : { addr ->
                            User user = new User(username:username, email:email, password:pwd, name:name, address:addr)
                            action(user)
                        }]
                    }]
                }]
            }]
        }]
    }
    
    def up = {
        dbConn.register(it)
    }
    
    def login(username, password) {
        if (dbConn.checkLogin(username, password)) {
            loggedInUser = username;
            println "Login succeed"
        } else {
            println "Login failed"
        }
    }
    
    def add(action) {
        [name: { name ->
            [price : { price ->
                [description : { description ->
                    if (loggedInUser==null) {
                        println "Login first to add product"
                    } else {
                        Product product = new Product(seller:loggedInUser, name:name, price:price, description:description)
                        action(product)
                    }
                }]
            }]
        }]
    }
    
    def product = {
        dbConn.addProduct(it)
    }
    
    def send(action) {
        [product: { product ->
            [rating : { rating ->
                [content : { content ->
                    if (loggedInUser==null) {
                        println "Login first to send review"
                    } else {  
                        Review review = new Review(product:product, reviewer:loggedInUser, rating:rating, content:content)
                       action(review)
                    }
                }]
            }]
        }]
    }
    
    def review = {
        dbConn.addReview(it)
    }
    
    def make(action) {
        [product: { product ->
            [quantity : { quantity ->
                if (loggedInUser==null) {
                    println "Login first to make transaction"
                } else {
                    Transaction transaction = new Transaction(product:product, quantity: quantity, buyer:loggedInUser)
                    action(transaction)
                }
            }]
        }]
    }
    
    def transaction = {
        dbConn.addTransaction(it)
    }
    
    def get(action) {
        if (loggedInUser==null) {
            println "Login first to get info"
        } else {
            action(loggedInUser)
        }
    }
    
    def sales = {
        dbConn.getSales(it)
    }
    
    def purchases = {
        dbConn.getPurchases(it)
    }
    
}


