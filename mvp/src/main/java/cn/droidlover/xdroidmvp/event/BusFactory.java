package cn.droidlover.xdroidmvp.event;

/**
 * Created by admin on 2018/5/4.
 */

public class BusFactory {
    private static IBus bus;


    public static IBus getBus() {
        if (bus == null) {
            synchronized (BusFactory.class) {
                if (bus == null) {
                    bus = new EventBusImpl();
                }
            }
        }
        return bus;
    }

}
