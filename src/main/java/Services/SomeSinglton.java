package Services;

import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
@Lock(LockType.WRITE)
@AccessTimeout(1000)
public class SomeSinglton {

//  @AccessTimeout(0)
  public String getResponseMsg() throws InterruptedException {
    List<Integer> tmpList = new ArrayList<Integer>();
    for (int i = 0; i < 1_000_000_0; i++) {
      tmpList.add(i);
    }
    String msg = "Hey, from singlton EJB";
    return msg;
  }
}
