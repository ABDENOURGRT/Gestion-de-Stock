import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//cette fenetre nous permit d'ajouter un produit

public class ajouterproduit extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField reffield;
	private JTextField nompfield;
	private JTextField quantfield;
	private JTextField prixfield;
	private JTextField catfield;
    
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
					ajouterproduit frame = new ajouterproduit();
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
	public ajouterproduit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit remplir les champs par les information du produit qu'on souhaite ajouter
				//on doit verifier que la referance introduite n'existe pas dans la table produit
				
				boolean trouve=false;//boolean qui nous permet de verifier l'existance de la referance introduite

				try {
					Statement stmt=DB.con.createStatement();
					String s="select * from produit ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {
						if(reffield.getText().equals(rs.getString("ref_pro"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==true) {
						JOptionPane.showMessageDialog(null, "cet produit existe dèja");
					}else {
						String ref = reffield.getText().toString();//recuperer la referance introduite
						String nomp = nompfield.getText().toString();//recuperer le nom de produit introduit
						String quant = quantfield.getText().toString();//recuperer la quantite introduite
						String prix = prixfield.getText().toString();//recuperer le prix introduit
						String c = catfield.getText().toString();//recuperer la categorie introduite
						int p=Integer.parseInt(prix);//convertir le prix de String a un int car dans la table produit c'est un int
						int q=Integer.parseInt(quant);//convertir la quantite de String a un int car dans la table produit c'est un int
						int k=Integer.parseInt(c);//convertir la categorie de String a un int car dans la table categorie c'est un int
						try {
						
							String z="insert into produit values(?,?,?,?,?)";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setString(1, ref);
							pr.setString(2, nomp);
							pr.setLong(3, q);
							pr.setLong(4, p);
							pr.setLong(5, k);
							pr.executeUpdate();
							
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}catch(SQLException e1){ 
					e1.printStackTrace();
					
				}
				fermer();
			}
		});
		btnNewButton.setBounds(388, 289, 112, 37);
		contentPane.add(btnNewButton);
		
		reffield = new JTextField();
		reffield.setBounds(136, 55, 159, 31);
		contentPane.add(reffield);
		reffield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("AJOUTER UN PRODUIT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(154, 0, 287, 44);
		contentPane.add(lblNewLabel);
		
		nompfield = new JTextField();
		nompfield.setColumns(10);
		nompfield.setBounds(136, 107, 159, 31);
		contentPane.add(nompfield);
		
		quantfield = new JTextField();
		quantfield.setColumns(10);
		quantfield.setBounds(136, 159, 159, 31);
		contentPane.add(quantfield);
		
		prixfield = new JTextField();
		prixfield.setColumns(10);
		prixfield.setBounds(136, 212, 159, 31);
		contentPane.add(prixfield);
		
		JLabel lblNewLabel_1 = new JLabel("REFERANCE :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 55, 116, 31);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NOM_PRODUIT :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 107, 116, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("QUANTITE :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(10, 159, 116, 31);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("PRIX :");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(10, 212, 116, 31);
		contentPane.add(lblNewLabel_1_3);
		
		catfield = new JTextField();
		catfield.setColumns(10);
		catfield.setBounds(136, 264, 159, 31);
		contentPane.add(catfield);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("CATEGORIE :");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setBounds(10, 264, 116, 31);
		contentPane.add(lblNewLabel_1_3_1);
	}

}
