package types.src;

public interface Complex<T>{
	
	public Complex<T> add(Complex<T> other);
	
	public Complex<T> subtract(Complex<T> other);
	
	public Complex<T> multiply(Complex<T> other);
	
	public Complex<T> scalarMultiply(T scalar);
	
	public Complex<T> divide(Complex<T> other);
	
	public T getReal();
	
	public T getImaginary();
	
	public Complex<T> getConjugate();
	
	public Complex<T> pow(Complex<T> pow);
	
	public Complex<T> cos();
	
	public Complex<T> sin();
	
	public Complex<T> pow(long pow);
	
	public Complex<T> pow(double pow);
	
	public Complex<T> log();
	
	public Complex<T> log(int base);
	
	public Complex<T> sqrt();
	
	public Complex<T> cbrt();
	
	public Complex<T> root(int k, double root);
	
	public Complex<T> root(double root);
	
	public T getArg();
	
	public T getModulus();
}
