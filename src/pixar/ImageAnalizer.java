package pixar;

import javax.swing.*;
import java.awt.image.PixelGrabber;
import java.awt.*;


import java.awt.*;
import java.awt.image.*;
import java.applet.*;

public class ImageAnalizer{
    
    private JFileChooser fileChooser = new JFileChooser();
    public String filename;
    public PixelGrabber grabber;
    public int ImageWidth;
    public int ImageHeight;
    public byte[] GreyImagePixArray;
    public int[] SimplifiedPixArray;
    public int[][] SimplifiedPixMatrix;
    public int[] ColorImagePixArray;
    public String ColorModel;
    public Image img;
    public Image Diagram = null;
    public int[] DiagramValues = new int[256];

    public ImageAnalizer(){


    }

    public boolean isGreyscaleImage(PixelGrabber pg) {
        return pg.getPixels() instanceof byte[];
    }

    public void convertToSimplePixArray(){
        int size = GreyImagePixArray.length;
        if( size == 0 )
            return;

        SimplifiedPixArray = new int[size];
        for( int i = 0; i < size; i++ ){
            SimplifiedPixArray[i] =
                    ( GreyImagePixArray[i] < 0 ) ? 128 + ( 128 + GreyImagePixArray[i] ) : GreyImagePixArray[i] ;
        }

    }

    public void convertToMatrix(){
        int size = SimplifiedPixArray.length;
        if( size == 0 )
            return;

        SimplifiedPixMatrix = new int[ ImageHeight ][ ImageWidth ];

        for( int i = 0; i < size; i++ ){
            int row = (i / ImageWidth);
            int col = i - ( row * ImageWidth );
            SimplifiedPixMatrix[row][col] = SimplifiedPixArray[i];
        }

    }

    public void analizeMatrix(){
        for( int i=0; i<ImageHeight; i++ ){

            for( int k=0; k<ImageWidth; k++ ){
                if( SimplifiedPixMatrix[i][k] > 150 )
                System.out.print("0 ");
                else
                    System.out.print("1 ");
            }
            System.out.print("\n");
        }


    }

    public void sortColors(){

        for( int i=0; i < ImageHeight; i++ ){

            for( int k=0; k < ImageWidth; k++ ){

                DiagramValues[ (SimplifiedPixMatrix[i][k]) ]++;

            }

        }

    }

    public void generateDiaramPixArray(){


    }

    public void loadFile(){

        if( fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION ){

            filename = fileChooser.getSelectedFile().toString();
            img = Toolkit.getDefaultToolkit().getImage(filename);

            try {

            grabber = new PixelGrabber(img, 0, 0, -1, -1, false);


                if ( grabber.grabPixels() ) {
                    ImageWidth = grabber.getWidth();
                    ImageHeight = grabber.getHeight();
                    GreyImagePixArray = new byte[ ImageHeight * ImageWidth ];
                    if( isGreyscaleImage( grabber ) ){
                        GreyImagePixArray = (byte[]) grabber.getPixels();
                        convertToSimplePixArray();
                        convertToMatrix();
                        analizeMatrix();
                        sortColors();

                        int diagramWidth = 100;
                        int diagramHeight = 512;
                        byte[] DiagramBytes = new byte[ diagramWidth*diagramHeight ];
                        
                        for( int i = 0 ; i < 256; i = i+1 ){
                            
                            for( int j = 0 ; j < 256; j++ ){
                                
                                for( int k = 0; k < DiagramValues[j]; k++ ){
                                    
                                    DiagramBytes[i*100+k] = 100;
                                    
                                }
                                
                            }
                         
                        
                        }



                        Diagram = Toolkit.getDefaultToolkit().createImage( 
                                new MemoryImageSource( diagramWidth , diagramHeight , grabber.getColorModel() , DiagramBytes , 0, diagramWidth ) );
   
                        



                    }
                    else{
                        ColorImagePixArray = (int[]) grabber.getPixels();
                    }
                    ColorModel = grabber.getColorModel().toString();
                    System.out.println(ColorModel);
                }

            }
            catch (InterruptedException e) {
                System.out.println( e );
            }





        }


    }

}