package exercise_1.Structural;

// Proxy Pattern: Internet Access Control

// Component Interface
interface Internet
{
    void connectTo(String site); //
}

// Concrete Class
class RealInternet implements Internet
{

    @Override
    public void connectTo(String site) {
        System.out.println("connecting to "+site);
    }
}

// Proxy
class ProxyInternet implements Internet
{
    private RealInternet realInternet = new RealInternet();
    final static String[] blockedSites = {"abc.com","xyz.com"};

    @Override
    public void connectTo(String site) {
        for(String blocksite:blockedSites)
        {
            if(site.equals(blocksite))
            {
                System.out.println("Access Denied to "+ site);
                return;
            }
        }
        realInternet.connectTo(site);
    }
}

// Client Code
public class ProxyPattern {
    public static void main(String[] args) {
        Internet internet = new ProxyInternet();
        internet.connectTo("google.com"); // Output: connecting to google.com
        internet.connectTo("abc.com");    // Output: Access Denied to abc.com
    }
}

// Provides a Proxy Internet to control access to Real Internet.
// Blocks access to specified websites while allowing others.
// Client interacts with the Internet interface without knowing about the proxy.
// Adds an extra layer of functionality (access control) transparently.
