package zuoye;

/*
*  定义用户使用的界面
*
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class CustomGUI extends JFrame implements MouseListener,ItemListener {
    private JTabbedPane jtp;

    private JPanel jp1, jp2;

    private JButton shopping,QueryOrder;

    private JLabel shopvari2, shopnumber2,shop_cnumber2, //设置购买的品种，购买的数量
                    Picturelabel1, Picturelabel2;
    private JTextField shopvari1, shopnumber1, shop_cnumber1,OrderCondition,CustomCondition;

    private JTextArea QueryResult;
    
    private JRadioButton order,CustomNumber; //查询方式

    private ImageIcon picture;
    
    private JScrollPane scroll = null;
    
    private Opengauss db2 = null;

    CustomGUI(){
        setPicture();

        setButton();

        setLabel();

        setTextField();

        setPanel();

        setLayout();

        setBase();

        setThis();

        db2 = new Opengauss();
    }

    private void setPicture(){
        picture = new ImageIcon("G:\\magic1\\4.png");
        picture.setImage(picture.getImage().getScaledInstance(280,250,100));

        Picturelabel1 = new JLabel();
        Picturelabel1.setIcon(picture);

        Picturelabel2 = new JLabel();
        Picturelabel2.setIcon(picture);
    }

    private void setButton(){
        shopping = new JButton("购物");
        shopping.setFont(new Font("宋体",1,20));
        shopping.setBounds(200,300,100,45);
        shopping.setMargin(new Insets(0,0,0,0));

        order = new JRadioButton("订单");
        order.setFont(new Font("宋体", 1, 15));
        order.setMargin(new Insets(0, 0, 0, 0));
        order.setBounds(30, 300, 55, 20);

        CustomNumber = new JRadioButton("顾客");
        CustomNumber.setFont(new Font("宋体", 1, 15));
        CustomNumber.setMargin(new Insets(0, 0, 0, 0));
        CustomNumber.setBounds(30, 330, 55, 20);

        QueryOrder = new JButton("查询");
        QueryOrder.setFont(new Font("宋体", 1, 20));
        QueryOrder.setBounds(250, 400, 100, 45);
        
        initial();
    }

    private void setLabel(){
        shop_cnumber2 = new JLabel("编号");
        shop_cnumber2.setFont(new Font("宋体",1,22));
        shop_cnumber2.setBackground(Color.BLUE);
        shop_cnumber2.setBounds(100,80,120,50);

        shopvari2 = new JLabel("品种");
        shopvari2.setFont(new Font("宋体",1,22));
        shopvari2.setBackground(Color.cyan);
        shopvari2.setBounds(100,130,120,50);

        shopnumber2 = new JLabel("数量");
        shopnumber2.setFont(new Font("宋体",1,22));
        shopnumber2.setBackground(Color.cyan);
        shopnumber2.setBounds(100,180,120,50);

        Picturelabel1.setBounds(400,200,280,250);

        Picturelabel2.setBounds(400,200,280,250);

    }

    private void setTextField(){
        shop_cnumber1 = new JTextField();
        shop_cnumber1.setFont(new Font("宋体", 1, 23));
        shop_cnumber1.setBounds(210, 80, 200, 35);

        shopvari1 = new JTextField();
        shopvari1.setFont(new Font("宋体", 1, 23));
        shopvari1.setBounds(210, 130, 200, 35);

        shopnumber1 = new JTextField();
        shopnumber1.setFont(new Font("宋体", 1, 23));
        shopnumber1.setBounds(210, 180, 200, 35);

        //query
        QueryResult = new JTextArea("查询结果");
        QueryResult.setFont(new Font("宋体",1,20));
        QueryResult.setEditable(false);
        QueryResult.setLineWrap(true);

        scroll = new JScrollPane(QueryResult);
        scroll.setBounds(30,30,400,260);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);    // 当需要垂直滚动条时显示
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 当需要水平滚动条时显示

        OrderCondition = new JTextField();
        OrderCondition.setFont(new Font("宋体",1,18));
        OrderCondition.setBounds(90,300,100,21);

        OrderCondition.setEditable(true);


        CustomCondition = new JTextField();
        CustomCondition.setFont(new Font("宋体",1,18));
        CustomCondition.setBounds(90,330,100,21);

        CustomCondition.setEditable(true);

    }

    private void setPanel(){
        jp1 = new JPanel();
        jp2 = new JPanel();
    }

    private void setLayout(){
        jp1.setLayout(null);

        jp1.add(shopping);
        jp1.add(shop_cnumber1);
        jp1.add(shopnumber1);
        jp1.add(shopvari1);

        jp1.add(shopnumber2);
        jp1.add(shop_cnumber2);
        jp1.add(shopvari2);

        jp1.add(Picturelabel1);

        jp2.setLayout(null);
        jp2.add(scroll);
        jp2.add(order);
        jp2.add(OrderCondition);

        jp2.add(QueryOrder);

        jp2.add(CustomNumber);
        jp2.add(CustomCondition);

        jp2.add(Picturelabel2);
    }

    private void setBase(){
        jtp = new JTabbedPane(JTabbedPane.TOP);
        jtp.addTab("购买",jp1);
        jtp.addTab("查询",jp2);
    }

    private void setThis(){
        this.add(jtp);
        this.setTitle("顾客界面");
        this.setLocation(300,300);
        this.setSize(800,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    void initial(){

        shopping.addMouseListener(this);
        QueryOrder.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent push) {
        if (push.getSource().equals(shopping)) {
            String shop__number = shopnumber1.getText();
            String shop__cnumber = shop_cnumber1.getText();
            String shop__vari = shopvari1.getText();

            try {
                db2.setRs(db2.executeQuery(shop__vari));
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            try {
                if (db2.getRs().next()) {
                    db2.executeInsert2(shop__vari, shop__cnumber, shop__number);
                    JOptionPane.showOptionDialog(this, "添加信息成功", "Message", JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                } else {
                    JOptionPane.showOptionDialog(this, "购物失败", "温馨提示",
                            -1, 1, null, null, null);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else if(push.getSource().equals(QueryOrder)){
            try{
                String a ="%", b = "%";

                if(order.isSelected() && !OrderCondition.getText().trim().isEmpty()) {
                    a = OrderCondition.getText();
                }
                if(CustomNumber.isSelected() && !CustomCondition.getText().trim().isEmpty()){
                    b = CustomCondition.getText();
                }

                db2.setRs(db2.executeQueryByCondition(a,b));

                int i = 0;

                while(db2.getRs().next()){
                    ++i;
                    QueryResult.append("\r\n" + "number  " + i + "  record" + "\r\n"
                            + "品种：      " + db2.getRs().getString(1) + "\r\n"
                            + "订单编号 ：  " + db2.getRs().getString(2) + "\r\n"
                            + "顾客编号：   " + db2.getRs().getString(3) + "\r\n"
                            + "购买数量：   " + db2.getRs().getString(4) + "\r\n"
                            +
                            ("\r\n-----------------"));
                    }
                    QueryResult.setText(QueryResult.getText() +
                            "\r\n" + "共有" + i + "记录");

                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
                finally {
                    db2.CloseRS();
                    db2.CloseStmt();
                    db2.CloseConnection();
            }
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
        if(e.getSource().equals(order)){
            OrderCondition.setEditable(order.isSelected());
        }
    }
}
