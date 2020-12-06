package main.page;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;

import main.domain.Hospital;
import main.domain.Reservation;

import main.PatientApplication;

public class ProcessReservationPage extends JPanel {
	private JPanel bodyPanel;
	private JPanel symptomListPanel;

	private PageHandler pageHandler;
	private String hospitalId;

	HashMap<String, String[]> symptomMap = new HashMap<>();

	public ProcessReservationPage(PageHandler pageHandler, String hospitalId) {
		this.pageHandler = pageHandler;
		this.hospitalId = hospitalId;
		this.setLayout(null);
		initSymptomMap();
		build();
	}

	void build() {

		Hospital hospital = pageHandler.service.getHospital(hospitalId);

		JLabel hospitalNameLabel = new JLabel(hospital.getHospitalName());
		hospitalNameLabel.setFont(new Font("���� ���", Font.BOLD, 23));
		hospitalNameLabel.setBounds(20, 20, 300, 40);
		this.add(hospitalNameLabel);

		JButton cancelButton = new JButton("�������");
		cancelButton.setBounds(370, 20, 100, 40);
		cancelButton.setBackground(Color.GREEN);
		cancelButton.setFont(new Font("���� ���", Font.BOLD, 16));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pageHandler.change("HospitalInfoPage");
			}
		});
		this.add(cancelButton);

		bodyPanel = new JPanel();
		bodyPanel.setLayout(null);
		bodyPanel.setBackground(Color.WHITE);
		bodyPanel.setBounds(10, 90, 460, 550);

		JLabel seletedDateLabel = new JLabel("��¥�� �����ϼ���");
		seletedDateLabel.setBounds(30, 20, 200, 30);
		seletedDateLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		bodyPanel.add(seletedDateLabel);

		String now = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy MM dd"));
		String[] nowSegList = now.split(" ");

		JComboBox selectedYearComboBox = new JComboBox();
		selectedYearComboBox.setBounds(35, 60, 80, 30);
		selectedYearComboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		selectedYearComboBox.setModel(new DefaultComboBoxModel(new String[] { "2020", "2021" }));
		selectedYearComboBox.setSelectedItem(nowSegList[0]);
		bodyPanel.add(selectedYearComboBox);

		JComboBox selectedMonthComboBox = new JComboBox();
		selectedMonthComboBox.setBounds(135, 60, 80, 30);
		selectedMonthComboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		selectedMonthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		selectedMonthComboBox.setSelectedItem(nowSegList[1]);
		bodyPanel.add(selectedMonthComboBox);

		JComboBox selectedDayComboBox = new JComboBox();
		selectedDayComboBox.setBounds(235, 60, 80, 30);
		selectedDayComboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		selectedDayComboBox.setModel(new DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24",
				"25", "26", "27", "28", "29", "30", "31" }));
		selectedDayComboBox.setSelectedItem(nowSegList[2]);
		bodyPanel.add(selectedDayComboBox);

		ArrayList<String> timeList = new ArrayList<>();
		for (int i = 9; i < 21; i++) {
			timeList.add(String.format("%02d:00", i));
			timeList.add(String.format("%02d:30", i));
		}

		JComboBox selectedTimeComboBox = new JComboBox();
		selectedTimeComboBox.setBounds(335, 60, 80, 30);
		selectedTimeComboBox.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		selectedTimeComboBox.setModel(new DefaultComboBoxModel(timeList.toArray()));
		bodyPanel.add(selectedTimeComboBox);

		JLabel selectedSubjectLabel = new JLabel("��������� �������ּ���");
		selectedSubjectLabel.setBounds(30, 120, 224, 30);
		selectedSubjectLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		bodyPanel.add(selectedSubjectLabel);

		JComboBox selectedSubjectComboBox = new JComboBox();
		selectedSubjectComboBox.setBounds(35, 160, 200, 30);
		selectedSubjectComboBox.setModel(new DefaultComboBoxModel(hospital.getCareTypeList().toArray()));
		selectedSubjectComboBox.setFont(new Font("���� ���", Font.BOLD, 16));
		selectedSubjectComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadSymptomListPanel(selectedSubjectComboBox.getSelectedItem().toString());
			}
		});
		bodyPanel.add(selectedSubjectComboBox);

		JLabel selectedSymptomLabel = new JLabel("������ üũ���ּ���");
		selectedSymptomLabel.setBounds(30, 220, 185, 30);
		selectedSymptomLabel.setFont(new Font("���� ���", Font.BOLD, 18));
		bodyPanel.add(selectedSymptomLabel);

		symptomListPanel = makeSymptomListPanel(selectedSubjectComboBox.getSelectedItem().toString());
		bodyPanel.add(symptomListPanel);

		JButton reservationButton = new JButton("��  ��");
		reservationButton.setBackground(new Color(211, 211, 211));
		reservationButton.setFont(new Font("���� ���", Font.BOLD, 23));
		reservationButton.setBounds(30, 480, 400, 40);
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reservationDate = "";
				reservationDate += selectedYearComboBox.getSelectedItem().toString() + "-";
				reservationDate += selectedMonthComboBox.getSelectedItem().toString() + "-";
				reservationDate += selectedDayComboBox.getSelectedItem().toString();
				String reservationTime = selectedTimeComboBox.getSelectedItem().toString();
				String careType = selectedSubjectComboBox.getSelectedItem().toString();
				ArrayList<String> symptomList = new ArrayList<>();

				for (Component button : symptomListPanel.getComponents()) {
					JToggleButton selectedButton = (JToggleButton) button;
					if (selectedButton.isSelected())
						symptomList.add(selectedButton.getText());
				}

				String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

				if (now.compareTo(reservationDate + " " + reservationTime) > 0) {
					JOptionPane.showMessageDialog(null, "���� ������ �߸��Ǿ����ϴ�.");
					return;
				}

				if (symptomList.size() == 0) {
					symptomList.add(" ");
				}

				if (pageHandler.service.makeReservation(hospitalId, reservationDate, reservationTime, careType,
						symptomList)) {
					pageHandler.change("ReservationListPage");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "�ش� �ð��� ������ ���� á���ϴ�.");
					return;
				}
			}
		});
		bodyPanel.add(reservationButton);

		this.add(bodyPanel);

	}

	private void reloadSymptomListPanel(String careType) {
		bodyPanel.remove(symptomListPanel);
		symptomListPanel = makeSymptomListPanel(careType);
		bodyPanel.add(symptomListPanel);
		revalidate();
		repaint();
	}

	private JPanel makeSymptomListPanel(String careType) {

		JPanel symptomListPanel = new JPanel();
		symptomListPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		symptomListPanel.setBounds(18, 250, 420, 200);
		symptomListPanel.setBackground(Color.WHITE);

		String[] symptomList = symptomMap.get(careType);

		for (String symptom : symptomList) {
			JToggleButton selectSymptomToggleButton = new JToggleButton(symptom);
			selectSymptomToggleButton.setBackground(new Color(102, 102, 102));
			selectSymptomToggleButton.setForeground(Color.WHITE);
			selectSymptomToggleButton.setBorder(new EmptyBorder(0, 0, 0, 0));
			selectSymptomToggleButton.setFont(new Font("���� ���", Font.BOLD, 16));
			selectSymptomToggleButton.setPreferredSize(new Dimension(120, 45));
			selectSymptomToggleButton.setUI(new MetalToggleButtonUI() {
				@Override
				protected Color getSelectColor() {
					return Color.ORANGE;
				}
			});
			selectSymptomToggleButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (((JToggleButton) e.getSource()).isSelected()) {
						selectSymptomToggleButton.setForeground(Color.BLACK);
					} else {
						selectSymptomToggleButton.setForeground(Color.WHITE);
					}
				}
			});
			symptomListPanel.add(selectSymptomToggleButton);
		}

		return symptomListPanel;
	}

	private void initSymptomMap() {
		symptomMap.put("�Ǻΰ�", new String[] { "���帧", "�˷���", "�縶��", "����", "����", "ȭ��", "����", "������", "�Ǻο�" });
		symptomMap.put("ġ��", new String[] { "��ġ", "����", "���ö�Ʈ", "�̽ø�", "�ΰ������", "�ո�����", "��������", "�����", "�ź�ġ" });
		symptomMap.put("�Ȱ�", new String[] { "�ٷ���", "����������", "����", "�ٽ�", "���", "�þ����", "�쳻��", "�鳻��" });
		symptomMap.put("����", new String[] { "����", "��ȭ�ҷ�", "����", "��", "ȣ����", "�๰", "�ڸ���", "��ħ", "����" });
		symptomMap.put("�񴢱��", new String[] { "����", "����", "�����ظ�", "��ȭ�ҷ�", "�Ǻο���", "�̹���", "�ε巯��", "��������" });
		symptomMap.put("�Ҿ�û�ҳ��", new String[] { "�߿�", "����", "����", "����", "�Ǻο���", "����", "�Ϳ���", "�๰", "����" });
		symptomMap.put("����ΰ�", new String[] { "������", "�����Ҽ�", "����", "��������", "��������", "�Ǻο���" });
		symptomMap.put("�����ܰ�", new String[] { "����", "������", "����", "�δ�ջ�", "ô������", "���������", "������" });
		symptomMap.put("�Ű��", new String[] { "����", "����", "������", "����������", "�Ű���ȯ", "ô����ȯ", "ġ��", "������ȯ" });
	}

}
