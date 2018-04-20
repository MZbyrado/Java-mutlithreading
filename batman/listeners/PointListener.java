package batman.listeners;

import java.util.EventListener;

public interface PointListener extends EventListener {
        void pointHandler(PointEvent event);
}
