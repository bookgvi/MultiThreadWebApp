package Services;

import javax.ejb.Singleton;

@Singleton
public class SomeSinglton {
  public String getResponseMsg() {
    String msg = "Hey, from singlton EJB";
    return msg;
  }
}
