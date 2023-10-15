import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class suppcli extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idsupfield;
    
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
					suppcli frame = new suppcli();
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
	public suppcli() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 197);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idsupfield = new JTextField();
		idsupfield.setBounds(141, 47, 120, 31);
		contentPane.add(idsupfield);
		idsupfield.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id_client :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(73, 47, 64, 31);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("supprimer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier que l'id_client saisi existe dans la table client
				//on supprime le client de la table client
				
				boolean trouve=false;//boolean qui nous permet de verifier l'existance d'un client
				String id = idsupfield.getText().toString();//recuperer id_client
		        int i=Integer.parseInt(id);//convertir id_client
		        try {//la verification
					Statement stmt=DB.con.createStatement();
					String s="select * from client ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {
						if(idsupfield.getText().equals(rs.getString("id_client"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cet client n'existe pas");
					}else {
						try {
						//la suppression de la table client
							String z="delete from client where id_client=?";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setLong(1, i);
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
		btnNewButton.setBounds(141, 104, 120, 31);
		contentPane.add(btnNewButton);
	}

}
