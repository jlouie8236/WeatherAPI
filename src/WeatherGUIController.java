import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherGUIController implements ActionListener
{
    private JTextArea weatherInfo;
    private JTextField weatherEntryField;
    private WeatherAPI client;

    public WeatherGUIController()
    {
        weatherInfo = new JTextArea(10, 20);
        weatherEntryField = new JTextField();
        client = new WeatherAPI();

        setupGui();
    }

    private void setupGui()
    {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Current Weather");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.pink);

        JPanel logoWelcomePanel = new JPanel();
        logoWelcomePanel.add(welcomeLabel);

        //------------------------------------------

        JPanel entryPanel = new JPanel();
        JLabel weatherLabel = new JLabel("Enter Zip Code: ");
        weatherEntryField = new JTextField(5);
        JButton submitButton = new JButton("Submit");
        JButton clearButton = new JButton("Clear");
        JCheckBox celsiusCheck = new JCheckBox("Show Celsius");
        entryPanel.add(weatherLabel);
        entryPanel.add(weatherEntryField);
        entryPanel.add(submitButton);
        entryPanel.add(clearButton);
        entryPanel.add(celsiusCheck);

        //------------------------------------------

        JPanel weatherPanel = new JPanel();

        //------------------------------------------

        frame.add(logoWelcomePanel, BorderLayout.NORTH);
        frame.add(entryPanel, BorderLayout.CENTER);
        frame.add(weatherPanel, BorderLayout.SOUTH);

        submitButton.addActionListener(this);
        clearButton.addActionListener(this);
        celsiusCheck.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton button = new JButton();
        JCheckBox checkBox = new JCheckBox();
        String text = "";
        boolean selected = false;
        try
        {
            button = (JButton) (e.getSource());
            text = button.getText();
        }
        catch(Exception i)
        {
            checkBox = (JCheckBox) (e.getSource());
            selected = checkBox.isSelected();
        }
        if (text.equals("Submit")) {
            String zip = weatherEntryField.getText();
            CurrentForecast forecast = client.getCurrentForecast(zip);
            String info = "Temperature: " + forecast.getTempF() + "Condition: " + forecast.getCurrentCondition();
            
        }
        else if (text.equals("Clear"))
        {
            weatherEntryField.setText("");
        }

    }
}