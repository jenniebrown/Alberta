/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alberta;

/**
 *
 * @author giancarlo
 */
public class ReturnFacade {
    private Return returning;
    private Register reg;
    public ReturnFacade (Register reg)
    {
        this.reg = reg;
    }
    
    public void enterReturn (Return r)
    {
        this.returning = r;
        System.out.println ("Attempting Return");
    }
    
    public boolean checkUPC(int upc) {
        return reg.checkUPC(upc);
    }
     
    public void completeTransaction() {
         reg.updateInventory(returning.getRental());
     }
}
