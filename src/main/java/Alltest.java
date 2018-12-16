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
        List<A> alist = new ArrayList<A>();
        for (int i = 0; i < a.length; ++i) {
            alist.add(new A(a[i].a, a[i].getS()));
        }
        Collections.sort(alist, new Comparator<A>() {
            @Override
            public int compare(final A o1, final A o2) {
                if (o1.a < o2.a)
                    return -1;
                else if (o1.a > o2.a)
                    return 1;
                else
                    return 0;
            }
        });


// then, when you want to schedule a task
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println(alist);
            }
        };

        for(int i = 0; i < a.length; i++) {
            timeSpent =  a[i].a - (System.currentTimeMillis() - startTime);
            if(timeSpent < 0){
                timeSpent = 0;
            }
            executor.schedule(task, timeSpent, TimeUnit.MILLISECONDS);
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
        timeInSecond *= 1000;
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

      public String getS() {
          return s;
      }

      String s;

      public String toString(){
          return "Time event: " + a + "\n"
                  + " Message event: " + s;
      }
}







