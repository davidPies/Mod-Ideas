package pack1;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
    public static void main(String[] args){
        frame1();
    }
    public void frame1(){
        JFrame myFrame = new JFrame("Sample Frame");
        myFrame.setSize(300,400);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myFrame.setVisible(true);
    }
}
