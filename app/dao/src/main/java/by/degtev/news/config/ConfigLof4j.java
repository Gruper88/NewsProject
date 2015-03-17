package by.degtev.news.config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLof4j {
    private static Logger logger = Logger.getLogger(ConfigLof4j.class);
    private static Properties logProperty = new Properties();
    private static String logFile;

    public ConfigLof4j(String logFile){
        this.logFile = logFile;
    }

    /**
     * logger initialization
     */
    public void init(){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(logFile);
            logProperty.load(is);
            PropertyConfigurator.configure(logProperty);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}