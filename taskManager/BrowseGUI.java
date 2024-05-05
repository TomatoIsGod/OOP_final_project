package taskManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class BrowseGUI extends JFrame {
    private ArrayList<Task> taskList;
    private DefaultListModel<Task> listModel;
    private JList<Task> list;
    private JButton addNewButton, editButton, deleteButton;

    public BrowseGUI() {
        super("Task Manager");
        taskList = new ArrayList<>();
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setLayout(null);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(10, 10, 380, 200);
        add(scrollPane);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setBounds(10, 220, 380, 70);

        addNewButton = new JButton("Add New");
        addNewButton.setBounds(10, 5, 120, 30);
        buttonPanel.add(addNewButton);

        editButton = new JButton("Edit");
        editButton.setBounds(135, 5, 100, 30);
        buttonPanel.add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(240, 5, 120, 30);
        buttonPanel.add(deleteButton);

        add(buttonPanel);

        addNewButton.addActionListener(e -> openAddNewGUI());
        editButton.addActionListener(e -> openEditGUI());
        deleteButton.addActionListener(e -> deleteTasks());

        setSize(400, 320);
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
