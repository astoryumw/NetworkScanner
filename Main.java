import java.util.Arrays;

// private IP addresses: 10.x.x.x, 192.168.x.x, 172.16-31.x.x
class Main {
    public static void main(String[] args) {
        System.out.println("Hello world.");
        try {
            Nmap n = new Nmap();
            String[] myString = n.getIfconfig();
            String ip = n.findIP(myString);
            System.out.println(ip);

            String[] myIP = ip.split("\\.");
            System.out.println(Arrays.toString(myIP));
            n.getNetworkIPs(myIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}