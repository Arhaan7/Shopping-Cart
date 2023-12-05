
//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {

    private String name;
    //changed to an arraylist
    private List<Product> stock;
    private List <Product> cartItems;
    private double revenue, sale;
    private int transactions;


    //constructor
    public ElectronicStore(String initName){
        revenue = 0.0;
        name = initName;
        //intializing hash set of product
        stock = new ArrayList<Product>();
        cartItems = new ArrayList<Product>();
    }

    public String getName(){
        return name;
    }

    public double getRevenue() {
        return revenue;
    }

    public int getTransactions(){
        return transactions;
    }

    public double getSale() {
        return sale;
    }

    public void addProductCart(Product p){

       p.addtoCart(1);
       if(!cartItems.contains(p)){
           cartItems.add(p);
       }

    }

    public void removeProductCart(int index){
        Product p = cartItems.get(index);
        p.removefromCart(1);

        if(p.getCartQuantity() == 0){
            cartItems.remove(p);
        }
    }

    public double cartValue(){
        double total = 0.00;
        for(Product p: getCartItems()){
            total += p.getPrice() * p.getCartQuantity();
        }
        return total;
    }

    public void completeSale(){

        for(Product p: cartItems){
            int quantity = p.getCartQuantity();
            //setting the stock quantity back to its original state for the product (cart quantity + in stock amount)
            // this makes it so when the sale is complete it checks properly that there are
            // enough products to complete the sale
            p.setStockQuantity(p.getStockQuantity() + quantity);
            p.setCartQuantity(0);

            revenue += p.sellUnits(quantity);
        }
        transactions +=1;
        sale = revenue/transactions;

        cartItems = new ArrayList<Product>();

    }

    public List<Product> mostPopular(){

        if(getTransactions() >=1) {
            //adding the has
            List<Product> mostPopular = new ArrayList<Product>(stock);
            //sorting the list to see the top x customers
            Collections.sort(mostPopular, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    //Double.compare allows two doubles to be sorted and compared
                    //0 is returned if they match, -1 returned if customer 2 is greater than 1
                    //1 returned if customer 1 money spent is greater than customer 2
                    return Double.compare(o2.getSoldQuantity(), o1.getSoldQuantity());
                }
            });
            //returning sub list of items from 0 to 3
            return mostPopular.subList(0, 3);
        }
        else{
            return stock.subList(0, 3);
        }

    }
    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct){
        if(!stock.contains(newProduct)){
            stock.add(newProduct);
            return true;
        }
        else{
            return false;
        }

    }


    public List<Product> getStock() {
        return stock;
    }

    public List<Product> getCartItems(){
        return cartItems;
    }



        public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Future Electronics");
        Desktop d1 = new Desktop(1000, 15, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 15, 4.0, 32, true, 500, "Server");
        Desktop d3 = new Desktop(600, 15, 6.0, 32, true, 1000, "Server");
        Laptop l1 = new Laptop(150, 5, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Laptop l3 = new Laptop(750, 4, 3.5, 32, true, 500, 14);
        Fridge f1 = new Fridge(500, 15, 250, "Beige", "Pear Fridge", false);
        Fridge f2 = new Fridge(750, 15, 125, "Clay", "Star Freezer", true);
        ToasterOven t1 = new ToasterOven(100, 8, 50, "Black", "No Name Brand", false);
        ToasterOven t2 = new ToasterOven(40, 10, 50, "Silver", "Toast", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(d3);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(l3);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
}

