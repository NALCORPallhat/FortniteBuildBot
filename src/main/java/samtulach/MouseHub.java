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

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Handles functionality relating to the mouse.
 *
 * @author SamuelTulach
 */
public class MouseHub implements NativeKeyListener {

    private final Robot robot;

    public MouseHub(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        System.out.println("Detected key press: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

        if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
            try {
                GlobalScreen.unregisterNativeHook();
            } catch (NativeHookException e1) {
                e1.printStackTrace();
            }
        }

        if (e.getKeyCode() == NativeKeyEvent.VC_F) {
            System.out.println("Automating building");
            if (!tryAutomateBuild()) {
                System.out.println("Failed to automate building :(");
            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Detected key released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Detected key typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    /**
     * Clicks the left mouse down.
     */
    private void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    /**
     * Automates building.
     *
     * @return If automating the building fails.
     */
    private boolean tryAutomateBuild() {
        try {
            automateBuild();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Automates building.
     *
     * @throws Exception If automating the building fails.
     */
    public void automateBuild() throws Exception {
        robot.keyPress(KeyEvent.VK_Q);
        robot.keyRelease(KeyEvent.VK_Q);
        Thread.sleep(10);

        robot.keyPress(KeyEvent.VK_F1);
        robot.keyRelease(KeyEvent.VK_F1);
        Thread.sleep(10);

        /* this can probably be broken down in to quite a bit less code */
        moveInSteps(0, 10, 100);
        Thread.sleep(10);
        Thread.sleep(10);
        leftClick();

        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);

        Thread.sleep(10);
        moveInSteps(0, -10, 150);
        Thread.sleep(10);
        leftClick();

        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);
        leftClick();

        Thread.sleep(10);
        moveInSteps(10, 0, 65);
    }

    /**
     * Moves the mouse in steps.
     *
     * @param stepX The amount to move in the x coordinate every step.
     * @param stepY The amount to move in the y coordinate every step.
     * @param steps The number of steps to perform total.
     * @throws Exception If moving the mouse fails.
     */
    public void moveInSteps(int stepX, int stepY, int steps) throws Exception {
        Robot robot = new Robot();
        for (int i = 0; i < steps; i++) {
            Point loc = MouseInfo.getPointerInfo().getLocation();
            robot.mouseMove(loc.x + stepX, loc.y + stepY);
            Thread.sleep(1);
        }
    }
}