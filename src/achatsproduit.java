import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
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


/*cette fenetre permit d'afficher les achats d'un produit connu par sa referance qu'on doit saisir*/
public class achatsproduit extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTable tableachatpro;
	private JButton btnNewButton;
	private JTextField refpfield;
	private JLabel lblNewLabel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					achatsproduit frame = new achatsproduit();
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
	public achatsproduit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 492);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ACHATS D'UN PRODUIT");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 11, 345, 48);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 51, 486, 295);
		contentPane.add(scrollPane);
		
		tableachatpro = new JTable();
		scrollPane.setViewportView(tableachatpro);
		
		btnNewButton = new JButton("AFFICHER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*on doit chercher d'abord la referance introduite si elle existe dans le stock de produit si oui alors on affiche unoquement la liste 
				 de details_commande qui contient le produit avec la referance introduite */
				String ref=refpfield.getText().toString();
				boolean trouveref=false;
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
							
							String x="select * from details_commande where ref_pro=? ";
							PreparedStatement pk = DB.con.prepareStatement(x); 
							pk.setString(1, ref);
							ResultSet t=pk.executeQuery();
							tableachatpro.setModel(DbUtils.resultSetToTableModel(t));
						}catch (SQLException e1) {
					        e1.printStackTrace();
				         }
					}
				}catch (SQLException e1) {
			        e1.printStackTrace();
		         }
			}
		});
		btnNewButton.setBounds(412, 377, 132, 38);
		contentPane.add(btnNewButton);
		
		refpfield = new JTextField();
		refpfield.setBounds(194, 377, 185, 38);
		contentPane.add(refpfield);
		refpfield.setColumns(10);
		
		lblNewLabel_1 = new JLabel("LA REFERANCE  PRODUIT :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(29, 377, 167, 39);
		contentPane.add(lblNewLabel_1);
	}

}
