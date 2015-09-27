/*
 */
package cse216project;

/*
 */
public class Store {
    //private List<Register> register = new ArrayList()<Register>;
    private ProductCatalog catalog = new ProductCatalog();
    private Register register = new Register(catalog);
    
    public Register getRegister () {
        return register;
    }
    public Inventory getInventory() {
        return inventory;
    }
}
