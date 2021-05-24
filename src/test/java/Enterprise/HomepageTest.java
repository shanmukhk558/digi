package Enterprise;

import org.testng.annotations.Test;

import Pages.Enterprise.Homepage;
import Pages.Enterprise.LoginPage;
import Utility.Testdata;
import junit.framework.Assert;

public class HomepageTest extends Base.BaseTest {
	
	@Test
	public void verifyuser() {
		Homepage homepage = new Homepage(getDriver());
		homepage.VerifyUser();	
	}
	
	@Test
	public void cart() {
		Homepage homepage = new Homepage(getDriver());
		homepage.cart();
		
	}
	

}
