package exercise_1.Behavioural;

// Command Pattern: Smart AC Control

// Component Interface
interface Command
{
    void execute();
}

// Concrete Components
class AirConditioner
{
    void on()
    {
        System.out.println("AC is ON");
    }
    void off()
    {
        System.out.println("AC is OFF");
    }
}

// Concrete Classes
class AcOnCommand implements Command
{
    AirConditioner ac;

    public AcOnCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.on();
    }
}

class AcOffCommand implements Command
{
    AirConditioner ac;

    public AcOffCommand(AirConditioner ac) {
        this.ac = ac;
    }

    @Override
    public void execute() {
        ac.off();
    }
}

// Invoker class
class RemoteControl
{
    Command command;
    void setCommand(Command c)
    {
        this.command = c;
    }

    void pressButton()
    {
        command.execute();
    }
}

// Client Code
public class CommandPattern {
    public static void main(String[] args) {
        AirConditioner ac = new AirConditioner();

        AcOnCommand acOn = new AcOnCommand(ac);
        AcOffCommand acOff = new AcOffCommand(ac);

        RemoteControl control = new RemoteControl();

        control.setCommand(acOn);
        control.pressButton(); // Output: AC is ON

        control.setCommand(acOff);
        control.pressButton(); // Output: AC is OFF
    }
}

// Encapsulates AC actions (ON/OFF) as command objects.
// RemoteControl acts as the invoker, executing commands without knowing implementation details.
// AirConditioner is the receiver that performs the actual operation.
// Supports flexible, decoupled control of smart home devices like ACs.
