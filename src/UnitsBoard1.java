
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class UnitsBoard1  extends javax.swing.JFrame {


    public static void main(String yes) throws Exception {
        
        
//        String yes = "yes";
        int WINDOW_WIDTH = 1000;
        int WINDOW_HEIGHT = 800;
        System.out.println("Working Directory = " +  System.getProperty("user.dir"));
        String[] Chapter = {"Cells","Genetics","Circulation & Digestion & Respiration","Plants","Diversity of living things", "Evolution" };
        String show;
        if (yes == "yes"){
            show ="Unit Jeopardy!";               
        }
        else{
            show="Random Jeopardy!";
        }

        JFrame frame = new JFrame(show);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int width  = 6;
        int height = 6;
        int l,m,n;
        String Qestio,Answe,Section;
        String[][] Question=new String[height][width];
        String[][] Answer=new String[height][width];
        JButton[] categories = new JButton[width];
        for (int i = 0; i < width; i++) {
            categories[i] = new JButton(Chapter[i]);
            categories[i].setSize(WINDOW_WIDTH / width - 4, 20);
        }
        JButton[][] BLK = new JButton[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BLK[i][j] = new JButton("Level" + i);
                JBox.setSize(BLK[i][j], WINDOW_WIDTH / width - 4,
                        WINDOW_HEIGHT / height - 36);
                BLK[i][j].setBackground(Color.BLUE);
                BLK[i][j].setForeground(Color.YELLOW);
                BLK[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
                BLK[i][j].setFont(new Font("DejaVu Serif", Font.BOLD, 25));
                
            }
        }
        JButton BLK1 = new JButton("Go Back");
        BLK1.setSize(WINDOW_WIDTH / width - 4, 20);
        BLK1.setBackground(Color.YELLOW);
        BLK1.setForeground(Color.BLUE);
        BLK1.setBorder(BorderFactory.createRaisedBevelBorder());
        BLK1.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
        JBox board =
                JBox.vbox(
                        JBox.hbox(BLK1),
                        JBox.hbox(categories[0],categories[1],categories[2],categories[3],categories[4],categories[5]),
                        JBox.hbox(BLK[0][0], BLK[0][1], BLK[0][2], BLK[0][3],BLK[0][4], BLK[0][5]),
                        JBox.hbox(BLK[1][0], BLK[1][1], BLK[1][2], BLK[1][3],BLK[1][4], BLK[1][5]),
                        JBox.hbox(BLK[2][0], BLK[2][1], BLK[2][2], BLK[2][3],BLK[2][4], BLK[2][5]),
                        JBox.hbox(BLK[3][0], BLK[3][1], BLK[3][2], BLK[3][3],BLK[3][4], BLK[3][5]),
                        JBox.hbox(BLK[4][0], BLK[4][1], BLK[4][2], BLK[4][3],BLK[4][4], BLK[4][5]),
                        JBox.hbox(BLK[5][0], BLK[5][1], BLK[5][2], BLK[5][3],BLK[5][4], BLK[5][5])
                );
        board.setSize(WINDOW_WIDTH - 20, WINDOW_HEIGHT);
        frame.add(board);
        frame.setVisible(true);
                String f1="src\\QuestionAnswer.txt";

            try{
          final BufferedReader reader = new BufferedReader(new FileReader(f1));
        final ArrayList<String> lines = new ArrayList<String>();
        String s;
        String S;
        S="";
        while ((s = reader.readLine()) != null) {
            S=S+s;
        }
        reader.close();

        for ( int i = 0; i < height; i++) {
            l=S.indexOf(Chapter[i]);
            if(i<5)
            {
                m=S.indexOf(Chapter[i+1]);
                Section=S.substring(l,m);
            }
            else
            {
                Section=S.substring(l,S.length());
            }
            for (int  j = 0; j < width; j++) {
                l=Section.indexOf(String.valueOf(j+1)+". ");
                m=Section.indexOf("a. ",1);
                Qestio=Section.substring(l+3, m);
                Question[i][j]=Qestio;
                Section=Section.substring(m,Section.length());
                
                n=Section.indexOf("()",1);
                Answe=Section.substring(2,n);
                Answer[i][j]=Answe;
            }
        }

        BLK1.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {               
            new    ModeOption().setVisible(true);
                   frame.dispose();
//                   frame.hide();
                   frame.setVisible(false);                
            }});
        
        
        for ( int i = 0; i < height; i++) {
            for (int  j = 0; j < width; j++) {

                String cd;
                String ab; 
                if (yes == "yes"){
                cd=Question[j][i];
                ab=Answer[j][i];  
                }
                else{
                Random rand = new Random();
                int i1 = rand.nextInt(6);
                int j1 = rand.nextInt(6);
                 cd=Question[i1][j1];
                 ab=Answer[i1][j1]; 
                }

                 
                BLK[i][j].addActionListener(new ActionListener() {
                    @Override

                    public void actionPerformed(ActionEvent e) {
                        String your_answer = JOptionPane.showInputDialog(cd);
                        JOptionPane.showMessageDialog(null, "Reference and yours: "+ ab +" Vs " + your_answer);
                        JButton source = (JButton) e.getSource();
                        source.setEnabled(false);
                        System.out.println(ab.length());
                        System.out.println(your_answer.length());
                        if (your_answer.equals(ab.trim())) {
                        source.setBackground(Color.GREEN);
                        }
                        else {
                            source.setBackground(Color.red);
                        }
                    }
                });
            }
        }
         } catch(Exception e){
//        return false;            // Always must return something
    }

           
    }
    
}


