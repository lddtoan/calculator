import junit.framework.TestCase;

public class CalculatorTestCase extends TestCase {
    public void testAddSub() {
        assertEquals(Calculator.calculate("5 - 10 + 12 - -7"), "14");
    }

    public void testMulDiv() {
        assertEquals(Calculator.calculate("-5 * 10 / 2 * 13"), "-325");
    }

    public void testBracket() {
        assertEquals(Calculator.calculate("(-5 + 7) * 3 - 2 * (10 / 5)"), "2");
    }
}
