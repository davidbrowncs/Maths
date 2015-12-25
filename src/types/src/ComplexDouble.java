
package types.src;

import ops.DoubleOps;

public final class ComplexDouble implements Complex<Double> {

	private final double r;
	private final double i;

	public static final ComplexDouble IDENTITY = new ComplexDouble(1, 0);
	public static final ComplexDouble ZERO = new ComplexDouble(0, 0);

	private static final double[] factorials = new double[11];
	static {
		for (int i = 0; i < factorials.length; i++) {
			factorials[i] = DoubleOps.factorial(i);
		}
	}

	public static final ComplexDouble MAX_VALUE = new ComplexDouble(Double.MAX_VALUE, Double.MAX_VALUE);
	public static final ComplexDouble MIN_VALUE = new ComplexDouble(Double.MIN_VALUE, Double.MIN_VALUE);

	public ComplexDouble(double real, double imaginary) {
		this.r = real;
		this.i = imaginary;
	}

	@Override
	public ComplexDouble add(Complex<Double> o) {
		return new ComplexDouble(r + o.getReal(), i + o.getImaginary());
	}

	@Override
	public ComplexDouble subtract(Complex<Double> o) {
		return new ComplexDouble(r - o.getReal(), i - o.getImaginary());
	}

	@Override
	public ComplexDouble multiply(Complex<Double> o) {
		double real = r * o.getReal() - i * o.getImaginary();
		double imaginary = r * o.getImaginary() + i * o.getReal();
		return new ComplexDouble(real, imaginary);
	}

	@Override
	public ComplexDouble scalarMultiply(Double scalar) {
		return new ComplexDouble(scalar * r, scalar * i);
	}
	
	@Override
	public ComplexDouble divide(Complex<Double> other) {
		double divisor = other.getReal() * other.getReal() + other.getImaginary() * other.getImaginary();
		double newReal = (r * other.getReal() + i * other.getImaginary()) / divisor;
		double newImaginary = (i * other.getImaginary() - r * other.getImaginary()) / divisor;
		return new ComplexDouble(newReal, newImaginary);
	}

	public ComplexDouble pow(double num) {
		if (num == 0) {
			return IDENTITY;
		}
		ComplexDouble integerPart = pow((long) num);
		int expansions = factorials.length;
		num -= (int) num;
		ComplexDouble logVal = log().scalarMultiply(num);
		ComplexDouble previous = logVal;
		ComplexDouble result = IDENTITY;
		for (int i = 1; i < expansions; i++, previous = previous.multiply(logVal)) {
			result = result.add(previous.scalarMultiply(1d / (double) factorials[i]));
		}
		return integerPart.multiply(result);
	}

	public ComplexDouble log() {
		return new ComplexDouble(Math.log(getModulus()), getArg());
	}

	@Override
	public ComplexDouble pow(Complex<Double> pow) {
		double angle = pow.getReal() * getArg() + (1d / 2d) * pow.getImaginary() * Math.log(r * r + i * i);
		double factor = Math.pow((r * r + i * i), (pow.getReal() / 2d)) * Math.exp(-pow.getImaginary() * getArg());
		return new ComplexDouble(factor * Math.cos(angle), factor * Math.sin(angle));
	}

	public ComplexDouble pow(long num) {
		if (num < 0) {
			return IDENTITY.divide(pow(-num));
		} else if (num == 0) {
			return IDENTITY;
		}
		if (num % 2 == 1) {
			if (num == 1) {
				return this;
			} else {
				return pow(num - 1l).multiply(this);
			}
		} else {
			if (num == 2) {
				return multiply(this);
			} else {
				return pow(num / 2l).pow(2l);
			}
		}
	}

	@Override
	public Double getReal() {
		return r;
	}

	@Override
	public Double getImaginary() {
		return i;
	}

	@Override
	public ComplexDouble getConjugate() {
		return new ComplexDouble(r, -i);
	}

	@Override
	public ComplexDouble sqrt() {
		return root(0, 2);
	}

	@Override
	public ComplexDouble cbrt() {
		return root(0, 3);
	}

	public ComplexDouble root(double root) {
		return root(0, root);
	}

	public static void main(String[] args) {
		ComplexDouble c = new ComplexDouble(-5, 2);
		System.out.println(c.root(0, 6));
	}

	@Override
	public ComplexDouble root(int k, double root) {
		if (root == 0 || k >= root) {
			throw new IllegalArgumentException();
		}
		double theta = getArg();
		double r = getModulus();
		double factor = Math.pow(r, 1d / root);
		double left = Math.cos((theta + Math.PI * 2d * k) / root);
		double right = Math.sin((theta + Math.PI * 2d * k) / root);
		return new ComplexDouble(factor * left, factor * right);
	}

	public String toString() {
		return r + (i >= 0 ? " + " : " - ") + Math.abs(i) + "i";
	}

	public ComplexDouble clone() {
		return new ComplexDouble(r, i);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(i);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ComplexDouble))
			return false;
		ComplexDouble other = (ComplexDouble) obj;
		if (Double.doubleToLongBits(i) != Double.doubleToLongBits(other.i))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}

	@Override
	public Double getArg() {
		double atanVal = Math.atan(i / r);
		if (r > 0) {
			return atanVal;
		} else if (r < 0 && i >= 0) {
			return Math.PI + atanVal;
		} else if (r < 0 && i < 0) {
			return atanVal - Math.PI;
		} else if (r == 0 && i > 0) {
			return Math.PI / 2d;
		} else if (r == 0 && i < 0) {
			return -Math.PI / 2d;
		} else if (r == 0 && i == 0) {
			return 0d;
		} else {	//Shouldn't get here
			throw new IllegalStateException();
		}
	}

	@Override
	public Double getModulus() {
		return Math.sqrt(r * r + i * i);
	}

	@Override
	public ComplexDouble log(int base) {
		ComplexDouble logged = log();
		double loggedBase = Math.log(base);
		return new ComplexDouble(logged.getReal() / loggedBase, logged.getImaginary() / loggedBase);
	}

	@Override
	public ComplexDouble cos() {
		return new ComplexDouble(Math.cos(r) * Math.cosh(i), -Math.sin(r) * Math.sinh(i));
	}

	@Override
	public ComplexDouble sin() {
		return new ComplexDouble(Math.sin(r) * Math.cosh(i), Math.cos(r) * Math.sinh(i));
	}
}
