import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JsonFileParser {

    private static Pattern pattern = Pattern.compile("(\\d{2}):(\\d{2})");

    public static long dateParseRegExp(String period) {
        Matcher matcher = pattern.matcher(period);
        if (matcher.matches()) {
            return Long.parseLong(matcher.group(1)) * 60000
                    + Long.parseLong(matcher.group(2)) * 1000;
        } else {
            throw new IllegalArgumentException("Invalid format " + period);
        }
    }

    private static void waitOneSec()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
    }


    public static void main(String[] args) throws JsonParseException, JsonMappingException, MalformedURLException, IOException {

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };
        executor.schedule(task, 5, TimeUnit.SECONDS);

        File file = new File("events.json");
        URL url = file.toURI().toURL();

        String curtime;
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        ObjectMapper mapper = new ObjectMapper();

        //mapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, true);
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        Events events[] = mapper.readValue(url, Events[].class);
        /*for(Events evnt : events ){
            evnt.setTimeMilis(dateParseRegExp(evnt.getTime()));
            //System.out.println(evnt.getTimeMilis());
            //System.out.println(evnt.getEventName());
            // System.out.println(stopWatch.toString());
            //System.out.println(evnt.getTime());

        }*/

        do{
            waitOneSec();
            for(Events evnt : events ) {

                evnt.setTimeMilis(dateParseRegExp(evnt.getTime()));
            }
            System.out.println(stopWatch.toString());
        }while (stopWatch.getTime() <= 15000);
        stopWatch.stop();




    }
}
