import java.awt.BorderLayout;
import java.awt.Color;
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
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class supprimerproduit extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField refchfield;
	/**
	 * Launch the application.
	 */
	void fermer() {
		dispose();
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supprimerproduit frame = new supprimerproduit();
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
	public supprimerproduit() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 428, 256);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("SUPPRIMER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on doit verifier que la ref_pro saisi existe dans la table produit
				//on supprime le produit de la table produit
				
                 boolean trouve=false;//boolean qui nous permet de verifier l'existance d'un produit
				
				try {
					//la verification
					Statement stmt=DB.con.createStatement();
					String s="select * from produit ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {
						if(refchfield.getText().equals(rs.getString("ref_pro"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cet produit n'existe pas");
					}else {
						String ref = refchfield.getText().toString();
						
						try {
							//la suppression de la table client
							String z="delete from produit where ref_pro=?";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setString(1, ref);
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
		btnNewButton.setBounds(159, 117, 112, 37);
		contentPane.add(btnNewButton);
		
		refchfield = new JTextField();
		refchfield.setBounds(136, 55, 159, 31);
		contentPane.add(refchfield);
		refchfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SUPPRIMER UN PRODUIT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(46, 0, 287, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REFERANCE :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 55, 116, 31);
		contentPane.add(lblNewLabel_1);
	}
	}


