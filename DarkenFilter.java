
/**
 * Darkens image
 * 
 * Shamus Murray
 * 3/29/14
 */
public class DarkenFilter implements Filter
{
    /** 
     * filter
     * Gets RGB values for current pixel and 
     * divides by 2 to get darker values
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  //get image data

        for (int row = 0; row < pi.getHeight(); row++) { 
            for (int col = 0; col < pi.getWidth(); col++) {
                int red;
                int green;
                int blue;

                //Get RGB values
                red = data[row][col].getRed();
                green = data[row][col].getGreen();
                blue = data[row][col].getBlue();

                //divide by 2 to make darker
                red = red/2;
                green = green/2;
                blue = blue/2;
                
                //reassign values to pixel
                data[row][col].setAllColors(red,green,blue);

            }
        }
        // reset data into the PixelImage object pi
        pi.setData(data);
    }
}
