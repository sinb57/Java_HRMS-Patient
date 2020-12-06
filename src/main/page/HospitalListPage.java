
package main.page;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

import main.domain.*;

public class HospitalListPage extends JPanel {
	private PageHandler pageHandler;
	private JPanel hospitalListPanel;
	private JButton pagingButton_now;

	private String[] subjects = { "ÀüÃ¼", "Ä¡°ú", "¾È°ú", "³»°ú", "ÇÇºÎ°ú", "½Å°æ°ú", "ºñ´¢±â°ú", "Á¤Çü¿Ü°ú", "¼Ò¾ÆÃ»¼Ò³â°ú" };

	private static JTextField locationTextField;
	private JComboBox diagnosisComboBox;
	private JToggleButton salesToggleButton;
	private JTextField hospitalSearchTextField;
	private int pageNum = 1;

	public HospitalListPage(PageHandler pageHandler) {
		this.pageHandler = pageHandler;
		this.setLayout(null);
		build();
	}

	void build() {
		ImageIcon searchIcon = new ImageIcon(pageHandler.path + "searchIcon.png");
		Image searchImage = searchIcon.getImage();
		searchImage = searchImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		searchIcon = new ImageIcon(searchImage);

		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(null);
		categoryPanel.setBackground(new Color(0, 176, 240));
		categoryPanel.setBounds(-5, 10, 500, 100);

		JLabel locationIconLabel = new JLabel("");
		locationIconLabel.setIcon(new ImageIcon(pageHandler.path + "locationIcon.png"));
		locationIconLabel.setBounds(25, 10, 40, 40);
		categoryPanel.add(locationIconLabel);

		locationTextField = new JTextField(pageHandler.location);
		locationTextField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 15));
		locationTextField.setBounds(60, 15, 190, 30);
		categoryPanel.add(locationTextField);

		JButton locationSearchButton = new JButton("");
		locationSearchButton.setIcon(searchIcon);
		locationSearchButton.setBounds(253, 15, 35, 30);
		locationSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String location = locationTextField.getText();
				String[] dataList = location.split(" ");
				if (dataList.length != 3)
					return;
				if (dataList[0].lastIndexOf("µµ") != dataList[0].length() - 1)
					return;
				if (dataList[1].lastIndexOf("½Ã") != dataList[1].length() - 1)
					return;
				if (dataList[2].lastIndexOf("±¸") != dataList[2].length() - 1)
					return;

				pageHandler.location = location;
				pageNum = 1;
				reload();
			}
		});
		categoryPanel.add(locationSearchButton);

		JLabel salesToggleLabel = new JLabel("¿µ¾÷Áß¸¸ º¸ÀÌ±â");
		salesToggleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 13));
		salesToggleLabel.setBounds(330, 20, 101, 15);
		categoryPanel.add(salesToggleLabel);

		salesToggleButton = new JToggleButton("");
		salesToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((JToggleButton) e.getSource()).isSelected()) {
					salesToggleButton.setIcon(new ImageIcon(pageHandler.path + "salesToggleButtonLabel_click.png"));
				} else {
					salesToggleButton.setIcon(new ImageIcon(pageHandler.path + "salesToggleButtonLabel.png"));
				}
				pageNum = 1;
				reload();
			}
		});
		salesToggleButton.setBounds(430, 15, 28, 27);
		categoryPanel.add(salesToggleButton);

		hospitalSearchTextField = new JTextField();
		hospitalSearchTextField.setBounds(32, 60, 218, 30);
		hospitalSearchTextField.setColumns(10);
		categoryPanel.add(hospitalSearchTextField);

		JButton hospitalSearchButton = new JButton("");
		hospitalSearchButton.setIcon(searchIcon);
		hospitalSearchButton.setBounds(253, 60, 35, 30);
		hospitalSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reload();
			}
		});
		categoryPanel.add(hospitalSearchButton);

		diagnosisComboBox = new JComboBox(subjects);
		diagnosisComboBox.setModel(new DefaultComboBoxModel(subjects));
		diagnosisComboBox.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
		diagnosisComboBox.setBounds(335, 60, 124, 30);
		diagnosisComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum = 1;
				reload();
			}
		});
		categoryPanel.add(diagnosisComboBox);

		this.add(categoryPanel);

		hospitalListPanel = makeHospitalListPanel();
		this.add(hospitalListPanel);

		JButton pagingButton_previous = new JButton("<");
		pagingButton_previous.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_previous.setBounds(160, 640, 45, 45);
		pagingButton_previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum > 1)
					pageNum--;
				reload();
			}
		});
		this.add(pagingButton_previous);

		pagingButton_now = new JButton(pageNum + "");
		pagingButton_now.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_now.setBounds(220, 640, 45, 45);
		this.add(pagingButton_now);

		JButton pagingButton_next = new JButton(">");
		pagingButton_next.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 16));
		pagingButton_next.setBounds(280, 640, 45, 45);
		pagingButton_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageNum++;
				reload();
			}
		});
		this.add(pagingButton_next);

	}

	private void reload() {
		this.remove(hospitalListPanel);
		hospitalListPanel = makeHospitalListPanel();
		this.add(hospitalListPanel);
		pagingButton_now.setText(pageNum + "");
		revalidate();
		repaint();
	}

	private ArrayList<Hospital> getHospitalList() {
		String location = locationTextField.getText();
		String careType = String.valueOf(diagnosisComboBox.getSelectedItem());
		String state = "ÀüÃ¼";
		if (salesToggleButton.isSelected())
			state = "¿µ¾÷Áß";
		String keywords = hospitalSearchTextField.getText();

		return pageHandler.service.getHospitalList(pageNum, location, careType, state, keywords);
	}

	private JPanel makeHospitalListPanel() {

		JPanel hospitalListPanel = new JPanel();
		hospitalListPanel.setLayout(null);
		hospitalListPanel.setBackground(Color.WHITE);
		hospitalListPanel.setBounds(25, 120, 430, 510);

		ArrayList<JPanel> hospitalInfoPanelList = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			JPanel hospitalInfoPanel = new JPanel();
			hospitalInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			hospitalInfoPanel.setBackground(Color.WHITE);
			hospitalInfoPanel.setBounds(12, 10 + 125 * i, 410, 115);
			hospitalInfoPanel.setLayout(null);
			hospitalInfoPanelList.add(hospitalInfoPanel);
			hospitalListPanel.add(hospitalInfoPanel);
		}

		ArrayList<Hospital> hospitalList = getHospitalList();

		if (hospitalList != null) {

			for (int i = 0; i < hospitalList.size(); i++) {
				Hospital hospital = hospitalList.get(i);
				JPanel hospitalInfoPanel = hospitalInfoPanelList.get(i);

				JLabel hospitalNameLabel = new JLabel(hospital.getHospitalName());
				hospitalNameLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 22));
				hospitalNameLabel.setBounds(12, 10, 300, 35);
				hospitalInfoPanel.add(hospitalNameLabel);

				JLabel hospitalLocationLabel = new JLabel(hospitalList.get(i).getAddress());
				hospitalLocationLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
				hospitalLocationLabel.setBounds(12, 41, 300, 26);
				hospitalInfoPanel.add(hospitalLocationLabel);

				JPanel hospitalSubjectPanel = new JPanel();
				hospitalSubjectPanel.setBounds(12, 77, 300, 28);
				hospitalSubjectPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

				for (String careType : hospital.getCareTypeList()) {
					JLabel hospitalSubjectLabel = new JLabel(careType);
					hospitalSubjectLabel.setOpaque(true);
					hospitalSubjectLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 12));
					hospitalSubjectLabel.setBackground(new Color(52, 152, 219));
					hospitalSubjectLabel.setForeground(Color.WHITE);
					hospitalSubjectPanel.add(hospitalSubjectLabel);
				}
				hospitalInfoPanel.add(hospitalSubjectPanel);

				JPanel hospitalStatusPanel = new JPanel();
				hospitalStatusPanel.setLayout(null);
				hospitalStatusPanel.setBounds(334, 1, 75, 113);

				String state = hospital.getStateNow();
				JLabel hospitalStatusLabel = new JLabel(state);
				hospitalStatusLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 14));
				hospitalStatusLabel.setForeground(Color.WHITE);
				hospitalStatusLabel.setBounds(7, 40, 60, 30);
				hospitalStatusLabel.setHorizontalAlignment(JLabel.CENTER);

				switch (state) {
				case "¿µ¾÷Áß":
					hospitalStatusPanel.setBackground(new Color(0, 132, 212));
					break;
				case "ÈÞ¹«":
					hospitalStatusPanel.setBackground(new Color(175, 117, 117));
					break;
				case "¿µ¾÷¸¶°¨":
					hospitalStatusPanel.setBackground(new Color(175, 117, 117));
					break;
				case "½Ä»çÁß":
					hospitalStatusPanel.setBackground(new Color(255, 192, 0));
					break;
				}
				hospitalStatusPanel.add(hospitalStatusLabel);

				hospitalInfoPanel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						pageHandler.hospitalId = hospital.getHospitalId();
						pageHandler.change("HospitalInfoPage");
					}
				});

				hospitalInfoPanel.add(hospitalStatusPanel);
			}
		}

		return hospitalListPanel;
	}
}
