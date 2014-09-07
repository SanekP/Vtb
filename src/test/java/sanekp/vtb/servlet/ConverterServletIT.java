package sanekp.vtb.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.json.Json;
import javax.json.JsonReader;

import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.hamcrest.number.IsCloseTo;
import org.junit.Assert;
import org.junit.Test;

public class ConverterServletIT {
	static final URI SERVLET_URI = URI
			.create("http://localhost:8080/Vtb/convert");

	private double convert(String value, String direction) throws IOException {
		List<NameValuePair> form = Form.form().add("value", value)
				.add("direction", direction).build();
		try (JsonReader jsonReader = Json.createReader(Request
				.Post(SERVLET_URI).bodyForm(form).execute().returnContent()
				.asStream())) {
			return Double.parseDouble(jsonReader.readObject().getString(
					"result"));
		}
	}

	@Test
	public void testCelsiusToFahrenheit() throws IOException {
		Assert.assertThat(convert("42", "celsiusToFahrenheit"),
				IsCloseTo.closeTo(107.6, .1));
	}

	@Test
	public void testFahrenheitToCelsius() throws IOException {
		Assert.assertThat(convert("100500", "fahrenheitToCelsius"),
				IsCloseTo.closeTo(55815.6, .1));
	}
}
