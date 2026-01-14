import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame{
    private JButton[] buttons =  new JButton[9];
    private JLabel statusLabel =  new JLabel("Turno do Jogador X");
    private JButton resetButton =  new JButton("Reiniciar");
    private char currentPlayer = 'X';
    private int moves = 0;

    public TicTacToe(){
        setTitle("Tic Tac Toe");
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());  

        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3));


        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].addActionListener(new ButtonListener());
            gridPanel.add(buttons[i]);
        }

        add(gridPanel, BorderLayout.CENTER);
    add(statusLabel, BorderLayout.NORTH); 
    add(resetButton, BorderLayout.SOUTH); 

    resetButton.addActionListener(e -> resetGame());

    setVisible(true);
    }

    private boolean checkWin() {
        // Linhas
        for (int i = 0; i < 9; i += 3) {
            if (buttons[i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 1].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 2].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // Colunas
        for (int i = 0; i < 3; i++) {
            if (buttons[i].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 3].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[i + 6].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        // Diagonais
        if (buttons[0].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[4].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[8].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (buttons[2].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[4].getText().equals(String.valueOf(currentPlayer)) &&
            buttons[6].getText().equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    private void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
    
    private void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        currentPlayer = 'X';
        moves = 0;
        statusLabel.setText("Turno do Jogador X");
    }
    
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource(); 
            if (button.getText().equals("")) { 
                button.setText(String.valueOf(currentPlayer));
                moves++; 
                if (checkWin()) { 
                    statusLabel.setText("Jogador " + currentPlayer + " venceu!");
                    disableButtons();
                } else if (moves == 9) { 
                    statusLabel.setText("Empate!");
                } else { 
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    statusLabel.setText("Turno do Jogador " + currentPlayer);
                }
            }
        }
    }
}