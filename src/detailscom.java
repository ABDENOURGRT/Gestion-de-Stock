import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//cette fenetre nous permet d'afficher les details d'une commande

public class detailscom extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTextField idcomfield;
	private JTable tabledetails;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detailscom frame = new detailscom();
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
	public detailscom() {
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 732, 448);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DETAILS COMMANDE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(225, 11, 258, 34);
		contentPane.add(lblNewLabel);
		
		idcomfield = new JTextField();
		idcomfield.setBounds(90, 114, 142, 34);
		contentPane.add(idcomfield);
		idcomfield.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("ID_COM :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 114, 93, 34);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("AFFICHER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//on doit verifier si l'id_com saisi existe dans la table commande
				
				String id=idcomfield.getText().toString();//recuperer l'id_com 
				int i=Integer.parseInt(id);//convertir l'id_com de String a un int car dans la table commande c'est un int
				boolean trouve=false;//boolean qui nous permet de verifier si cette commande existe dans la table commande
				try {
					Statement stmt=DB.con.createStatement();
					String x="select * from commande ";
					ResultSet rx=stmt.executeQuery(x);
					while(rx.next()) {
						if(i==(rx.getInt("id_com"))) {
							trouve=true;
							break;
						} 
					}
					if(trouve) {
						try {
							
							//apres la verification on recupere les details de la commande de la table details_commande et les afficher
							//en cliquant sur le button afficher
							
							String s="select id_com,ref_pro,quantite,montant from details_commande where id_com=?";
							PreparedStatement pr = DB.con.prepareStatement(s); 
							pr.setInt(1, i);
							ResultSet rs=pr.executeQuery();
						    tabledetails.setModel(DbUtils.resultSetToTableModel(rs));
						}catch(SQLException e1){ 
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null, "cette commande n'existe pas");
					}
				}catch(SQLException e1){ 
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(105, 175, 110, 34);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(298, 56, 385, 342);
		contentPane.add(scrollPane);
		
		tabledetails = new JTable();
		scrollPane.setViewportView(tabledetails);
	}
}
