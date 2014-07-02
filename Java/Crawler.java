import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Crawler {

/**
* @param args
* @throws IOException 
*/

public static void main(String[] args) throws IOException {

URL url = new URL("http://deals.ebay.in/category/Mobilephones");

String content = getContent(url);
String matched = getReqContent(content);
String items[] = matched.split("</p></a>\\s+</div>");
String itemsRegex = "<div class=\"item\" id=\"(.*?)\"><div class=\".*?\"> <b></b> <a href=\"(.*?)\" target=\".*?\"><img src=\"(.*?)\" /></a> </div><div class=\".*?\">.*?</div> <p class=\".*?\">.*?</p><a href=\".*?\" class=\".*?\" data-title=\".*?\" target=\".*?\"><strong>(.*?)</strong><span>(.*?)</span><p>(.*?)";
Pattern pattern = Pattern.compile(itemsRegex);
Matcher matcher = null;

for(String item :items)
{
    matcher = pattern.matcher(item);
    if(matcher.find())
    {
        System.out.println("ItemId: " + matcher.group(1));
        System.out.println("ItemUrl: " + matcher.group(2));
        System.out.println("ItemImage: " + matcher.group(3));
        System.out.println("ItemName: " + matcher.group(4));
        System.out.println("ItemPrice: " + matcher.group(5));
        System.out.println("----------------------------------------");
    }
  }
}

public static String getContent(URL url) throws IOException
{
    String content = "";
    URLConnection urlc = url.openConnection();
    BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
    String line = br.readLine();
    while(line != null)
    {
      content = content + line;
      line = br.readLine();
    }
    return content;	
}

public static String getReqContent(String content)
{
    String matchedContent = "";
    String regex = "<div class=\"res-all su\">(.*?)<div id=\"pendingAspects\">";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(content);
    if(matcher.find())
    {
        matchedContent = matcher.group();
    }
    return matchedContent;
}
}
