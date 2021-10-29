import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyHashGenerator extends JFrame {
	
	public MyHashGenerator() {
		
		int textBlock_top = 160;
		JButton btn_OpenFolder;
		JLabel lbl_PrintMessage;
		
		setLayout(null);
		setBounds(0, 0, 500, 300);
		setTitle("Hash Generator - Trabalho de Seguran�a e Criptografia de Dados");
		
		lbl_PrintMessage = new JLabel("Selecione a pasta para realizar os c�lculos de hash");
		lbl_PrintMessage.setBounds(110, 40, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		btn_OpenFolder = new JButton("Abrir");
		btn_OpenFolder.setBounds(200,80,100,45);
		btn_OpenFolder.addActionListener(new Application());
		getContentPane().add(btn_OpenFolder);

		lbl_PrintMessage = new JLabel("Trabalho de Seguran�a e Criptografia de Dados");
		lbl_PrintMessage.setBounds(120, textBlock_top, 400, 20);
		getContentPane().add(lbl_PrintMessage);	
		
		lbl_PrintMessage = new JLabel("Aluno: Andr� Fonseca de Paiva - 2� semestre de 2021");
		lbl_PrintMessage.setBounds(105, textBlock_top + 20, 400, 20);
		getContentPane().add(lbl_PrintMessage);
		
		lbl_PrintMessage = new JLabel("Prof. Dr. Jo�o Benedito dos Santos J�nior");
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
			chooser.setDialogTitle("Selecione a pasta para a a��o");
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
			            
			            //System.out.println("Sequ�ncia de Bytes da HASH Gerada pelo Algoritmo MD5");
						//System.out.println("debug: " + directory + "\\" + file.getName());
			            //System.out.println(index);
			            //System.out.println(hashHexadecimal.toString());
			            //System.out.println(hashes + "\n");

			        } catch (IOException | NoSuchAlgorithmException erro) {
			            System.out.println(erro);
			        }
				}

				CreateFile(hashes, fullPathFiles, directory);
			}
		}
		
		public void CreateFile (String[] hashes, String[] files, File directory) {
			try {
				File output = new File(directory + "\\_output.txt");
				output.createNewFile();
			} catch (IOException erro) {
				System.out.println(erro);
			}
			try {
				FileWriter outputFile = new FileWriter(directory + "\\_output.txt");
				outputFile.write(directory + "\n\n");
				int index = 0;
				for (String hash : hashes) {
					if (files[index] != null) {
						outputFile.write(files[index] + "; \nHASH GERADA PELO ALGORITMO MD5: " + hashes[index] + "\n\n");						
					}
					index++;
				}
				outputFile.write("Gerador de HASHS MD5 desenvolvido por Andr� Fonseca de Paiva (PUC Minas 2021)\n");
				outputFile.write("Sob orienta��o do Prof. Dr. Jo�o Benedito dos Santos J�nior");
				outputFile.close();
				
			} catch (IOException erro) {
				System.out.println(erro);
			}
		}
	}
}
