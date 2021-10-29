import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.awt.Desktop;  

public class MyHashGenerator extends JFrame {
	
	public MyHashGenerator() {
		
		int textBlock_top = 160;
		JButton btn_OpenFolder;
		JLabel lbl_PrintMessage;
		
		setLayout(null);
		setBounds(0, 0, 500, 300);
		setTitle("Hash Generator - Trabalho de Segurança e Criptografia de Dados");
		
		lbl_PrintMessage = new JLabel("Selecione a pasta para realizar os cálculos de hash");
		lbl_PrintMessage.setBounds(95, 40, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		btn_OpenFolder = new JButton("Selecionar pasta");
		btn_OpenFolder.setBounds(160,80,150,45);
		btn_OpenFolder.addActionListener(new Application());
		getContentPane().add(btn_OpenFolder);

		lbl_PrintMessage = new JLabel("Trabalho de Segurança e Criptografia de Dados");
		lbl_PrintMessage.setBounds(100, textBlock_top, 400, 20);
		getContentPane().add(lbl_PrintMessage);	
		
		lbl_PrintMessage = new JLabel("Aluno: André Fonseca de Paiva - 2º semestre de 2021");
		lbl_PrintMessage.setBounds(90, textBlock_top + 20, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		lbl_PrintMessage = new JLabel("Prof. Dr. João Benedito dos Santos Júnior");
		lbl_PrintMessage.setBounds(120, textBlock_top + 40, 400, 20);
		getContentPane().add(lbl_PrintMessage);
	}

	public static void main (String args[]) {

		JFrame window = new MyHashGenerator();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public class Application implements ActionListener {
		JFileChooser chooser;
		String choosertitle;
		File stream;
		byte[] bytesEntradaHash = null;
	    byte[] bytesSaidaHash = null;
	    StringBuilder hashHexadecimal;
	    String[] hashes = new String[500];
	    String[] fullPathFiles = new String[500];
				
		public void actionPerformed (ActionEvent e) {
			chooser = new JFileChooser();
			chooser.setCurrentDirectory(new java.io.File("."));
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setDialogTitle("Selecione a pasta para a ação");
			chooser.setAcceptAllFileFilterUsed(true);
			int index = 0;
			
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File directory = chooser.getSelectedFile();
				File[] files = directory.listFiles();
				
				for (File file : files) {
					stream = new File(directory + "\\" + file.getName());
					
			        try {
			            bytesEntradaHash = Files.readAllBytes(stream.toPath());
			            MessageDigest algoritmoHash = MessageDigest.getInstance("MD5");
			            bytesSaidaHash = algoritmoHash.digest(bytesEntradaHash);

			            hashHexadecimal = new StringBuilder();
			            for (byte b : bytesSaidaHash) {
			                hashHexadecimal.append(String.format("%02X", 0xFF & b));
			            }

			            hashes[index] = hashHexadecimal.toString();
			            fullPathFiles[index] = directory + "\\" + file.getName();
			            index++;

			        } catch (IOException | NoSuchAlgorithmException erro) {
			            System.out.println(erro);
			        }
				}

				CreateFile(hashes, fullPathFiles, directory);
			}
		}
		
		public void CreateFile (String[] hashes, String[] files, File directory) {
			File output = new File(directory + "\\_output.txt");
			try {
				output.createNewFile();
			} catch (IOException erro) {
				System.out.println(erro);
			}
			try {
				PrintWriter clean = new PrintWriter(directory + "\\_output.txt");
				clean.print("");
				FileWriter outputFile = new FileWriter(directory + "\\_output.txt");
				outputFile.write("Diretório analisado:\n" + directory + "\n\n");
				int index = 0;
				for (String hash : hashes) {
					if (files[index] != null) {
						outputFile.write(files[index] + "; \nHASH GERADA PELO ALGORITMO MD5: " + hashes[index] + "\n\n");						
					}
					index++;
				}
				outputFile.write("Gerador de HASHS MD5 desenvolvido por André Fonseca de Paiva (PUC Minas 2021)\n");
				outputFile.write("Sob orientação do Prof. Dr. João Benedito dos Santos Júnior");
				outputFile.close();
				
			} catch (IOException erro) {
				System.out.println(erro);
			}
			JFrame alert = new JFrame();
			JOptionPane.showMessageDialog(alert, "Arquivo _output.txt\ncriado ou atualizado!");
			Desktop desktop = Desktop.getDesktop();
			try{
				desktop.open(output);
			}
			catch (Exception erro) {
				System.out.println(erro);
			}
		}
		
	
	}
}
