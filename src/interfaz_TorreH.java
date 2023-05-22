import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class interfaz_TorreH extends JFrame{

    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton btnA_B;
    private JButton btnA_C;
    private JButton btnB_A;
    private JButton btnB_C;
    private JButton btnC_A;
    private JButton btnC_B;
    private JComboBox cbNumerodeDiscos;
    private JButton btnReiniciar;
    private JButton btnIniciar;
    private JButton btnResolver;
    private JTextArea taMinMov;
    private JTextArea taNumMov;
    private JTextArea taTorreA;
    private JTextArea taTorreB;
    private JTextArea taTorreC;
    private Stack<String> pilaTorreA = new Stack<>();
    private Stack<String> pilaTorreB = new Stack<>();
    private Stack<String> pilaTorreC = new Stack<>();
    private int numMov = 0;
    private TowerManager tm = new TowerManager();



    public interfaz_TorreH(String titulo) {
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel1);
        this.pack();

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            taTorreA.setText("");
            taTorreB.setText("");
            taTorreC.setText("");
                int numDiscos = Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString());
                tm.TorreA(numDiscos, pilaTorreA);
                for (int i = numDiscos - 1; i >= 0; i--) {
                    taTorreA.setText(taTorreA.getText() + "\n" + pilaTorreA.get(i));
                }
                taMinMov.setText(String.valueOf(tm.numMinMov(numDiscos)));//Numero minimo de movimientos
            }
        });
        btnA_B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreA.isEmpty()) {
                    if (pilaTorreB.isEmpty() || pilaTorreA.peek().compareTo(pilaTorreB.peek()) <= -1) {
                        numMov = numMov + 1;
                        taNumMov.setText(String.valueOf(numMov));
                        tm.cambioDeTorre(pilaTorreA, pilaTorreB);
                        mensajes(taTorreA, taTorreB, pilaTorreA, pilaTorreB);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnA_C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreA.isEmpty()) {
                    if (pilaTorreC.isEmpty() || pilaTorreA.peek().compareTo(pilaTorreC.peek()) <= -1) {
                        numMov = numMov + 1;
                        taNumMov.setText(String.valueOf(numMov));


                        tm.cambioDeTorre(pilaTorreA, pilaTorreC);
                        mensajes(taTorreA, taTorreC, pilaTorreA, pilaTorreC);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnB_A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreB.isEmpty()) {
                    numMov = numMov + 1;
                    taNumMov.setText(String.valueOf(numMov));
                    if (pilaTorreA.isEmpty() || pilaTorreB.peek().compareTo(pilaTorreA.peek()) <= -1) {


                        //cambiar de torre b a torre a
                        tm.cambioDeTorre(pilaTorreB, pilaTorreA);
                        mensajes(taTorreB, taTorreA, pilaTorreB, pilaTorreA);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnB_C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreB.isEmpty()) {
                    if (pilaTorreC.isEmpty() || pilaTorreB.peek().compareTo(pilaTorreC.peek()) <= -1) {
                        numMov = numMov + 1;
                        taNumMov.setText(String.valueOf(numMov));

                        tm.cambioDeTorre(pilaTorreB, pilaTorreC);
                        mensajes(taTorreB, taTorreC, pilaTorreB, pilaTorreC);


                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnC_A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreC.isEmpty()) {
                    if (pilaTorreA.isEmpty() || pilaTorreC.peek().compareTo(pilaTorreA.peek()) <= -1) {
                        numMov = numMov + 1;
                        taNumMov.setText(String.valueOf(numMov));

                        //cambiar de torre c a torre a
                        tm.cambioDeTorre(pilaTorreC, pilaTorreA);
                        mensajes(taTorreC, taTorreA, pilaTorreC, pilaTorreA);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnC_B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pilaTorreC.isEmpty()) {
                    if (pilaTorreB.isEmpty() || pilaTorreC.peek().compareTo(pilaTorreB.peek()) <= -1) {
                        numMov = numMov + 1;
                        taNumMov.setText(String.valueOf(numMov));

                        tm.cambioDeTorre(pilaTorreC, pilaTorreB);
                        mensajes(taTorreC, taTorreB, pilaTorreC, pilaTorreB);

                    } else {
                        JOptionPane.showMessageDialog(null, "Movimiento no valido");
                    }
                }
            }
        });
        btnReiniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tm.limpiar(pilaTorreA, pilaTorreB, pilaTorreC);
                numMov = 0;
                taTorreA.setText("");
                taTorreB.setText("");
                taTorreC.setText("");
                taMinMov.setText("");
                taNumMov.setText("");
            }
        });
        btnResolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numDiscos = Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString());
                if (pilaTorreA.size() == numDiscos) {
                    tm.resolver(Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString()), pilaTorreA, pilaTorreB, pilaTorreC);
                    taNumMov.setText(String.valueOf(String.format("%.0f", tm.numMinMov(Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString())))));
                    mensajes(taTorreA, taTorreC, pilaTorreA, pilaTorreC);
                } else {

                    tm.limpiar(pilaTorreA, pilaTorreB, pilaTorreC);
                    numMov = 0;
                    taTorreA.setText("");
                    taTorreB.setText("");
                    taTorreC.setText("");
                    taMinMov.setText("");
                    taNumMov.setText("");

                    tm.TorreA(numDiscos, pilaTorreA);

                    for (int i = numDiscos - 1; i >= 0; i--) {
                        taTorreA.setText(taTorreA.getText() + "\n" + pilaTorreA.get(i));
                    }
                    tm.resolver(Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString()), pilaTorreA, pilaTorreB, pilaTorreC);
                    taNumMov.setText(String.valueOf(String.format("%.0f", tm.numMinMov(Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString())))));
                    mensajes(taTorreA, taTorreC, pilaTorreA, pilaTorreC);

                }
            }
        });
    }
    private void mensajes(JTextArea textArea1, JTextArea textArea2, Stack<String> torre1, Stack<String> torre2) {
        textArea1.setText("");
        textArea2.setText("");
        int numDiscos = Integer.parseInt(cbNumerodeDiscos.getSelectedItem().toString());

        if (torre2.size() == numDiscos && numMov == tm.numMinMov(numDiscos)) {
            JOptionPane.showMessageDialog(null, "Felicidades has alcanzado el obejtivo de minimo de movimientos");
        } else if (torre2.size() == numDiscos && numMov != tm.numMinMov(numDiscos)) {
            JOptionPane.showMessageDialog(null, "Felicidades lo has resuelto\n Intenta superar el objetivo con el minimo de mvimientos");
        }

        for (int i = torre1.size() - 1; i >= 0; i--) {
            textArea1.setText(textArea1.getText() + "\n" + torre1.get(i));
        }
        for (int i = torre2.size() - 1; i >= 0; i--) {
            textArea2.setText(textArea2.getText() + "\n" + torre2.get(i));
        }
    }
}
