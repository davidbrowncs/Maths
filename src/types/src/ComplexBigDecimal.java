package types.src;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ops.BigDecimalOps;

public class ComplexBigDecimal implements Complex<BigDecimal> {

	private final BigDecimal r;
	private final BigDecimal i;
	
	public static final ComplexBigDecimal IDENTITY = new ComplexBigDecimal(new BigDecimal(1), new BigDecimal(0));
	private static final BigDecimal[] factorials = new BigDecimal[1000];
	static {
		for (int i = 0; i < factorials.length; i++) {
			factorials[i] = BigDecimalOps.factorial(i);
		}
	}
	
	
	public ComplexBigDecimal(BigDecimal real, BigDecimal imaginary) {
		if (real == null || imaginary == null) {
			throw new IllegalArgumentException(real + ", " + imaginary);
		}
		this.r = real;
		this.i = imaginary;
	}
	
	@Override
	public ComplexBigDecimal add(Complex<BigDecimal> other) {
		return new ComplexBigDecimal(r.add(other.getReal()), i.add(other.getImaginary()));
	}

	@Override
	public ComplexBigDecimal subtract(Complex<BigDecimal> other) {
		return new ComplexBigDecimal(r.subtract(other.getReal()), i.subtract(other.getImaginary()));
	}

	@Override
	public ComplexBigDecimal multiply(Complex<BigDecimal> other) {
		BigDecimal real = r.multiply(other.getReal());
		BigDecimal tmp = i.multiply(other.getImaginary());
		real = real.subtract(tmp);
		
		BigDecimal imaginary = r.multiply(other.getImaginary());
		tmp = i.multiply(other.getReal());
		imaginary = imaginary.add(tmp);
		
		return new ComplexBigDecimal(real, imaginary);
	}

	@Override
	public ComplexBigDecimal scalarMultiply(BigDecimal scalar) {
		return new ComplexBigDecimal(scalar.multiply(r), scalar.multiply(i));
	}

	@Override
	public ComplexBigDecimal divide(Complex<BigDecimal> other) {
		BigDecimal divisor = other.getReal().multiply(other.getReal());
		BigDecimal tmp = other.getImaginary().multiply(other.getImaginary());
		divisor = divisor.add(tmp);
		
		BigDecimal newReal = r.multiply(other.getReal());
		tmp = i.multiply(other.getImaginary());
		newReal = newReal.add(tmp);
		newReal = newReal.divide(divisor, 200, RoundingMode.HALF_UP);
		
		BigDecimal newImaginary = i.multiply(other.getImaginary());
		tmp = r.multiply(other.getImaginary());
		newImaginary = newImaginary.subtract(tmp);
		newImaginary = newImaginary.divide(divisor, 200, RoundingMode.HALF_UP);
		
		return new ComplexBigDecimal(newReal, newImaginary);
	}

	@Override
	public BigDecimal getReal() {
		return r;
	}

	@Override
	public BigDecimal getImaginary() {
		return i;
	}

	@Override
	public ComplexBigDecimal getConjugate() {
		return new ComplexBigDecimal(r, i.multiply(new BigDecimal(-1)));
	}

	@Override
	public ComplexBigDecimal pow(Complex<BigDecimal> pow) {
		BigDecimal angle = pow.getReal().multiply(getArg());
		BigDecimal tmp = new BigDecimal(0.5).multiply(pow.getImaginary());
		
		return null;
	}

	@Override
	public Complex<BigDecimal> cos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> sin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> pow(long pow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> pow(double pow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> log() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> log(int base) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> sqrt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> cbrt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> root(int k, double root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Complex<BigDecimal> root(double root) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getArg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getModulus() {
		// TODO Auto-generated method stub
		return null;
	}


}
