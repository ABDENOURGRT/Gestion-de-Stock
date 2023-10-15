import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class gestiondeproduit extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JTextField cherchfield;
	private JTable tablepro;
    
	void fermer() {
		dispose();//cette procedure permet de fermer une jframe
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gestiondeproduit frame = new gestiondeproduit();
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
	public gestiondeproduit() {
		getContentPane().setBackground(Color.YELLOW);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GESTION DES PRODUITS");
		lblNewLabel.setBounds(299, 11, 395, 86);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.setBounds(93, 151, 112, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ajouterproduit obj=new ajouterproduit();
				obj.setVisible(true);
			}
		});
		getContentPane().add(btnNewButton);
		
		JButton btnModifier = new JButton("MODIFIER");
		btnModifier.setBounds(93, 233, 112, 37);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifierproduit obj=new modifierproduit();
				obj.setVisible(true);
			}
		});
		getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("SUPPRIMER");
		btnSupprimer.setBounds(93, 316, 112, 37);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supprimerproduit obj=new supprimerproduit();
				obj.setVisible(true);
			}
		});
		getContentPane().add(btnSupprimer);
		
		JButton btnQuitter = new JButton("QUITTER");
		btnQuitter.setBounds(22, 492, 112, 37);
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame=new JFrame("EXIT");
				if(JOptionPane.showConfirmDialog(frame,"confirmer si vous voulez quitter","EXIT",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});
		getContentPane().add(btnQuitter);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(366, 130, 562, 263);
		getContentPane().add(scrollPane);
		
		tablepro = new JTable();
		scrollPane.setViewportView(tablepro);
		
		cherchfield = new JTextField();
		cherchfield.setBounds(653, 404, 144, 31);
		getContentPane().add(cherchfield);
		cherchfield.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("RECHERCHE PAR CATEGORIE");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ce button permet d'afficher les produit de la meme categorie saisi
				
				String idcat=cherchfield.getText().toString();//recuperer l'id_cat qu'on souhaite afficher
				int id=Integer.parseInt(idcat);//convertir l'id_cat
				try {
					String s="select * from produit where id_cat=?";
					PreparedStatement pr = DB.con.prepareStatement(s); 
					pr.setInt(1, id);
					ResultSet rs=pr.executeQuery();
					tablepro.setModel(DbUtils.resultSetToTableModel(rs));
					//ResultSet rs=stmt.executeQuery(s);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_4.setBounds(422, 404, 215, 31);
		getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("Actualiser");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updatetablepro();
			}
		});
		btnNewButton_1.setBounds(366, 93, 112, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("1)mobile");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2.setBounds(678, 454, 104, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2 = new JLabel("choisissez la cat\u00E9gorie a chercher suivant l'ordre suivant :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(299, 446, 359, 31);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("2)\u00E9lectroniques");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_1.setBounds(678, 473, 104, 14);
		getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("3) \u00E9lectrom\u00E9nager ");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_2.setBounds(678, 492, 123, 14);
		getContentPane().add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("4) informatiques ");
		lblNewLabel_1_2_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_3.setBounds(678, 515, 104, 14);
		getContentPane().add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_1_2_4 = new JLabel("5)kits solaires ");
		lblNewLabel_1_2_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_2_4.setBounds(678, 536, 104, 14);
		getContentPane().add(lblNewLabel_1_2_4);
		
		JButton btnNewButton_2_1_1 = new JButton("");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fermer();
				Menu obj=new Menu();
				obj.frame.setVisible(true);
				obj.frame.setLocationRelativeTo(null);
			}
		});
		btnNewButton_2_1_1.setIcon(new ImageIcon("C:\\Users\\GARTI Abdenour\\Desktop\\poo\\retour.png"));
		
		btnNewButton_2_1_1.setBackground(new Color(240, 240, 240));
		btnNewButton_2_1_1.setBounds(22, 11, 51, 46);
		getContentPane().add(btnNewButton_2_1_1);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		
		
	}
	public void updatetablepro() {
		
		//cette procedure permet d'afficher la table produit
		
		String s="select * from produit";
		try {
			Statement stmt=DB.con.createStatement();
			ResultSet rs=stmt.executeQuery(s);
		    tablepro.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e1){ 
			e1.printStackTrace();
			
		}
	}
}
