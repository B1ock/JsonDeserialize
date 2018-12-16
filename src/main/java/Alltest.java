import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Alltest {

    public static void main(String[] args) throws IOException {


        long timeSpent, startTime = System.currentTimeMillis();

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        File file = new File("events.json");
        URL url = file.toURI().toURL();

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(A.class, new AJsonDeserializer());
        mapper.registerModule(module);

        final A []a = mapper.readValue(url, A[].class);

// then, when you want to schedule a task
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
        };
        for(int i = 0; i < a.length; i++) {
            timeSpent =  a[i].a - System.currentTimeMillis() - startTime;
            if(timeSpent < 0){
                timeSpent = 0;
            }
            executor.schedule(task, timeSpent, TimeUnit.SECONDS);
        }
        executor.shutdown();
    }


}

 class AJsonDeserializer extends JsonDeserializer<A> {

    @Override
    public A deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        Root root = jp.readValueAs(Root.class);
        final String time = root.time;
        int timeInSecond = (time.charAt(0) - '0') * 10 * 60;
        timeInSecond += (time.charAt(1) - '0') * 60;
        timeInSecond += (time.charAt(3) - '0') * 10;
        timeInSecond += (time.charAt(4) - '0');
        A account = new A(timeInSecond, root.eventName);


        return account;
    }

    private static class Root {

        public String eventName;
        public String time;


    }

}

  class A {

    public A(final int a, final String s) {
        this.a = a;
        this.s = s;
    }

    int a;
    String s;

      public String toString(){
          return "Time event: " + a + "\n"
                  + " Message event: " + s;
      }
}







