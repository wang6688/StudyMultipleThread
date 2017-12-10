package _8TestConcurrentApplications._8_6outputEfficientLogInformation;

import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.logging.*;

public class MyLogger {

    private static Handler handler;
    public static Logger getLogger(String name){
        Logger logger  = Logger.getLogger(name);
        logger.setLevel(Level.ALL);

        try {
            if(handler==null){
                handler = new FileHandler("recipe8.log");
                Formatter format = new MyFormatter();
                handler.setFormatter(format);
            }
            if(logger.getHandlers().length==0){
                logger.addHandler(handler);
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return  logger;
    }

}
