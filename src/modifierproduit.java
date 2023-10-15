import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class modifierproduit extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	
	private JTextField refmodfield;
	private JTextField nompmodfield;
	private JTextField quantmodfield;
	private JTextField prixmodfield;
	private JTextField catmodfield;
    
	void fermer() {
		dispose();
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifierproduit frame = new modifierproduit();
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
	public modifierproduit() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("MODIFIER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier que la ref_pro saisi existe dans la table produit
				//verifier si la categorie saisi existe dans la table categorie
				
				
				boolean trouve=false;//boolean qui nous permet de verifier l'xistance d'un produit
				try {
					Statement stmt=DB.con.createStatement();
					String s="select * from produit ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {//la verification
						if(refmodfield.getText().equals(rs.getString("ref_pro"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cet produit n'existe pas");
					}else {
						String ref=refmodfield.getText().toString();//recuperer la ref_pro
						String nomp=nompmodfield.getText().toString();//recuperer le nom de produit
						String quant = quantmodfield.getText().toString();//recuperer la quntite du produit
						String prix = prixmodfield.getText().toString();//recuperer son prix
						String cat = catmodfield.getText().toString();//recuperer sa categorie
						int x=Integer.parseInt(quant);//convertir la quantite
						int y=Integer.parseInt(prix);//convertir le prix
						int w=Integer.parseInt(cat);//convertir la categorie
						boolean trouvecat=false;
						try {//verification categorie
							Statement stm=DB.con.createStatement();
							String j="select * from categorie ";
							ResultSet rm=stm.executeQuery(j);
							while(rm.next()) {
								if(w==(rm.getInt("id_cat"))) {
									trouvecat=true;
									break;
								} 
							}
							if(trouvecat==true) {	
								//mettre à jour les informations du produit modifier
						      String z="update produit set nom_pro=?,quant_pro=?,prix_pro=?,id_cat=? where ref_pro=?";
						      PreparedStatement pr = DB.con.prepareStatement(z); 
						      pr.setString(1, nomp);
						      pr.setInt(2, x);
						      pr.setInt(3, y);
						      pr.setInt(4, w);
						      pr.setString(5, ref);
						      pr.executeUpdate();
						      JOptionPane.showMessageDialog(null, "les données ont été modifier avec succes");
						//ResultSet rs=stmt.executeQuery(s);
							}else {
								JOptionPane.showMessageDialog(null, "cette catégorie n'existe");
							}
                     }  catch (SQLException e1) {
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
		
		refmodfield = new JTextField();
		refmodfield.setBounds(136, 55, 159, 31);
		contentPane.add(refmodfield);
		refmodfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("MODIFIER UN PRODUIT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(154, 0, 287, 44);
		contentPane.add(lblNewLabel);
		
		nompmodfield = new JTextField();
		nompmodfield.setColumns(10);
		nompmodfield.setBounds(136, 107, 159, 31);
		contentPane.add(nompmodfield);
		
		quantmodfield = new JTextField();
		quantmodfield.setColumns(10);
		quantmodfield.setBounds(136, 159, 159, 31);
		contentPane.add(quantmodfield);
		
		prixmodfield = new JTextField();
		prixmodfield.setColumns(10);
		prixmodfield.setBounds(136, 212, 159, 31);
		contentPane.add(prixmodfield);
		
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
		
		catmodfield = new JTextField();
		catmodfield.setColumns(10);
		catmodfield.setBounds(136, 264, 159, 31);
		contentPane.add(catmodfield);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("CATEGORIE :");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setBounds(10, 264, 116, 31);
		contentPane.add(lblNewLabel_1_3_1);
	}

}
