package taskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class BrowseGUI extends JFrame {
    private ArrayList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JList<Task> list;
    private JButton addNewButton, editButton, deleteButton, showDetailsButton;

    public BrowseGUI() {
        super("Task Manager");
        taskList = new ArrayList<>();
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(null);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, 570, 250);  // Adjusted for additional button
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(10, 270, 570, 100);  // Adjusted for additional button

        addNewButton = new JButton("Add New");
        addNewButton.setBounds(10, 5, 120, 30);
        buttonPanel.add(addNewButton);

        editButton = new JButton("Edit");
        editButton.setBounds(140, 5, 120, 30);
        buttonPanel.add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(270, 5, 120, 30);
        buttonPanel.add(deleteButton);

        showDetailsButton = new JButton("Show Details");
        showDetailsButton.setBounds(400, 5, 150, 30);
        buttonPanel.add(showDetailsButton);
        showDetailsButton.addActionListener(e -> showTaskDetails());

        add(buttonPanel);

        addNewButton.addActionListener(e -> openAddNewGUI());
        editButton.addActionListener(e -> openEditGUI());
        deleteButton.addActionListener(e -> deleteTasks());

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void openAddNewGUI() {
        AddNewGUI addNewGUI = new AddNewGUI(this);
    }

    private void openEditGUI() {
        Task selectedTask = list.getSelectedValue();
        if (selectedTask != null) {
            EditGUI editGUI = new EditGUI(this, selectedTask);
        }
    }

    private void deleteTasks() {
        Task selectedTask = list.getSelectedValue();
        if (selectedTask != null) {
            taskList.remove(selectedTask);
            listModel.removeElement(selectedTask);
        }
    }

    private void showTaskDetails() {
        Task selectedTask = list.getSelectedValue();
        if (selectedTask != null) {
            JOptionPane.showMessageDialog(this,
                "Title: " + selectedTask.getTitle() + "\nDescription: " + selectedTask.getDescription(),
                "Task Details",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                "No task selected!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
        taskList.sort(Comparator.comparing(Task::getDeadline));
        refreshListModel();
    }

    public void updateTask(Task original, Task updated) {
        int index = taskList.indexOf(original);
        if (index != -1) {
            taskList.set(index, updated);
            taskList.sort(Comparator.comparing(Task::getDeadline));
            refreshListModel();
        }
    }

    private void refreshListModel() {
        listModel.clear();
        taskList.forEach(listModel::addElement);
    }

    public static void main(String[] args) {
        new BrowseGUI();
    }
}
