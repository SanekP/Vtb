package sanekp.vtb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.w3schools.webservices.TempConvertSoap;

@WebServlet("/convert")
public class ConverterServlet extends HttpServlet {
	private TempConvertSoap tempConvertSoap;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (tempConvertSoap == null) {
			System.out.println(tempConvertSoap);
			return;
		}
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		try (JsonGenerator jsonGenerator = Json.createGenerator(writer)) {
			String result;
			String value = request.getParameter("value");
			String direction = request.getParameter("direction");
			try {
				Method method = TempConvertSoap.class.getMethod(direction,
						String.class);
				result = (String) method.invoke(tempConvertSoap, value);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				result = e.toString();
			}
			jsonGenerator.writeStartObject().write("result", result).writeEnd();
		}
	}

	@Inject
	public void setTempConvertSoap(TempConvertSoap tempConvertSoap) {
		this.tempConvertSoap = tempConvertSoap;
	}
}
