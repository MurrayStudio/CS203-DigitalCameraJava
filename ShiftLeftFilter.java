
/**
 * Shifts image to the left and 
 * brings shifted part to the right of image
 * 
 * Shamus Murray
 * 3/29/14
 */
public class ShiftLeftFilter implements Filter
{
    /** 
     * filter
     * Shift all pixels to the left and bring 1st column of 
     * pixels to the last column of pixels via temp array of pixels.
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // get image data
        Pixel[] column =  new Pixel[pi.getHeight()]; //houses temp values

        for (int row = 0; row < pi.getHeight(); row++) { 
            for (int col = 0; col < pi.getWidth() - 1; col++) {
                if (col == 0)
                {
                    column[row] = data[row][0]; //assign 1st column values to array of temp variables
                }
                data[row][col] = data[row][col+1]; //shift all pixels except last column over to the left 1
            }
            data[row][pi.getWidth() - 1] = column[row]; //last column is assigned to the temp varible values
        }
        
        // reset data into the PixelImage object pi
        pi.setData(data);
    }
}
