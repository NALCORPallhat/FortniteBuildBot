/*
MIT License

Copyright (c) 2018 Samuel Tulach

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/


package samtulach;
import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class BuildingBot implements NativeKeyListener {
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                    GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                    e1.printStackTrace();
            }
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_F) {
            System.out.println(">> BOT FUNCTION RUN <<");
            botrun();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        try {
            GlobalScreen.addNativeKeyListener(new BuildingBot());
        } catch (Exception error){
            // restart
            System.out.println(">> ERROR <<");
            GlobalScreen.addNativeKeyListener(new BuildingBot());
        }
        GlobalScreen.addNativeKeyListener(new BuildingBot());
    }
    
    public void botrun(){
        try {
            Robot robot = new Robot();
            
            robot.keyPress(KeyEvent.VK_Q);
            robot.keyRelease(KeyEvent.VK_Q);
            Thread.sleep(10);
            robot.keyPress(KeyEvent.VK_F1);
            robot.keyRelease(KeyEvent.VK_F1);
            
            Thread.sleep(10);
            movewithsteps(0,10,100);
            Thread.sleep(10);
            //movewithsteps(0,-10,100);
            Thread.sleep(10);
            
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            
            //AGAIN
            Thread.sleep(10);
            movewithsteps(0,-10,150);
            Thread.sleep(10);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(10);
            movewithsteps(10,0,65);
            
        } catch (AWTException ex) {
            System.out.println("ERROR: " + ex.toString());
        } catch (InterruptedException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }
    
    public void movewithsteps(int x, int y, int steps) throws AWTException, InterruptedException {
        Robot robot = new Robot();
        for (int i = 0;i<steps;i++){
            robot.mouseMove(MouseInfo.getPointerInfo().getLocation().x + x,MouseInfo.getPointerInfo().getLocation().y + y);
            Thread.sleep(1);
        }
    }
}