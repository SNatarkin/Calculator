import RPN.ArgumentController;
import RPN.Converter;
import RPN.ExpressionUtil;
import RPN.PostfixConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    private final ArgumentController checkInput = new ExpressionUtil();


    @Test
    public void checkInfixToPostfix() {
        PostfixConverter converter = new Converter();
        assertEquals("1 1 +", converter.convertToPostfix("1+1"));
        assertEquals("2 4 -", converter.convertToPostfix("2-4"));
        assertEquals("2 1 *", converter.convertToPostfix("2*1"));
        assertEquals("1 9 /", converter.convertToPostfix("1/9"));
        assertEquals("3 3 * 3 +", converter.convertToPostfix("3*3+3"));
        assertEquals("3 3 3 * +", converter.convertToPostfix("3+3*3"));
        assertEquals("5 1 / 4 + 3 4 * -", converter.convertToPostfix("5/1+4-3*4"));
        assertEquals("2.1 1.9 + 3 2 * - 4 - 5 10 * + 35 -", converter.convertToPostfix("2.1+1.9-3*2-4+5*10-35"));

    }

    @Test(expected = RuntimeException.class)
    public void testСheckForLetters() {
        checkInput.check("A2.1+1.9-3*2-4+5*10-35");
        checkInput.check("A2.1+1.9-3*2-4+5*10(-35");
        checkInput.check("2.1+1.9-3*2-4С+5*10-35");
    }

    @Test(expected = RuntimeException.class)
    public void testCheckWhiteSpace() {
        checkInput.check("1+ 1.9-3*2-4+5*10-35");
        checkInput.check(" 1+1.9-3*2-4+5*10-35");
        checkInput.check("1+ 1.9-3*2-4+5*10-35 ");
    }

    @Test(expected = RuntimeException.class)
    public void testDuplicateOperand() {
        checkInput.check("2.1++1.9-3*2-4+5*10-35");
        checkInput.check("2.1++1.9-3*2-4+5*10-35");
        checkInput.check("2.1++1.9-3**2-4+5*10-35");
        checkInput.check("2.1+1.9-3*2--4+5*10-35");
    }

    @Test(expected = RuntimeException.class)
    public void operandAtTheBeginningOrAtTheEnd() {
        checkInput.check("+2.1+1.9-3*2-4+5*10-35");
        checkInput.check("2.1+1.9-3*2-4+5*10-35+");
        checkInput.check("2.1+1.9-3*2-4+5*10-35*");
        checkInput.check("*2.1+1.9-3*2-4+5*10-35");
    }

    @Test(expected = RuntimeException.class)
    public void notSupportedOperand() {
        checkInput.check("2^2");
        checkInput.check("2%2");
        checkInput.check("2*2^9");
    }
}


