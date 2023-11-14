/*
Arhaan Wazid
101256222
Assignment 4 Comp 1406
 */
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.Text;

import java.util.ArrayList;


public class ElectronicStoreView extends Pane {

    private Button Reset, Add, Remove, Complete;
    private TextField sale1, sale2, revenueField;
    private Label label7;
    private ListView<Product> StockList, PopularItem;
    private ListView <String> Cart;

    public ElectronicStoreView(){



        // Create the labels
        Label label1 = new Label("Store Summary:");
        label1.relocate(20, 10);
        Label label2 = new Label("# Sales:");
        label2.relocate(20, 40);
        Label label3 = new Label("Revenue:");
        label3.relocate(12, 70);
        Label label4 = new Label("$/Sale:");
        label4.relocate(20, 100);
        Label label5 = new Label("Most Popular Items:");
        label5.relocate(20,130);
        Label label6 = new Label("Store Stock:");
        label6.relocate(295, 10);
        label7 = new Label("Shopping Cart:");
        label7.relocate(600, 10);

        //create the list view for stock list, users cart, and popular items
        StockList = new ListView<Product>();
        StockList.relocate(185, 40);
        StockList.setPrefSize(400, 280);

        Cart = new ListView<String>();
        Cart.relocate(600, 40);
        Cart.setPrefSize(400, 280);

        PopularItem = new ListView<Product>();
        PopularItem.relocate(10, 150);
        PopularItem.setPrefSize(160, 170);




        //creating textfields
        sale1 = new TextField();
        sale1.relocate(65, 40);
        sale1.setPrefSize(100,15);

        revenueField = new TextField();
        revenueField.relocate(65, 70);
        revenueField.setPrefSize(100, 15);

         sale2 = new TextField();
        sale2.relocate(65, 100);
        sale2.setPrefSize(100, 15);

        //creating buttons
        Reset = new Button("Reset Store");
        getReset().relocate(20, 330);
        getReset().setPrefSize(130, 50);
        getReset().setStyle("-fx-font: 12 arial; -fx-base: rgb(0,0,0); " + "-fx-text-fill: rgb(255,255,255);");

        Add = new Button("Add to Cart");
        getAdd().relocate(265, 330);
        getAdd().setPrefSize(130, 50);
        getAdd().setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); " + "-fx-text-fill: rgb(255,255,255);");

        Remove = new Button("Remove from Cart");
        getRemove().relocate(600, 330);
        getRemove().setPrefSize(130, 50);
        getRemove().setStyle("-fx-font: 12 arial; -fx-base: rgb(250,0,0); " + "-fx-text-fill: rgb(255,255,255);");

        Complete = new Button("Complete Sale");
        getComplete().relocate(735, 330);
        getComplete().setPrefSize(130, 50);
        getComplete().setStyle("-fx-font: 12 arial; -fx-base: rgb(0,0,255); " + "-fx-text-fill: rgb(255,255,255);");


        //adding all to the pane
        getChildren().addAll(label1, label2, label3, label4, label5, label6, label7, StockList, PopularItem, Cart,sale1, revenueField, sale2, Reset, Add, Remove, Complete);

        //setting screen size
        setPrefSize(1050, 400);


    }

    //get method for add button
    public Button getAdd() {
        return Add;
    }
    //get method for the complete sale button
    public Button getComplete() {
        return Complete;
    }
    //get method for the reset button
    public Button getReset(){
        return Reset;
    }
    //get method for the remove button 
    public Button getRemove(){
        return Remove;
    }

    //get method for amount of sales textfield
    public TextField getSale1() {
        return sale1;
    }

    //get method for average sale textfield
    public TextField getSale2() {
        return sale2;
    }

    //revenue text field get method
    public TextField getRevenueField() {
        return revenueField;
    }

    //label 7 textfield
    public Label getLabel7() {
        return label7;
    }

    //list view get methods for stocklist, popular items, and cart items
    public ListView<Product> getStockList() {
        return StockList;
    }

    public ListView<Product> getPopularItem() {
        return PopularItem;
    }

    public ListView<String> getCart() {
        return Cart;
    }

    public void update(ElectronicStore model){

        //setting revenue textfield
        String Rev = String.format("%.2f", model.getRevenue());
        getRevenueField().setText(Rev);

        //setting amounts of sales made
        String transaction = Integer.toString(model.getTransactions());
        getSale1().setText(transaction);

        //if no transaction is made, average sale will be set to N/A
        if(model.getTransactions()>= 1){
            String sale = String.format("%.2f", model.getSale());
            getSale2().setText(sale);
        }
        else{
            getSale2().setText("N/A");
        }

        // labeling current cart value
        String cartTotal = String.format("%.2f", model.cartValue());
        getLabel7().setText("Shopping Cart ($"+cartTotal+")");

        //list that keeps track of what items are in stock
        ArrayList<Product> inStock = new ArrayList<Product>();
        for(Product pro: model.getStock()){
            if(pro.getStockQuantity() > 0){
                inStock.add(pro);
            }
        }

        //setting the stock list in list view
        getStockList().setItems(FXCollections.observableArrayList(inStock));

        //using an arrayList of string to store the items in the cart
        //using string to incorporate the amount of a certain item in the cart
        ArrayList<String> cart = new ArrayList<String>();
        for(Product p: model.getCartItems()){
            cart.add(p.getCartQuantity() +" x "+ p);
        }
        //setting the cart view list
        getCart().setItems(FXCollections.observableArrayList(cart));


        //setting popular items in the popular items list view
        getPopularItem().setItems(FXCollections.observableArrayList(model.mostPopular()));

        //undisabling/disabling add button
        if(getStockList().getSelectionModel().getSelectedIndex() >= 0){
            getAdd().setDisable(false);
        }
        else{
            getAdd().setDisable(true);
        }
        //undisabling/disabling complete sale button
        if(model.getCartItems().size() > 0){
            getComplete().setDisable(false);
        }
        else{
            getComplete().setDisable(true);
        }
        //undisabling/disabling remove button
        if(getCart().getSelectionModel().getSelectedIndex() >= 0){
            getRemove().setDisable(false);
        }
        else{
            getRemove().setDisable(true);
        }

    }
}
