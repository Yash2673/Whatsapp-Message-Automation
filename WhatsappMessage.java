import java.util.*;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class WhatsappMessage {
	
	WebDriver driver;
	String baseUrl;
	
	@BeforeClass
	public void beforeClass() {
		driver = new ChromeDriver();
		baseUrl = "https://web.whatsapp.com/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	
	@Test
	public void test() throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter done after scanning QR code");
		String s = sc.next();
		
		WebElement searchButton = driver.findElement(By.xpath("//button[@aria-label='Search or start new chat']"));
		searchButton.click();
		WebElement searchBox = driver.findElement(By.xpath("//body/div[@id='app']/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/label[1]/div[1]/div[2]"));
		
		System.out.println("Enter the keyword to search for chats");
		String searchText = sc.next();
		searchBox.sendKeys(searchText);
		
		System.out.println("Enter the message to be sent");
		String message = sc.next();
		
		System.out.println("Enter the number of times, the message needs to be sent to each chat");
		int count = sc.nextInt();
		
		List<WebElement> chats = driver.findElements(By.xpath("//div[@aria-label='Search results.']/div//span[@data-icon='default-group']"));
		for(int i=0;i<chats.size();i++)
		{
			WebElement chat = chats.get(i);
			chat.click();
			Thread.sleep(1000);
			WebElement messageBox = driver.findElement(By.xpath("//div[@title='Type a message']"));
			for(int j=0;j<count;j++)
			{
				messageBox.click();
				messageBox.sendKeys(message);
				messageBox.sendKeys(Keys.ENTER);
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
