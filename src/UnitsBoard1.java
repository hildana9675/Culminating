/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Gloria Song
 */
public class UnitsBoard1  extends javax.swing.JFrame {
private static int score1=0;
private static int score2=0;
private static int score3=0; 
private static int total=0; 

    public static void main(String yes) throws Exception {
        
        //determine the size of game board  
        int WINDOW_WIDTH = 1000;
        int WINDOW_HEIGHT = 1000;
        //Array with each units 
        String[] Chapter = {"Cells","Genetics","Circulation & Digestion & Respiration","Plants","Diversity of living things", "Evolution" };
        String show;
        //if user presses unit button, then unit board game appears 
        //look at mode option java 
        if (yes == "yes"){
            show ="Unit Jeopardy!";               
        }
        //if user presses the random button, then the random board game appears 
        else{
            show="Random Jeopardy!";
        }
        
        //creates a jframe 
        JFrame frame = new JFrame(show);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //set width and height 
        int width  = 6;
        int height = 6;
        //value for 
        int l,m,n;
        
        String Qestio,Answe,Section;
        //initiate array for question and answers to the question 
        String[][] Question=new String[height][width];
        String[][] Answer=new String[height][width];
        //2 dimensional array, populated by jbuttons 
        JButton[][] BLK = new JButton[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                BLK[i][j] = new JButton(new Integer(i*6+j+1).toString());
                JBox.setSize(BLK[i][j], WINDOW_WIDTH / width - 0,
                        WINDOW_HEIGHT / height - 36);
                //set desired colour for the buttons 
                BLK[i][j].setBackground(Color.WHITE);
                BLK[i][j].setForeground(Color.BLACK);
                BLK[i][j].setBorder(BorderFactory.createRaisedBevelBorder());
                BLK[i][j].setFont(new Font("DejaVu Serif", Font.BOLD, 25));
                
            }
        }
        
        //create button 
        JButton[] BLK1 = new JButton[4];
        BLK1[0] = new JButton("Go Back");
        BLK1[1] = new JButton("Correct:"+ score1);
        BLK1[2] = new JButton("Wrong:"+ score2);
        //label textfield 
        JTextField text=new JTextField("Correct:");
        JTextField text1=new JTextField("Wrong:");
        JTextField text2=new JTextField("Total:");
        //set desired font, colours for the textfield 
        text.setFont(new Font("DejaVu Serif", Font.BOLD, 20));       
        text.setForeground(Color.GREEN);
        text.setBackground(Color.WHITE);
        text1.setFont(new Font("DejaVu Serif", Font.BOLD, 20));       
        text1.setForeground(Color.red);
        text1.setBackground(Color.WHITE);
        text2.setFont(new Font("DejaVu Serif", Font.BOLD, 20));       
        text2.setForeground(Color.BLUE);
        text2.setBackground(Color.WHITE);
        JLabel Res1    = new JLabel("Correct:"+ score1+score2+score3);
        
        //loactions of the jbox in 2 diemensional array 
        JBox board =
                JBox.vbox(
                        JBox.hbox(BLK1[0]),
                        JBox.hbox(BLK[0][0], BLK[0][1], BLK[0][2], BLK[0][3],BLK[0][4], BLK[0][5]),
                        JBox.hbox(BLK[1][0], BLK[1][1], BLK[1][2], BLK[1][3],BLK[1][4], BLK[1][5]),
                        JBox.hbox(BLK[2][0], BLK[2][1], BLK[2][2], BLK[2][3],BLK[2][4], BLK[2][5]),
                        JBox.hbox(BLK[3][0], BLK[3][1], BLK[3][2], BLK[3][3],BLK[3][4], BLK[3][5]),
                        JBox.hbox(BLK[4][0], BLK[4][1], BLK[4][2], BLK[4][3],BLK[4][4], BLK[4][5]),
                        JBox.hbox(BLK[5][0], BLK[5][1], BLK[5][2], BLK[5][3],BLK[5][4], BLK[5][5]),
                        JBox.hbox(text,text1,text2));
        
        //open the frame. Makes it visible 
        board.setSize(WINDOW_WIDTH - 20, WINDOW_HEIGHT);
        frame.add(board);
        frame.setVisible(true);
        //read file 
        String f1="QuestionAnswer.txt";

            try{
                BufferedReader reader = new BufferedReader(new FileReader(f1));
                ArrayList<String> lines = new ArrayList<String>();
                //s is every single line of the file
                String s; 
                //S is total file
                String S;
                S="";
                while ((s = reader.readLine()) != null) {
                    S=S+s;
            }
                reader.close();
                
                for ( int i = 0; i < height; i++) {
                    // one dimensional array created for each unit at the beginning of code
                    l = S.indexOf(Chapter[i]);
                    if(i<5){
                        m = S.indexOf(Chapter[i+1]);
                        //begining index is the first unit (l) and the first unit ends before the second unit (m)
                        //for loop repeats 
                        Section=S.substring(l,m);
                    }
                    else {
                        Section=S.substring(l,S.length());
                    }
                    //Choose questions and answers
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
                
                //back button
                BLK1[0].addActionListener(new ActionListener() {
//                    
            
                public void actionPerformed(ActionEvent e) {  
                    //if press back, go back to mode option frame. Makes it visible
                    new ModeOption().setVisible(true);
                    //destroys jfram for the board game 
                    frame.dispose();
                    frame.setVisible(false);                
              }});
                
                for ( int i = 0; i < height; i++) {
                    for (int  j = 0; j < width; j++) {
                        //Question
                        String Que;
                        //RightAnswer
                        String Right_Answer; 
                        if (yes == "yes"){
                            Que = Question[j][i];
                            Right_Answer = Answer[j][i]; 
                            //reset points to 0
                            score1 = 0;
                            score2 = 0;
                            score3 = 0;
                        }
                        //open board game that is randomized 
                        else{
                            Random rand = new Random();
                            int i1 = rand.nextInt(6);
                            int j1 = rand.nextInt(6);
                            Que = Question[i1][j1];
                            Right_Answer = Answer[i1][j1]; 
                            //reset points to 0
                            score1 = 0;
                            score2 = 0;
                            score3 = 0;
                        }
                        
                        BLK[i][j].addActionListener(new ActionListener() {
                           
                            public void actionPerformed(ActionEvent e) {
                                //compares the users answer to the correct answer 
                                String your_answer = JOptionPane.showInputDialog(Que);
                                JOptionPane.showMessageDialog(null, "Reference and yours: "+ Right_Answer +" Vs " + your_answer);
                                JButton source = (JButton) e.getSource();
                                source.setEnabled(false);
                                System.out.println(Right_Answer.length());
                                System.out.println(your_answer.length());

                                //adds point if your right
                                if (your_answer.equalsIgnoreCase(Right_Answer.trim())) {
                                    source.setBackground(Color.GREEN);
                                    score1++;
                                    text.setText("Correct:"+score1);
                                }
                                //add point if your wrong 
                                else {
                                    source.setBackground(Color.red);
                                    score2++;
                                    text1.setText("Wrong:"+score2);
                                }
                                //adds all points 
                                total=score1+score2;
                                text2.setText("Total:"+total);
                    }
                });
            }
        }
         } catch(Exception e){

    }

           
    }
    
}


