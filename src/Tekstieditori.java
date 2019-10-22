import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.awt.event.InputEvent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;


public class Tekstieditori extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnTiedosto;
	private JMenu mnMuokkaa;
	private JMenu mnTietoja;
	private JToolBar toolBar;
	private JMenuItem mntmAvaa;
	private JMenuItem mntmTallenna;
	private JMenuItem mntmSulje;
	private JMenuItem mntmEtsi;
	private JMenuItem mntmKorvaa;
	private JMenuItem mntmTietojaOhjelmasta;
	private JEditorPane editorPane;
	private JButton avaus;
	private JTextPane teksti;
	private JTextField syöte;
	private JButton haku;
	private JTextField tuloste;
	private JButton korvaa;
	private JLabel vaihto;
	private JButton tallennus;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tekstieditori frame = new Tekstieditori();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Tekstieditori() {
		setTitle("Juuson tekstieditori");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(700, 500);
		setLocationRelativeTo(null);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnTiedosto = new JMenu("Tiedosto");
		menuBar.add(mnTiedosto);
		
		mntmAvaa = new JMenuItem("Avaa");
		mntmAvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Avaa();
			}
		});
		
		mntmAvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmAvaa);
		
		mntmTallenna = new JMenuItem("Tallenna");
		mntmTallenna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tallenna();
			}
		});
		mntmTallenna.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmTallenna);
		
		mntmSulje = new JMenuItem("Sulje");
		mntmSulje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sulje();
			}
		});
		mntmSulje.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnTiedosto.add(mntmSulje);
		
		mnMuokkaa = new JMenu("Muokkaa");
		menuBar.add(mnMuokkaa);
		
		mntmEtsi = new JMenuItem("Etsi");
		mntmEtsi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etsi();				
			}
		});
		mntmEtsi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmEtsi);
		
		mntmKorvaa = new JMenuItem("Korvaa");
		mntmKorvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Korvaa();
			}
		});
		mntmKorvaa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnMuokkaa.add(mntmKorvaa);
		
		mnTietoja = new JMenu("Tietoja");
		menuBar.add(mnTietoja);
		
		mntmTietojaOhjelmasta = new JMenuItem("Tietoja ohjelmasta");
		mntmTietojaOhjelmasta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tietoja();
			}
		});
		mntmTietojaOhjelmasta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		mnTietoja.add(mntmTietojaOhjelmasta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		avaus = new JButton();
		Image open = new ImageIcon(this.getClass().getResource("open.png")).getImage();
		avaus.setIcon(new ImageIcon(open));
		avaus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Avaa();
			}
		});
		toolBar.add(avaus);
		
		
		tallennus = new JButton("");
		Image save = new ImageIcon(this.getClass().getResource("save.png")).getImage();
		tallennus.setIcon(new ImageIcon(save));
		tallennus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tallenna();
			}
		});
		toolBar.add(tallennus);
		
		syöte = new JTextField();
		toolBar.add(syöte);
		
		
		haku = new JButton("\tEtsi\t");
		haku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Etsi();
			}
		});
		
		vaihto = new JLabel();
		Image nuoli = new ImageIcon(this.getClass().getResource("arrow.png")).getImage();
		vaihto.setIcon(new ImageIcon(nuoli));
		toolBar.add(vaihto);
		
		tuloste = new JTextField();
		toolBar.add(tuloste);
		toolBar.add(haku);
		
		korvaa = new JButton("Korvaa");
		korvaa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Korvaa();
			}
		});
		toolBar.add(korvaa);
		
		editorPane = new JEditorPane();
		contentPane.add(editorPane, BorderLayout.CENTER);
	}
	
	private void Avaa() {
		//Tiedoston valintaikkuna
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.setDialogTitle("Valitse tiedosto");
		//valintaikkuna.setApproveButtonText("Avaa tiedosto");
		valintaikkuna.showOpenDialog(null);
		String rivi ="";
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		
		//Tiedoston lukemisen hoitava koodi
		try {
			
			Scanner lukija = null;
			File tiedosto = new File(uusiTiedosto);
			lukija = new Scanner(tiedosto);
			
			while (lukija.hasNextLine() ) {
				rivi += lukija.nextLine()+"\n";
				System.out.println(rivi);
			}
		} catch (FileNotFoundException p) {
			System.out.println("Tiedostoa ei löydy");
		}
		editorPane.setText(rivi);;
	}
	
	
	private void Tallenna() {
		//Tallennusikkuna
		JFileChooser valintaikkuna = new JFileChooser();
		valintaikkuna.setDialogTitle("Tallenna nimellä");
		//valintaikkuna.setApproveButtonText("Tallenna");
		valintaikkuna.showSaveDialog(null);
		
		String uusiTiedosto = valintaikkuna.getSelectedFile().getAbsolutePath();
		
		System.out.println("Tallennettava tiedosto: " + uusiTiedosto);
		
		//Tallennuksen koodi
		try {
			
			PrintWriter writer = new PrintWriter( uusiTiedosto );
			String sisalto = editorPane.getText();
			
			writer.println( sisalto );
			
			writer.flush();
			writer.close();
			
		} catch (Exception el) {
			System.out.println("Tallentamisessa tapahtui virhe!");
			el.printStackTrace();
		}
	}
	
	private void Sulje () {
		try {
			//Kysytään käyttäjältä haluaako hän tallentaa ennen sulkemista
			int valinta = JOptionPane.showConfirmDialog(null, "Tallennetaanko muutokset?", "Hetkinen!", JOptionPane.YES_NO_OPTION);
			if (valinta == JOptionPane.YES_OPTION) {
				Tallenna();
				System.exit(0);
			} else {
				System.exit(0);
			}
		} catch (Exception el) {
			el.printStackTrace();
		}
	}
	
	private void Etsi() {
		//Käytetään indexOf metodia
		
		String sisalto = editorPane.getText();
		sisalto = sisalto.toLowerCase();
		
		//Käyttäjä saa itse syöttää hakusanan
		String haettava = syöte.getText();
		int indeksi = sisalto.indexOf(haettava);
		System.out.println("Indeksi: " + indeksi);
		
		//Jostain syystä nappulaa käyttämällä ei vaihda väriä tekstikentästä ??
		//MenuItemistä vaihtaa
		editorPane.setSelectionColor(Color.RED);
		editorPane.setSelectionStart(indeksi);
		editorPane.setSelectionEnd(indeksi + haettava.length());
	}
	
	private void Korvaa() {
		String sisalto = editorPane.getText();
		
		//Luetaan minkä sanan käyttäjä haluaa muuttaa ja miksi
		String vanha = syöte.getText();
		String uusi = tuloste.getText();
		
		String korvattu = sisalto.replaceAll(vanha, uusi);
		editorPane.setText(korvattu);
	}
	
	private void Tietoja() {
		JFrame tietoja = new JFrame("Tietoja tekijästä");
		tietoja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tietoja.setBounds(100, 100, 450, 300);
		tietoja.setSize(300, 300);
		tietoja.setLocationRelativeTo(null);
		
		JLabel label = new JLabel();
		Image kuva = new ImageIcon(this.getClass().getResource("pic.jpg")).getImage();
		label.setIcon(new ImageIcon(kuva));
		
		teksti = new JTextPane();
		teksti.setText("\r\n\r\n\r\n\r\nT\u00E4m\u00E4n tekstieditorin on koodannut\r\nJuuso Nousiainen.\r\nAlla olennaisia linkkej\u00E4 sivuilleni\r\n\r\nGitHub:\r\nhttps://github.com/Nosseman\r\n");
		
		tietoja.getContentPane().add(label, BorderLayout.WEST);
		tietoja.getContentPane().add(teksti, BorderLayout.CENTER);
		tietoja.setVisible(true);
	}

}
