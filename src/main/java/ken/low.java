package ken;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.regex.Pattern;

/**
 * Created by admin on 31.03.2017.
 */
public class low {
    public static void download(URI link) throws MalformedURLException {
        URL url = link.toURL();
        String html = "";
        Pattern p = Pattern.compile("href\\s*=\\([^ >]+|\"[^\"]*\"('[^']*')");
        String href = "";
        URI child = link.resolve(href);
        System.out.println(child);
    }
}
