package batman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;

        initRootLayout();
        showCanvas();
    }

    public void initRootLayout(){

        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/rootLayout.fxml"));
            rootLayout = loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setTitle("Batman");
            primaryStage.setScene(scene);


            primaryStage.show();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showCanvas()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/canvas.fxml"));
            AnchorPane anchorPane = loader.load();
            rootLayout.setCenter(anchorPane);

        }catch(IOException e)
        {
            e.printStackTrace();
        }


    }






    public static void main(String[] args) {
        launch(args);
    }
}
