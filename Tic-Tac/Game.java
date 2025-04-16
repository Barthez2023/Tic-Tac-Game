import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Game {
    //creation de la fenetre du jeu
    int boardWith=600;
    int boardheight=650; //50px sera pour le texte en haut d ela fenetre 
    
    JFrame frame=new JFrame("Tic-Tac Game");
    //creation de jlabel et du jpanel
    JLabel text=new JLabel();
    JPanel textpanel=new JPanel();
    JPanel gamepanel=new JPanel();

    //creation des boutton qui seront en quelque sorte les cases de notre jeu
    JButton boutton[][] = new JButton[3][3];

    String joueurX="X";
    String joueurO="O";
    String joueurActuel=joueurX;
    public Game(){
        frame.setVisible(true);
        frame.setSize(boardWith,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        text.setBackground(Color.DARK_GRAY);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("Arial", Font.BOLD, 50));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("Tic-Tac Game");
        text.setOpaque(true);
        
        textpanel.setLayout(new BorderLayout());
        textpanel.add(text);
        frame.add(textpanel,BorderLayout.NORTH);

        gamepanel.setLayout(new GridLayout(3,3));
        /*
         * borderLayout(3, 3) signifie que l'intérieur de gamePanel va utiliser un borderLayout avec des marges (3 pixels horizontal et vertical) entre ses composants internes.
        Si on ajoute d'autres composants à l'intérieur de gamePanel, c’est ce layout qui décidera comment ils sont positionnés (NORTH, SOUTH, CENTER, etc.).
        Cela configure donc comment les composants à l’intérieur du gamePanel seront arrangés.
        GridLayout: par contre vas dessiner un grille de 3x3 et vas positioner nos element a l'interieur. 
        */
        gamepanel.setBackground(Color.LIGHT_GRAY);
        frame.add(gamepanel);

        for (int i = 0; i < boutton.length; i++) {
            for (int j = 0; j < boutton.length; j++) {
                JButton tile=new JButton();
                boutton[i][j]=tile;
                gamepanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                //tile.setText(joueurActuel);
            }
            
        }
    }
}