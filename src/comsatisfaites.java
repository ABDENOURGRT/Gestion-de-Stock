import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

//cette fenetre permet d'afficher les commandes en instance en cliquant sur le button actualiser

public class comsatisfaites extends JFrame {
	DataBasesCnct DB=new DataBasesCnct("tpPOO", "tpPOO");
	private JPanel contentPane;
	private JTable tablecomsatis;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					comsatisfaites frame = new comsatisfaites();
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
	public comsatisfaites() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 412);
		contentPane = new JPanel();
		contentPane.setBackground(Color.YELLOW);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COMMANDE SATISFAITES");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(120, 11, 345, 48);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 51, 486, 295);
		contentPane.add(scrollPane);
		
		tablecomsatis = new JTable();
		scrollPane.setViewportView(tablecomsatis);
		
		JButton btnNewButton = new JButton("ACTUALISER");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//afficher de la table commande ou le type=1 càd satisfaites
				
				String s="select * from commande where type='1'";
				try {
					Statement stmt=DB.con.createStatement();
					ResultSet rs=stmt.executeQuery(s);
					tablecomsatis.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(SQLException e1){ 
					e1.printStackTrace();
					
				}
			}
		});
		btnNewButton.setBounds(50, 26, 112, 23);
		contentPane.add(btnNewButton);
	}

}
