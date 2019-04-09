/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.advice;

import com.swcguild.masteryproject.dao.MasteryProjectPersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Blake
 */
public class MasteryProjectAuditDaoFileImpl implements MasteryProjectAuditDao {

    private static final String FILE_NAME = "log.txt";

    @Override
    public void writeAuditEntry(String log) throws MasteryProjectPersistenceException {
        try {
            PrintWriter out;
            out = new PrintWriter(new FileWriter("log.txt", true));
            out.println(log);
            out.flush();
            out.close();
        } catch (IOException ex) {

        }
    }
}
