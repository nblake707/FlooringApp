/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.advice;

import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Blake
 */
public class LoggingAdvice {

    MasteryProjectAuditDao logger;

    //Constructor based injection
    //passing an instance of the dependency to the constructor
    //The object has no idea what implementation of its dependency was passed in.
    public LoggingAdvice(MasteryProjectAuditDao logger) {
        this.logger = logger;
    }

    //joinPoint is a type from the AspectJ tools library.
    //The object holds information about the method to which the advice is being applied.
    public void createAuditEntry(JoinPoint jp) {

        //getArgs method: used to get all of the arguments that were passed to the method 
        //to which the this advice is being applied
        Object[] args = jp.getArgs();
        //used get name method to get the name of the method to which the advice is being applied. 
        //concatenate the name of the method with the string rep of each of the arguments to form the entry to the audit log.
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        //surrounded in try/catch to prevent faulty audit logger from causing a system failure
        try {
            //writing audit entry string to the audit log
            logger.writeAuditEntry(auditEntry);
        } catch (MasteryProjectPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
