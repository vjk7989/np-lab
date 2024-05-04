import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Inet {
    public static void main(String[] args) {
        String startIpAddress = "192.168.1.1";
        String endIpAddress = "192.168.1.150";

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress iaddr = inetAddresses.nextElement();
                    if (iaddr instanceof Inet4Address
                            && isIpAddressInRange(iaddr.getHostAddress(), startIpAddress, endIpAddress)) {
                        System.out.println(networkInterface.toString() + " (ipv4) : " + iaddr.getHostAddress());
                    }
                }
            }
        } catch (SocketException s) {
            s.printStackTrace();
        }
    }

    private static boolean isIpAddressInRange(String ipAddress, String startIpAddress, String endIpAddress) {
        try {
            InetAddress start = InetAddress.getByName(startIpAddress);
            InetAddress end = InetAddress.getByName(endIpAddress);
            InetAddress current = InetAddress.getByName(ipAddress);

            long startIp = ipToLong(start);
            long endIp = ipToLong(end);
            long currentIp = ipToLong(current);

            return currentIp >= startIp && currentIp <= endIp;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static long ipToLong(InetAddress ipAddress) {
        byte[] octets = ipAddress.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }
}
