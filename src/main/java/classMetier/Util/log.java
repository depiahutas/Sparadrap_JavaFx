package classMetier.Util;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class log {

    static  Logger logger = Logger.getLogger(log.class.getName());

    public log(){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream(".\\ressources\\logs\\mylogging.properties"));
        }
        catch (SecurityException | IOException e){
            e.printStackTrace();
        }
        logger.setLevel(Level.FINE);
        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new MyHandler());

        try{
            Handler fileHandler = new FileHandler(".\\ressources\\logs\\logger.log",2000,5);
            fileHandler.setFormatter(new MyFormatter());
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);

            for (int i=0; i<1000; i++){
                logger.log(Level.INFO, "MSG"+i);
            }
            logger.log(Level.CONFIG, "Config data");

        }catch (SecurityException | IOException el){
            el.printStackTrace();
        }
    }
}


class MyHandler extends StreamHandler{

    public void publish(LogRecord record){
        super.publish(record);
    }

    public void flush(){
        super.flush();
    }

    public void close() throws SecurityException{
        super.close();
    }
}

class MyFormatter extends Formatter{

    @Override
    public String format(LogRecord record) {
        return record.getLongThreadID()+" :: "+record.getSourceClassName()+" :: "+record.getSourceMethodName()+" :: "
                +new java.util.Date(record.getMillis())+" :: "+record.getMessage()+"\n";
    }
}

class MyFilter implements Filter{

    @Override
    public boolean isLoggable(LogRecord record) {
        return record.getLevel() != Level.CONFIG;
    }
}
