package smartcity.drone;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

@SuppressWarnings("unused")
public class Geolocator {

	public static String[] geoLocate(String location) throws Exception {
		String[] latLongs = getLatLongPositions(location);
		return latLongs;
	}

	// From the given address, the method gets the latitude and longitude
	public static String[] getLatLongPositions(String address) throws Exception {
		int responseCode = 0;
		// We get tha Google API geolocator with the given address
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		// We convert this into URL
		URL url = new URL(api);

		// Returns a URLConnection instance that represents a connection
		// to the remote object referred to by the URL.
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect(); // Opens a communications link to the resource referenced by thisURL
		responseCode = httpConnection.getResponseCode(); // Gets the status code from an HTTP response message

		if (responseCode == 200) { // IF 200 OK - IF 401 Unauthorized
			// Defines the API to obtain DOM Document instances from an XMLdocument.
			// Using this class, an application programmer can obtain a Document from XML.
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			// Building a DOM object - Document represents the entire HTML or XMLdocument
			Document document = builder.parse(httpConnection.getInputStream());
			// Create XPathFactory for creating XPath Object
			XPathFactory xPathFactory = XPathFactory.newInstance();
			// Create an XPath object from XPathFactory
			XPath xpath = xPathFactory.newXPath();
			// Compile the XPath expression
			XPathExpression expression = xpath.compile("/GeocodeResponse/status");
			// Evaluate the compiled XPath expression in the specified context
			// and return the result as the specified type.
			String status = (String) expression.evaluate(document, XPathConstants.STRING);

			if (status.equals("OK")) {
				expression = xpath.compile("//geometry/location/lat");
				String latitude = (String) expression.evaluate(document, XPathConstants.STRING);

				expression = xpath.compile("//geometry/location/lng");
				String longitude = (String) expression.evaluate(document, XPathConstants.STRING);

				return new String[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - reponse status : " + status);
			}

		}
		return null;
	}

}
