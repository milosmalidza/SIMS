package model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.MainWindow;

public class DineApp {
	
	private static DineApp instance = null;
	public static ArrayList<Korisnik> korisnici = new ArrayList<>();
	
	public MainWindow mainWindow;
	
	public DineApp() {
		this.mainWindow = new MainWindow();
		//MainWindow.changeFont(this.mainWindow);
		this.mainWindow.revalidate();
	}
	
	public static DineApp getInstance() { //singleton
		
		if (instance == null) {
			instance = new DineApp();
			return instance;
		}
		
		return instance;
		
	}
	
	public static void main(String[] args) {
		try {
			MainWindow.font = Font.createFont(Font.TRUETYPE_FONT, new File("./data/fonts/Montserrat-Regular.ttf")).deriveFont(12f); 
			GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
			genv.registerFont(MainWindow.font);
			File f = new File("./korisnici.sims");
			if (!f.exists()) {
				f.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./korisnici.sims"));
				out.writeObject(DineApp.korisnici);
			}
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("./korisnici.sims"));
			korisnici = (ArrayList<Korisnik>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // stavlja windows okruzenje
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		DineApp.getInstance();

	}

}
