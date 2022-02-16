package org.jesperancinha.universitybaseconverter.helpers;

import org.junit.jupiter.api.Test;

import static org.jesperancinha.universitybaseconverter.helpers.Converter.getDecToBase;
import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void getDecToBaseTest() {
        String decToBase = getDecToBase(10, 2);
        assert decToBase.equals("1010");
    }
}