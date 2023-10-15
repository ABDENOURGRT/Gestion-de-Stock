import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.SystemColor;

public class gestiondeclients extends JFrame {
    DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField nomcfield;
	private JTextField pnomcfield;
	private JTextField adrfield;
	private JTextField mpfield;
	private JTable tablecli;
    
	void fermer() {
		dispose();//procedure permet de fermer une jframe
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestiondeclients frame = new gestiondeclients();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gestiondeclients() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DES CLIENTS");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(299, 11, 395, 86);
		contentPane.add(lblNewLabel);
		
		nomcfield = new JTextField();
		nomcfield.setBounds(197, 114, 121, 20);
		contentPane.add(nomcfield);
		nomcfield.setColumns(10);
		
		pnomcfield = new JTextField();
		pnomcfield.setBounds(197, 157, 121, 20);
		contentPane.add(pnomcfield);
		pnomcfield.setColumns(10);
		
		adrfield = new JTextField();
		adrfield.setBounds(197, 203, 121, 20);
		contentPane.add(adrfield);
		adrfield.setColumns(10);
		
		mpfield = new JTextField();
		mpfield.setBounds(197, 252, 121, 20);
		contentPane.add(mpfield);
		mpfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("NOM :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(108, 117, 63, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PRENOM :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(108, 160, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ADRESSE :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(89, 206, 98, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("MOT_DE_PASSE :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(67, 255, 120, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = nomcfield.getText().toString();//recuperer le nom du client qu'on souhaite ajouter
				String prenom = pnomcfield.getText().toString();//recuperer le prenom du client qu'on souhaite ajouter
				String adresse = adrfield.getText().toString();//recuperer l'adresse du client qu'on souhaite ajouter
				String motpass = mpfield.getText().toString();//recuperer le mot_de_passe du client qu'on souhaite ajouter
		
				try {
				    
					//l'insertion des informations du client qu'on souhaite ajouter dans la table client
					//pour l'id_client il s'incremente automatiquement
					
					String s="insert into client values(entry_id.nextval,?,?,?,?)";
					PreparedStatement pr = DB.con.prepareStatement(s); 
					pr.setString(1, nom);
					pr.setString(2, prenom);
					pr.setString(3, adresse);
					pr.setString(4, motpass);
					pr.executeUpdate();
					updatetablecli();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton.setBounds(33, 310, 112, 37);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("SUPPRIMER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suppcli obj=new suppcli();
				obj.setVisible(true);
			}
		});
	
		btnNewButton_1.setBounds(155, 310, 112, 37);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("MODIFIER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modcli obj=new modcli();
				obj.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(277, 310, 112, 37);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(491, 135, 419, 371);
		contentPane.add(scrollPane);
		
		tablecli = new JTable();
		scrollPane.setViewportView(tablecli);
		
		JButton btnNewButton_3 = new JButton("ACTUALISER");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetablecli();//on fait appelle a la procedure qui fait l'affichage de la table client
			}
		});
		btnNewButton_3.setBounds(490, 101, 112, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("QUITTER");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame("EXIT");
				if(JOptionPane.showConfirmDialog(frame,"confirmer si vous voulez quitter","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		btnNewButton_4.setBounds(33, 494, 112, 37);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			
			//ce button nous permet de retourner à la fenetre Menu càd la principale
			public void actionPerformed(ActionEvent e) {
				fermer();
				Menu obj=new Menu();
				obj.frame.setVisible(true);
				obj.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon("C:\\Users\\GARTI Abdenour\\Desktop\\poo\\retour.png"));
		btnNewButton_2_1.setBackground(new Color(240, 240, 240));
		btnNewButton_2_1.setBounds(22, 11, 51, 46);
		contentPane.add(btnNewButton_2_1);
	}
	
	//cette procedure permet d'afficher la table client
	public void updatetablecli() {
		String s="select * from client";
		try {
			Statement stmt=DB.con.createStatement();
			ResultSet rs=stmt.executeQuery(s);
		    tablecli.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e1){ 
			e1.printStackTrace();
			
		}
	}
	}

