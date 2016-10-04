package org.usfirst.frc832.TShirtCannon;
import org.usfirst.frc832.TShirtCannon.commands.*;
import org.usfirst.frc832.TShirtCannon.controls.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    // driverPad assignments
    public final Joystick driverPad; // Logitech F310/F510/F710 ONLY
    // Need to do full mapping of all possible buttons
    
    // controlStick assignments
    public final Joystick controlStick; // Logitech Extreme 3D ONLY
    //public final POVHat hatSwitch; // POV Hat on stick face
    public final JoystickButton stickTrigger; // Trigger on back of stick, map to 1
    public final JoystickButton thumbButton; // Thumb Button on side of stick, map to 2
    public final JoystickButton button3; // Button 3 on bottom left of stick face, map to 3
    public final JoystickButton button4; // Button 4 on bottom right of stick face, map to 4
    public final JoystickAnalogButton POVup;
    public final JoystickAnalogButton POVdown;
    public final JoystickAnalogButton POVleft;
    public final JoystickAnalogButton POVright;
    
    /* Block of currently unused buttons; remove from block as needed
    public JoystickButton button5; // Button 5 on top left of stick face, map to 5
    public JoystickButton button6; // Button 6 on top right of stick face, map to 6
    public JoystickButton button7; // Button 7 on top left of base side, map to 7
    public JoystickButton button8; // Button 8 on top right of base side, map to 8
    public JoystickButton button9; // Button 9 on middle left of base side, map to 9
    public JoystickButton button10; // Button 10 on middle right of base side, map to 10
    public JoystickButton button11; // Button 11 on bottom left of base side, map to 11
    public JoystickButton button12; // Button 12 on bottom right of base side, map to 12
    // The POV Hat, twist, and throttle are all mapped to axis (need to get axis numbers for there)
    */
    
    public OI() {
        driverPad = new Joystick(1); // Logitech F310/F510/F710 ONLY
        // Do we need any controls on the driverPad?
        // Consult team
                
        controlStick = new Joystick(2); // Logitech Extreme 3D Pro ONLY
        // Button Mappings for Control Stick
        
        POVup = new JoystickAnalogButton(controlStick, 5, 1.0); // POV Hat Up
        POVdown = new JoystickAnalogButton(controlStick, 5, -1.0); // POV Hat Down
        POVleft = new JoystickAnalogButton(controlStick, 6, 1.0); // POV Hat Left
        POVright = new JoystickAnalogButton(controlStick, 6, -1.0); // POV Hat Right
        stickTrigger = new JoystickButton(controlStick, 1); // Trigger on back of stick
        thumbButton = new JoystickButton(controlStick, 2); // Thumb Button on side of stick
        button3 = new JoystickButton(controlStick, 3); // Button 3 on bottom left of stick face
        button4 = new JoystickButton(controlStick, 4); // Button 4 on bottom right of stick face
        
        
        
        /* Block of currently unused buttons; remove from block as needed
        button5 = new JoystickButton(controlStick, 5); // Button 5 on top left of stick face
        button6 = new JoystickButton(controlStick, 6); // Button 6 on top right of stick face
        button7 = new JoystickButton(controlStick, 7); // Button 7 on top left of base side
        button8 = new JoystickButton(controlStick, 8); // Button 8 on top right of base side
        button9 = new JoystickButton(controlStick, 9); // Button 9 on middle left of base side 
        button10 = new JoystickButton(controlStick, 10); // 
        button11 = new JoystickButton(controlStick, 11); // 
        button12 = new JoystickButton(controlStick, 12); // 
        hatSwitch = new POVHat(controlStick);
        */
        
        
        // Command mappings for Control Stick
        thumbButton.whileHeld(new cannonControl()); // Run warning light while button held
        button3.whenReleased(new fillToPSI()); // Run compressors to setpoint once button pressed
        POVup.whileHeld(new angleUp()); // Angle the cannon up while button held
        POVdown.whileHeld(new angleDown()); // Angle the cannon down while button held
    }
    
    public Joystick getdriverPad() {
        return driverPad;
    }
    public Joystick getcontrolStick() {
        return controlStick;
    }
}