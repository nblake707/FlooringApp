/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

/**
 *
 * @author Blake
 */
public class MasteryProjectPersistenceException extends Exception{
     public MasteryProjectPersistenceException(String message) {
        super(message);
    }

    public MasteryProjectPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
