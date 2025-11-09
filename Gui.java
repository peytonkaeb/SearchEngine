 //I wrote everything in this

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

    public class Gui {
            public static void main(String[] args){

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,700);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        //Color lightNavy = new Color(12, 38, 85, 250); //a is alpha or transparency
        Color lightNavy = new Color(0, 20, 60);
        frame.getContentPane().setBackground(lightNavy); 

        JLabel title  = new JLabel();  
        title.setText("Trie Search");
        title.setOpaque(true);
        //title.setBackground(new Color(12, 38, 85, 250));
        title.setBackground(lightNavy); 
        Color lilac = new Color(200, 180, 255);
        title.setForeground(lilac);   
        title.setAlignmentX(Component.CENTER_ALIGNMENT);  
                                //  font family    style      size
        title.setFont(new Font("SansSerif", Font.PLAIN, 40));  
        title.setBorder(BorderFactory.createEmptyBorder(150, 0, 20, 0)); // spacing above/below 
        
        
        JTextField searchField = new JTextField();
        searchField.setMaximumSize(new Dimension(250, 30));
        searchField.setForeground(lilac);
        searchField.setBackground(new Color(12, 38, 85));
        searchField.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchField.setFont(new Font("SansSerif", Font.BOLD, 20));
     

        //searchField.setLayout(300,500);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        searchButton.setBackground(lilac);
        searchButton.setForeground(lightNavy);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setMaximumSize(new Dimension(250,35));

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("SansSerif", Font.PLAIN, 20));  
        outputArea.setForeground(lilac);
        outputArea.setBackground(lightNavy);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        scrollPane.setMaximumSize(new Dimension(250,150));



        //add all components:
        frame.add(title);
        frame.add(searchField);
        frame.add(Box.createRigidArea(new Dimension(0, 20))); // 5px vertical space
        frame.add(searchButton);
        frame.add(Box.createRigidArea(new Dimension(0, 20))); // 5px vertical space
        frame.add(scrollPane);


        frame.setVisible(true);

        searchButton.addActionListener(e -> {
        //logic
       List<String> words = List.of("hello", "dog", "hell", "cat", "a", "hel","help","helps","helping");
        Trie trie = new Trie(words); 
        String inputText = searchField.getText();
        List<String> line = trie.suggest(inputText);
        for(int i = 0; i < line.size(); i++){
            outputArea.append(line.get(i) + "\n");
        }
        System.out.println(trie.suggest(inputText)); // print suggestions for prefix "hel"
        // Output: [hel, hell, hello, help, helps, helping]
        });

    }
}
/*
    public void mainCall(){
  // create trie with given words
        System.out.println("Start typing your word, hit search to get suggestions:");
        Scanner scnr = new Scanner(System.in);
        String searchWord = scnr.nextLine().trim();

        System.out.println(trie.suggest(searchWord)); // print suggestions for prefix "hel"
        // Output: [hel, hell, hello, help, helps, helping]
    }  */
