package programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nilesh
 */
public class PageDownloader {

	/**
	 * @param args
	 *            the command line arguments
	 * @throws java.net.MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException, IOException {
		// TODO code application logic here
		URL url = new URL("http://rockinst.org");

		// Get the input stream through URL Connection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.addRequestProperty("User-Agent", "Mozilla/4.76");
		InputStream is = con.getInputStream();
		// Once you have the Input Stream, it's just plain old Java IO stuff.
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		// read each line and write to System.out
		Set<String> ar = new HashSet<String>();
		while ((line = br.readLine()) != null) {
			if (line.contains("a href=")) {
				line = line.trim();
				int start = line.indexOf("<a href=\"");
				int end= line.indexOf("\">");
				if(end > 0 && start < end) {
					//System.out.println(line);
					System.out.println(line.substring(start+9, end));
				}
				//System.out.println(line.trim().substring(, ));
				//ar.add(line.trim().substring(line.indexOf("=")+1, line.lastIndexOf("\"")-1));
			}
		}
		System.out.println(ar.toString());
	}
}