package zuoye;

import sun.font.DelegatingShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PostGUI extends JFrame implements MouseListener,ItemListener {


    private JTabbedPane jtabbed;

    private JPanel jp1;

    private JButton postuser;

    private JLabel name2, sex2, age2, address2, passage2, //设置购买的品种，购买的数量
                   Picturelabel1;

    private JTextField name1, sex1, age1, address1, passage1;

    private Opengauss db3 = null;

    private ImageIcon picture;

    PostGUI(){
        setPicturelabel();

        setButton();

        setLabel();

        setTextField();

        setPanel();

        setLayout();

        setBase();

        setThis();

        db3 = new Opengauss();
    }

    private void setPicturelabel(){
        picture = new ImageIcon("G:\\magic1\\4.png");
        picture.setImage(picture.getImage().getScaledInstance(280,250,100));
        Picturelabel1 = new JLabel();
        Picturelabel1.setIcon(picture);
    }

    private void setButton(){
        postuser = new JButton("注册");
        postuser.setFont(new Font("宋体",1,20));
        postuser.setBounds(150,400,100,45);
        postuser.setMargin(new Insets(0,0,0,0));

        initial();
    }

    private void setLabel(){
        name2 = new JLabel("名字");
        name2.setFont(new Font("宋体",1,22));
        name2.setBackground(Color.BLUE);
        name2.setBounds(100,30,120,50);

        sex2 = new JLabel("性别");
        sex2.setFont(new Font("宋体",1,22));
        sex2.setBackground(Color.cyan);
        sex2.setBounds(100,80,120,50);

        age2 = new JLabel("年龄");
        age2.setFont(new Font("宋体",1,22));
        age2.setBackground(Color.cyan);
        age2.setBounds(100,130,120,50);

        address2 = new JLabel("地址");
        address2.setFont(new Font("宋体",1,22));
        address2.setBackground(Color.cyan);
        address2.setBounds(100,180,120,50);

        passage2 = new JLabel("密码");
        passage2.setFont(new Font("宋体",1,22));
        passage2.setBackground(Color.cyan);
        passage2.setBounds(100,230,120,50);

        Picturelabel1.setBounds(400,200,280,250);

    }

    private void setTextField(){
        name1 = new JTextField();
        name1.setFont(new Font("宋体", 1, 23));
        name1.setBounds(210, 30, 200, 35);

        sex1 = new JTextField();
        sex1.setFont(new Font("宋体", 1, 23));
        sex1.setBounds(210, 80, 200, 35);

        age1 = new JTextField();
        age1.setFont(new Font("宋体", 1, 23));
        age1.setBounds(210, 130, 200, 35);

        address1 = new JTextField();
        address1.setFont(new Font("宋体", 1, 23));
        address1.setBounds(210, 180, 200, 35);

        passage1 = new JTextField();
        passage1.setFont(new Font("宋体", 1, 23));
        passage1.setBounds(210, 230, 200, 35);
    }

    private void setPanel(){
        jp1 = new JPanel();
    }

    private void setLayout(){
        jp1.setLayout(null);

        jp1.add(postuser);
        jp1.add(name1);
        jp1.add(age1);
        jp1.add(sex1);
        jp1.add(address1);
        jp1.add(passage1);


        jp1.add(age2);
        jp1.add(name2);
        jp1.add(sex2);
        jp1.add(address2);
        jp1.add(passage2);

        jp1.add(Picturelabel1);
    }

    private void setBase(){
        jtabbed = new JTabbedPane(JTabbedPane.TOP);
        jtabbed.addTab("注册",jp1);
    }

    private void setThis(){
        this.add(jtabbed);
        this.setTitle("注册界面");
        this.setLocation(300,300);
        this.setSize(800,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    void initial(){

        postuser.addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent push) {
        if (push.getSource().equals(postuser)) {
            String age = age1.getText();
            String name = name1.getText();
            String sex= sex1.getText();
            String address = address1.getText();
            String password = passage1.getText();
            db3.executeInsert3(name, sex, age, address, password);
            JOptionPane.showOptionDialog(this, "添加信息成功", "Message", JOptionPane.CLOSED_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);

        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}
