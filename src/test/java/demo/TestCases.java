package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;
import java.util.logging.Level;
import demo.utils.ExcelDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import java.util.List;

public class TestCases extends ExcelDataProvider{ // Lets us read the data
        ChromeDriver driver;

        /*
         * TODO: Write your tests here with testng @Test annotation.
         * Follow `testCase01` `testCase02`... format or what is provided in
         * instructions
         */

        @Test(alwaysRun = true, enabled = true)
        public void testCase01() throws InterruptedException{
                System.out.println("Test Case 01 execution started");
                String url ="https://www.youtube.com/";                                               
                boolean navigationSuccess = Wrappers.wrapperNavigate(driver, url);
                String currentUrl = driver.getCurrentUrl();
                
                //Asserting whether navigated url is matching with the expected one
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertEquals(currentUrl, url);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'About')]")));

                // locating about link on the side panel
                WebElement aboutLink = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
                
                // scrolling down to the about link
                Wrappers.wrapperAdvancedScroll(driver, aboutLink); 
                Wrappers.wrapperAdvancedClick(aboutLink);
                Thread.sleep(4000);

                // locating youtube Text
                WebElement aboutYouTubeText = driver.findElement(By.xpath("//h1[contains(@class,'lb-font-display-1')]"));

                //Scrolling down to youtube text
                Wrappers.wrapperAdvancedScroll(driver, aboutYouTubeText);
                System.out.println(aboutYouTubeText.getText());

                WebElement oneMoreText = driver.findElement(By.xpath("//p[contains(@class,'lb-font-display-3')][1]"));
                
                //Scrolling down to youtube text
                Wrappers.wrapperAdvancedScroll(driver, oneMoreText);
                System.out.println(oneMoreText.getText());
                softAssert.assertAll();

                System.out.println("Test Case 01 execution ended");
        }

        @Test(alwaysRun = true, enabled = true)
        public void testCase02() throws InterruptedException {
                System.out.println("Test Case 02 execution started");
                String url ="https://www.youtube.com";                                               
                boolean navigationSuccess = Wrappers.wrapperNavigate(driver, url);
               
                // WebDriver wait
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//yt-formatted-string[contains(text(),'Movies')]")));

                // locating Movie link on the side panel
                WebElement moviesLink = driver.findElement(By.xpath("//yt-formatted-string[contains(text(),'Movies')]"));

                // scrolling down to the about link
                Wrappers.wrapperAdvancedScroll(driver, moviesLink); 
                Wrappers.wrapperAdvancedClick(moviesLink);

                //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='right-arrow']//div[contains(@class,'feedback-shape__fill')]")));
                Thread.sleep(3000);

                WebElement rightArrow = driver.findElement(By.xpath("//div[@id='right-arrow']//div[contains(@class,'feedback-shape__fill')]"));
                do{
                        Wrappers.wrapperAdvancedClick(rightArrow);
                }
                while(rightArrow.isDisplayed());
                Thread.sleep(3000);

                SoftAssert softAssert = new SoftAssert();

                /* Get the Genere data */
                List<WebElement> genere = driver.findElements(By.xpath("//span[contains(@class,'grid-movie-renderer-metadata')]"));
                int genereSize = genere.size();
                System.out.println("Size of Genere "+" "+genereSize);

                /* Pass the genere Size to the xpath to get the which genere the movie is */
                String genreData = driver.findElement(By.xpath("(//span[contains(@class,'grid-movie-renderer-metadata')])[" + genereSize + "]")).getText();
                System.out.println("Genere Data is"+" "+genreData);

                /* get the last index of space */
                String[] actualGenereData = genreData.split("\\s+");
                String actualGenere = actualGenereData[0].trim();
                System.out.println("Actual Genre after extracting the data is"+" "+actualGenere);
                softAssert.assertTrue(actualGenere.equals("Comedy") || actualGenere.equals("Animation"), "Genre Does not match");

                /* Get the movie certification */
                List<WebElement> movieCertification = driver.findElements(By.xpath("//p[contains(@class,'ytd-badge-supported-renderer')]"));
                int movieCertificationSize = movieCertification.size();
                System.out.println("movieCertification size is"+" "+movieCertificationSize);

                /* Pass the genere Size to the xpath to get the which genere the movie is */
                String movieCertificationData = driver.findElement(By.xpath("(//p[contains(@class,'ytd-badge-supported-renderer')])["+movieCertificationSize+"]")).getText();
                softAssert.assertTrue(movieCertificationData.equals("U"), "Does not match with the expected value" + movieCertificationData);
                System.out.println("movieCertification Data is"+" "+movieCertificationData);
                System.out.println("Test Case 02 execution ended");
                softAssert.assertAll();
                
        }

       @Test(alwaysRun = true, enabled = true)
        public void testCase03() throws InterruptedException{
                System.out.println("Test Case 03 execution started");
                String url ="https://www.youtube.com";                                               
                boolean navigationSuccess = Wrappers.wrapperNavigate(driver, url);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='Music']")));

                /*Locating Movies link */
                WebElement musicLink = driver.findElement(By.xpath("//yt-formatted-string[text()='Music']"));
                Wrappers.wrapperAdvancedClick(musicLink);
                Thread.sleep(3000);

                /* Scroll down to the right arrow */
                WebElement rightArrowMusic = driver.findElement(By.xpath("(//div[@id='right-arrow']//div[contains(@class,'feedback-shape__fill')])[1]"));
                
                /*Scroll down to the specified axis */
                Wrappers.wrapperAdvancedScrollWithOutElement(driver, 450);
                Thread.sleep(5000);
                //WebElement rightArrowMusic = driver.findElement(By.xpath("(//div[@id='right-arrow']//div[contains(@class,'feedback-shape__fill')])[1]"));
                
                /* Clicking on right arrow */
                do{
                        Wrappers.wrapperAdvancedClick(rightArrowMusic); 
                }while(rightArrowMusic.isDisplayed());

                /*Get the play list element and print the playlist */
                WebElement playListElement = driver.findElement(By.xpath("(//h3[contains(@class,'ytd-compact-station-renderer')])[11]"));
                System.out.println("PlayList is"+" "+ playListElement.getText());

                SoftAssert softAssertion = new SoftAssert();

                /*Get the no of tracks and assert it*/
                WebElement tracksElement = driver.findElement(By.xpath("(//p[@id='video-count-text' and contains(@class,'ytd-compact-station-renderer')])[11]"));
                String numberOfTracks = tracksElement.getText().trim();
                System.out.println("No of Tracks"+" "+numberOfTracks);
                String tracksAftertrimming = numberOfTracks.replaceAll("\\D+","");
                System.out.println("Get No of Tracks"+" "+tracksAftertrimming);
                int trackCount = Integer.parseInt(tracksAftertrimming);
                int expectedtrackCount = 50; 
                //softAssert.assertTrue(tracksAftertrimming <= 50, "Number of tracks is not less than or equal to 50");
                //softAssertion.assertThat(trackCount).as("Number of tracks").isLessThanOrEqualTo(50);
                //softAssertion.asssertTrue(trackCount > expectedtrackCount, "Track count is greater than 50");
                //softAssertion.assertTrue(trackCount < expectedtrackCount, "Track count is greater than 50");
                if( trackCount <= expectedtrackCount){
                        softAssertion.assertTrue(true);
                }else{
                        softAssertion.assertTrue(false);
                }
                System.out.println("Test Case 03 execution ended");
                softAssertion.assertAll();
        }

        @Test(alwaysRun = true, enabled = true)
        public void testCase04() throws InterruptedException{
                System.out.println("Test Case 04 execution started");
                String url ="https://www.youtube.com/";
                Wrappers.wrapperNavigate(driver, url);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//yt-formatted-string[text()='News']")));
                
                /*Scrolled down to News Link*/
                WebElement newsLink = driver.findElement(By.xpath("//yt-formatted-string[text()='News']"));
                Wrappers.wrapperAdvancedScroll(driver, newsLink);

                /* New Link locating and click on the new link */
                Wrappers.wrapperAdvancedClick(newsLink);
                System.out.println("Clicked on the news link");
                Thread.sleep(8000);

                /*Scroll down to the Latest News link */
                Wrappers.wrapperAdvancedScrollWithOutElement(driver, 280);
                Thread.sleep(8000);
                System.out.println("Scrolled Down");
                
                /*To get the Title of 3 news of Latest News */
                List<WebElement> latestNewsTitleElement = driver.findElements(By.xpath("//a[@id='author-text']//span[@class='style-scope ytd-post-renderer']"));
                System.out.println("To get latest news");
                for(int i=0; i<latestNewsTitleElement.size()-1; i++){
                        String latestNewsTitle = latestNewsTitleElement.get(i).getText();
                        System.out.println("News Title is "+" "+latestNewsTitle);
                }

                /*To get the body of the lastest 3 news */
                List<WebElement> lastestNewsBodyElement = driver.findElements(By.xpath("//yt-formatted-string[@id='home-content-text']//span"));
                for(int i=0; i<3; i++){
                        String lastestNewsBodyText = lastestNewsBodyElement.get(i).getText();
                        System.out.println("News Body is "+" "+lastestNewsBodyText);
                }

                /* To get the body of the 3 Latest news*/
                int totalLikesCount = 0;
                List<WebElement> likesElement = driver.findElements(By.id("vote-count-middle"));
                for(int i=0; i<3; i++){
                        String likesText = likesElement.get(i).getText().trim();
                        int likesCount = Integer.parseInt(likesText);
                        totalLikesCount = totalLikesCount+likesCount;
                }
                System.out.println("Total number of likes"+ " "+totalLikesCount);
                System.out.println("Test Case 04 execution ended");
        }

        @Test(alwaysRun = true, enabled = true, dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
        public void testCase05(String fileName) throws InterruptedException {
                System.out.println("Test Case 05 execution started");
                String url ="https://www.youtube.com/";
                Wrappers.wrapperNavigate(driver, url);

                Thread.sleep(5000);

                int totalViews = 0;

                WebElement searchBox = driver.findElement(By.xpath("//input[@id='search']"));
                searchBox.sendKeys(fileName);

                Actions actions = new Actions(driver);
                actions.sendKeys(Keys.ENTER).perform();
                Thread.sleep(15000);

        //         //Wrappers.wrapperAdvancedScrollWithOutElement(driver,100);
        //         Thread.sleep(3000);

        // //        List<WebElement> likesElement = driver.findElements(By.xpath("//span[@class='inline-metadata-item style-scope ytd-video-meta-block'][1]"));
        // //         for(int i=0; i<likesElement.size();i++){
        // //               System.out.println(likesElement.get(i).getText().trim());
        // //         }

        //         WebElement likesElement = driver.findElement(By.xpath("//span[@class='inline-metadata-item style-scope ytd-video-meta-block'][1]"));
        //         System.out.println(likesElement.getText().trim());
        //         Thread.sleep(5000);

        System.out.println("Test Case 05 execution stopped");

                                
        }

        /*
         * Do not change the provided methods unless necessary, they will help in
         * automation and assessment
         */
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
        }

    

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();
        }
}