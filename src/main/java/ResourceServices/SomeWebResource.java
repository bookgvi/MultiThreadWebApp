package ResourceServices;

import Services.SomeSinglton;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class SomeWebResource {

  @EJB
  SomeSinglton someSinglton;

  @GET
  public Response getAll() {
    Map<String, String> responseMap = new HashMap<String, String>();
    responseMap.put("msg", someSinglton.getResponseMsg());
    return Response.ok().entity(responseMap).build();
  }
}
