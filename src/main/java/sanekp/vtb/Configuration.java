package sanekp.vtb;

import javax.enterprise.inject.Produces;

import com.w3schools.webservices.TempConvert;
import com.w3schools.webservices.TempConvertSoap;

public class Configuration {
	@Produces
	public TempConvertSoap getTempConvertSoap() {
		return new TempConvert().getTempConvertSoap();
	}
}
