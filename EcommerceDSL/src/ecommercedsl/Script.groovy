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

sign up username "candyolivia" email "candy@gmail.com" password "123" name "candy" address "Jl.Cisitu Lama IX"

sign up username "asanilta" email "asanilta@gmail.com" password "abc" name "Asanilta Fahda" address "Jl. Ciwaregu No. 3-B"

login "candyolivia", "123"

add product name "Produk Keren" price "50000" description "Produk ini sangat keren"

login "asanilta", "abc"

make transaction product "2" quantity "5"

send review product "2" rating "5" content "Emang keren ternyata"

get purchases

login "candyolivia", "123"

get sales