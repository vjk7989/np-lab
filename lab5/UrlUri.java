import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * UrlUri
 */
public class UrlUri {
    static URI uri;
    static URL url;

    public static void main(String[] args) throws URISyntaxException, MalformedURLException {
        uri = new URI("ftp://Quintus2003@root.com:21/user?name=udayan&password=xyz");
        url = uri.toURL();

        System.out.println(uri.getHost());
        System.out.println(uri.getPort());
        System.out.println(uri.getScheme());
        System.out.println(uri.getUserInfo());
        System.out.println(uri.getAuthority());
        System.out.println(uri.getQuery());
        System.out.println(uri.normalize());
    }

}