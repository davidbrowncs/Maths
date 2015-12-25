
package types.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import types.src.ComplexDouble;

public class ComplexDoubleTest {

	private final static double delta = 10e-10;
	private ComplexDouble expected;
	private ComplexDouble received;

	@Test
	public void testAdd() {
		ComplexDouble d = new ComplexDouble(3, 5).add(new ComplexDouble(5, 1));
		testEqual(new ComplexDouble(8, 6), d);

		expected = new ComplexDouble(1980.002171, 5395.58979);
		received = new ComplexDouble(-1233.120829, -1039.53321).add(new ComplexDouble(3213.123, 6435.123));
		testEqual(expected, received);
	}

	private void testEqual(ComplexDouble expected, ComplexDouble received) {
		assertEquals(expected.getReal(), received.getReal(), delta);
		assertEquals(expected.getImaginary(), received.getImaginary(), delta);
	}

	@Test
	public void testAddNegative() {
		testEqual(new ComplexDouble(6, -7).add(new ComplexDouble(-2, 1)), new ComplexDouble(4, -6));
	}

	@Test
	public void testSquareRoot() {
		expected = new ComplexDouble(2.456732363513115284, 1.0176118640880409969);
		received = new ComplexDouble(5, 5).sqrt();
		testEqual(expected, received);

		expected = new ComplexDouble(2.3496376932191370810240273, 2.55358518350106107049);
		received = new ComplexDouble(-1, 12).sqrt();
		testEqual(expected, received);

		expected = new ComplexDouble(1.43161089573822132705589, -1.746284557795891527024672);
		received = new ComplexDouble(-1, -5).sqrt();
		testEqual(expected, received);

		expected = new ComplexDouble(1.581138830084189665999, 1.5811388300841896659994);
		received = new ComplexDouble(0, 5).sqrt();
		testEqual(expected, received);

		expected = new ComplexDouble(0, 0);
		received = new ComplexDouble(0, 0).sqrt();
		testEqual(expected, received);

		expected = new ComplexDouble(2.885230548905365840313794532869, -2.079556520111141067708377253418);
		received = new ComplexDouble(4, -12).sqrt();
		testEqual(expected, received);
	}

	@Test
	public void testNonIntegerPowers() {
		expected = new ComplexDouble(12.6006086331660978017152271079, -109.85372443291767789201586919306);
		received = new ComplexDouble(4, -2).pow(Math.PI);
		testEqual(expected, received);
	}

	@Test
	public void testComplexPow() {
		expected = new ComplexDouble(268.1577195433005615611513, 1135.1087128364833806647681294);
		received = new ComplexDouble(4, 2).pow(new ComplexDouble(1, -12));
		testEqual(expected, received);
	}

	@Test
	public void testMultiply() {
		expected = new ComplexDouble(1366.227313, -1701.513182);
		received = new ComplexDouble(5.3123, 4.23).multiply(new ComplexDouble(1.31, -321.34));
		testEqual(expected, received);
	}
	
	@Test
	public void testPow() {
		expected = new ComplexDouble(-14089.77984826690401378157, -3161.179002821943594285);
		received = new ComplexDouble(5.3123, 4.23).pow(5);
		testEqual(expected, received);
		
		expected = new ComplexDouble(-5, 3);
		received = new ComplexDouble(0, 1).multiply(new ComplexDouble(3, 5));
		testEqual(expected, received);
	}
}
