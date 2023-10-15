import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//cette fenetre nous permit d'ajouter plusieus produits a une commande

public class ajoutdetailcom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idcomfield;
	private JTextField refpfield;
	private JTextField quantfield;
    
	void fermer() {
		dispose();//cette procedure permit de fermer une jframe
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ajoutdetailcom frame = new ajoutdetailcom();
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
	public ajoutdetailcom() {
		setBackground(Color.YELLOW);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 390);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DETAILS DE COMMANDE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(77, 11, 322, 32);
		contentPane.add(lblNewLabel);
		
		idcomfield = new JTextField();
		idcomfield.setBounds(109, 73, 112, 32);
		contentPane.add(idcomfield);
		idcomfield.setColumns(10);
		
		refpfield = new JTextField();
		refpfield.setColumns(10);
		refpfield.setBounds(141, 156, 112, 32);
		contentPane.add(refpfield);
		
		quantfield = new JTextField();
		quantfield.setColumns(10);
		quantfield.setBounds(141, 229, 112, 32);
		contentPane.add(quantfield);
		
		JLabel lblNewLabel_1 = new JLabel("ID_COM :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 73, 89, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("REF_PRO :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(60, 156, 89, 32);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("QUANTITE :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(60, 229, 89, 32);
		contentPane.add(lblNewLabel_1_2);
		
		JButton btnNewButton = new JButton("AJOUTER AU PANIER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit d'abord voir l'id_com de la commande ajouter juste avant d'afficher cette fenetre càd la fenetre ajoutcom
				//il faut saisir l'id_com de cette commande une seule fois dans le champ reservé et ne pas le modifier 
				//on verifier bien sur si la commande existe dans la table
				//on doit verifier que les referances des produits souhaites ajouter a la commande existe dans la table produit
				/*on doit verifier aussi si la quantite demandé d'un produit est disponible dans le stock si oui alors on retire la quantite
				demandé du stock càd mettre à jour le stock */
				
				//pour arreter d'ajouter des produit cliquez  sur le button terminer
				
				
				
				   String id=idcomfield.getText().toString();//recuperer l'id_com
				   String ref=refpfield.getText().toString();//recuperer la ref_pro
				   String quant=quantfield.getText().toString();//recuperer la quantite demandé
				  
				   int i=Integer.parseInt(id);//convertir l'id de String a un int car dans la table commande c'est un int
				   int j=Integer.parseInt(quant);//convertir la qauntite de String a un int car dans la table details_commande c'est un int
				   boolean trouve=false;//boolean qui nous permet de verifier si l'id_com saisi existe dans la table commande
					try {
						Statement stmt=DB.con.createStatement();
						String s="select * from commande ";
						ResultSet rs=stmt.executeQuery(s);
						while(rs.next()) {
							if(i==(rs.getInt("id_com"))) {
								trouve=true;
								break;
							} 
						}
						if(trouve==false) {
							JOptionPane.showMessageDialog(null, "cet commande n'existe pas");
						}else {
						
							boolean trouveref=false;//boolean qui nous permet de verifier si la ref_pro saisi existe dans la table produit
							try {
								Statement stm=DB.con.createStatement();
								String w="select * from produit ";
								ResultSet r=stm.executeQuery(w);
								while(r.next()) {
									if(ref.equals(r.getString("ref_pro"))) {
										trouveref=true;
										break;
									} 
								}
								if(trouveref) {
									try {
										
										String x="select quant_pro,prix_pro from produit where ref_pro=? ";
										PreparedStatement pk = DB.con.prepareStatement(x); 
										pk.setString(1, ref);
										ResultSet t=pk.executeQuery();
										int a = 0;
										int b = 0;
										while(t.next()) {
											 a=t.getInt("quant_pro");//recuperer la quantite du stock de produit demandé 
											 b=t.getInt("prix_pro");//recuperer le prix de produit demandé pour calculer le montant
										}
										
									    if(j<=a) { //la condition qui permet de comparer entre la quantité demandé et la quantite en stock
										  try {		
								            int m=j*b;//le calcule du montant= la quantite demandé * le prix de produit demandé
										    String z="insert into details_commande (id_com,ref_pro,quantite,montant) values(?,?,?,?)";
										    PreparedStatement pr = DB.con.prepareStatement(z); 
										    pr.setInt(1, i);
										    pr.setString(2, ref);
										    pr.setInt(3, j);
										    pr.setInt(4, m);
										    pr.executeQuery();
										    JOptionPane.showMessageDialog(null, "Produit ajouter au panier ");
										    int sub=a-j;//sub= la quantite en stock - la qunatite demandé
										    try {
										    	String n="update produit set quant_pro=? where ref_pro=? ";
										    	//mettre à jour la quantite de stock
												PreparedStatement ph = DB.con.prepareStatement(n); 
												ph.setInt(1, sub);
												ph.setString(2, ref);
												ph.executeQuery();
										    }catch (SQLException e1) {
										        e1.printStackTrace();
									         }
										
										//ResultSet rs=stmt.executeQuery(s);
									         } catch (SQLException e1) {
										        e1.printStackTrace();
									         }
									    }else {
									    	JOptionPane.showMessageDialog(null, "il ne reste pas dans le stock la quantité que vous avez demandé");
									    }
									    
									} catch (SQLException e1) {
								        e1.printStackTrace();
							         }
								}else {
									JOptionPane.showMessageDialog(null, "cet produit n'existe pas");
								    }
								
								
								} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}catch(SQLException e1){ 
						e1.printStackTrace();
						
					}
					
			}
		});
		btnNewButton.setBounds(150, 298, 176, 41);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("TERMINER");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ce button nous permet de terminer notre commande
				
				fermer();
			}
		});
		btnNewButton_1.setBounds(368, 307, 98, 32);
		contentPane.add(btnNewButton_1);
	
		}
   }
