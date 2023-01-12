class test{
  public static void main(String[] args){
    ComplexNumber complex2_1 = new ComplexNumber(-2,-1.5);
    ComplexNumber complexZero = new ComplexNumber(0,0);
    for(double i = -1.5; i < 1.5; i += 0.5){
      ComplexNumber temp = new ComplexNumber(i,i);
      System.out.println(mandelTest(temp, 0, complexZero));
    }
  }//end main

  public static boolean mandelTest(ComplexNumber c, int count, ComplexNumber Zn1){
    System.out.println(c.toStringR5()+" " + count+ " "+ Zn1.toStringR5());
    if(Zn1.magnitude() > 2){
      return false;
    } else if(count > 5){
      return true;
    } else{
      ComplexNumber ret = Zn1.square();
      ret = ret.add(c);
      mandelTest(c, count+1, ret);
    }
    return false;
  }//end mandelTest



}//end class
