package sanekp.vtb;

import org.hamcrest.number.IsCloseTo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.w3schools.webservices.TempConvert;
import com.w3schools.webservices.TempConvertSoap;

public class TempConvertIT {
	private TempConvertSoap tempConvertSoap;

	@Before
	public void setUp() {
		TempConvert tempConvert = new TempConvert();
		tempConvertSoap = tempConvert.getTempConvertSoap();
	}

	@Test
	public void testCelsiusToFahrenheit() {
		String result = tempConvertSoap.celsiusToFahrenheit("42");
		double degrees = Double.parseDouble(result);
		Assert.assertThat(degrees, IsCloseTo.closeTo(107.6, .1));
	}

	@Test
	public void testFahrenheitToCelsius() {
		String result = tempConvertSoap.fahrenheitToCelsius("100500");
		double degrees = Double.parseDouble(result);
		Assert.assertThat(degrees, IsCloseTo.closeTo(55815.6, .1));
	}
}
