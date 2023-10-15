import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

public class produitmoins extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTable table;
	private JTable tablepromoins;
	private JTable tableprostock;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					produitmoins frame = new produitmoins();
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
	
	public int nbrepit(String s) {
		
		//cette fonction return le nombre de repititon d'une ref_pro dans la table details_commande
		
		int z=0;
		try {
			String h="select * from details_commande";
			PreparedStatement pk = DB.con.prepareStatement(h); 
			ResultSet t=pk.executeQuery();
			while(t.next()) {
				if(s.equals(t.getString("ref_pro"))) {
					z=z+1;
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return z;
		
	}
	
	public produitmoins() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 693, 468);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PRODUIT MOINS VENDU");
		lblNewLabel.setBounds(166, 0, 345, 48);
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(166, 59, 486, 191);
		contentPane.add(scrollPane);
	
		
		tablepromoins = new JTable();
		scrollPane.setViewportView(tablepromoins);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(166, 314, 486, 39);
		contentPane.add(scrollPane_1);
		
		tableprostock = new JTable();
		scrollPane_1.setViewportView(tableprostock);
		
		tableprostock = new JTable();
		scrollPane_1.setViewportView(tableprostock);
		
		JButton btnNewButton = new JButton("Actualiser");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  
				//on doit calculer le produit qui se reppete le minimum de fois dans la table details_commande
				
				try {
					String z="select min(count(*)) as moins from details_commande group by ref_pro";
					PreparedStatement pk = DB.con.prepareStatement(z); 
					ResultSet t=pk.executeQuery();
					int min=0;
					
					while(t.next()) {
						min=t.getInt("moins");//recuperer le nombre minimum
					}
					try {
						String x=" select ref_pro from details_commande ";
						PreparedStatement p = DB.con.prepareStatement(x); 
						ResultSet rs=p.executeQuery();
						String ref = null;
						while(rs.next()) {
							ref=rs.getString("ref_pro");//recuperer chaque ref_pro dans la table details_commande
							//on fait appelle à la fonction nbrepit avec la ref_pro recupere
							//on le compare avec le min de tout la table details_commande
							//s'il sont egaux alors c'est le produit le moins vendu
							if(nbrepit(ref)==min) {
								try {
									//afficher tout les details des commandes qui ont le produit le moins vendu
									String k="select * from details_commande where ref_pro=?";
									PreparedStatement prs = DB.con.prepareStatement(k); 
									prs.setString(1, ref);
									ResultSet rk=prs.executeQuery();
									tablepromoins.setModel(DbUtils.resultSetToTableModel(rk));
									try {
										//afficher les informations du produit moins vendu en stock
										String m="select * from produit where ref_pro=?";
										PreparedStatement pm = DB.con.prepareStatement(m); 
										pm.setString(1, ref);
										ResultSet rm=pm.executeQuery();
										tableprostock.setModel(DbUtils.resultSetToTableModel(rm));
									}catch (SQLException e1) {
								        e1.printStackTrace();
							         }
								}catch (SQLException e1) {
							        e1.printStackTrace();
						         }
							}
						}
						
						}catch (SQLException e1) {
					        e1.printStackTrace();
				         }
				}catch (SQLException e1) {
			        e1.printStackTrace();
		         }
			}
		});
		btnNewButton.setBounds(26, 56, 114, 28);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Les details du produit le moins vendu en stock :");
		lblNewLabel_1.setBounds(26, 293, 485, 14);
		contentPane.add(lblNewLabel_1);
	}

}
