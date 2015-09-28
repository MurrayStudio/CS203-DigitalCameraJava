
/**
 * Demosaic an image filtered by a digital camera bayer filter 
 * 
 * @Shamus Murray
 * @version 3/26/2014
 */
public class DemosaicFilter implements Filter
{   
    private int height; //used for helper method
    private int width; ////used for helper method

    /** 
     * filter
     * 
     * Averages red, green, and blue values on an image and assigns those values to pixels
     * so the image is demosaiced from its orignal filtered self.
     * 
     * @param pi The PixelImage object to modify
     */
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // get image data

        for (int row = 0; row < pi.getHeight(); row++) { 
            for (int col = 0; col < pi.getWidth(); col++) {                               
                //used to initialize variables, height and width, for helper method below
                height = pi.getHeight();
                width = pi.getWidth();
                //variables used to calculate color averages for pixels
                int redAverage = 0;
                int greenAverage = 0;
                int blueAverage = 0;
                int sumRed = 0;
                int sumGreen = 0;
                int sumBlue = 0;
                int countRed = 0;
                int countGreen = 0;
                int countBlue = 0;

                
                if (data[row][col].getDigCamColor() == Pixel.RED) //if pixel is red, then sum and average green and blue values for this pixel
                {
                    redAverage = data[row][col].getRed();

                    if (isValid(row-1,col) == false) 
                    {
                        sumGreen = sumGreen + data[row-1][col].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row+1,col)  == false)
                    {
                        sumGreen = sumGreen + data[row+1][col].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row,col-1)  == false)
                    {
                        sumGreen = sumGreen + data[row][col-1].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row,col+1) == false)
                    {
                        sumGreen = sumGreen + data[row][col+1].getGreen();
                        ++countGreen;
                    }

                    if (countGreen == 0) //if dividing average by 0, just set greenAverage to 0
                    {
                        greenAverage = 0;
                    }
                    else
                    {
                        greenAverage = sumGreen/countGreen;
                    }

                    if (isValid(row-1,col-1) == false)
                    {
                        sumBlue = sumBlue + data[row-1][col-1].getBlue();
                        ++countBlue;
                    }

                    if (isValid(row-1,col+1) == false)
                    {
                        sumBlue = sumBlue + data[row-1][col+1].getBlue();
                        ++countBlue;
                    }

                    if (isValid(row+1,col-1) == false)
                    {
                        sumBlue = sumBlue + data[row+1][col-1].getBlue();
                        ++countBlue;
                    }

                    if (isValid(row+1,col+1) == false)
                    {
                        sumBlue = sumBlue + data[row+1][col+1].getBlue();
                        ++countBlue;
                    }

                    if (countBlue == 0) //if dividing average by 0, just set blueAverage to 0
                    {
                        blueAverage = 0;
                    }
                    else
                    {
                        blueAverage = sumBlue/countBlue;
                    }

                    data[row][col].setAllColors(redAverage,greenAverage,blueAverage); //set pixel to new color values
                }

                
                
                if (data[row][col].getDigCamColor() == Pixel.GREEN) //if pixel is green, then sum and average red and blue values for this pixel
                {
                    greenAverage = data[row][col].getGreen();

                    if (row % 2 == 0) //if pixel is even then calculates red and blue this way...
                    {
                        if (isValid(row,col-1) == false)
                        {
                            sumRed = sumRed + data[row][col-1].getRed();
                            ++countRed;
                        }

                        if (isValid(row,col+1) == false)
                        {
                            sumRed = sumRed + data[row][col+1].getRed();
                            ++countRed;
                        }

                        if (countRed == 0) //if dividing average by 0, just set redAverage to 0
                        {
                            redAverage = 0;
                        }
                        else
                        {
                            redAverage = sumRed/countRed;
                        }

                        if (isValid(row-1,col) == false)
                        {
                            sumBlue = sumBlue + data[row-1][col].getBlue();
                            ++countBlue;
                        }

                        if (isValid(row+1,col) == false)
                        {
                            sumBlue = sumBlue + data[row+1][col].getBlue();
                            ++countBlue;
                        }

                        if (countBlue == 0) //if dividing average by 0, just set blueAverage to 0
                        {
                            blueAverage = 0;
                        }
                        else
                        {
                            blueAverage = sumBlue/countBlue;
                        }
                    }

                    if (row % 2 == 1) //if pixel is odd then calculates red and blue this way...
                    {
                        if (isValid(row-1,col) == false)
                        {
                            sumRed = sumRed + data[row-1][col].getRed();
                            ++countRed;
                        }

                        if (isValid(row+1,col) == false)
                        {
                            sumRed = sumRed + data[row+1][col].getRed();
                            ++countRed;
                        }

                        if (countRed == 0) //if dividing average by 0, just set redAverage to 0
                        {
                            redAverage = 0;
                        }
                        else
                        {
                            redAverage = sumRed/countRed;
                        }

                        if (isValid(row,col-1) == false)
                        {
                            sumBlue = sumBlue + data[row][col-1].getBlue();
                            ++countBlue;
                        }

                        if (isValid(row,col+1) == false)
                        {
                            sumBlue = sumBlue + data[row][col+1].getBlue();
                            ++countBlue;
                        }

                        if (countBlue == 0) //if dividing average by 0, just set blueAverage to 0
                        {
                            blueAverage = 0;
                        }
                        else
                        {
                            blueAverage = sumBlue/countBlue;
                        }
                    }

                    data[row][col].setAllColors(redAverage,greenAverage,blueAverage); //set pixel to new color values
                }

                
                
                if (data[row][col].getDigCamColor() == Pixel.BLUE) //if pixel is blue, then sum and average green and red values for this pixel
                {
                    blueAverage = data[row][col].getBlue();

                    if (isValid(row-1,col) == false)
                    {
                        sumGreen = sumGreen + data[row-1][col].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row+1,col) == false)
                    {
                        sumGreen = sumGreen + data[row+1][col].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row,col-1) == false)
                    {
                        sumGreen = sumGreen + data[row][col-1].getGreen();
                        ++countGreen;
                    }

                    if (isValid(row,col+1) == false)
                    {
                        sumGreen = sumGreen + data[row][col+1].getGreen();
                        ++countGreen;
                    }

                    if (countGreen == 0) //if dividing average by 0, just set greenAverage to 0
                    {
                        greenAverage = 0;
                    }
                    else
                    {
                        greenAverage = sumGreen/countGreen;
                    }

                    if (isValid(row-1,col-1) == false)
                    {
                        sumRed = sumRed + data[row-1][col-1].getRed();
                        ++countRed;
                    }

                    if (isValid(row-1,col+1) == false)
                    {
                        sumRed = sumRed + data[row-1][col+1].getRed();
                        ++countRed;
                    }

                    if (isValid(row+1,col-1) == false)
                    {
                        sumRed = sumRed + data[row+1][col-1].getRed();
                        ++countRed;
                    }

                    if (isValid(row+1,col+1) == false)
                    {
                        sumRed = sumRed + data[row+1][col+1].getRed();
                        ++countRed;
                    }

                    if (countRed == 0) //if dividing average by 0, just set redAverage to 0
                    {
                        redAverage = 0;
                    }
                    else
                    {
                        redAverage = sumRed/countRed;
                    }

                    data[row][col].setAllColors(redAverage,greenAverage,blueAverage); //set pixel to new color values
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

