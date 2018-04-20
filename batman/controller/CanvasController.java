package batman.controller;


import batman.listeners.PointEvent;
import batman.listeners.PointListener;
import batman.model.DrawerTask;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.paint.Color;


import java.awt.*;
import java.awt.image.BufferedImage;

public class CanvasController {

    @FXML
    private Canvas batmanCanvas;

    @FXML
    private TextField pointAmmountTextField;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Label result;

    @FXML
    private Label activeTasks;

    private DrawerTask task;
    private int pointAmmount;
    private int ammountActiveTasks =0;



    int iterator =0;
    @FXML
    private void handleStartButton(){

        try
        {
            pointAmmount = Integer.parseInt(pointAmmountTextField.getText());
        }catch(NumberFormatException e)
        {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Input");
            alert.setContentText("Your input is incorrect.");
            alert.showAndWait();
            e.printStackTrace();
            return;
        }

        GraphicsContext gc = batmanCanvas.getGraphicsContext2D();
        drawShapes(gc);

//        BufferedImage bi= new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
        task = new DrawerTask(pointAmmount, new PointListener(){

            @Override
            public void pointHandler(PointEvent event) {

                int x = (int) event.getPoint().getX();
                int y = (int) event.getPoint().getY();
                gc.getPixelWriter().setColor(x, y, javafx.scene.paint.Color.YELLOW);

//                iterator++;
//                bi.setRGB(x, y,Color.YELLOW.getRGB() );

            }
        });

//        if(iterator%1000==0)
//        {
//            gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
//
//        }


        progressBar.progressProperty().bind(task.progressProperty());
        progressIndicator.progressProperty().bind(task.progressProperty());
        try
        {
            new Thread(task).start();
        }catch (InternalError error)
        {
            error.printStackTrace();
        }

        ammountActiveTasks ++;
        activeTasks.setText(Integer.toString(ammountActiveTasks));

        System.out.println(Color.YELLOW.hashCode());
        System.out.println(Color.YELLOW.toString());



        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if(ammountActiveTasks>0)
                {
                    ammountActiveTasks --;
                    activeTasks.setText(Integer.toString(ammountActiveTasks));
                }

                double var = (double)task.getValue();
                result.setText(Double.toString(var));

            }
        });

    }

    @FXML
    private void drawShapes(GraphicsContext gc)
    {
        gc.setFill(javafx.scene.paint.Color.BLUE);
        gc.fillRect(gc.getCanvas().getLayoutX(),gc.getCanvas().getLayoutY(),gc.getCanvas().getWidth(),gc.getCanvas().getHeight());

    }

    @FXML
    private void handleStopButton(){
        task.cancel();
        if(ammountActiveTasks>0)
        {
            ammountActiveTasks --;
            activeTasks.setText(Integer.toString(ammountActiveTasks));
        }

    }




}
