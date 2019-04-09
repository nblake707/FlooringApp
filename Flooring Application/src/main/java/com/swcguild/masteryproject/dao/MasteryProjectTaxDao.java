/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import com.swcguild.masteryproject.dto.Tax;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Blake
 */
public interface MasteryProjectTaxDao {

    List<Tax> getTaxes() throws MasteryProjectPersistenceException;

    Tax readByAbbr(String abbreviation) throws MasteryProjectPersistenceException;
}
