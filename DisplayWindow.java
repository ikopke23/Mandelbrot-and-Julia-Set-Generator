/**
 * Class DisplayWindow
 *
 * Used for translating pixels into a complex plane
 */

class DisplayWindow{
  private double leftX, bottomY;//The position of the DisplayWindow
  private double length, width;//The length along the x-axis and width along the y-axis of the DisplayWindow
  private double rMax, rMin;//The min and max values on the real axis of the Complex plane. Starts at -2 ≤ a ≤ 2
  private double iMax, iMin;//The min and max values on the imaginary axis of the Complex plane. Starts at-1.5 ≤ a ≤ 1.5
  private double zoomFactor;//Starts at 1. Increases/decreases according to “i”/“I” and “o”/“O” key presses.
  private ComplexNumber center;//Starts at 0+0i. Changes according to mouse clicks on each DisplayWindow

/**
 * Display Window Constructor
 *
 *
 * @param leftx  Double, the x coordinate of the bottom left point
 * @param bottomy Double, the y coordinate of the bottom left point
 * @param len  double, length of the display window
 * @param wid  double, the height of the display window
 */
  public DisplayWindow(double leftx, double bottomy, double len, double wid){
    this.leftX = leftx;
    this.bottomY = bottomy;
    this.length = len;
    this.width = wid;
    resetView();
  }//end constructor

/**
 * Used to set the window to the default values
 * real minimum -2, real maximum 2
 * imaginary minimum -1.5, imaginary 1.5
 *
 */
  public void resetView(){
    this.rMax = 2;
    this.rMin = -2;
    this.iMax = 1.5;
    this.iMin = -1.5;
    this.zoomFactor = 1;
    this.center = new ComplexNumber();
  }//end resetView

/**
 * Changes the center of the plane to a new complex coordinate,
 *
 * @param c  ComplexNumber that the plane will center around.
 */
  public void recenter(ComplexNumber c){
    this.rMin = c.getReal()-(this.rMax-this.center.getReal());
    this.rMax = c.getReal()+(this.rMax-this.center.getReal());
    this.iMin = c.getImaginary()-(this.iMax-this.center.getImaginary());
    this.iMax = c.getImaginary()+(this.iMax-this.center.getImaginary());
    this.center = c;
    ComplexNumber.ComplexPrintln(c);
    System.out.println("New rMax = "+ this.rMax);
    System.out.println("New rMin = "+ this.rMin);
    System.out.println("New iMax = "+ this.iMax);
    System.out.println("New iMin = "+ this.iMin);

  }
/**
 * allows access of the real Maximum
 *
 * @return returns the rMax
 */
  public double getRMax(){
    return this.rMax;
  }
/**
 * allows access of the real Minimum
 *
 * @return returns the rMin
 */
  public double getRMin(){
    return this.rMin;
  }
/**
 * allows access of the imaginary Maximum
 *
 * @return returns the iMax
 */
  public double getIMax(){
    return this.iMax;
  }
/**
 * allows access of the imaginary Minimum
 *
 * @return returns the iMin
 */
  public double getIMin(){
    return this.iMin;
  }

/**
 * Determines what each pixel should be within the complex plane of the x-axis
 *
 * @return returns the step size for each pixel
 */
  public double getStepSizeX(){
    return (this.rMax - this.rMin)/this.length;
  }//end getStepSizeX
/**
 * Determines what each pixel should be within the complex plane of the imaginary-axis
 *
 * @return returns the step size for each pixel
 */
  public double getStepSizeY(){
    return (this.iMax - this.iMin)/this.width;
  }//end getStepSizeY

/**
 * Takes in a real part of the complex number and converts it to an x coordinate
 *
 * @param  a double, the real part of a complex number
 * @return  returns a X value for the a value
 */
  public double mapX(double a){
    return map(a, this.rMin, this.rMax, this.leftX, this.leftX+this.length);
  }//end mapX

/**
 * Takes in the imaginary part of the complex number and converts it to an y coordinate
 *
 * @param  b double,  the imaginary part of a complex number
 * @return  returns a y value for the b value
 */
  public double mapY(double b){
    return map(b, this.iMin, this.iMax, this.bottomY, this.bottomY+this.width);
  }//end mapY

/**
 * Maps a x value to the imaginary plane
 *
 * @param  windowX  double, the x coordinate
 * @return  returns the real value of a imaginary number
 */
  public double mapA(double windowX){
      return map(windowX, this.leftX, this.leftX+this.length, this.rMin, this.rMax);
  }//end mapA

/**
 * Maps a y coordinate to another
 *
 * @param  windowY  double, takes in a y coordinate
 * @return  returns a imaginary part of a complex number
 */
  public double mapB(double windowY){
    return map(windowY, this.bottomY, this.bottomY+this.width, this.iMin, this.iMax);
  }//end mapB

/**
 * Returns a new double from a range of values that is the same in reference to the new range of values
 * @param  value   double, the value that is transfered
 * @param  oldMin  double, old range minimum
 * @param  oldMax  double, old range maximum
 * @param  newMin  double, new range minimum
 * @param  newMax  double, new range maximum
 * @return  double, the new value
 */
  private double map(double value, double oldMin, double oldMax, double newMin, double newMax){
    return ((value - oldMin)/(oldMax - oldMin)*(newMax - newMin)) + newMin;
  }//end map

/**
 * moves the range of values over 1/10th of the displaywindow to the right
 * and moves the center over 1/10th
 */
  public void rightShift(){
    double temp = this.rMin;
    this.rMin += (this.center.getReal()-this.rMin)/5;
    this.rMax += (this.rMax-this.center.getReal())/5;
    this.center = new ComplexNumber(this.center.getReal()+(this.center.getReal()-temp)/5,this.center.getImaginary());
  }//end rightShift
/**
 * moves the range of values over 1/10th of the displaywindow to the left
 * and moves the center over 1/10th
 */
  public void leftShift(){
    double temp = this.rMin;
    this.rMin -= (this.center.getReal()-this.rMin)/5;
    this.rMax -= (this.rMax-this.center.getReal())/5;
    this.center = new ComplexNumber(this.center.getReal()-(this.center.getReal()-temp)/5,this.center.getImaginary());
  }//end leftShift
/**
 * moves the range of values over 1/10th of the displaywindow up
 * and moves the center over 1/10th
 */
  public void upShift(){
    double temp = this.iMax;
    this.iMin += (this.center.getImaginary()-this.iMin)/5;
    this.iMax += (this.iMax-this.center.getImaginary())/5;
    this.center = new ComplexNumber(this.center.getReal(),this.center.getImaginary()+((temp-this.center.getImaginary())/5));
  }//end upShift
/**
 * moves the range of values over 1/10th of the displaywindow down
 * and moves the center over 1/10th
 */
  public void downShift(){
    double temp = this.iMin;
    this.iMin -= (this.center.getImaginary()-this.iMin)/5;
    this.iMax -= (this.iMax-this.center.getImaginary())/5;
    this.center = new ComplexNumber(this.center.getReal(),this.center.getImaginary()-((this.center.getImaginary()-temp)/5));
  }//end downShift

/**
 * Zoom in the range of values based around the
 * @param change  the change in ZoomFactor
 */
  public void zoomIn(double change){
    System.out.println("zoomFactor = " + this.zoomFactor);
    this.zoomFactor += change;
    this.rMax = ((this.rMax-this.center.getReal())/this.zoomFactor)+this.center.getReal();
    this.rMin = this.center.getReal()-((this.center.getReal()-this.rMin)/this.zoomFactor);
    this.iMax = ((this.iMax-this.center.getImaginary())/this.zoomFactor)+this.center.getImaginary();
    this.iMin = this.center.getImaginary()-((this.center.getImaginary()-this.iMin)/this.zoomFactor);
  }//end zoomIn
/**
 * Zoom out the range of values based around the ZoomFactor
 * @param change the change in ZoomFactor
 */
  public void zoomOut(double change){
    System.out.println("zoomFactor = " + this.zoomFactor);
    if((this.zoomFactor-change) < 1 ){
      this.zoomFactor += change;
    } else{
      this.zoomFactor -= change;
    }
    this.rMax = ((this.rMax-this.center.getReal())*this.zoomFactor)+this.center.getReal();
    this.rMin = this.center.getReal()-((this.center.getReal()-this.rMin)*this.zoomFactor);
    this.iMax = ((this.iMax-this.center.getImaginary())*this.zoomFactor)+this.center.getImaginary();
    this.iMin = this.center.getImaginary()-((this.center.getImaginary()-this.iMin)*this.zoomFactor);
  }//end zoomIn


/**
 * Prints the information of the displayWindow into the console
 */
  public void println(){
    System.out.println("leftX = "+ this.leftX + ", bottomY = " + this.bottomY + ", length = " + this.length + ", width = " + this.width);
    System.out.println("rMax = " + this.rMax + ", rMin = "+ this.rMin + ", iMax = "+this.iMax + ", iMin = " + this.iMin + ", zoomFactor = "+ this.zoomFactor + ", center = " + this.center);
  }//end println





/**
 * Demonstrates some uses of the displayWindow
 * @param args argument line
 */
  public static void main(String
  [] args){
    ComplexNumber c1 = new ComplexNumber(1.0,-1.0);
    ComplexNumber comp0= new ComplexNumber();

    DisplayWindow lefWin = new DisplayWindow(0,0,400,300);
    lefWin.println();
    System.out.println("stepSize1 = "+ lefWin.getStepSizeX());
    System.out.println(lefWin.mapX(c1.getReal()));
    System.out.println(lefWin.mapA(200));
    System.out.println(lefWin.mapX(comp0.getImaginary()));

    // leftWin.zoomIn(1.2);

    System.out.println("stepSize2 = "+ lefWin.getStepSizeX());
    lefWin.println();

  }//end main


}//end class
