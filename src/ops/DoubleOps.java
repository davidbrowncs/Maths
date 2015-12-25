
package ops;

public final class DoubleOps {

	private DoubleOps() {}

	public static double root(double val, double root) {
		if (root == 0) {
			throw new IllegalArgumentException();
		}
		double result = val;
		double epsilon = val / 10e20;
		int ops = 0;
		while (Math.abs(result - (val / result)) > epsilon * result && ops < 100) {
			result += (1d / root) * ((val / Math.pow(result, root - 1)) - result);
			++ops;
		}
		return result;
	}
	
	public static int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException(Integer.toString(n));
		}
		int val = 1;
		for (int i = 0; i < n; i++) {
			val += i * val;
		}
		return val;
	}
}
