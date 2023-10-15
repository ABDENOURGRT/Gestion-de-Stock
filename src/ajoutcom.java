import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

//cette fenetre permit de saisir les informations de la commande a ajouter càd id_client,type et la date

public class ajoutcom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idclifield;
	private JTextField datefield;
	private JTextField typefield;
    
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
					ajoutcom frame = new ajoutcom();
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
	public ajoutcom() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 547, 356);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("AJOUTER UNE COMMANDE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(42, 11, 422, 37);
		contentPane.add(lblNewLabel);
		
		idclifield = new JTextField();
		idclifield.setBounds(103, 118, 149, 29);
		contentPane.add(idclifield);
		idclifield.setColumns(10);
		
		datefield = new JTextField();
		datefield.setColumns(10);
		datefield.setBounds(342, 118, 149, 29);
		contentPane.add(datefield);
		
		JButton btnNewButton = new JButton("AJOUTER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier que l'id_client qu'on a saisi existe dans la table client càd il faut que ce client aie un compte
				//pour le type il faut saisir seulement 1 pour satisfaites ou 0 pour en instance
				//la date est une chaine de caractere String
				
				/*si tout les testes sont juste il suffit de cliquer sur le button ajouter et ca va nous ouvrir une nouvelle fenetre 
				ajoutdetailcom qui nous permet de saisir les produit commandé  */
				   
				   String id=idclifield.getText().toString();//recuperer l'id_client
				   String date=datefield.getText().toString();//recuperer la date
				   String type=typefield.getText().toString();//recuperer le type
				   int typ=Integer.parseInt(type);//convertir le type de String a un int car dans la table commande c'est un int
				   int i=Integer.parseInt(id);//convertir l'id de String a un int car dans la table client c'est un int
				   boolean trouve=false;//boolean qui nous permet de verifier si cet client a un compte
				   if(!id.equals("") && !date.equals("") && !type.equals("")) {
				    try {
						Statement stmt=DB.con.createStatement();
						String s="select * from client ";
						ResultSet rs=stmt.executeQuery(s);
						while(rs.next()) {
							if(i==(rs.getInt("id_client"))) {
								trouve=true;
								break;
							} 
						}
						if(trouve==false) {
							JOptionPane.showMessageDialog(null, "cet client n'existe pas");
						}else {
							if(typ==0 || typ==1) {
								String w="insert into commande (id_com,id_client,date_com,type) values(entry_id.nextval,?,?,?)";
								// entry_id.nextval pour l'auto increment dans la BDD
								try {
									PreparedStatement pr = DB.con.prepareStatement(w); 
									pr.setInt(1, i);
									pr.setString(2, date);
									pr.setInt(3, typ);
									pr.executeUpdate();
									ajoutdetailcom obj=new ajoutdetailcom();
									obj.setVisible(true);
									
		
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
							}else {
								JOptionPane.showMessageDialog(null, "ce type n'existe pas");
							}
							
						}
					}catch(SQLException e1){ 
						e1.printStackTrace();
						
					}
                 fermer();
			}else {
				JOptionPane.showMessageDialog(null, "Veillez saisir les champs vides svp");
			}
			}	   
		});
		btnNewButton.setBounds(379, 225, 112, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ID_CLIENT :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 118, 106, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DATE :");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(262, 118, 90, 29);
		contentPane.add(lblNewLabel_2);
		
		typefield = new JTextField();
		typefield.setColumns(10);
		typefield.setBounds(103, 166, 112, 32);
		contentPane.add(typefield);
		
		JLabel lblNewLabel_1_3 = new JLabel("TYPE :");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(10, 166, 89, 32);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tapez pour le type comme suivant :");
		lblNewLabel_2_1.setBounds(10, 209, 200, 26);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_3 = new JLabel("0 : en instance");
		lblNewLabel_3.setBounds(171, 236, 103, 23);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("1 : satisfaites");
		lblNewLabel_3_1.setBounds(171, 259, 103, 23);
		contentPane.add(lblNewLabel_3_1);
	}
}
