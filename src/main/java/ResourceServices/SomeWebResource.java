package ResourceServices;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class SomeWebResource {

  @GET
  public Response getAll() {
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put("msg", "this is response");
    return Response.ok().entity(responseMap).build();
  }
}
