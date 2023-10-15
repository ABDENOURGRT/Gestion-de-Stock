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

public class modifiercom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idcomfield;
	
	void fermer() {
		dispose();//cette procedure permet de fermer une jframe
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifiercom frame = new modifiercom();
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
	public modifiercom() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 543, 205);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MODIFIER UNE COMMANDE");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(33, 11, 422, 37);
		contentPane.add(lblNewLabel);
		
		idcomfield = new JTextField();
		idcomfield.setBounds(149, 59, 149, 29);
		contentPane.add(idcomfield);
		idcomfield.setColumns(10);
		
		JButton btnNewButton = new JButton("MODIFIER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier que l'id_com qu'on saisi existe dans la table commande
				//on doit supprimer tout les details de cette dans la table details_commande comme ca on peut les modifier
				
               boolean trouve=false;//boolean qui nous permet de verifier l'xistance d'une commande
			   String idcom=idcomfield.getText().toString();//recuperer id_com
			   int id=Integer.parseInt(idcom);//convertir id_com
		        try {
					Statement stmt=DB.con.createStatement();
					String s="select * from commande ";
					ResultSet rs=stmt.executeQuery(s);
					while(rs.next()) {//la verification
						if(id==(rs.getInt("id_com"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve==false) {
						JOptionPane.showMessageDialog(null, "cette commande n'existe pas");
					}else {
						
						try {
						    //la suppretion des details de la commande qu'on souhaite modifier
							String z="delete from details_commande where id_com=?";
							PreparedStatement pr = DB.con.prepareStatement(z); 
							pr.setLong(1, id);
							pr.executeUpdate();
							
							//ResultSet rs=stmt.executeQuery(s);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						moddetailcom obj=new moddetailcom();
						obj.setVisible(trouve);
					  }
		          }catch (SQLException e1) {
						e1.printStackTrace();
					}
		          fermer();
		        }
		});
		btnNewButton.setBounds(383, 99, 112, 37);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("ID_COMMANDE :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(33, 59, 106, 29);
		contentPane.add(lblNewLabel_1);
	}

}
