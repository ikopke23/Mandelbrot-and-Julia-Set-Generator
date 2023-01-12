import java.lang.ArithmeticException;

/**
 * @author      Ian Kopke
 * @version     1.0
 * @since       2022, 2, February 22
 */
public class ComplexNumber {

	private double a;
	private double b;

	/**
	 * Creates a new ComplexNumber with both real and imaginary components
	 * @param a the real component of the complex number
	 * @param b the imaginary component of the complex number
	 */
	public ComplexNumber(double a, double b){
		this.a = a;
		this.b = b;
	}

	/**
	 * The "copy constructor"
	 * Creates a new ComplexNumber from an existing ComplexNumber
	 * @param c a ComplexNumber
	 */
	public ComplexNumber(ComplexNumber c){
		this.a = c.getReal();
		this.b = c.getImaginary();
	}
	/**
	*Default Constructor for ComplexNumber
	*Initializes a and b as 0
	*
	*/
	public ComplexNumber(){
		this.a = 0;
		this.b = 0;
	}
	/**
	 * An "accessor" method
	 * Returns the real component of this ComplexNumber
	 * @return a the private real component of this ComplexNumber
	 */
	public double getReal(){
		return this.a;
	}

	/**
	 * An "accessor" method
	 * Returns the imaginary component of this ComplexNumber
	 * @return b the private imaginary component of this ComplexNumber
	 */
	public double getImaginary(){
		return this.b;
	}

	/**
	 * toString method that returns a string of the complex number
	 *
	 * @return ret
	 */
	public String toString(){
		String ret = "";
		if((this.a == 0) && (this.b == 0)){
			ret = ret+"0.0";
			return ret;
		}else if(this.a == 0){
			ret = ret+""+this.b+"i";
			return ret;
		}else if(this.b == 0){
			ret = ret+""+this.a;
			return ret;
		}else if(this.b == 1){
			ret = ret+""+this.a+"+i";
			return ret;
		}else if(this.b == -1){
			ret = ret+""+this.a+"-i";
			return ret;
		}else if(this.b < -1){
			ret = ret+""+this.a+this.b+"i";
			return ret;
		}else{
			ret = ret+""+this.a+"+"+this.b+"i";
			return ret;
		}
	}//end toString()


	public String toStringR5(){
		String ret = "";
		if((this.a == 0) && (this.b == 0)){
			ret = ret+"0.0";
			return ret;
		}else if(this.a == 0){
			ret = ret+""+String.format("%.5f",this.b)+"i";
			return ret;
		}else if(this.b == 0){
			ret = ret+""+String.format("%.5f",this.a);
			return ret;
		}else if(this.b == 1){
			ret = ret+""+String.format("%.5f",this.b)+"+i";
			return ret;
		}else if(this.b == -1){
			ret = ret+""+String.format("%.5f",this.a)+"-i";
			return ret;
		}else if(this.b < -1){
			ret = ret+""+String.format("%.5f",this.a)+String.format("%.5f",this.b)+"i";
			return ret;
		}else{
			ret = ret+""+String.format("%.5f",this.a)+"+"+String.format("%.5f",this.b)+"i";
			return ret;
		}
	}//end toString()






	/**
	 * ComplexPrintln prints the complex number in the terminal with an line break
	 *
	 * @param c Complex number that will be printed
	 */
	public static void ComplexPrintln(ComplexNumber c){
		if(c.getReal() == 0){ //(a == c)
			System.out.println(c.getImaginary()+"i");// print {b}i
			return;
		}else if(c.getImaginary() == 0){//(b == 0)
			System.out.println(c.getReal());//print a
			return;
		}else if(c.getImaginary() < 0){//(b < 0)
			System.out.println(c.getReal()+""+c.getImaginary()+"i"); //print a-bi
			return;
		}else{
			System.out.println(c.getReal()+"+"+c.getImaginary()+"i"); //print a+bi
		}
	}//end ComplexPrintln

	/**
	 * ComplexPrintln prints the complex number in the terminal without an line break
	 *
	 * @param c  ComplexNumber that will be printed
	 */
	public static void ComplexPrint(ComplexNumber c){
		if(c.getReal() == 0){ //(a == c)
			System.out.print(c.getImaginary()+"i");// print {b}i
			return;
		}else if(c.getImaginary() == 0){//(b == 0)
			System.out.print(c.getReal());//print a
			return;
		}else if(c.getImaginary() < 0){//(b < 0)
			System.out.print(c.getReal()+""+c.getImaginary()+"i"); //print a-bi
			return;
		}else{
			System.out.print(c.getReal()+"+"+c.getImaginary()+"i"); //print a+bi
		}
	}//End ComplexPrint
	/**
	 * compareTo
	 * Returns a negative integer if the magnitude of this is less than than the magnitude of the parameter
	 * Returns 0 if the magnitude of the parameter is equal to the magnitude of this
	 * Returns a positive integer if the magnitude of this is greater than the magnitude of the parameter.
	 *
	 * @param  c Complex number that is being compared
	 * @return int depending on the comparison
	 */
	public int compareTo(ComplexNumber c){
		double mag1 = Math.sqrt(Math.pow(this.a,2)+Math.pow(this.b,2));
		double mag2 = c.magnitude();
		if(mag1 < mag2){
			return -1;
		}else if(mag1 == mag2){
			return 0;
		} else{
			return 1;
		}
	}//end compareTo

	public static void magPrintln(ComplexNumber c){
		System.out.println(c.magnitude());
  }//end magPrintln

	public static void magPrintlnFull(ComplexNumber c){
		System.out.print("The magnitude of ");
		ComplexNumber.ComplexPrint(c);
		System.out.println(" is = " + c.magnitude());
  }//end magPrintlnFull
	/**
	 *	magnitude finds the length of the complex number vector
	 *
	 * @return mag double
	 */
	public double magnitude(){
		double mag = Math.sqrt(Math.pow(this.a,2)+Math.pow(this.b,2));
		return mag;
	}//end magnitude
	/**
	 * conjugate inverts the imaginary part of complec Number
	 *
	 * @return new complex number
	 */
	private ComplexNumber conjugate(){
		return new ComplexNumber(this.a,(-1*this.b));
	}//end conjugate


// --------------Static Methods ---------------
	/**
	 * Add two complex numbers
	 *
	 * @param  c1  The first complex number
	 * @param  c2   The second complex number
	 * @return  New ComplexNumber
	 */
	public static ComplexNumber add(ComplexNumber c1, ComplexNumber c2){
		return new ComplexNumber(c1.getReal()+c2.getReal(),c1.getImaginary()+c2.getImaginary());
	}//end Add
	/**
	 * Subtracts a complex number from another complex number
	 *
	 * @param  c1  Complex number that is subtracted from
	 * @param  c2  complex number that is being subtracted
	 * @return  New ComplexNumber
	 */
	public static ComplexNumber subtract(ComplexNumber c1, ComplexNumber c2){
		return new ComplexNumber(c1.getReal()-c2.getReal(),c1.getImaginary()-c2.getImaginary());
	}//end Subtract
	/**
	 * Multiplies the complex numbers together
	 *
	 * @param  c1  The multiplicand
	 * @param  c2  the multiplier
	 * @return  New ComplexNumber
	 */
	public static ComplexNumber multiply(ComplexNumber c1, ComplexNumber c2){
		return new ComplexNumber(c1.getReal()*c2.getReal()-c1.getImaginary()*c2.getImaginary(),c1.getReal()*c2.getImaginary()+(c2.getReal()*c1.getImaginary()));
	}//end Subtract
	/**
	 * Divides the first complex number by the second complex number
	 * throws ArithmeticException if someone tries to divide by 0
	 *
	 * @param  c1   dividend
	 * @param  c2   divisor
	 * @return    New ComplexNumber
	 */
	public static ComplexNumber divide(ComplexNumber c1, ComplexNumber c2){
		if((c2.getReal() == 0) && (c2.getImaginary() == 0)){
			throw new ArithmeticException("Attempt to divide by zero");
		}
		ComplexNumber temp = c2.conjugate();
		double comDiv = Math.pow(c2.getReal(),2) + Math.pow(c2.getImaginary(),2); //comDiv = common divisor
		return new ComplexNumber((c1.getReal()*temp.getReal()-c1.getImaginary()*temp.getImaginary())/comDiv,(c1.getReal()*temp.getImaginary()+temp.getReal()*c1.getImaginary())/comDiv);
	}//end Subtract
	/**
	 * Squares the complex number inputted
	 *
	 * @param  c  The complex number that is squared
	 * @return  New ComplexNumber
	 */
	public static ComplexNumber square(ComplexNumber c){
		return ComplexNumber.multiply(c,c);
	}//end square

	//------------Instance methods-------------
	/**
	 * Instance version of Add, call with number to add the parameter
	 *
	 * @param  c2 the second complex number in the equation
	 * @return  new ComplexNumber
	 */
	public ComplexNumber add(ComplexNumber c2){
		return new ComplexNumber(this.a+c2.getReal(),this.b+c2.getImaginary());
	}//end Add
	/**
	 * Instance version of Subtract
	 *
	 * @param  c2  the Complex number that is being subtracted from the number it is called with
	 * @return new complex number
	 */
	public ComplexNumber subtract(ComplexNumber c2){
		return new ComplexNumber(this.a-c2.getReal(),this.b-c2.getImaginary());
	}//end Subtract
	/**
	 * Instance version of Multiply
	 *
	 * @param  c2  the number the caller number is multiplied by (the multiplier)
	 * @return  new Complex Number
	 */
	public ComplexNumber multiply(ComplexNumber c2){
		return new ComplexNumber(this.a*c2.getReal()-this.b*c2.getImaginary(),this.a*c2.getImaginary()+(c2.getReal()*(this.b)));
	}//end Subtract
	/**
	 * Divides a number by the divisor
	 * throws ArithmeticException if someone tries to divide by 0
	 *
	 * @param  c2 the divisor of the equation
	 * @return a new Complex Number
	 */
	public ComplexNumber divide(ComplexNumber c2){
		if((c2.getReal() == 0) && (c2.getImaginary() == 0)){
			throw new ArithmeticException("Attempt to divide by zero");
		}
		ComplexNumber temp = c2.conjugate();
		double comDiv = Math.pow(c2.getReal(),2) + Math.pow(c2.getImaginary(),2); //comDiv = common divisor
		return new ComplexNumber((this.a*temp.getReal()-this.b*temp.getImaginary())/comDiv,(this.a*temp.getImaginary()+temp.getReal()*this.b)/comDiv);
	}//end Subtract

	/**
	 * Multiplies a complex number by itself
	 *
	 * @return  New ComplexNumber
	 */

	public ComplexNumber square(){
		return new ComplexNumber(Math.pow(this.a,2)-Math.pow(this.b,2),(2*(this.a*this.b)));
	}//end square

	/**
	 * A tester method
	 * @param args command line
	 */
	public static void main(String[] args) {
		ComplexNumber c1 = new ComplexNumber(2, 3);
		ComplexNumber c2 = new ComplexNumber(3,2);
		ComplexNumber c3 = new ComplexNumber(1,-2);
		ComplexNumber c4 = new ComplexNumber(c1);


		System.out.print("Specific Constructor, Accessor. (c1:1-2i r:1 i:-2):");
		System.out.println("c1:"+c3+" r:"+c3.getReal()+" i"+c3.getImaginary());
		System.out.print("Copy Constructor, Accessor. (c2:1-2i r:1 i:-2):");
		System.out.println("c2:"+c4+" r:"+c4.getReal()+" i:"+c4.getImaginary());
		ComplexNumber.ComplexPrintln(ComplexNumber.add(c1,c2));
		System.out.println(c1.toString());
		System.out.println(c1.magnitude());
		// ComplexNumber.ComplexPrintln(c1.conjugate());
		// //-----------Basic Static method test
		System.out.println("Add 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(ComplexNumber.add(c1,c2));
		System.out.println("Subtract 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(ComplexNumber.subtract(c1,c2));
		System.out.println("Multiply 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(ComplexNumber.multiply(c1,c2));
		System.out.println("divide 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(ComplexNumber.divide(c1,c2));
		System.out.println("Square 2+3i ");
		ComplexNumber.ComplexPrintln(ComplexNumber.square(c1));
		//-----------Basic Non static method tests
		System.out.println("Add 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(c1.add(c2));
		System.out.println("Subtract 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(c1.subtract(c2));
		System.out.println("Multiply 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(c1.multiply(c2));
		System.out.println("divide 2+3i and 3+2i");
		ComplexNumber.ComplexPrintln(c1.divide(c2));
		System.out.println("Square 2+3i ");
		ComplexNumber.ComplexPrintln(c1.square());

		System.out.println(c2.toString());
		System.out.println(c2.magnitude());
		// ComplexNumber.ComplexPrint(c1.divide(ComplexNumber.ComplexNumber(0,0)));
		System.out.println(c1.compareTo(c4));
		System.out.println(c2.compareTo(c3));
		System.out.println(c4.compareTo(c1));

	}
}
