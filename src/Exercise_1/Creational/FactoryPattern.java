package Exercise_1.Creational;

// Factory Pattern: Food Factory

// Component Interface
interface Food
{
    void prepare();
}


// Concrete Class
class Pizza implements Food
{
    @Override
    public void prepare()
    {
        System.out.println("Preparing Pizza...");
    }
}

class Burger implements Food
{
    @Override
    public void prepare()
    {
        System.out.println("Preparing Burger...");
    }
}


// Factory
class FoodFactory
{
    public static Food getFood(String order)
    {
        Food food = null;

        switch (order)
        {
            case "burger":
                food =  new Burger();
                break;
            case "pizza":
                food = new Pizza();
                break;
            default:
                System.out.println("Can't Serve ...");

        }
        return food;

    }
}

// Client Code
public class FactoryPattern {
    public static void main(String[] args) {

        Food burger = FoodFactory.getFood("burger");
        if(burger != null)
            burger.prepare(); // Output: Preparing Burger...


        Food pizza = FoodFactory.getFood("pizza");
        if(pizza != null)
            pizza.prepare();  // Output: Preparing Pizza...
    }
}


// created a factory that creates different types of food based on user input.
// The FoodFactory class is responsible for creating instances of Food products (Burger and Pizza).
// This pattern encapsulates the creation logic, allowing the client code to request objects without knowing the exact class of the product.
