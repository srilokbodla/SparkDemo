import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(getHerokuAssignedPort());     //setting default listner port on which our server runs
        //sample get request routing with variable count
        get("/getRandam/:count", (req, res) -> {
            int count = Integer.parseInt(req.params(":count"));
            return json(random(count));
        });
        /*req is a request object contains default html request object res is object that we manipulate and send to one who send request
        req.params is used to accept parameters this is response to request , here we are sending serialized array . Here Json function is used to serialize output
        into json string*/
    }

    public static int[] random(int count) {
        int[] randomIntegers = new int[count];
        for (int i = 0; i < count; i++) {
            randomIntegers[i] = ((int) (Math.random() * 1000));
            System.out.println(randomIntegers[i]);
        }
        return randomIntegers;
    }

    public static String json(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();     // objectMapper class contains methods for converting
        return objectMapper.writeValueAsString(obj);// this covert content of object to json string
    }
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
