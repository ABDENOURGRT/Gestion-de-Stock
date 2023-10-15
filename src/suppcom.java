import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class suppcom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idcomfield;
    
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
					suppcom frame = new suppcom();
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
	public suppcom() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 410, 249);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SUPPRIMER UNE COMMANDE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(-14, 11, 422, 37);
		contentPane.add(lblNewLabel);
		
		idcomfield = new JTextField();
		idcomfield.setBounds(130, 80, 169, 30);
		contentPane.add(idcomfield);
		idcomfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID_COMMANDE :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(24, 80, 103, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("SUPPRIMER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier que l'id_com saisi existe dans la table commande
				//on supprime la commande de la table commande ansi que ses details de la table details_commande
				
                boolean trouve=false;//boolean qui nous permet de verifier l'existance d'une commande
                String ids=idcomfield.getText().toString();//recuperer id_com
                int id=Integer.parseInt(ids);//convertir id_com
				try {//la verification
					Statement stmt=DB.con.createStatement();
					String s="select * from commande ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {
						if(id==(rs.getInt("id_com"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cette commande n'existe pas");
					}else {
						try {
							//la suppression de la table commande
							String z="delete from commande where id_com=?";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setInt(1, id);
							pr.executeUpdate();
							
							try {
								//la suppression de la table details_commande
								String c="delete from details_commande where id_com=?";
								PreparedStatement ps = DB.con.prepareStatement(c); 
								ps.setInt(1, id);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "commande a été supprimé");
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
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
		btnNewButton.setBounds(139, 144, 112, 37);
		contentPane.add(btnNewButton);
	}

}
