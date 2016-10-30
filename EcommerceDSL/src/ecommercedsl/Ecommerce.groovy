/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ecommercedsl

import groovy.xml.MarkupBuilder


class Ecommerce {
    def dbConn = new DB()
    
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
        def row = dbConn.checkLogin(username, password)
        println "Login succeed"
    }
    
    def add(action) {
        [seller: { seller ->
            [name: { name ->
                [price : { price ->
                    [description : { description ->
                        Product product = new Product(seller:seller, name:name, price:price, description:description)
                        action(product)
                    }]
                }]
            }]
        }]
    
    }
    
    def product = {
        dbConn.addProduct(it)
    }
    
    def send(action) {
        [product: { product ->
            [reviewer: { reviewer ->
                [rating : { rating ->
                    [content : { content ->
                        Review review = new Review(product:product, reviewer:reviewer, rating:rating, content:content)
                        action(review)
                    }]
                }]
            }]
        }]
    }
    
    def review = {
        dbConn.addReview(it)
    }
    
    def getSeller(id) {
        def row = dbConn.getProductSeller(id)
        println row.seller
    }
}


