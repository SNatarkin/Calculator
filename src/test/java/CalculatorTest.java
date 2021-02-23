import rpn.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    private final IArgumentController checkInput = new ArgumentControllerImpl();
    private final ICalculator calculator = new EvaluationCalculator(new ArgumentControllerImpl(), new PostfixConverter());

    @Test
    public void checkInfixToPostfix() {
        Converter converter = new PostfixConverter();
        assertEquals("1 1 +", converter.convertToPostfix("1+1"));
        assertEquals("2 4 -", converter.convertToPostfix("2-4"));
        assertEquals("2 1 *", converter.convertToPostfix("2*1"));
        assertEquals("1 9 /", converter.convertToPostfix("1/9"));
        assertEquals("3 3 * 3 +", converter.convertToPostfix("3*3+3"));
        assertEquals("3 3 3 * +", converter.convertToPostfix("3+3*3"));
        assertEquals("5 1 / 4 + 3 4 * -", converter.convertToPostfix("5/1+4-3*4"));
        assertEquals("2.1 1.9 + 3 2 * - 4 - 5 10 * + 35 -", converter.convertToPostfix("2.1+1.9-3*2-4+5*10-35"));
    }

    @Test
    public void testCheckForLetters() {
        assertFalse(checkInput.check("A2.1+1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("A2.1+1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("2.1+1.9-3*2-4С+5*10-35"));
    }

    @Test
    public void testCheckWhiteSpace() {
        assertFalse(checkInput.check("1+ 1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check(" 1+1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("1+ 1.9-3*2-4+5*10-35 "));
    }

    @Test
    public void testDuplicateOperand() {
        assertFalse(checkInput.check("2.1++1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("2.1++1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("2.1++1.9-3**2-4+5*10-35"));
        assertFalse(checkInput.check("2.1+1.9-3*2--4+5*10-35"));
    }

    @Test
    public void operandAtTheBeginningOrAtTheEnd() {
        assertFalse(checkInput.check("+2.1+1.9-3*2-4+5*10-35"));
        assertFalse(checkInput.check("2.1+1.9-3*2-4+5*10-35+"));
        assertFalse(checkInput.check("2.1+1.9-3*2-4+5*10-35*"));
        assertFalse(checkInput.check("*2.1+1.9-3*2-4+5*10-35"));
    }

    @Test
    public void notSupportedOperand() {
        assertFalse(checkInput.check("2^2"));
        assertFalse(checkInput.check("2%2"));
        assertFalse(checkInput.check("2*2^9"));
    }

    @Test
    public void testCalculator() {
        assertTrue(calculator.calculate("22+2*2/2+2") == 26.0);
        assertTrue(calculator.calculate("23434*213+231") == 4991673.0);
        assertTrue(calculator.calculate("12+5+4-4*5-1") == 0.0);
        assertTrue(calculator.calculate("232*2+2-4-3+7") == 466.0);
    }

    @Test
    public void testCalculatorFractional() {
        assertEquals(12.75, calculator.calculate("2*2+21/2*5/6"), 0.00001);
        assertEquals(28.74, calculator.calculate("3.3*7.1+5.31"), 0.00001);
        assertEquals(23.1375, calculator.calculate("5.5*3.9+5.4/3.2"), 0.00001);
        assertEquals(16.9, calculator.calculate("5-9.1+10.5*8.2/4.1"), 0.00001);
    }
}



