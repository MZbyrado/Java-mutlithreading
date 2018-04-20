package batman.model;

import batman.listeners.PointEvent;
import batman.listeners.PointListener;
import javafx.concurrent.Task;


import java.util.Random;

public class DrawerTask extends Task {

    private int numberOfPoints;


    public DrawerTask(int numberOfPoints, PointListener pointListener)
    {
        this.numberOfPoints = numberOfPoints;
        this.pointListener = pointListener;
    }

    private PointListener pointListener;


    private double x;
    private double y;
    private double d = 500;
    private double c =1.0;





    @Override
    protected Object call() throws Exception{

        int k =0;
        double integral;
        double field = 16*16;
        final int MIN = -8, MAX = 8;


        Random random = new Random();
        for(int i =0; i <numberOfPoints;i++)
        {
            if(isCancelled()) break;
            x = MIN + (MAX - MIN) * random.nextDouble();
            y = MIN + (MAX - MIN) * random.nextDouble();
            if(Equation.calc(x,y))
            {
                k++;
                int x1=(int)((d-c)*(x-(-8))/(8-(-8))+c);
                int y1=(int)((d-c)*(y-(-8))/(8-(-8))+c);
                x1+=150;
                if(y1>250) {
                    int tmp = y1-250;
                    y1 = 250 -tmp;
                }
                else if (y1<250)
                {
                    int tmp = 250 -y1;
                    y1 = 250 + tmp;
                }
                //if((x>1 && x < 749) || (y>1 && y<249))
                pointListener.pointHandler(new PointEvent(this,new Point(x1,y1)));
            }


            updateProgress(i,numberOfPoints-1);
        }
        integral = field*k/numberOfPoints;
        return integral;
    }




}
