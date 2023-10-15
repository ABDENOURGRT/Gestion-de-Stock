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

public class moddetailcom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idcomfield;
	private JTextField datefield;
	private JTextField refpfield;
	private JTextField quantfield;
	private JTextField typefield;
	private JTextField idclifield;
    
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
					moddetailcom frame = new moddetailcom();
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
	public moddetailcom() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 418);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MODIFIER DETAILS DE COMMANDE");
		lblNewLabel.setBounds(123, 11, 380, 43);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		idcomfield = new JTextField();
		idcomfield.setBounds(97, 69, 115, 31);
		contentPane.add(idcomfield);
		idcomfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID_COM :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 69, 87, 31);
		contentPane.add(lblNewLabel_1);
		
		datefield = new JTextField();
		datefield.setColumns(10);
		datefield.setBounds(433, 298, 115, 31);
		contentPane.add(datefield);
		
		refpfield = new JTextField();
		refpfield.setColumns(10);
		refpfield.setBounds(112, 152, 115, 31);
		contentPane.add(refpfield);
		
		quantfield = new JTextField();
		quantfield.setColumns(10);
		quantfield.setBounds(112, 214, 115, 31);
		contentPane.add(quantfield);
		
		typefield = new JTextField();
		typefield.setColumns(10);
		typefield.setBounds(433, 65, 115, 31);
		contentPane.add(typefield);
		
		JLabel lblNewLabel_1_1 = new JLabel("REF_PRO :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(24, 152, 87, 31);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("QUANTITE :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(24, 214, 87, 31);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("TYPE :");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(346, 65, 87, 31);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("DATE :");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setBounds(346, 298, 87, 31);
		contentPane.add(lblNewLabel_1_4);
		
		JButton btnNewButton = new JButton("MODIFIER LE PANIER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//ce button permet de modifier les details d'une commande càd faire un nouveau panier en gardant le id_com
				//insi changer le type de la commande
				//changer le client 
				//changer la date
				//pour arreter la modification on clique sur le button terminer
				
				//on doit verifier que cette commande existe 
				
				
				   boolean trouve=false;//boolean qui nous permet de verifier l'xistance d'une commande
				   String idcli=idclifield.getText().toString();//recuperer id_client
				   String id=idcomfield.getText().toString();//recuperer id_com a modifier
				   String ref=refpfield.getText().toString();//recupere la referance
				   String quant=quantfield.getText().toString();//recuperer la quantite
				   String type=typefield.getText().toString();//recuperer le type
				   int typ=Integer.parseInt(type);//convertir le type
				   int i=Integer.parseInt(id);//convertir le id_com
				   int v=Integer.parseInt(idcli);//convertir id_client
				   int j=Integer.parseInt(quant);//convertir la quantite
					try {
						Statement stmt=DB.con.createStatement();
						String s="select * from commande ";
						ResultSet rs=stmt.executeQuery(s);
						while(rs.next()) {
							if(i==(rs.getInt("id_com"))) {
								trouve=true;
								break;//c'est la verification de l'existance de la commande
							} 
						}
						if(trouve==false) {
							JOptionPane.showMessageDialog(null, "cet commande n'existe pas");
						}else {
							if(typ==0 || typ==1) {
								boolean trouvecli=false;//boolean qui nous permet de verifier l'xistance d'un client
							try {
								Statement stmtr=DB.con.createStatement();
								String u="select * from client ";
								ResultSet lm=stmt.executeQuery(u);
								while(lm.next()) {//on doit verifier si le nouveau client a deja un compte
									if(v==(lm.getInt("id_client"))) {
										trouvecli=true;
										break;
									} 
								}
							    if(trouvecli==false) {
							    	JOptionPane.showMessageDialog(null, "cet client n'existe pas");
							    }else {
							    	try {
							    		
							    		//metre à jour le type et id_client dans la table commande
								    	String q="update commande set type=?,id_client=? where id_com=? ";
										PreparedStatement pq = DB.con.prepareStatement(q); 
										pq.setInt(1, typ);
										pq.setInt(2, v);
										pq.setInt(3, i);
										pq.executeQuery();
								    }catch (SQLException e1) {
								        e1.printStackTrace();
							         }
								
								 
								boolean trouveref=false;//boolean qui nous permet de verifier l'xistance d'un produit
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
											//on doit verifier que la quantite demandé est disponible dans les stock
											String x="select quant_pro,prix_pro from produit where ref_pro=? ";
											PreparedStatement pk = DB.con.prepareStatement(x); 
											pk.setString(1, ref);
											ResultSet t=pk.executeQuery();
											int a = 0;
											int b = 0;
											while(t.next()) {
												 a=t.getInt("quant_pro");//recuperer la quantite du produit en stock
												 b=t.getInt("prix_pro");//recuperer son prix pour calculer le montant
											}
										    if(j<=a) {//comparer entre la quantite demandé et la quantite en stock
											  try {		
									            int m=j*b;//le calcule du montant = prix_pro * la quantite demandé
									            //ajouter à la table details_commande les informations necessaire
											    String z="insert into details_commande (id_com,ref_pro,quantite,montant) values(?,?,?,?)";
											    PreparedStatement pr = DB.con.prepareStatement(z); 
											    pr.setInt(1, i);
											    pr.setString(2, ref);
											    pr.setInt(3, j);
											    pr.setInt(4, m);
											    pr.executeQuery();
											    int sub=a-j;//la nouvelle quantite du produit en stock
											    try {
											    	//mettre à jour les informations en stock sur les produit commandé
											    	String n="update produit set quant_pro=? where ref_pro=? ";
													PreparedStatement ph = DB.con.prepareStatement(n); 
													ph.setInt(1, sub);
													ph.setString(2, ref);
													ph.executeQuery();
											    }catch (SQLException e1) {
											        e1.printStackTrace();
										         }
											
											
										         } catch (SQLException e1) {
											        e1.printStackTrace();
										         }
											  JOptionPane.showMessageDialog(null, "les détails de la commande ont été modifier avec succes");
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
								}catch (SQLException e1) {
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "ce type n'existe pas");
							}
						}
					}catch(SQLException e1){ 
						e1.printStackTrace();
						
					}
			}
		});
		btnNewButton.setBounds(85, 271, 169, 31);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Tapez pour le type comme suivant :");
		lblNewLabel_2.setBounds(261, 126, 200, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("0 : en instance");
		lblNewLabel_3.setBounds(423, 160, 103, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("1 : satisfaites");
		lblNewLabel_3_1.setBounds(423, 194, 103, 23);
		contentPane.add(lblNewLabel_3_1);
		
		idclifield = new JTextField();
		idclifield.setColumns(10);
		idclifield.setBounds(433, 248, 115, 31);
		contentPane.add(idclifield);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("ID_CLIENT :");
		lblNewLabel_1_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4_1.setBounds(346, 248, 87, 31);
		contentPane.add(lblNewLabel_1_4_1);
		
		JButton btnNewButton_1 = new JButton("TERMINER");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			//ce button nous permet de terminer la modification d'une commande
			public void actionPerformed(ActionEvent e) {
				fermer();
			}
		});
		btnNewButton_1.setBounds(108, 317, 104, 31);
		contentPane.add(btnNewButton_1);
	}

}
