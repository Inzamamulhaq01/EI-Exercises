package exercise_1.Creational;

// Singleton Pattern: Mac Resolver

class MacResolver {
    private static MacResolver instance;
    private String macAddress;

    private MacResolver() {}

    public static MacResolver getInstance() {
        if (instance == null) {
            instance = new MacResolver();
        }
        return instance;
    }

    public String getAddress() {
        return macAddress;
    }

    public void setAddress(String address) {
        this.macAddress = address;
    }

}

//  Client Code
class SingletonPattern
{
    public static void main(String[] args) {
        MacResolver address1 = MacResolver.getInstance();
        address1.setAddress("00:1A:2B:3C:4D:5E");

        MacResolver address2 = MacResolver.getInstance();
        System.out.println(address2.getAddress()); // Output: 00:1A:2B:3C:4D:5E

        System.out.println(address1 == address2);  // Output: true

    }
}


// created a simple mac resolver that ensures only one instance is used throughout the application.
// The MacResolver class ensures that only one instance of the mac resolver is created.
// This is achieved through a private constructor and a  method getInstance that returns the single instance.




