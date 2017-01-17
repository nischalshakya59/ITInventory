package inventory.ui.frame;

import inventory.dao.EmployeeDAO;
import inventory.dto.EmployeeDTO;
import inventory.ui.panel.EmployeePanel;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EmployeeFrame extends javax.swing.JFrame {

    public EmployeeFrame() {
        initComponents();
        this.setTitle("Employee");
        employeeidtxtfield.setEditable(false);
        setId();
    }

    EmployeeDTO employeedto = new EmployeeDTO();
    EmployeeDAO employeedao = new EmployeeDAO();

    public void setId() {
        int employeeid = employeedao.employeeId() + 1;
        employeeidtxtfield.setText(String.valueOf(employeeid));
    }

    public void check() {
        if (employeefnametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee first name", "Inventory Mangement System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (employeelastnametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee last name", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (employeecontatcnotxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee contact number", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (employeeaddresstxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee address", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (employeeposttxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee post", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JTextField) employeejoindate.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the valid joined date", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (employeesalarytxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the employee salary", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void insert() {
        int id = Integer.parseInt(employeeidtxtfield.getText());
        String firstname = employeefnametxtfield.getText();
        String lastname = employeelastnametxtfield.getText();
        String contact = employeecontatcnotxtfield.getText();
        String address = employeeaddresstxtfield.getText();
        String post = employeeposttxtfield.getText();
        String joineddate = (((JTextField) employeejoindate.getDateEditor().getUiComponent()).getText());
        double salary = Double.parseDouble(employeesalarytxtfield.getText());
        String workingperiod = " ";
        if (employeeworkingperiodtxtfield.getText().length() != 0) {
            workingperiod = employeeworkingperiodtxtfield.getText() + "-" + employeeworkingperiodcombobox.getSelectedItem();
            employeedto.setEmployeeworkingyears(workingperiod);
        } else {
            employeedto.setEmployeeworkingyears(" ");
        }

        String leavedate = (((JTextField) employeeleavedate.getDateEditor().getUiComponent()).getText());

        String firstnamecap = firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
        String lastnamecap = lastname.substring(0, 1).toUpperCase() + lastname.substring(1);
        String addresscap = address.substring(0, 1).toUpperCase() + address.substring(1);
        String postcap = post.substring(0, 1).toUpperCase() + post.substring(1);

        employeedto.setEmployeeid(id);
        employeedto.setEmployeename(firstnamecap);
        employeedto.setEmployeelname(lastnamecap);
        employeedto.setEmployeecontactno(contact);
        employeedto.setEmployeeaddress(addresscap);
        employeedto.setEmployeepost(postcap);
        employeedto.setEmployeejoindate(joineddate);
        employeedto.setEmployeesalary(salary);

        employeedto.setEmployeeleavedate(leavedate);

        if (employeedao.insertemployee(employeedto) == true) {
            setId();
            reset();
        }
    }

    public void reset() {
        employeeidtxtfield.setText("");
        employeefnametxtfield.setText("");
        employeelastnametxtfield.setText("");
        employeecontatcnotxtfield.setText("");
        employeeaddresstxtfield.setText("");
        employeeposttxtfield.setText("");
        setDate();
        employeesalarytxtfield.setText("");
        employeeworkingperiodtxtfield.setText("");
        savebtn.setText("Save");
        setId();
    }

    public void setDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMMM dd yyyy");
        employeejoindate.setDate(dNow);
        employeeleavedate.setDate(dNow);
    }

    public void update(EmployeeDTO edto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        employeeidtxtfield.setText(String.valueOf(edto.getEmployeeid()));
        employeefnametxtfield.setText(edto.getEmployeename());
        employeelastnametxtfield.setText(edto.getEmployeelname());
        employeecontatcnotxtfield.setText(edto.getEmployeecontactno());
        employeeaddresstxtfield.setText(edto.getEmployeeaddress());
        employeeposttxtfield.setText(edto.getEmployeepost());
        ((JTextField) employeejoindate.getDateEditor().getUiComponent()).setText(edto.getEmployeejoindate());
        employeesalarytxtfield.setText(String.valueOf(edto.getEmployeesalary()));
        String workingperiod = edto.getEmployeeworkingyears();
        String[] parts = workingperiod.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        employeeworkingperiodtxtfield.setText(part1);
        employeeworkingperiodcombobox.setSelectedItem(part2);
        ((JTextField) employeeleavedate.getDateEditor().getUiComponent()).setText(edto.getEmployeeleavedate());
    }

    public void updateFinal() {
        employeedto.setEmployeeid(Integer.parseInt(employeeidtxtfield.getText()));
        String empfname = employeefnametxtfield.getText().substring(0, 1).toUpperCase() + employeefnametxtfield.getText().substring(1);
        employeedto.setEmployeename(empfname);
        String emplname = employeelastnametxtfield.getText().substring(0, 1).toUpperCase() + employeelastnametxtfield.getText().substring(1);
        employeedto.setEmployeelname(emplname);
        employeedto.setEmployeecontactno(employeecontatcnotxtfield.getText());
        String address = employeeaddresstxtfield.getText().substring(0, 1).toUpperCase() + employeeaddresstxtfield.getText().substring(1);
        employeedto.setEmployeeaddress(address);
        String post = employeeposttxtfield.getText().substring(0, 1).toUpperCase() + employeeposttxtfield.getText().substring(1);
        employeedto.setEmployeepost(post);
        employeedto.setEmployeejoindate(((JTextField) employeejoindate.getDateEditor().getUiComponent()).getText());
        employeedto.setEmployeesalary(Double.valueOf(employeesalarytxtfield.getText()));
        employeedto.setEmployeeworkingyears(employeeworkingperiodtxtfield.getText() + "-" + employeeworkingperiodcombobox.getSelectedItem());
        employeedto.setEmployeeleavedate(((JTextField) employeeleavedate.getDateEditor().getUiComponent()).getText());
        if (employeedao.updateEmployee(employeedto) == true) {
            reset();
            setId();
        }
    }

    public int compare() {
        long diff = employeejoindate.getDate().getTime()
                - employeeleavedate.getDate().getTime();
        int diffdays = (int) (diff / 1000 / 60 / 60 / 24);
        return diffdays;
    }

    public void compareDate() {
        if (compare() < 0) {
            System.out.println("smaller");
        } else {
            System.out.println("larger");

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        employeeidlab = new javax.swing.JLabel();
        employeeidtxtfield = new javax.swing.JTextField();
        employeefnametxtfield = new javax.swing.JTextField();
        employeefnamelab = new javax.swing.JLabel();
        employeelastnamelab = new javax.swing.JLabel();
        employeelastnametxtfield = new javax.swing.JTextField();
        employeecontactnolab = new javax.swing.JLabel();
        employeecontatcnotxtfield = new javax.swing.JTextField();
        employeeaddresslab = new javax.swing.JLabel();
        employeeaddresstxtfield = new javax.swing.JTextField();
        employeepostlab = new javax.swing.JLabel();
        employeeposttxtfield = new javax.swing.JTextField();
        employeesalarylab = new javax.swing.JLabel();
        employeesalarytxtfield = new javax.swing.JTextField();
        employeeworkingperiodcombobox = new javax.swing.JComboBox();
        employeejoindate = new com.toedter.calendar.JDateChooser();
        employeejoindatelab = new javax.swing.JLabel();
        employeeworkingperiodlab = new javax.swing.JLabel();
        employeeworkingperiodtxtfield = new javax.swing.JTextField();
        employeeleavedatelab = new javax.swing.JLabel();
        employeeleavedate = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        savebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        employeeidlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeeidlab.setText("Id :-");

        employeeidtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeefnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeefnametxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                employeefnametxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeefnametxtfieldKeyReleased(evt);
            }
        });

        employeefnamelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeefnamelab.setText("First Name :-");

        employeelastnamelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeelastnamelab.setText("Last Name :-");

        employeelastnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeecontactnolab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeecontactnolab.setText("Contact No :-");

        employeecontatcnotxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeeaddresslab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeeaddresslab.setText("Address :-");

        employeeaddresstxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        employeeaddresstxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeaddresstxtfieldKeyReleased(evt);
            }
        });

        employeepostlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeepostlab.setText("Post :-");

        employeeposttxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeesalarylab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeesalarylab.setText("Salary :-");

        employeesalarytxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeeworkingperiodcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "Month", "Day" }));

        employeejoindate.setDateFormatString("MMM d yyyy");

        employeejoindatelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeejoindatelab.setText("Joined Date :-");

        employeeworkingperiodlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeeworkingperiodlab.setText("Working Period :-");

        employeeworkingperiodtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        employeeleavedatelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeeleavedatelab.setText("Leave Date :-");

        employeeleavedate.setDateFormatString("MMM d yyyy");
        employeeleavedate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                employeeleavedateKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeleavedateKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(employeeidlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeeidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employeefnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeefnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(employeelastnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeelastnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employeecontactnolab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeecontatcnotxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employeeaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeejoindatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeeleavedatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(employeejoindate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(employeeaddresstxtfield)
                            .addComponent(employeeleavedate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(employeepostlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(employeeposttxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(employeesalarylab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(employeesalarytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(employeeworkingperiodlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(employeeworkingperiodtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employeeworkingperiodcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        upperpanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {employeeaddresstxtfield, employeejoindate});

        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeidlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeefnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeefnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeelastnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeelastnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeecontatcnotxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeecontactnolab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeposttxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeepostlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeaddresstxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(employeesalarytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeesalarylab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(employeejoindate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeejoindatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(employeeworkingperiodtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeworkingperiodlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeworkingperiodcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeeleavedate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(employeeleavedatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/save.png"))); // NOI18N
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 193, Short.MAX_VALUE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 225, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 72, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void employeefnametxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeefnametxtfieldKeyPressed

    }//GEN-LAST:event_employeefnametxtfieldKeyPressed

    private void employeefnametxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeefnametxtfieldKeyReleased
    }//GEN-LAST:event_employeefnametxtfieldKeyReleased

    private void employeeaddresstxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeaddresstxtfieldKeyReleased

    }//GEN-LAST:event_employeeaddresstxtfieldKeyReleased

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new EmployeePanel().setVisible();

    }//GEN-LAST:event_formWindowClosed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed

        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();

        } else if (savebtn.getText().equalsIgnoreCase("update")) {
            check();
            updateFinal();
            setId();
            reset();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void employeeleavedateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeleavedateKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == employeeleavedate && keycode == KeyEvent.VK_ENTER) {
            compareDate();
        }

    }//GEN-LAST:event_employeeleavedateKeyPressed

    private void employeeleavedateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeleavedateKeyReleased
    }//GEN-LAST:event_employeeleavedateKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel employeeaddresslab;
    private javax.swing.JTextField employeeaddresstxtfield;
    private javax.swing.JLabel employeecontactnolab;
    private javax.swing.JTextField employeecontatcnotxtfield;
    private javax.swing.JLabel employeefnamelab;
    private javax.swing.JTextField employeefnametxtfield;
    private javax.swing.JLabel employeeidlab;
    private javax.swing.JTextField employeeidtxtfield;
    private com.toedter.calendar.JDateChooser employeejoindate;
    private javax.swing.JLabel employeejoindatelab;
    private javax.swing.JLabel employeelastnamelab;
    private javax.swing.JTextField employeelastnametxtfield;
    private com.toedter.calendar.JDateChooser employeeleavedate;
    private javax.swing.JLabel employeeleavedatelab;
    private javax.swing.JLabel employeepostlab;
    private javax.swing.JTextField employeeposttxtfield;
    private javax.swing.JLabel employeesalarylab;
    private javax.swing.JTextField employeesalarytxtfield;
    private javax.swing.JComboBox employeeworkingperiodcombobox;
    private javax.swing.JLabel employeeworkingperiodlab;
    private javax.swing.JTextField employeeworkingperiodtxtfield;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton savebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
