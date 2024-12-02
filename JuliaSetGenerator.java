/**
  JuliaSetGenerator.java
  Milestone 1 demo solution with DisplayWindow class

  Requires prior implementation of:
    -ComplexNumber.java constructors, accessors
    -DisplayWindow.java constructor, map, mapX, mapY

*/

import java.awt.Color;

public class JuliaSetGenerator{

  public static void main(String args[]){
    int x = 800;
    int y = 350;
    //for(int i = 0; i < args.length; i++){
      //  System.out.println(args[i]);

    //}
    if(args.length > 0){
      try {
        x = Integer.parseInt(args[0]);
        y = Integer.parseInt(args[1]);
      }
      catch (NumberFormatException e) {
        x = 800;
        y = 350;
      } 
    }
    StdDraw.setCanvasSize(x,y);
    StdDraw.setXscale(0,x);
    StdDraw.setYscale(0,y);
    StdDraw.enableDoubleBuffering();
    DisplayWindow leftWin= new DisplayWindow(0,50,x/2,y-100);
    DisplayWindow rightWin = new DisplayWindow(x/2,50,x/2,y-100);
    boolean julia = false;
    ComplexNumber JC = new ComplexNumber();

    leftWin.println();
    drawMandelbrot(leftWin);
    // rightWin.println();

    while(true){
        StdDraw.setPenColor(StdDraw.BLACK);
        // StdDraw.line(200,0,200,300);

        StdDraw.setPenColor(StdDraw.BLACK);
        if((StdDraw.mouseX() < 400) && (StdDraw.mouseY() > 50)){
          ComplexNumber lP = new ComplexNumber(leftWin.mapA(StdDraw.mouseX()), leftWin.mapB(StdDraw.mouseY()));
          // StdDraw.text(StdDraw.mouseX()+50, StdDraw.mouseY()-50, mP.toStringR5());
          // System.out.println(lP.toString());
          if(StdDraw.mousePressed()){
            System.out.println("LEFT RECENTER");
            // leftWin.println();
            leftWin.recenter(lP);
            // leftWin.println();
            JC = lP; // used as a save for the complex number clicked on
            julia = true; //so the code knows when to initially draw the Julia set
            drawMandelbrot(leftWin);
          }
        } else if((StdDraw.mouseX() > 400) && (StdDraw.mouseY() > 50)){
          ComplexNumber rP = new ComplexNumber(rightWin.mapA(StdDraw.mouseX()), rightWin.mapB(StdDraw.mouseY()));

          if(StdDraw.mousePressed()){
            System.out.println("RIGHT RECENTER");
            rightWin.recenter(rP);
            drawJulia(rightWin, JC);
          }
        }//end click else if statement



      if(StdDraw.hasNextKeyTyped() == true){
        if(StdDraw.isKeyPressed(83)){ // 83 == S
          System.out.println("Saved");
          StdDraw.save("mandelbrotSave.png");
        }
        //-----------right Window---------
        // for O;  79 = O
        if(StdDraw.isKeyPressed(79) && StdDraw.isKeyPressed(16)){
          System.out.println("O is pressed (o + SHIFT) ");
          rightWin.zoomOut(0.1);
          // rightWin.zoomOut();
          // StdDraw.clear();
          drawJulia(rightWin, JC);
        } else if(StdDraw.isKeyPressed(73) && StdDraw.isKeyPressed(16)){
          // for I 73 = I
          System.out.println("I is pressed (i + shift)");
          rightWin.zoomIn(0.1);
          // rightWin.zoomIn();
          drawJulia(rightWin, JC);
        } else if(StdDraw.isKeyPressed(82) && StdDraw.isKeyPressed(16)){
          // for R 82 = R
          System.out.println("R is pressed (r + SHIFT) ");
          rightWin.resetView();
          drawJulia(rightWin, JC);
          //----------Arrow Key Movement-----------
        }else if(StdDraw.isKeyPressed(37) && StdDraw.isKeyPressed(16)){
          //Left Arrow key
          System.out.println("Left Arrow and Shift are pressed");
          rightWin.leftShift();
          drawJulia(rightWin, JC);
        } else if(StdDraw.isKeyPressed(38) && StdDraw.isKeyPressed(16)){
          //Up Arrow key
          System.out.println("Up Arrow and Shift are  pressed");
          rightWin.upShift();
          drawJulia(rightWin, JC);
        } else if(StdDraw.isKeyPressed(39) && StdDraw.isKeyPressed(16)){
          //Right Arrow key
          System.out.println("Right Arrow and Shift are pressed");
          rightWin.rightShift();
          drawJulia(rightWin, JC);
        } else if(StdDraw.isKeyPressed(40) && StdDraw.isKeyPressed(16)){
          //Down Arrow key
          System.out.println("Down Arrow and Shift are pressed");
          rightWin.downShift();
          drawJulia(rightWin, JC);
          // ------------Left Window stuff -------------
        } else if(StdDraw.isKeyPressed(79)){
          //for O;  79 = O
          leftWin.zoomOut(0.1);
          // leftWin.zoomOut();
          System.out.println("o is pressed ");
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(73)){
          // for I 73  I
          System.out.println("i is pressed ");
          leftWin.zoomIn(0.1);
          // leftWin.zoomIn();
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(82)){
          // for R 82 = R
          System.out.println("r is pressed ");
          leftWin.resetView();
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(37)){
          //Arrow Key Movement
          //Left Arrow key          System.out.println("Left Arrow is pressed");
          leftWin.leftShift();
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(38)){
          //Up Arrow key
          System.out.println("Up Arrow is pressed");
          leftWin.upShift();
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(39)){
          //Right Arrow key
          System.out.println("Right Arrow is pressed");
          leftWin.rightShift();
          drawMandelbrot(leftWin);
        } else if(StdDraw.isKeyPressed(40)){
          //Down Arrow key
          System.out.println("Down Arrow is pressed");
          leftWin.downShift();
          drawMandelbrot(leftWin);
        }//end if/else
      }//end hasKeyTyped if statement



        if(julia == true){
          drawJulia(rightWin, JC);
        }
        //------ displaying the coordinates oin the bottom of the screen
        StdDraw.setPenColor(StdDraw.BLACK);
        if((StdDraw.mouseX() < 400) && (StdDraw.mouseY() > 45) ){
          ComplexNumber lP = new ComplexNumber(leftWin.mapA(StdDraw.mouseX()), leftWin.mapB(StdDraw.mouseY()));
          StdDraw.text(200,25,lP.toStringR5());
        } else if(StdDraw.mouseY() > 45){
          ComplexNumber rP = new ComplexNumber(rightWin.mapA(StdDraw.mouseX()), rightWin.mapB(StdDraw.mouseY()));
          StdDraw.text(600,25,rP.toStringR5());
        }



      StdDraw.show();
      StdDraw.setPenColor(StdDraw.WHITE);
      StdDraw.filledRectangle(0,0,800,45);
      // StdDraw.clear();
        // break;
    }//infinite drawing loop

  }//main


  public static void drawMandelbrot(DisplayWindow win){
    ComplexNumber complexZero = new ComplexNumber(0,0);
    for(double i = win.getRMin(); i <= win.getRMax(); i+= win.getStepSizeX()){
      for(double k = win.getIMin(); k <= win.getIMax(); k+= win.getStepSizeY()){
        // ComplexNumber c = new ComplexNumber(win.mapA(i),win.mapB(k));
        ComplexNumber c = new ComplexNumber(i,k);
        // System.out.println(mandelRec(c, 0,  complexZero));
        int count = mandelRec(c, 0,  complexZero);
        if(count  < 0){
          StdDraw.setPenColor(StdDraw.BLACK);
        } else{
          // StdDraw.setPenColor(count, 100,100);
          StdDraw.setPenColor(Color.getHSBColor((float)(count+220.0f)/360.0f,1.0f,1.0f));
          // System.out.println(count);
        }
        StdDraw.filledCircle(win.mapX(i),win.mapY(k),1);
      }
    }//
    StdDraw.show();
  }//end drawJulia




  public static void drawJulia(DisplayWindow rightWin, ComplexNumber lP){

    ComplexNumber complexZero = new ComplexNumber(0,0);
    for(double i = rightWin.getRMin(); i <= rightWin.getRMax(); i+= rightWin.getStepSizeX()){
      for(double k = rightWin.getIMin(); k <= rightWin.getIMax(); k+= rightWin.getStepSizeY()){
        ComplexNumber c = new ComplexNumber(i,k);
        // System.out.println(mandelRec(c, 0,  complexZero));

        int count = mandelRec(lP, 0,  c);
        if(count  < 0){
          StdDraw.setPenColor(StdDraw.BLACK);
        } else{
          // StdDraw.setPenColor(Color.getHSBColor((float)(count+220.0f)/360.0f,1.0f,1.0f));
          // StdDraw.setPenColor(Color.getHSBColor(230.0f,(float)(count/240.0f)-0.2f,(float)(count/240.0f)+0.15f));
          StdDraw.setPenColor(Color.getHSBColor(205.0f/360.0f,1.0f,(float)(count/240.0f)+0.15f));
        }
        StdDraw.filledCircle(rightWin.mapX(i),rightWin.mapY(k),1);
      }
    }
    StdDraw.show();
  }//end drawJulia


  public static int mandelRec(ComplexNumber c, int count, ComplexNumber Zn1){
    // System.out.println(c.toStringR5()+" " + count+ " "+ Zn1.toStringR5());
    ComplexNumber ret = Zn1.square().add(c);
    if(ret.magnitude() > 2){
      return count;
    } else if(count > 360){
      return -1;
    } else{
      return mandelRec(c, count+1, ret);
    }
    // return false;
  }//end mandelRec

}//JuliaSetGenerator class
