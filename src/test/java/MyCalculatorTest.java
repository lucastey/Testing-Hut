import org.example.MyCalculator;
import org.testng.annotations.Test;
import org.testng.Assert;
public class MyCalculatorTest {
    @Test
    void testAddMethod(){
        int first = 20, second = 60;
        int expectedResult = first + second;
        int actualResult = MyCalculator.add(first, second);

        Assert.assertEquals(expectedResult, actualResult, "The actual result should be 80 as well");
    }

    @Test
    void testMultiplyMethod(){
        int firstOperand = 10, secondOperand = 5;
        int expectedResult = firstOperand * secondOperand;
        int actualResult = MyCalculator.multiply(firstOperand, secondOperand);

        Assert.assertEquals(actualResult, expectedResult);
    }
}
