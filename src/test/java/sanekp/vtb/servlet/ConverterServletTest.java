package sanekp.vtb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.w3schools.webservices.TempConvertSoap;

public class ConverterServletTest extends EasyMockSupport {
	private ConverterServlet converterServlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private StringWriter stringWriter;
	private TempConvertSoap tempConvertSoap;

	@Before
	public void setUp() throws IOException {
		tempConvertSoap = createMock(TempConvertSoap.class);
		converterServlet = new ConverterServlet();
		converterServlet.setTempConvertSoap(tempConvertSoap);
		request = createMock(HttpServletRequest.class);
		response = createMock(HttpServletResponse.class);
		response.setContentType("application/json");
		stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		EasyMock.expect(response.getWriter()).andReturn(printWriter);
	}

	@Test
	public void testFahrenheitToCelsius() throws ServletException, IOException {
		String value = "Sample value";
		String result = "Another value";
		EasyMock.expect(request.getParameter("value")).andReturn(value)
				.anyTimes();
		EasyMock.expect(request.getParameter("direction"))
				.andReturn("fahrenheitToCelsius").anyTimes();
		EasyMock.expect(tempConvertSoap.fahrenheitToCelsius(value)).andReturn(
				result);
		replayAll();
		converterServlet.doPost(request, response);
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("result", result).build();
		Assert.assertThat(stringWriter.toString(),
				CoreMatchers.equalTo(jsonObject.toString()));
	}

	@Test
	public void testCelsiusToFahrenheit() throws ServletException, IOException {
		String value = "Sample value";
		String result = "Another value";
		EasyMock.expect(request.getParameter("value")).andReturn(value)
				.anyTimes();
		EasyMock.expect(request.getParameter("direction"))
				.andReturn("celsiusToFahrenheit").anyTimes();
		EasyMock.expect(tempConvertSoap.celsiusToFahrenheit(value)).andReturn(
				result);
		replayAll();
		converterServlet.doPost(request, response);
		JsonObject jsonObject = Json.createObjectBuilder()
				.add("result", result).build();
		Assert.assertThat(stringWriter.toString(),
				CoreMatchers.equalTo(jsonObject.toString()));
	}

	@After
	public void tearDown() {
		verifyAll();
	}
}
