/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.service;

/**
 *
 * @author Blake
 */
public class NoOrderFoundException extends Exception {
 public NoOrderFoundException(String message) {
        super(message);
    }

    public NoOrderFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NoOrderFoundException() {
    }
    
}
