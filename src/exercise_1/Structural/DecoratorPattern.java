package exercise_1.Structural;

// Decorator Pattern: Coffee Maker

// Component Interface

interface Coffee
{
    String getDesc();
    int getCost();
}

// Concrete Class
class ColdCoffee implements Coffee{
    @Override
    public String getDesc() {
        return "Cold Coffee";
    }

    @Override
    public int getCost() {
        return 10;
    }
}

// Decorator
class CoffeeDecorator implements Coffee{
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String getDesc() {
        return coffee.getDesc();
    }

    @Override
    public int getCost() {
        return coffee.getCost();
    }
}

// Concrete Decorators
class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDesc()
    {
        return coffee.getDesc() +", Milk";
    }
    public int getCost()
    {
        return coffee.getCost() + 3;
    }
}

class SugarDecorator extends CoffeeDecorator
{

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDesc()
    {
        return coffee.getDesc() +", Sugar";
    }
    public int getCost()
    {
        return coffee.getCost() + 2;
    }
}

// Client Code
public class DecoratorPattern {

    public static void main(String[] args) {

        Coffee coffee = new ColdCoffee();
        System.out.println(coffee.getDesc()+" costs $"+coffee.getCost());  // Output: Cold Coffee costs $10

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDesc()+" costs $"+coffee.getCost());  // Output: Cold Coffee, Milk costs $13

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDesc()+" costs $"+coffee.getCost());  // Output: Cold Coffee, Milk, Sugar costs $15
    }
}


// created a decorator that adds additional behavior to a coffee-making process.
// The Coffee interface defines the basic methods getCost and getDescription.
// The ColdCoffee class implements the Coffee interface as a cold coffee without any additives.
// The CoffeeDecorator class implements the Coffee interface and is used as a base class for concrete decorators.
// The MilkDecorator and SugarDecorator classes extend the CoffeeDecorator class to add additional behavior to the getCost and getDescription methods.