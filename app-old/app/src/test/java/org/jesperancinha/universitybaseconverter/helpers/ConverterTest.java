package nl.joaofilipesabinoesperancinha.basicunitconverter.helpers;

import org.junit.jupiter.api.Test;

import static nl.joaofilipesabinoesperancinha.basicunitconverter.helpers.Converter.getDecToBase;
import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {

    @Test
    void getDecToBaseTest() {
        String decToBase = getDecToBase(10, 2);
        assert decToBase.equals("1010");
    }
}