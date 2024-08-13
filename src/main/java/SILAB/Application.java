package SILAB;

import SILAB.logic.Service;
import SILAB.presentation.instrumentos.Controller;
import SILAB.presentation.instrumentos.Model;
import SILAB.presentation.instrumentos.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        window = new JFrame();
        window.setContentPane(new JTabbedPane());

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });


        SILAB.presentation.tipos.Model tiposModel= new SILAB.presentation.tipos.Model();
        SILAB.presentation.tipos.View tiposView = new SILAB.presentation.tipos.View();
        tiposController = new SILAB.presentation.tipos.Controller(tiposView,tiposModel);

        SILAB.presentation.calibraciones.Model calibraModel= new SILAB.presentation.calibraciones.Model();
        SILAB.presentation.calibraciones.View calibraView = new SILAB.presentation.calibraciones.View();
        calibracionesController = new SILAB.presentation.calibraciones.Controller(calibraView,calibraModel);

        Model instrumentosModel2= new Model();
        View instrumentosView2= new View();
        instrumentosController2 = new Controller(instrumentosView2,instrumentosModel2);

        JLabel aboutLabel = new JLabel("<html><center><h1>Integrantes:</h1></center><br>" +
                                "<li>Jordan Alvarez Gonzalez</li>" +
                "<li>Cristopher Castro Aguilar</li>" +
                "<li>Alejandro Araya Mendez</li></ul></html>");

// Establecer el tamaño de la fuente para el texto
        aboutLabel.setFont(new Font("Arial", Font.PLAIN, 14));

// Alinear el texto al centro horizontalmente
        aboutLabel.setHorizontalAlignment(SwingConstants.CENTER);

// Agregar el JLabel a un JPanel
        JPanel aboutPanel = new JPanel(new BorderLayout());
        aboutPanel.add(aboutLabel, BorderLayout.CENTER);


// Agregar el panel "Acerca de" a la pestaña

        window.getContentPane().add("Tipos de Instrumento",tiposView.getPanel());
        window.getContentPane().add(" Instrumento",instrumentosView2.getPanel());
        window.getContentPane().add(" Calibraciones",calibraView.getPanel());
        window.getContentPane().add("Acerca de", aboutPanel);

        //  window.getContentPane().add(" Acerca De", );


        window.setSize(900,450);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setIconImage((new ImageIcon(Application.class.getResource("presentation/icons/icon.png"))).getImage());
        window.setTitle("SILAB: Sistema de Laboratorio Industrial");
        window.setVisible(true);
    }

    public static SILAB.presentation.tipos.Controller tiposController;
    public static Controller instrumentosController2;
    public static SILAB.presentation.calibraciones.Controller calibracionesController;

    public final static int modeCreation=1;
    public final static int modeEdit=2;
    public final static int modeMedicion=3;


    public static JFrame window;
}
