import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bruchrechner {

    private JPanel mainPanel;
    private JButton inEineGemischteZahlBtn;
    private JButton eingabenZurücksetzenBtn;
    private JTextField zaehler1;
    private JTextField nenner1;
    private JTextField zaehler2;
    private JTextField nenner2;
    private JComboBox comboBoxOperants;
    private JButton kuerzenBtn;
    private JButton ausrechnenBtn;
    private JLabel outputZaehler;
    private JLabel outputGemischteZahl;
    private JLabel outputNenner;

    public bruchrechner() {
        ausrechnenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //get operant from ComboBox and use it to determine the appropriate mathematical operation
                char operant = String.valueOf(comboBoxOperants.getSelectedItem()).charAt(0);

                //GET ME THE INPUT
                //Bruch 1
                int z1 = Integer.parseInt(zaehler1.getText());
                int n1 = Integer.parseInt(nenner1.getText());
                //Bruch 2
                int z2 = Integer.parseInt(zaehler2.getText());
                int n2 = Integer.parseInt(nenner2.getText());

                //determine the appropriate mathematical operation
               switch (operant){
                   case '+':

                       //If addition: get the gemeinsamen Nenner
                       int gemeinsamerNenner;

                       //Wenn nenner gleich sind
                       if(n1 == n2){
                           gemeinsamerNenner = n1;
                       } else {
                           //und wenn nicht:
                           gemeinsamerNenner = n1 * n2;

                           //Zähler 1 ausrechnen
                           int multiplikatorZ1 = (gemeinsamerNenner/n1);

                           z1 = z1 * multiplikatorZ1;

                           //Zähler2 ausrechnen
                           int multiplikatorZ2 = (gemeinsamerNenner/n2);

                           z2 = z2 * multiplikatorZ2;
                       }

                       //gemeinsamer Nenner
                       int gemeinsamerZaehler = z1 + z2;

                       //Put Zähler to outputLabel
                       outputZaehler.setText(String.valueOf(gemeinsamerZaehler));
                       //Put Nenenr to outputLabel
                       outputNenner.setText(String.valueOf(gemeinsamerNenner));

                       break;

                   case '-':

                       //Now do the same thing but with subtraction

                       //Wenn nenner gleich sind
                       if(n1 == n2){
                           gemeinsamerNenner = n1;
                       } else {
                           //und wenn nicht:
                           gemeinsamerNenner = n1 * n2;

                           //Zähler 1
                           int multiplikatorZ1 = (gemeinsamerNenner/n1);

                           z1 = z1 * multiplikatorZ1;

                           //Zähler 2
                           int multiplikatorZ2 = (gemeinsamerNenner/n2);

                           z2 = z2 * multiplikatorZ2;
                       }

                       //gemeinsamer Nenner
                       int gemeinsamerZaehler2 = z1 - z2;

                       //Put Zähler to outputLabel
                       outputZaehler.setText(String.valueOf(gemeinsamerZaehler2));
                       //Put Nenner to outputLabel
                       outputNenner.setText(String.valueOf(gemeinsamerNenner));

                       break;

                   case 'x':

                       //calculate gemeinsamer Zähler
                       gemeinsamerZaehler = z1 * z2;
                       String gemeinsamerZaehlerString = String.valueOf(gemeinsamerZaehler);

                       //And the Nenner
                       gemeinsamerNenner = n1 * n2;
                       String gemeinsamerNennerString = String.valueOf(gemeinsamerNenner);

                        //Put them both in Label
                       outputZaehler.setText(gemeinsamerZaehlerString);
                       outputNenner.setText(gemeinsamerNennerString);

                       break;

                   case '/':

                       //Same thing here, but we now do a switcheroo with the second one
                       gemeinsamerZaehler = z1 * n2;
                       String gemeinsamerZaehlerString2 = String.valueOf(gemeinsamerZaehler);
                       gemeinsamerNenner = n1 * z2;
                       String gemeinsamerNennerString2 = String.valueOf(gemeinsamerNenner);

                       //And ofc put them both in Label
                       outputZaehler.setText(gemeinsamerZaehlerString2);
                       outputNenner.setText(gemeinsamerNennerString2);

                       break;
               }
            }
        });


        inEineGemischteZahlBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int zaehler = Integer.parseInt(outputZaehler.getText());
                int nenner = Integer.parseInt(outputNenner.getText());

                String ganzzahlTeil = String.valueOf(zaehler / nenner);

                String restZaehler = String.valueOf(zaehler % nenner);

                outputGemischteZahl.setText(ganzzahlTeil);
                outputZaehler.setText(restZaehler);
            }
        });


        eingabenZurücksetzenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputNenner.setText("");
                outputZaehler.setText("");
                outputGemischteZahl.setText("");

                zaehler1.setText("");
                zaehler2.setText("");
                nenner1.setText("");
                nenner2.setText("");
            }
        });


        kuerzenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //euklidischer Algorithmus

                int ggt = ggt(Integer.parseInt(outputZaehler.getText()), Integer.parseInt(outputNenner.getText()));

                int gemiensamerZaehler = Integer.parseInt(outputZaehler.getText());
                int gemeinsamerNenner = Integer.parseInt(outputNenner.getText());

                int zaehlerKurz = gemiensamerZaehler / ggt;
                int nennerKurz = gemeinsamerNenner / ggt;

                outputZaehler.setText(String.valueOf(zaehlerKurz));
                outputNenner.setText(String.valueOf(nennerKurz));
            }
        });

    }

    public int ggt(int zaehler, int nenner){
        while (nenner != 0) {
            int temp = nenner;
            nenner = zaehler % nenner;
            zaehler = temp;
        }
        return zaehler;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("burchrechner gui");
        frame.setContentPane(new bruchrechner().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 300);
    }
}
