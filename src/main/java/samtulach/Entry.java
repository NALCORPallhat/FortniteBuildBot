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

import java.awt.*;

/**
 * @author Demmonic
 */
public class Entry {

    private Entry() { }

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("Failed to register native hook!");
            e.printStackTrace();
            System.exit(1);
        }

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            System.err.println("Failed to create robot!");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            GlobalScreen.addNativeKeyListener(new MouseHub(robot));
        } catch (Exception e) {
            System.out.println(">> ERROR <<");
            GlobalScreen.addNativeKeyListener(new MouseHub(robot));
        }

        GlobalScreen.addNativeKeyListener(new MouseHub(robot));
    }

}
