package pixar;

/**
 *
 * @author WorkSpace
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Set System L&F
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName());
        }
        catch (javax.swing.UnsupportedLookAndFeelException e) {
           // handle exception
        }
        catch (ClassNotFoundException e) {
           // handle exception
        }
        catch (InstantiationException e) {
           // handle exception
        }
        catch (IllegalAccessException e) {
           // handle exception
        }

        Main program = new Main();
        program.run();
    }

    private void run(){
        Frame gui = new Frame();
        gui.setSize( 800, 600 );
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
    }

}
