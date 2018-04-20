package batman.listeners;

import batman.model.Point;

import java.util.EventObject;

public class PointEvent extends EventObject {
    Point point;

    public PointEvent(Object source, Point point)
    {
        super(source);
        this.point=point;

    }

    public Point getPoint() {
        return point;
    }
}
