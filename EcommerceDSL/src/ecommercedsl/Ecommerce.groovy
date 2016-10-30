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
}


