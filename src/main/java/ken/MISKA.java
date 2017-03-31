package ken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

import java.net.*;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by admin on 31.03.2017.
 */
public class MISKA {


    public static void download(URI link, HashSet<URI> visited) throws IOException {
        if (visited.contains(link) || visited.size() >= 100)
            return;
        visited.add(link);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(link);

        try (CloseableHttpResponse resp = client.execute(get)) {
            String html = EntityUtils.toString(resp.getEntity());
            Pattern p = Pattern.compile("href\\s*=\\s*([^\\s>]+|\"[^\"]*\"|'[^']*')");
            Matcher m = p.matcher(html);
            while (m.find()) {
                String href=m.group(1);
                if (href.startsWith("\"")) {
                    href = href.substring(1, href.length() - 1);
                }
                URI child = link.resolve(href.trim());
                download(child,visited);
                System.out.println(child);

            }
        }
        client.close();
    }
    public static void main(String args[]) throws IOException,  URISyntaxException {
HashSet<URI> visited = new HashSet<>();
        MISKA.download(new URI("http://lib.ru/"),visited);
    }
}