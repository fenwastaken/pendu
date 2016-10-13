import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Pendu extends JFrame{

	//Variables Globales-------------------------------------------------------------
	static Vector<Icon> vImage = new Vector<Icon>();
	static char lettreEntree;
	static JLabel image = new JLabel();
	static JLabel guess = new JLabel();
	static int iErreurs = 0;
	static JLabel listeErreurs = new JLabel();
	static String listeErreursVar;
	static String secretWord;
	static String affichage = "";
	static boolean tabMot[];
	static JLabel mot = new JLabel();
	static int compteurToWin = 0;
	static Boolean win = false;
	static Boolean lose = false;
	JButton rejouer = new JButton("Reset");
	int espace = 15;
	static JPanel panoPano = new JPanel();
	static int winCounter;
	static int loseCounter;
	static JLabel cWin = new JLabel();
	static JLabel cLose = new JLabel();
	static String lettresRestantes;
	static JRadioButton optLeague = new JRadioButton("League");
	static JRadioButton optPokemon = new JRadioButton("Pokemon");
	static JLabel imageLBL = new JLabel();
	static ImageIcon fondL;
	static ImageIcon fondP;
	static JPanel background = new JPanel();
	static JPanel panoButton = new JPanel();
	static JPanel zoneClient = new JPanel();


	//constructeur

	public Pendu() throws IOException{
		this.setSize(400,500);
		//this.setMinimumSize(new Dimension(400, 500));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("The Hangyordle Game, For CrystalShine");
		this.setResizable(false);
		initControles();
		this.setAlwaysOnTop(false);
	}

	private void initControles() throws IOException{

		rejouer.setFocusable(false);
		optLeague.setFocusable(false);
		optPokemon.setFocusable(false);
		
		
		optLeague.setSelected(true);
		ButtonGroup pokeLeague = new ButtonGroup();
		pokeLeague.add(optLeague);
		pokeLeague.add(optPokemon);

		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu1.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu2.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu3.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu4.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu5.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu6.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu7.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu8.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu9.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu10.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/pendu11.jpg")));
		vImage.addElement(new ImageIcon (this.getClass().getResource("MyFiles/lol.jpg")));

		fondL = new ImageIcon(this.getClass().getResource("MyFiles/fond.jpg"));
		fondP = new ImageIcon(this.getClass().getResource("MyFiles/fond2.jpg"));


		zoneClient.setLayout(null);


		background.setLayout(null);
		background.setBounds(0, 0, 400, 500);


		imageLBL.setIcon(fondL);
		imageLBL.setBounds(0, 0, 400, 500);
		imageLBL.setLayout(null);

		background.add(imageLBL);


		zoneClient = (JPanel) this.getContentPane();


		//panoPano Kingdom

		zoneClient.setFocusable(true);
		panoPano.setBounds(0, 260, 394, 212);
		panoPano.setLayout(new BoxLayout(panoPano, BoxLayout.Y_AXIS));
		panoPano.setOpaque(false);

		JPanel panoMot = new JPanel();
		panoMot.setLayout(new BoxLayout(panoMot, BoxLayout.X_AXIS));
		panoMot.setOpaque(false);

		panoPano.add(Box.createRigidArea(new Dimension(0, espace)));
		panoPano.add(panoMot);
		mot.setFont(new Font(Font.DIALOG,1,30));
		mot.setForeground(Color.WHITE);

		panoMot.add(mot);

		JPanel panoErreurs = new JPanel();
		panoErreurs.setLayout(new BoxLayout(panoErreurs, BoxLayout.X_AXIS));
		panoErreurs.setOpaque(false);

		listeErreurs.setFont(new Font(Font.DIALOG,1,30));
		listeErreurs.setText("Pick a letter");
		listeErreurs.setForeground(Color.WHITE);
		panoErreurs.add(listeErreurs);

		panoPano.add(Box.createRigidArea(new Dimension(0, espace)));
		panoPano.add(panoErreurs);

		JPanel panoGuess = new JPanel();
		panoGuess.setLayout(new BoxLayout(panoGuess, BoxLayout.X_AXIS));
		panoGuess.setOpaque(false);

		guess.setText(Character.toString(lettreEntree));
		guess.setFont(new Font(Font.DIALOG,1,24));
		guess.setForeground(Color.WHITE);
		guess.setText(lettresRestantes);
		panoGuess.add(guess);

		panoPano.add(Box.createRigidArea(new Dimension(0, espace)));
		panoPano.add(panoGuess);


		panoButton.setLayout(new BoxLayout(panoButton, BoxLayout.X_AXIS));
		panoButton.setOpaque(false);
		optLeague.setOpaque(false);
		optPokemon.setOpaque(false);
		optLeague.setForeground(Color.WHITE);
		optPokemon.setForeground(Color.WHITE);
		panoButton.add(optLeague);
		panoButton.add(optPokemon);
		panoButton.add(rejouer);

		//=====================================================================================

		panoPano.add(Box.createRigidArea(new Dimension(0, espace)));
		panoPano.add(panoButton);

		image.setBounds(100, 30, 200, 200);
		image.setIcon(vImage.get(0));
		image.setBorder(BorderFactory.createBevelBorder(NORMAL));
		zoneClient.add(image);
		zoneClient.add(panoPano);

		cWin.setText("W: " + winCounter);
		cLose.setText("L: " + loseCounter);
		//cWin.setBounds(5,  200, 90, 30); 
		//cLose.setBounds(305,  200, 85, 30);
		cWin.setFont(new Font(Font.DIALOG,1,30));
		//cWin.setForeground(Color.WHITE);
		cLose.setFont(new Font(Font.DIALOG,1,30));
		//cLose.setForeground(Color.WHITE);	

		JPanel cWinBox = new JPanel();
		JPanel cLoseBox = new JPanel();
		cWinBox.setBorder(BorderFactory.createBevelBorder(0));
		cLoseBox.setBorder(BorderFactory.createBevelBorder(0));
		cWinBox.setBounds(5,  180, 90, 50); 
		cLoseBox.setBounds(305,  180, 85, 50);

		cWinBox.add(cWin);
		cLoseBox.add(cLose);
		zoneClient.add(cWinBox);
		zoneClient.add(cLoseBox);

		zoneClient.add(background);

		//listeners

		zoneClient.addKeyListener(new AppKeyListener());
		zoneClient.addFocusListener(new AppFocusListener());
		rejouer.addActionListener(new AppActionListener());
		image.addMouseListener(new AppMouseListener());


	}

	//class

	class AppMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Focus();
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	class AppFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			listeErreurs.setText(listeErreursVar);
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			//Focus();
			listeErreurs.setText("click on teemo");

		}

	}

	class AppActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if(e.getSource() == rejouer){

				if (win != true)loseCounter++;

				lose = false;
				win  = false;
				try {
					NouvellePartie();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				affichage();
				refreshImage();

			}

		}

	}

	class AppKeyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			String alphabet = "abcdefghijklmnopqrstuvwxyz .'";
			lettreEntree = e.getKeyChar();
			System.out.println("key pressed: \n" + e.getKeyChar()+ "\n" + e.getKeyCode());


			if (Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)){
				Toolkit.getDefaultToolkit().setLockingKeyState(KeyEvent.VK_CAPS_LOCK, false);
			}


			if (e.getKeyCode() == KeyEvent.VK_ENTER && (win == true || lose == true)){

				if (win != true && lose != true)loseCounter++;

				lose = false;
				win  = false;
				try {
					NouvellePartie();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resetJLabels();

				affichage();
				refreshImage();
				Focus();

			}

			if (e.getKeyCode() == KeyEvent.VK_LEFT){
				optLeague.setSelected(true);
				System.out.println("left");
			}

			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				optPokemon.setSelected(true);
				System.out.println("right");
			}


			if(win == false && lose == false && (e.getKeyCode()  != KeyEvent.VK_ENTER ) && (listeErreursVar.indexOf(e.getKeyChar()) == -1) && (alphabet.indexOf(e.getKeyChar()) != -1) && lettresRestantes.indexOf(e.getKeyChar()) != -1){
				checkGuess();
				listeErreurs.setText(listeErreursVar);
				updateLettres();
				guess.setText(lettresRestantes);
				affichage();
				refreshImage();
				CheckWin();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}
	}

	public static void resetJLabels(){
		guess.setText(lettresRestantes);
		listeErreurs.setText("Pick a letter");
	}


	public static void resetLettres(){
		lettresRestantes = "abcdefghijklmnopqrstuvwxyz .'";
	}

	public static void updateLettres(){
		String temp;

		temp = lettresRestantes.substring(0, lettresRestantes.indexOf(lettreEntree));
		temp += lettresRestantes.substring(lettresRestantes.indexOf(lettreEntree)+1, lettresRestantes.length());

		lettresRestantes = temp;
	}

	public static void refreshImage(){
		image.setIcon(vImage.get(iErreurs));
		cWin.setText("W: " + winCounter);
		cLose.setText("L: " + loseCounter);
	}


	public static void Focus(){
		zoneClient.requestFocus();
	}


	public void CheckWin(){
		if (win == true){
			listeErreurs.setText("You won!");
			image.setIcon(vImage.get(11));
			winCounter++;
			Focus();

			System.out.println("victoire" + winCounter);
		}
		else if (lose == true){
			listeErreurs.setText("You lost!");
			guess.setText("It was " + secretWord);
			loseCounter++;
			Focus();
		}
	}


	public static void NouvellePartie() throws IOException{
		int i;
		//reset
		resetLettres();
		guess.setText(lettresRestantes);
		iErreurs = 0;
		listeErreursVar = "";
		affichage = "";
		listeErreurs.setText(listeErreursVar);
		compteurToWin = 0;
		Boolean win = false;
		Boolean lose = false;

		//choix de la liste





		//choisir Mot

		if(optPokemon.isSelected() == false){

			imageLBL.setIcon(fondL);
			zoneClient.repaint();

			String[] tab ={"aatrox", "ahri", "alistar", "amumu", "anivia", "annie", "ashe", "azir", "bard", "blitzcrank", 
					"brand", "braum", "caitlyn", "cassiopeia", "cho'gath", "corki", "darius", "diana", "dr.mundo", "draven", 
					"elise", "evelynn", "ezreal", "fidlestick", "fiora", "fizz", "galio", "gankplank", "garen", "gnar", "gragas", 
					"graves", "hecarim", "heimerdinger", "irelia", "janna", "jarvan", "jax", "jayce", "jinx", "kalista", "karma", 
					"karthus", "kassadin", "katarina:kayle", "kennen", "kha'zix", "kog'maw", "leblanc", "lee sin", "leona", "lissandra", 
					"lucian", "lulu", "lux", "malphite", "malzahar", "maokai", "maitre yi", "miss fortune", "mordekaiser", "morgana", "nami", 
					"nasus", "nautilus", "nidalee", "nocturne", "nunu", "olaf", "orianna", "pantheon", "poppy", "quinn", "rammus", "rek'sai", 
					"renekton", "rengar", "riven", "rumble", "ryze", "sejuani", "shaco", "shen", "shyvana", "singed", "sion", "sivir", "skarner", 
					"sona", "soraka", "swain", "syndra", "talon", "taric", "teemo", "tresh", "tristana", "trundle", "tryndamere", "twisted fate", 
					"twitch", "udyr", "urgot", "varus", "vayne", "veigar", "vel'koz", "vi", "viktor", "vladimir", "volibear", "warwick", "wukong", 
					"xerath", "xin zhao", "yasuo", "yorick", "zac", "zed", "ziggs", "zilean", "zyra", "urf", "morello", "ao shin"};
			int randomNumber = (int)(Math.random()*tab.length);


			secretWord = tab[randomNumber];

			//création tableau de booléens

			tabMot = new boolean[secretWord.length()];

			for(i = 0; i < secretWord.length(); i++){
				tabMot[i] = false;
			}
		}
		else{

			imageLBL.setIcon(fondP);
			zoneClient.repaint();

			String tab2[] = {"bulbizarre", "herbizarre", "florizarre", "salameche", "reptincel", "dracaufeu", "carapuce", "carabaffe", "tortank", "chenipan",
					"chrysacier", "papilusion", "aspicot", "coconfort", "dardargnan", "roucool", "roucoups", "roucarnage", "rattata", "rattatac", "piafabec",
					"rapasdepic", "abo", "arbok", "pikachu", "raichu", "sabelette", "sablaireau", "nidoran femelle", "nidorina", "nidoqueen", "nidoran male",
					"nidorino", "nidoking", "melofee", "melodelfe", "goupix", "feunard", "rondoudou", "grodoudou", "nosferapti", "nosferalto", "mystherbe",
					"ortide", "rafflesia", "paras", "parasect", "mimitoss", "aeromite", "taupiqueur", "triopikeur", "miaouss", "persian", "psykokwak", "akwakwak",
					"ferosinge", "colossinge", "caninos", "arcanin", "ptitard", "tetarte", "tartard", "abra", "kadabra", "alakazam", "machoc", "machopeur",
					"mackogneur", "chetiflor", "boustiflor", "empiflor", "tentacool", "tentacruel", "racaillou", "gravalanch", "grolem", "ponyta", "galopa",
					"ramoloss", "flagadoss", "magneti", "magneton", "canarticho", "doduo", "dodrio", "otaria", "lamantine", "tadmorv", "grotadmorv", "kokiyas",
					"crustabri", "fantominus", "spectrum", "ectoplasma", "onix", "soporifik", "hypnomade", "krabby", "krabboss", "voltorbe", "electrode",
					"noeunoeuf", "noadkoko", "osselait", "ossatueur", "kicklee", "tygnon", "excelangue", "smogo", "smogogo", "rhinocorne", "rhinoferos",
					"leveinard", "saquedeneu", "kangourex", "hypotrempe", "hypocean", "poissirene", "poissoroy", "stari", "staross", "mr.mime", "insecateur",
					"lippoutou", "elektek", "magmar", "scarabrute", "tauros", "magicarpe", "leviator", "lokhlass", "metamorph", "evoli", "aquali", "voltali",
					"pyroli", "porygon", "amonita", "amonistar", "kabuto", "kabutops", "ptera", "ronflex", "artikodin", "electhor", "sulfura", "minidraco",
					"draco", "dracolosse", "mewtwo", "mew", "germignon", "macronium", "meganium", "hericendre", "feurisson", "typhlosion", "kaiminus", "crocrodil",
					"aligatueur", "fouinette", "fouinar", "hoothoot", "noarfang", "coxy", "coxyclaque", "mimigal", "migalos", "nostenfer", "loupio", "lanturn",
					"pichu", "melo", "toudoudou", "togepi", "togetic", "natu", "xatu", "wattouat", "lainergie", "pharamp", "joliflor", "marill", "azumarill",
					"simularbre", "tarpaud", "granivol", "floravol", "cotovol", "capumain", "tournegrin", "heliatronc", "yanma", "axoloto", "maraiste", "mentali",
					"noctali", "cornebre", "roigada", "feuforeve", "zarbi", "qulbutoke", "girafarig", "pomdepic", "foretress", "insolourdo", "scorplane", "steelix",
					"snubbull", "granbull", "qwilfish", "cizayox", "caratroc", "scarhino", "farfuret", "teddiursa", "ursaring", "limagma", "volcaropod", "marcacrin",
					"cochignon", "corayon", "remoraid", "octillery", "cadoizo", "demanta", "airmure", "malosse", "demolosse", "hyporoi", "phanpy", "donphan",
					"porygon2", "cerfrousse", "queulorior", "debugant", "kapoera", "lippouti", "elekid", "magby", "ecremeuh", "leuphorie", "raikou", "entei",
					"suicune", "embrylex", "ymphect", "tyranocif", "lugia", "ho-oh", "celebi"};

			int randomNumber = (int)(Math.random()*tab2.length);


			secretWord = tab2[randomNumber];

			//création tableau de booléens

			tabMot = new boolean[secretWord.length()];

			for(i = 0; i < secretWord.length(); i++){
				tabMot[i] = false;
			}

		}


	}

	public static void affichage(){
		compteurToWin = 0;
		String msg = "";
		int i;

		for (i = 0; i < secretWord.length(); i++){
			if(tabMot[i] == true){
				msg += secretWord.substring(i, i+1) + " ";
				compteurToWin++;
				if (compteurToWin >= secretWord.length()){
					win = true;
				}
			}
			else {
				msg += "_" + " ";
			}
		}

		affichage = msg;
		mot.setText(affichage);

	}

	public static void tabCounter(){
		int i;
		int counter = 0;

		for(i = 0; i < tabMot.length; i++){
			if (tabMot[i] == true){
				counter ++;
			}
		}



	}

	public static void checkGuess(){
		int i;

		if(secretWord.indexOf(Character.toString(lettreEntree))> -1){
			for(i = 0; i < secretWord.length(); i++){
				if (Character.toString(lettreEntree).equals(secretWord.substring(i,i+1))){
					tabMot[i] = true;
				}
			}

		}
		else{
			iErreurs++;
			if (iErreurs >= 10)lose = true;
			listeErreursVar += Character.toString(lettreEntree);

		}
	}

	//main-------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		NouvellePartie();
		affichage();
		Pendu test = new Pendu();
		test.setVisible(true);
		Focus();
	}
}

