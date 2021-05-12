package gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;

//CHECKSTYLE:OFF
public class UILoader
{
    public static Parent fxmlLoader(URL url) throws IOException
    {
        FXMLLoader loader;
        loader = new FXMLLoader(url);
        Parent root = loader.load();
        return root;
    }
}
