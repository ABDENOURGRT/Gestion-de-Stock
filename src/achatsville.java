import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;


/*cette fenetre permit d'afficher les achats par une ville qu'on doit saisir*/
public class achatsville extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTable tableville;
	private JButton btnNewButton;
	private JTextField villefield;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					achatsville frame = new achatsville();
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
	public achatsville() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 492);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ACHATS PAR VILLE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 11, 345, 48);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 51, 486, 295);
		contentPane.add(scrollPane);
		
		tableville = new JTable();
		scrollPane.setViewportView(tableville);
		
		btnNewButton = new JButton("AFFICHER");//pour les details des commandes faite par les clients de cette ville
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*on doit verifier d'abord s'il existe un client qui habite dans la ville saisi si oui alors :
				apres on verifier s'il a fait une commande bien sur en utilisant son id dans la recherche si oui alors:
				on affiche les details des commandes faite par les clients de cette ville */
				
				String ville=villefield.getText().toString();//recuperer la ville a afficher
				boolean trouveville=false;//boolean qui nous permet de savoir s'il existe un client qui habite dans la ville saisi 
				try {
					Statement stm=DB.con.createStatement();
					String w="select * from client ";
					ResultSet r=stm.executeQuery(w);
					while(r.next()) {
						if(ville.equals(r.getString("adresse"))) {
							trouveville=true;
							break;
						} 
					}
					if(trouveville) {
						try {
							
							String x="select id_client from client where adresse=? ";
							PreparedStatement pk = DB.con.prepareStatement(x); 
							pk.setString(1, ville);
							ResultSet t=pk.executeQuery();
							while(t.next()) {
								int id=t.getInt("id_client");
								boolean trouveidcli=false;//boolean qui nous permet de savoir s'il existe des commandes faite par des clients de cette ville 
								try {
									String p="select * from commande";
									Statement stmt=DB.con.createStatement();
									ResultSet rs=stmt.executeQuery(p);
									while(rs.next()) {
										if(id==(r.getInt("id_client"))) {
											trouveidcli=true;
											break;
										} 
									}
									if(trouveidcli) {
										try {
											String z="select id_com from commande where id_client=?";
											PreparedStatement pr = DB.con.prepareStatement(z); 
											pr.setInt(1, id);
											ResultSet rh=pr.executeQuery();
											while(rh.next()) {
												int b=rh.getInt("id_com");
												
												try {
													String h="select * from details_commande";
													Statement stmts=DB.con.createStatement();
													ResultSet rk=stmts.executeQuery(h);
													boolean trouve1=false;//boolean qui nous permet de savoir s'il existe des details d'une commande faite par des clients de cette ville
													while(rk.next()) {
														if(b==(rk.getInt("id_com"))) {
															trouve1=true;
														} 
													
														if(trouve1) {
															try {
																String l="select * from details_commande where id_com=?";
																PreparedStatement prs = DB.con.prepareStatement(l); 
																prs.setInt(1, b);
																ResultSet rbs=prs.executeQuery();
																tableville.setModel(DbUtils.resultSetToTableModel(rbs));
																
															}catch (SQLException e1) {
														        e1.printStackTrace();
													         }
														}
															
														
													}
													
												}catch (SQLException e1) {
											        e1.printStackTrace();
										         }
											}
										}catch (SQLException e1) {
									        e1.printStackTrace();
								         }
									}
								}catch (SQLException e1) {
							        e1.printStackTrace();
						         }
								
							}
							
						}catch (SQLException e1) {
					        e1.printStackTrace();
				         }
					}else {
						JOptionPane.showMessageDialog(null, "Aucun client avec cette adresse");
					}
				}catch (SQLException e1) {
			        e1.printStackTrace();
		         }
			}
		});
		btnNewButton.setBounds(412, 377, 129, 30);
		contentPane.add(btnNewButton);
		
		villefield = new JTextField();
		villefield.setBounds(194, 377, 185, 38);
		contentPane.add(villefield);
		villefield.setColumns(10);
		
		lblNewLabel_1 = new JLabel("LA VILLE A AFFICHER :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(56, 377, 140, 39);
		contentPane.add(lblNewLabel_1);
	}

}
