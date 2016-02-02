package pixar;

import java.awt.Color;

public class ColorAnalizer {
    public int SliderVal = 0;
    public byte ByteVal = 0;

    public void analizeSelected(){    
        
        SliderVal = Frame.jSlider1.getValue();
        ByteVal = ( SliderVal > 127 ) ? (byte) -(256-SliderVal) : (byte) SliderVal;
        Color RedColor = new Color(SliderVal,0,0);
        Color GreenColor = new Color(0,SliderVal,0);
        Color BlueColor = new Color(0,0,SliderVal);
        Color MixedColor = new Color(SliderVal,SliderVal,SliderVal);

        Frame.jTextField1.setText(ByteVal+"");
        Frame.jTextField2.setText(SliderVal+"");
        Frame.jTextField3.setText(SliderVal+"");
        Frame.jTextField4.setText(SliderVal+"");
        
        Frame.jTextField2.setBackground(RedColor);
        Frame.jTextField3.setBackground(GreenColor);
        Frame.jTextField4.setBackground(BlueColor);
        Frame.jTextField5.setBackground(MixedColor);

    }

}
