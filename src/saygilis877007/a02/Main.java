package saygilis877007.a02;


import saygilis877007.Image;
import java.util.ArrayList;

/**
 * Created by home on 17.10.18.
 */
public class Main {
    static int width = 360;
    static int heigth = 240;
    static int circles = 10; //Anzahl der Kreise
    static double n = 10;   //Sampling der Kreise

    public static void main(String[]args){
        ArrayList<Circle> listCircles = listCircles(circles);
        Image image =new Image(width, heigth);
    }
}
