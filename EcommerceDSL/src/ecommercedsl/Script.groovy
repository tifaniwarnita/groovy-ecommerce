/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ecommercedsl

def script = new GroovyScriptEngine( '.' ).with {
  loadScriptByName( 'src/ecommercedsl/Ecommerce.groovy' )
} 
this.metaClass.mixin script

//sign up username "candyolivia" email "candy@gmail.com" password "123" name "candy" address "Jl.Cisitu Lama IX"

login "candyolivia", "123"