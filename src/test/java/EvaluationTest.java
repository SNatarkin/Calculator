import RPN.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class EvaluationTest {


    @Mock
    IArgumentController mockController = new ArgumentControllerImpl();

    @Mock
    Converter mockConverter = new PostfixConverter();

    @Test
    public void testExpressionEvaluationCheckWithMockController() {

        Converter convert = new PostfixConverter();
        ICalculator calculator = new EvaluationCalculator(mockController, convert);
        when(mockController.check(anyString())).thenReturn(true);
        assertEquals(2, calculator.calculate("1+1"), 0.00001);
        assertEquals(12.75, calculator.calculate("2*2+21/2*5/6"), 0.00001);
        assertEquals(28.74, calculator.calculate("3.3*7.1+5.31"), 0.00001);
        assertEquals(23.1375, calculator.calculate("5.5*3.9+5.4/3.2"), 0.00001);
        assertEquals(16.9, calculator.calculate("5-9.1+10.5*8.2/4.1"), 0.00001);
        assertTrue(calculator.calculate("22+2*2/2+2") == 26.0);
        assertTrue(calculator.calculate("23434*213+231") == 4991673.0);
        assertTrue(calculator.calculate("12+5+4-4*5-1") == 0.0);
        assertTrue(calculator.calculate("232*2+2-4-3+7") == 466.0);

    }
    @Test
    public void testExpressionEvaluationCheckWithMockConverter() {

        IArgumentController controller  = new ArgumentControllerImpl();
        ICalculator calculator = new EvaluationCalculator(controller, mockConverter);
        when(mockConverter.convertToPostfix("1+1")).thenReturn("1 1 +");
        assertTrue(calculator.calculate("1+1") ==2);
        when(mockConverter.convertToPostfix("3+3*3")).thenReturn("3 3 3 * +");
        assertTrue(calculator.calculate("3+3*3") ==12);
        when(mockConverter.convertToPostfix("5/10+4-3*4")).thenReturn("5 10 / 4 + 3 4 * -");
        assertEquals(-7.5 ,calculator.calculate("5/10+4-3*4") ,0.00001);
        when(mockConverter.convertToPostfix("2.1+1.9-3*2-4+5*10-35")).thenReturn("2.1 1.9 + 3 2 * - 4 - 5 10 * + 35 -");
        assertTrue(calculator.calculate("2.1+1.9-3*2-4+5*10-35") == 9);
    }
    @Test
    public void testExpressionEvaluationCheckWithAllConstructorMock() {
        ICalculator calculator = new EvaluationCalculator(mockController, mockConverter);
        when(mockController.check(anyString())).thenReturn(true);
        when(mockConverter.convertToPostfix("1+1")).thenReturn("1 1 +");
        assertEquals(2, calculator.calculate("1+1"), 0.00001);
        when(mockConverter.convertToPostfix("3+3*3")).thenReturn("3 3 3 * +");
        when(mockConverter.convertToPostfix("5/10+4-3*4")).thenReturn("5 10 / 4 + 3 4 * -");
        assertEquals(-7.5 ,calculator.calculate("5/10+4-3*4") ,0.00001);

    }


}







