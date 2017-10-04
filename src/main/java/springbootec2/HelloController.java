package springbootec2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootec2.util.Version;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by haugom on 03.10.17.
 */
@RestController
public class HelloController {

    @Autowired
    private Repo repo;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public Map<String, Object> hello(@RequestParam(value = "name", defaultValue = "Boxfuse") String name) {
        Map<String, Object> result = new HashMap<>();
        result.put("greeting", "Hello from version " + Version.CURRENT_VERSION + ", db time: " + repo.currentTimeInDatabase() + ", you said -> " + name + "!");
        return result;
    }
}
