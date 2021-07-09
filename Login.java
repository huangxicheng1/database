package zuoye;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login implements ActionListener {



    private JFrame jframe;    //主窗口
    private JLabel inputname;      //用户名的lable
    private JLabel inputpassword;   //密码的lable
    private JTextField username;      //用户框
    private JPasswordField password; //密码框
    private JButton login;           //登录按钮
    private JButton post;
    private JButton cancel;
    private JLabel Picturelabel1;

    private ImageIcon picture;
    //private JButton

    Login() {
        jframe = new JFrame("  登录");
        inputname = new JLabel("用户名:");
        inputpassword = new JLabel("    密码:");
        username = new JTextField();
        password = new JPasswordField();

        login = new JButton("登录");
        cancel = new JButton("退出");
        post = new JButton("注册");

        jframe.setSize(400, 250);
        jframe.setLocation(600, 400);
        jframe.setLayout(new FlowLayout());
        username.setPreferredSize(new Dimension(300, 30));
        password.setPreferredSize(new Dimension(300, 30));

        jframe.getContentPane().add(inputname);
        jframe.getContentPane().add(username);
        jframe.getContentPane().add(inputpassword);
        jframe.getContentPane().add(password);
        jframe.getContentPane().add(login);
        jframe.getContentPane().add(cancel);
        jframe.getContentPane().add(post);

        picture = new ImageIcon("G:\\magic1\\6.png");
        picture.setImage(picture.getImage().getScaledInstance(200,80,100));
        Picturelabel1 = new JLabel();
        Picturelabel1.setIcon(picture);
        //Picturelabel1.setBounds(100,250,150,50);
        jframe.getContentPane().add(Picturelabel1);


        login.addActionListener(this);
        cancel.addActionListener(this);
        post.addActionListener(this);

        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);




    }
    @Override
    public void actionPerformed(ActionEvent push) {
        if (push.getSource().equals(cancel)) System.exit(0); //0是正常退出程序，非0整数表示非正常退出程序

        //不想退出的两种情况——输对或者输错
        else if (push.getSource().equals(login)) {
            if (username.getText().equals("brw") && String.valueOf(password.getPassword()).equals("brw")) {
                new OperationGUI();
                jframe.setVisible(false);
                jframe.dispose();
            }else if(username.getText().equals("xiaohong") && String.valueOf(password.getPassword()).equals("1")){
                new CustomGUI();
                jframe.setVisible(false);
                jframe.dispose();
            }

            else {
                JOptionPane.showOptionDialog(jframe, "输错了", "错误提示",
                        JOptionPane.CLOSED_OPTION,
                        JOptionPane.ERROR_MESSAGE, null, null, null);
            }
        }
        else if(push.getSource().equals(post))
            new PostGUI();
            jframe.setVisible(false);
            jframe.dispose();
    }

}


