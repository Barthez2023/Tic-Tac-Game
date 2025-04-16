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

    boolean gameover=false;
    int turns;
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
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt){
                        if(gameover) return;                      //permet de ne pas donner la permission de changer la va leur dans les cases
                        JButton tile= (JButton)evt.getSource();    /*getSource() sert à identifier l’objet qui a déclenché l'événement. etant donne que l'on a plusieur boutton on utilise getSource() pour retrouver exactement lequel a été cliqué.*/
                        if (tile.getText()=="") {     //optionnel permet de ne pas jouer de facon infini
                            tile.setText(joueurActuel);
                            turns++;
                            CheckVainqueur();
                            if (!gameover) {
                                if (joueurActuel==joueurX) {
                                    joueurActuel=joueurO;
                                }
                                else
                                    joueurActuel=joueurX;
                                //avec l'operateur ternaire on a:joueurActuel=joueurActuel==joueurX?joueurO:joueurX
                                text.setText(joueurActuel+ "'s turn.");
                            }
                        }
                        
                    }
                });
            }
            
        }
    }
    private void CheckVainqueur(){
        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne horizontale alors celui la  sera le vainqueur
        for (int i = 0; i < boutton.length; i++) {
            if (boutton[i][0].getText() == ""){
                continue;
            }
            if (boutton[i][0].getText()==boutton[i][1].getText() && boutton[i][1].getText()==boutton[i][2].getText()) {
                for (int j = 0; j < boutton.length; j++) {
                    setVainqueur(boutton[i][j]);
                }
                gameover=true;
                return;
            }
        }
        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne verticale alors celui la  sera le vainqueur
        for (int i = 0; i < boutton.length; i++) {
            if (boutton[0][i].getText() == ""){
                continue;
            }
            if (boutton[0][i].getText()==boutton[1][i].getText() && boutton[1][i].getText()==boutton[2][i].getText()) {
                for (int j = 0; j < boutton.length; j++) {
                    setVainqueur(boutton[j][i]);
                }
                gameover=true;
                return;
            }
        }

       //on va verifier si soit les "X" ou les "O" on reussir a former une ligne diagonale principale alors celui la  sera le vainqueur
        if (boutton[0][0].getText().equals(boutton[1][1].getText()) && boutton[1][1].getText().equals(boutton[2][2].getText()) && !boutton[0][0].getText().equals("")) {
            for (int j = 0; j < boutton.length; j++) {
                setVainqueur(boutton[j][j]);
            }
            gameover=true;
            return;
        }

        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne diagonale secondaire alors celui la  sera le vainqueur
        if (boutton[2][0].getText().equals(boutton[1][1].getText()) && boutton[1][1].getText().equals(boutton[0][2].getText()) && !boutton[2][0].getText().equals("")) {
            int i=2;
            for (int j = 0; j < boutton.length; j++) {
                setVainqueur(boutton[i][j]);
                i--;
            }
            gameover=true;
            return;
        }

       /*//on va verifier si soit les "X" ou les "O" on reussir a former une ligne diagonale secondaire alors celui la  sera le vainqueur
        if (boutton[2][0].getText()==boutton[1][1].getText() && boutton[1][1].getText()==boutton[0][2].getText()) {
            int i=2;
            for (int j = 0; j < boutton.length; j++) {
                setVainqueur(boutton[i][j]);
                i--;
            }
            gameover=true;
            return;
        }*/

        if (turns==9) {
            for (int i = 0; i < boutton.length; i++) {
                for (int j = 0; j < boutton.length; j++) {
                    setTile(boutton[i][j]);
                }
            }
            gameover=true;
        }
    }
    private void setVainqueur(JButton tile){
        tile.setForeground(Color.GREEN);
        tile.setBackground(Color.gray);
        text.setText(joueurActuel +" is the Winner");
        text.setForeground(Color.GREEN);
    
    }
    private void setTile(JButton tile){
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        text.setText("Game Over trie again!!!");

    }
}