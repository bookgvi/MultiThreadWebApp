package ResourceServices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class SomeWebResource {

  @GET
  public Response getAll() {
    return Response.ok().entity("QQQ").build();
  }
}
