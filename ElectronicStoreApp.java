/*
Arhaan Wazid
101256222
Assignment 4 Comp 1406
 */
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;

import static javafx.application.Application.launch;

public class ElectronicStoreApp extends Application {
    //creating instance of electronic store then calling create store method
    private ElectronicStore model;

    //initializing model by creating a store
    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
    }
    public void start(Stage primaryStage){
        //creating main pane
        Pane  MainPane = new Pane();

        //creating instance of the view
        ElectronicStoreView view = new ElectronicStoreView();
        MainPane.getChildren().add(view);
        MainPane.setStyle("-fx-font: 13 calibri; -fx-base: rgb(100,100,100); " + "-fx-text-fill: rgb(255,255,255);");

        //Mouse event that triggers the add button when an item is selected in the stock list
        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }

        });

        //Mouse event that triggers the remove button when an item is selected in the cart list
        view.getCart().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {

                //updating the view
                view.update(model);
            }

        });

        //add button handler
        view.getAdd().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //gets selected product from the stock list
               Product selectedProduct = view.getStockList().getSelectionModel().getSelectedItem();
               //adds the selected product to the cart
                model.addProductCart(selectedProduct);
                //updating the model
                view.update(model);

            }
        });

        //remove button handler
        view.getRemove().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //getting selected index from cart list
                int selectedIndex = view.getCart().getSelectionModel().getSelectedIndex();
                //calling method to remove it from the cart
                model.removeProductCart(selectedIndex);
                view.update(model);

            }
        });

        //complete sale button handler
        view.getComplete().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //method called to complete the sale of the products
                model.completeSale();
                //updating the view
                view.update(model);

            }
        });

        //reset button handler
        view.getReset().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                //creates new instance of the store meaning all the stores' states is initialized
                //to original values
                model = ElectronicStore.createStore();
                view.update(model);

            }
        });

        //setting up the stage for the screen on javafx
        primaryStage.setTitle("Electronic store Application- " + model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(MainPane));
        primaryStage.show();
        view.update(model);

    }
    //creating update interface for the view class
    public interface ElectronicStoreView1 {
        //Cause view to update its appearance based on given model & selected DVD
        public void update(ElectronicStore model);
    }
    public static void main(String[] args){launch(args);}
}
