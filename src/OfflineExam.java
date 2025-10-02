
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class OfflineExam extends JFrame {

    private final JTextField ipField = new JTextField("192.168.0.10");
    private final JTextField portField = new JTextField("1521");
    private final JLabel lanStatus = new JLabel("LAN: Not connected");
    private final JLabel dbStatus = new JLabel("DB: Unknown");

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);

    public OfflineExam() {
        setTitle("OFFLINE EXAM - CREATORS AND DOERS WITH DEVICE");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(980, 680);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(12, 12));
        add(buildHeader(), BorderLayout.NORTH);

        // Add all screens as cards
        cardPanel.add(buildLoginCard(), "login");
        cardPanel.add(buildAdminCard(), "admin");
        cardPanel.add(buildTeacherCard(), "teacher");
        cardPanel.add(buildStudentCard(), "student");
        add(cardPanel, BorderLayout.CENTER);

        add(buildFooter(), BorderLayout.SOUTH);

        cardLayout.show(cardPanel, "login"); // start with login
    }

    private JComponent buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("OFFLINE EXAM - CREATORS AND DOERS WITH DEVICE", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        title.setBorder(BorderFactory.createEmptyBorder(16, 16, 8, 16));
        header.add(title, BorderLayout.NORTH);

        JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        connectionPanel.setBorder(BorderFactory.createEmptyBorder(0, 8, 12, 8));
        connectionPanel.add(new JLabel("Server IP:"));
        ipField.setColumns(12);
        connectionPanel.add(ipField);
        connectionPanel.add(new JLabel("Port:"));
        portField.setColumns(6);
        connectionPanel.add(portField);

        JButton testBtn = new JButton(new AbstractAction("Test Connection") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = ipField.getText().trim();
                String port = portField.getText().trim();
                if (ip.isEmpty() || port.isEmpty()) {
                    JOptionPane.showMessageDialog(OfflineExam.this, "Enter IP and Port.", "Validation", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                lanStatus.setText("LAN: Connected to " + ip + ":" + port);
                dbStatus.setText("DB: Oracle reachable (simulated)");
            }
        });
        connectionPanel.add(testBtn);

        header.add(connectionPanel, BorderLayout.SOUTH);
        return header;
    }

    // ---------------- Login Screen ----------------
    private JPanel buildLoginCard() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(4, 4, 4, 4);
        gc.fill = GridBagConstraints.HORIZONTAL;

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JComboBox<String> role = new JComboBox<>(new String[]{"Admin", "Teacher", "Student"});

        gc.gridx = 0; gc.gridy = 0; form.add(new JLabel("Username:"), gc);
        gc.gridx = 1; gc.gridy = 0; form.add(username, gc);
        gc.gridx = 0; gc.gridy = 1; form.add(new JLabel("Password:"), gc);
        gc.gridx = 1; gc.gridy = 1; form.add(password, gc);
        gc.gridx = 0; gc.gridy = 2; form.add(new JLabel("Role:"), gc);
        gc.gridx = 1; gc.gridy = 2; form.add(role, gc);

        JButton loginBtn = new JButton(new AbstractAction("Login") {
            @Override
            public void actionPerformed(ActionEvent e) {
                String u = username.getText().trim();
                char[] p = password.getPassword();
                String r = String.valueOf(role.getSelectedItem());

                if (u.isEmpty() || p.length == 0) {
                    JOptionPane.showMessageDialog(panel, "Enter username and password.", "Login", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Simulated authentication success
                switch (r) {
                    case "Admin":
                        cardLayout.show(cardPanel, "admin");
                        break;
                    case "Teacher":
                        cardLayout.show(cardPanel, "teacher");
                        break;
                    default:
                        cardLayout.show(cardPanel, "student");
                }
            }
        });

        JPanel bottom = new JPanel(new BorderLayout(8, 8));
        bottom.add(form, BorderLayout.CENTER);
        bottom.add(loginBtn, BorderLayout.SOUTH);

        panel.add(bottom, BorderLayout.CENTER);
        return panel;
    }

    // ---------------- Admin Screen ----------------
    private JPanel buildAdminCard() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Admin Dashboard"));

        JTextArea info = new JTextArea(
                "Admin Functions:\n" +
                        "• Manage users\n" +
                        "• Manage courses\n" +
                        "• Backup / Restore"
        );
        info.setEditable(false);
        panel.add(new JScrollPane(info), BorderLayout.CENTER);

        JButton backBtn = new JButton("Return to Login");
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "login"));
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- Teacher Screen ----------------
    private JPanel buildTeacherCard() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Teacher Dashboard"));

        DefaultListModel<String> examListModel = new DefaultListModel<>();
        JList<String> examList = new JList<>(examListModel);
        panel.add(new JScrollPane(examList), BorderLayout.CENTER);

        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField examField = new JTextField(20);
        JButton addExam = new JButton("Add Exam");
        addExam.addActionListener(e -> {
            String title = examField.getText().trim();
            if (!title.isEmpty()) {
                ExamManager.addExam(new Exam(title));
                examListModel.addElement(title);
                examField.setText("");
            }
        });
        controls.add(new JLabel("Exam:"));
        controls.add(examField);
        controls.add(addExam);

        panel.add(controls, BorderLayout.NORTH);

        JButton backBtn = new JButton("Return to Login");
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "login"));
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    // ---------------- Student Screen ----------------
    private JPanel buildStudentCard() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createTitledBorder("Student Dashboard"));

        DefaultListModel<String> examListModel = new DefaultListModel<>();
        for (Exam exam : ExamManager.getExams()) {
            examListModel.addElement(exam.title);
        }
        JList<String> examList = new JList<>(examListModel);
        panel.add(new JScrollPane(examList), BorderLayout.CENTER);

        JButton attemptBtn = new JButton("Attempt Selected Exam");
        attemptBtn.addActionListener(e -> {
            String selected = examList.getSelectedValue();
            if (selected == null) {
                JOptionPane.showMessageDialog(panel, "Select an exam first.", "No Exam", JOptionPane.WARNING_MESSAGE);
                return;
            }
            JOptionPane.showMessageDialog(panel, "Attempting exam: " + selected + "\nTODO: Implement exam UI.", "Exam", JOptionPane.INFORMATION_MESSAGE);
        });
        panel.add(attemptBtn, BorderLayout.NORTH);

        JButton backBtn = new JButton("Return to Login");
        backBtn.addActionListener(e -> cardLayout.show(cardPanel, "login"));
        panel.add(backBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout(8, 8));
        footer.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        left.add(lanStatus);
        left.add(new JSeparator(SwingConstants.VERTICAL));
        left.add(dbStatus);

        JButton syncBtn = new JButton(new AbstractAction("Sync Manager") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(OfflineExam.this,
                        "Sync queued local exam data/submissions to Oracle.\n" +
                                "Status: simulated.\n" +
                                "TODO: Implement delta push & conflict handling.",
                        "Sync Manager", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton exitBtn = new JButton(new AbstractAction("Logout / Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        right.add(syncBtn);
        right.add(exitBtn);

        footer.add(left, BorderLayout.WEST);
        footer.add(right, BorderLayout.EAST);
        return footer;
    }

    private String currentEndpoint() {
        return ipField.getText().trim() + ":" + portField.getText().trim();
    }

    // ----- Data Models -----
    static class Exam {
        String title;
        java.util.List<Question> questions = new ArrayList<>();
        Exam(String title) { this.title = title; }
    }

    static class Question {
        String text;
        String answer;
        Question(String text, String answer) {
            this.text = text;
            this.answer = answer;
        }
    }

    static class ExamManager {
        static java.util.List<Exam> exams = new ArrayList<>();
        static void addExam(Exam e) { exams.add(e); }
        static java.util.List<Exam> getExams() { return exams; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new OfflineExam().setVisible(true);
        });
    }
}




