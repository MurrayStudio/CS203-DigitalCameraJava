
/**
 * Applies a negative filter to an image
 * 
 * Shamus Murray
 * 3/29/14
 */
public class NegativeFilter implements Filter
{
    /** 
     * filter
     * take highest RGB value and subtract by actual RGB values
     * for the pixels; this will yield a negative filter.
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // get image data

        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                int newRed;
                int newGreen;
                int newBlue;
                
                //subtract highest RGB value from actual pixel RGB value
                newRed = 255-data[row][col].getRed();
                newGreen = 255-data[row][col].getGreen();
                newBlue = 255-data[row][col].getBlue();
                
                //set new value
                data[row][col].setAllColors(newRed,newGreen,newBlue);
            }
        }
        // reset data into the PixelImage object pi
        pi.setData(data);
    }
}
