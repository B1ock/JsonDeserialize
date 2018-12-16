import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


public class AJsonDeserializerTest {



    @Test
    public void test_deserialize() throws IOException, JsonProcessingException {

        String json = "{\n" +
                "    \"eventName\": \"John entered the magazine\",\n" +
                "    \"time\": \"01:51\"\n" +
                "  }";
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(A.class, new AJsonDeserializer());
        mapper.registerModule(module);

        final A test1 = mapper.readValue(json, A.class);
        assertEquals( "John entered the magazine", test1.getS());
        assertEquals(111000, test1.getA());
    }

}


