
/**
 * Flips image horizontally
 * 
 * Shamus Murray
 * 3/29/14
 */
public class FlipHorizontalFilter implements Filter
{
    /** 
     * filter
     * flips pixel image horizontally via a vertical center line
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // get image data

        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth() / 2; col++) {
                // swap values across center line
                Pixel temp = data[row][col];
                data[row][col] = data[row][pi.getWidth() - col - 1];
                data[row][pi.getWidth() - col - 1] = temp;
            }
        }  
        // reset data into the PixelImage object pi
        pi.setData(data);
    }
}
