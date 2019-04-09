/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.masteryproject.dao;

import static com.swcguild.masteryproject.dao.MasteryProjectProductDaoFileImpl.DELIMITER;
import static com.swcguild.masteryproject.dao.MasteryProjectProductDaoFileImpl.PRODUCT_FILE;
import com.swcguild.masteryproject.dto.Product;
import com.swcguild.masteryproject.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blake
 */
public class MasteryProjectTaxDaoFileImpl implements MasteryProjectTaxDao {

    public static final String TAX_FILE = "tax.txt";
    public static final String DELIMITER = ",";
    private Map<String, Tax> taxes = new HashMap<>();

    public MasteryProjectTaxDaoFileImpl() {
        try {
            this.loadLibrary();
        } catch (MasteryProjectPersistenceException ex) {
            Logger.getLogger(MasteryProjectTaxDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Tax> getTaxes() throws MasteryProjectPersistenceException {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax readByAbbr(String abbreviation) {
        return taxes.get(abbreviation);
    }

    public void loadLibrary() throws MasteryProjectPersistenceException {
        Scanner scanner;

        try {

            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new MasteryProjectPersistenceException("could not load Inventory data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            String abbreviation = (currentTokens[0]);
            BigDecimal tax = new BigDecimal(currentTokens[1]);

            Tax currentTax = new Tax(abbreviation, tax);

            taxes.put(currentTax.getAbbreviation(), currentTax);
        }
        scanner.close();
    }

}
