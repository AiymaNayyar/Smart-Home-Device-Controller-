import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class device{
    private String name;
    private boolean ison;

    device(String name, boolean ison) {
        this.name = name;
        this.ison = ison;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void turnon() {
        this.ison = true;
        System.out.println(getName()+" is turned on ");

    }
    public void turnoff() {
        this.ison = false;
        System.out.println(getName()+" is turned off");
    }

    public boolean isOn() {
        return ison;
    }

@Override
    public String toString(){
        return "the device "+getName()+" is "+(ison?"on":"off");
    }
}
class light extends device{
    int brightness;
    light(String name, boolean ison) {
        super("light", true);
        this.brightness=60;
    }
    public void increaseb(){
        if(brightness<100){
            brightness+=5;
            System.out.println(" brightness increase to "+brightness);
        }
        else{
            System.out.println(" bright is already 100%");
        }

    }
    public void decreaseb(){
        if(brightness>0){
            brightness-=5;
            System.out.println(" brightness decrease to "+brightness);

        }
        else{
            System.out.println(" bright is already 0%");
        }

    }
    @Override
    public String toString(){
        return super.toString();
    }
}
class thermostat extends  device{
    int temperature;
    thermostat(String name, boolean ison) {
        super("thermostat", true);
        this.temperature=30;
    }
    public void increaset(){
        if(temperature<100){
            temperature+=5;
            System.out.println(" temperature increase to "+temperature);

        }
        else{
            System.out.println("temperature is already 100 degree ");
        }
    }
    public void decreaset(){
        if(temperature>0){
            temperature-=5;
            System.out.println(" temperature decrease to "+temperature);

        }
        else{
            System.out.println(" temperature is already 0 degree");
        }
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
class securitycamera extends device{
    boolean capture;
    securitycamera(String name, boolean ison) {
        super("security camera", true);
        this.capture=true;
    }

    @Override
    public String toString(){
        return super.toString()+"  is "+(capture?" capturing":" not capturing");
    }
}
class manager{
    ArrayList<device> devices;
    manager() {
        this.devices = new ArrayList<>();
    }
    public void adddevice(device d){
        devices.add(d);
        System.out.println(d+" and is added succesfuly");
    }
    public void removedevice(device b){
        devices.remove(b);
        System.out.println(b+" and is removed succesfully");
    }

    public void saveFile() {
        String f1 = "file1.txt";
        try (BufferedWriter b1 = new BufferedWriter(new FileWriter(f1))) {
            for (device d : devices) {
                b1.write(d.toString());
                b1.newLine();
            }
        } catch (IOException e) {
            System.out.println(" error file is not created ");
            e.printStackTrace();
        }
    }
}
class window extends JFrame {
    window() {
        manager m = new manager();
        setTitle(" smart homecontroller");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        CardLayout c=new CardLayout();
        JPanel mp=new JPanel(c);
        JPanel p = new JPanel(new FlowLayout());
        JButton bl = new JButton(" Light  ");
        JButton bt = new JButton(" Thermostat");
        JButton bc = new JButton(" Camera ");
        p.add(bl);
        p.add(bt);
        p.add(bc);
        JPanel pl=new JPanel(new FlowLayout());
        JButton b = new JButton(" turnon light  ");
        JButton b11 = new JButton(" turnoff light ");
        JButton b1 = new JButton(" add light");
        JButton b4 = new JButton(" remove light");
        JButton hl=   new JButton(" Home");
        JLabel m1=new JLabel("");
        JSlider s1=new JSlider(0,100,50);
        pl.add(b1);
        pl.add(b4);
        pl.add(b);
        pl.add(b11);
        pl.add(hl);
        pl.add(m1);
        pl.add(s1);
        bl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device d=new light("light",false);
                light l=new light("light",true);
                b1.addActionListener(ev -> {m.adddevice(d);m1.setText(" light is added ");});
                b4.addActionListener(ev->{m.removedevice(d);m1.setText(" light is removed ");});
                b.addActionListener(ev->{d.turnon();m.saveFile();m1.setText(" light is on ");});
                b11.addActionListener(ev->{d.turnoff();m.saveFile();m1.setText(" light is off");});
                s1.addChangeListener(ev->{if(l.brightness<s1.getValue()){l.increaseb();m1.setText(" brightness increased "+s1.getValue());}
                    else if(l.brightness>s1.getValue()){l.decreaseb();;m1.setText(" brightness decreased "+s1.getValue());}});
            }
        });
        JPanel pt=new JPanel(new FlowLayout());
        JButton b12 = new JButton(" turnon thermostat ");
        JButton b13 = new JButton(" turnoff thermostat ");
        JButton b2 = new JButton(" add thermostat");
        JButton b5 = new JButton(" remove thermostat");
        JButton b9 = new JButton(" increase temperature");
        JButton b10 = new JButton(" decrease temperature");
        JButton ht=   new JButton(" Home");
        JLabel m2=new JLabel("");
        pt.add(b2);
        pt.add(b5);
        pt.add(b12);
        pt.add(b13);
        pt.add(b9);
        pt.add(b10);
        pt.add(ht);
        pt.add(m2);
        bt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device d=new thermostat("thermostat",false);
                thermostat t = new thermostat(" thermostat", true);
                b2.addActionListener(ev -> {m.adddevice(d);m2.setText(" thermostat is added ");});
                b5.addActionListener(ev->{m.removedevice(d);m2.setText(" thermostat is removed ");});
                b12.addActionListener(ev->{d.turnon();m.saveFile();m2.setText(" thermostat is on ");});
                b13.addActionListener(ev->{d.turnoff();m.saveFile();m2.setText( " thermostat is off ");});
                b9.addActionListener(ev->{t.increaset(); m2.setText(" temperature increased");});
                b10.addActionListener(ev->{t.decreaset();m2.setText(" temperature decreased");});

            }
        });
                JPanel pc=new JPanel(new FlowLayout());
                JButton b14 = new JButton(" turnon camera ");
                JButton b15 = new JButton(" turnoff camera ");
                JButton b3 = new JButton(" add security camera");
                JButton b6 = new JButton(" remove security camera");
                JButton hc=   new JButton(" Home");
                JLabel m3=new JLabel("");
                pc.add(b3);
                pc.add(b6);
                pc.add(b14);
                pc.add(b15);
                pc.add(hc);
                pc.add(m3);
        bc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                device d=new securitycamera("camera",false);
                b3.addActionListener(ev-> {m.adddevice(d);m.saveFile();m3.setText(" camera is added ");});
                b6.addActionListener(ev->{m.removedevice(d);m3.setText(" camera is removed");});
                b14.addActionListener(ev->{d.turnon();m3.setText(" camera is on");});
                b15.addActionListener(ev->{d.turnoff();m3.setText(" camera is off ");});
            }
        });
         mp.add(p," Home");
         mp.add(pl," Light");
         mp.add(pt," Thermostat");
         mp.add(pc," Camera");
        bl.addActionListener(ev -> c.show(mp, " Light"));
        bt.addActionListener(ev -> c.show(mp, " Thermostat"));
        bc.addActionListener(ev -> c.show(mp, " Camera"));
        hl.addActionListener(ev->  c.show(mp," Home"));
        ht.addActionListener(ev->  c.show(mp," Home"));
        hc.addActionListener(ev->  c.show(mp," Home"));
        add(mp);
        setVisible(true);
    }
}
    public class homecontroller {

        public static void main(String[] args) {
            window w =new window();
        }
    }

