/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.advice;

import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;

/**
 *
 * @author Blake
 */
public interface MasteryProjectAuditDao {

    public void writeAuditEntry(String log) throws MasteryProjectPersistenceException;
}
