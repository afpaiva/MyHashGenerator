import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyHashGenerator extends JFrame {
	
	public MyHashGenerator() {
		
		int textBlock_top = 160;
		JButton btn_OpenFolder;
		JLabel lbl_PrintMessage;
		
		setLayout(null);
		setBounds(0, 0, 500, 300);
		setTitle("Hash Generator - Trabalho de Segurança e Criptografia de Dados");
		
		lbl_PrintMessage = new JLabel("Selecione a pasta para realizar os cálculos de hash");
		lbl_PrintMessage.setBounds(110, 40, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		btn_OpenFolder = new JButton("Abrir");
		btn_OpenFolder.setBounds(200,80,100,45);
		btn_OpenFolder.addActionListener(new OpenDir());
		getContentPane().add(btn_OpenFolder);

		lbl_PrintMessage = new JLabel("Trabalho de Segurança e Criptografia de Dados");
		lbl_PrintMessage.setBounds(120, textBlock_top, 400, 20);
		getContentPane().add(lbl_PrintMessage);	
		
		lbl_PrintMessage = new JLabel("Aluno: André Fonseca de Paiva - 2º semestre de 2021");
		lbl_PrintMessage.setBounds(105, textBlock_top + 20, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		lbl_PrintMessage = new JLabel("Prof. Dr. João Benedito dos Santos Júnior");
		lbl_PrintMessage.setBounds(130, textBlock_top + 40, 400, 20);
		getContentPane().add(lbl_PrintMessage);
	}

	public static void main (String args[]) {

		JFrame window = new MyHashGenerator();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public class OpenDir implements ActionListener {
		
		JFileChooser chooser;
		String choosertitle;
				
		public void actionPerformed (ActionEvent e) {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setDialogTitle("Selecione a pasta para a ação");
			chooser.setAcceptAllFileFilterUsed(true);
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				System.out.println("debug: " + chooser.getCurrentDirectory());
			}
			else {
				System.out.println("nothing");
			}
		}
	}
}
