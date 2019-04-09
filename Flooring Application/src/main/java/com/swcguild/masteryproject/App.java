/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject;


import com.swcguild.masteryproject.controller.MasteryProjectController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Blake
 */
public class App {

    public static void main(String[] args) {
    

    //created a new application context object and stored it the the variable "ctx" of type ApplicationContext
    //implementation is called classPathXmlApplicationContext
    ApplicationContext ctx
            //Passed in the name the name of our Spring application context configuration file to the constructor
            // of ClassPathXmlApplicationContext
            = new ClassPathXmlApplicationContext("applicationContext.xml");
    MasteryProjectController controller =  ctx.getBean("controller", MasteryProjectController.class);
            //using the getBean method to retrieve the beans instantiated by the Spring application context
         
    //Id of bean we want to retrieve,the type of bean you want to retrieve
    //returns an object reference

    controller.Run();
   
    }
}
