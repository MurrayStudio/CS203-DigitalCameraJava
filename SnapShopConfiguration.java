/**
 * SnapShopConfiguration
 * A class to configure the SnapShop application
 * 
 * Shamus Murray 
 * 03/23/2014
 * 
 */
public class SnapShopConfiguration {
    /**
     * configure
     * Method to configure the SnapShop.  
     * Call methods like addFilter
     * and setDefaultFilename in this method.
     * @param theShop   The SnapShop application
     */
    public static void configure(SnapShop theShop) {
        // set default directory
        theShop.setDefaultDirectory("./Images/");
     
        theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
        theShop.addFilter(new DemosaicFilter(), "Demosaic Filter");
        theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
        theShop.addFilter(new DarkenFilter(), "Darken Filter");
        theShop.addFilter(new ShiftLeftFilter(), "Shift Left");
        theShop.addFilter(new EdgeFilter(), "Edge Filter");
        theShop.addFilter(new NegativeFilter(), "Negative Filter");
        // add other filters you write here:
        // the arguments to addFilter are a Filter object, followed by the 
        // text you want shown on the button
    }
    
    /** 
     * main
     * creates a new SnapShop object
     */
    public static void main(String args[]) {
      SnapShop theShop = new SnapShop();
    }  
}
