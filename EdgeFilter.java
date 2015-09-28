
/**
 * All edges turn black, everything else turns white
 * 
 * Shamus Murray    
 * 3/29/14
 */
public class EdgeFilter implements Filter
{
    private int height; //used for helper method
    private int width; ////used for helper method
    /** 
     * filter
     * Calulates RGB values for the middle pixel and surrounding
     * pixels and if the difference between the two pixels is
     * significant than that middle pixel is an edge.
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // get image data
        boolean isEdge[][] = new boolean[pi.getHeight()][pi.getWidth()];

        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                //used to initialize variables, height and width, for helper method below
                height = pi.getHeight();
                width = pi.getWidth();
                //variables used to calculate color averages for pixels
                int red;
                int green;
                int blue;
                int[] redAverage = new int[4];
                int[] greenAverage = new int[4];
                int[] blueAverage = new int[4];
                
                //get RGB values for middle pixel
                red = data[row][col].getRed();
                green = data[row][col].getGreen();
                blue = data[row][col].getBlue();

                //If the neighboring pixel is in the array, then get the RGB value for that pixel
                if (isValid(row-1,col) == false)
                {
                    redAverage[0] = data[row-1][col].getRed();
                    greenAverage[0] = data[row-1][col].getGreen();
                    blueAverage[0] = data[row-1][col].getBlue();
                }
                if (isValid(row+1,col) == false)
                {
                    redAverage[1] = data[row+1][col].getRed();
                    greenAverage[1] = data[row+1][col].getGreen();
                    blueAverage[1] = data[row+1][col].getBlue();
                }
                if (isValid(row,col-1) == false)
                {
                    redAverage[2] = data[row][col-1].getRed();
                    greenAverage[2] = data[row][col-1].getGreen();
                    blueAverage[2] = data[row][col-1].getBlue();
                }
                if (isValid(row,col+1) == false)
                {
                    redAverage[3] = data[row][col+1].getRed();
                    greenAverage[3] = data[row][col+1].getGreen();
                    blueAverage[3] = data[row][col+1].getBlue();
                }
                
                //if one of the RGB values of the neighboring pixels is more than 35 above or below
                //one of the middle pixel's RGB values, then that pixel is an edge (isEdge[row][col] is true)
                if ((Math.abs(redAverage[0] - red) >= 35) || (Math.abs(greenAverage[0] - green) >= 35) || (Math.abs(blueAverage[0] - blue) >= 35)
                || (Math.abs(redAverage[1] - red) >= 35) || (Math.abs(greenAverage[1] - green) >= 35) || (Math.abs(blueAverage[1] - blue) >= 35)
                || (Math.abs(redAverage[2] - red) >= 35) || (Math.abs(greenAverage[2] - green) >= 35) || (Math.abs(blueAverage[2] - blue) >= 35)
                || (Math.abs(redAverage[3] - red) >= 35) || (Math.abs(greenAverage[3] - green) >= 35) || (Math.abs(blueAverage[3] - blue) >= 35))
                {
                    isEdge[row][col] = true;
                }
                else
                {
                    isEdge[row][col] = false;
                }
            }

        }  

        //go through array again to set black to any pixels that 
        //are edges and white to any pixels that aren't edges
        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                if (isEdge[row][col] == true)
                {
                    data[row][col].setAllColors(0,0,0);
                }
                else
                {
                    data[row][col].setAllColors(255,255,255);
                }
            }
        }
        // reset data into the PixelImage object pi
        pi.setData(data);
    }

    /** 
     * Used to check if the row and col of the pixel being checked is in the array data
     * 
     * @param int row used to keep track of what row the program is on
     * @param int col used to keep track of what col the program is on
     */
    public boolean isValid(int row, int col)
    {
        if ((row >= 0) && (row < height) && (col >= 0) && (col < width)) //is row and col in array?
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
