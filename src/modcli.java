import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class modcli extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idmodfield;
	private JTextField nomcmodfield;
	private JTextField pnomcmodfield;
	private JTextField adrmodfield;
	private JTextField mpmodfield;
    
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
					modcli frame = new modcli();
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
	public modcli() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("MODIFIER");
		btnNewButton.setBounds(388, 289, 112, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean trouve=false;
				try {
					Statement stmt=DB.con.createStatement();
					String s="select * from client ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {
						String b=idmodfield.getText().toString();
						int a=Integer.parseInt(b);
						if(a==(rs.getInt("id_client"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cet client n'existe pas");
					}else {
						String id=idmodfield.getText().toString();
						String nom = nomcmodfield.getText().toString();
						String pnom = pnomcmodfield.getText().toString();
						String adresse = adrmodfield.getText().toString();
						String mp = mpmodfield.getText().toString();
						int x=Integer.parseInt(id);
						try {
						
							String z="update client set nom=?,prenom=?,adresse=?,mot_de_passe=? where id_client=?";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setString(1, nom);
							pr.setString(2, pnom);
							pr.setString(3, adresse);
							pr.setString(4, mp);
							pr.setInt(5, x);
							pr.executeUpdate();
							JOptionPane.showMessageDialog(null, "les données ont été modifier avec succes");
							//ResultSet rs=stmt.executeQuery(s);
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
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("MODIFIER LES DONNEES D'UN CLIENT");
		lblNewLabel.setBounds(82, 0, 418, 44);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		contentPane.add(lblNewLabel);
	
		
		
		
		JLabel lblNewLabel_1 = new JLabel("ID_CLIENT :");
		lblNewLabel_1.setBounds(10, 55, 116, 31);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NOM_CLIENT :");
		lblNewLabel_1_1.setBounds(10, 112, 116, 31);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("PRENOM_CLIENT :");
		lblNewLabel_1_2.setBounds(10, 164, 116, 31);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("ADRESSE :");
		lblNewLabel_1_3.setBounds(10, 217, 116, 31);
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1_3);
	
		
		JLabel lblNewLabel_1_3_1 = new JLabel("MOT_PASS :");
		lblNewLabel_1_3_1.setBounds(10, 269, 116, 31);
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel_1_3_1);
		
		idmodfield = new JTextField();
		idmodfield.setBounds(123, 55, 143, 31);
		contentPane.add(idmodfield);
		idmodfield.setColumns(10);
		
		nomcmodfield = new JTextField();
		nomcmodfield.setColumns(10);
		nomcmodfield.setBounds(123, 112, 143, 31);
		contentPane.add(nomcmodfield);
		
		pnomcmodfield = new JTextField();
		pnomcmodfield.setColumns(10);
		pnomcmodfield.setBounds(123, 164, 143, 31);
		contentPane.add(pnomcmodfield);
		
		adrmodfield = new JTextField();
		adrmodfield.setColumns(10);
		adrmodfield.setBounds(123, 217, 143, 31);
		contentPane.add(adrmodfield);
		
		mpmodfield = new JTextField();
		mpmodfield.setColumns(10);
		mpmodfield.setBounds(123, 269, 143, 31);
		contentPane.add(mpmodfield);
	}
	
}
