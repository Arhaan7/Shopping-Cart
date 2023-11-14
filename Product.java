/*
Arhaan Wazid
101256222
Assignment 4 Comp 1406
 */
//Base class for all products the store will sell
public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    //instance created to keep track of the product stock within the cart
    private int cartQuantity;

    public Product(double initPrice, int initQuantity) {
        cartQuantity = 0;
        price = initPrice;
        stockQuantity = initQuantity;
    }

    //get/set methods
    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    //adding the products to the cart
    public void addtoCart(int quantity) {

        //checks to see if the item can be added to cart then changes the values as needed
        // stock quantity goes by substracting
        if ((quantity > 0) && stockQuantity >= quantity) {
            stockQuantity = stockQuantity - quantity;
            cartQuantity = cartQuantity + quantity;
        }
    }

    //remove cart method
    public void removefromCart(int quantity){
        //if the quantity is greater than 0, the following is updated with the proper values
        if (quantity > 0) {
            stockQuantity = stockQuantity + quantity;
            cartQuantity = cartQuantity - quantity;
        }
    }


}