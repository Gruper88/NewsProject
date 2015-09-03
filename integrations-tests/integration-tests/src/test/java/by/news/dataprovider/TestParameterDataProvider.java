package by.news.dataprovider;

import by.news.config.Path;
import by.news.model.News;
import org.testng.annotations.DataProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class TestParameterDataProvider {

    private static String TEST_PROPERTIES_FILE = Path.TEST_PROPERTIES_FILE;
    private static Map<String, String> testData;
    private static News news;

    @DataProvider(name = "newsData")
    public static Object[][] providerNewsTest() {
        news = initNews();
        testData = initTestData();
        return new Object[][]{{news, testData}};
    }

    public static Map<String, String> initTestData() {
        testData = new HashMap<String, String>();

        Properties testProperties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(TEST_PROPERTIES_FILE);
            testProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        testData.put("loginPageURL", testProperties.getProperty("loginPage"));
        testData.put("username", testProperties.getProperty("username"));
        testData.put("password", testProperties.getProperty("password"));
        testData.put("errorMassage", testProperties.getProperty("errorMassage"));
        return testData;
    }

    public static News initNews() {
        news = new News();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("D:\\GitNewsSpringProject\\integrations-tests\\integration-tests\\src\\test\\resources\\newsData.xml"));
            document.getDocumentElement().normalize();
            NodeList newsList = document.getElementsByTagName("news");
            for (int i = 0; i < newsList.getLength(); i++) {
                Node node = newsList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    news.setDate(eElement.getElementsByTagName("newsDate").item(0).getTextContent());
                    news.setTitle(eElement.getElementsByTagName("newsTitle").item(0).getTextContent());
                    news.setDescription(eElement.getElementsByTagName("newsAnnotation").item(0).getTextContent());
                    news.setCategory(eElement.getElementsByTagName("newsCategory").item(0).getTextContent());
                    news.setNewstext(eElement.getElementsByTagName("newsText").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }
}
