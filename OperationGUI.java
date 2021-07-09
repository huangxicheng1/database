package zuoye;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class OperationGUI extends JFrame implements MouseListener, ItemListener {
    private JTabbedPane jtabbedpane;

    private JPanel jpanel1,jpanel2,jPanel3,jPanel4,jpanel5;

    private JButton Insertinformation,DeleteProduct,UpdateProduct,QueryOrder,QueryOrder2;//Query;

    private JLabel InsertPname2, InsertPlace2, InsertRemaing_number2, InsertType2, InsertUnit_price2, Insertvarieties2,//插入农产品基本信息
                   DeleteVari2, //删除的品种
                   UpdateNumber2, UpdatePrice2, UpdateName2, //更新剩余的数量
                    Picturelabel1,Picturelabel2,Picturelabel3,Picturelabel4,Picturelabel5; //插入的图片

    private JTextField InsertPname1, InsertPlace1, InsertRemaing_number1, InsertType1, InsertUnit_price1, Insertvarieties1, //要插入的农产品信息表
                       DeleteVari1, // 删除农产品
                       UpdateNumber1, UpdatePrice1, UpdateName1, //更新的农产品数量和单价
                       OrderCondition, CustomCondition, ProductConditon,
                       PlaceCondition,TypeCondition;

    private JTextArea QueryResult,QueryRusltProduct; //查询结果的输出表

    private JRadioButton order,CustomNumber, Productname,//按照农产品 按照订单号查询，按照顾客编号查询
                         Place,Type;
    private JScrollPane scroll,Productscorll = null;

    private Opengauss db = null;

    private ImageIcon picture;

    OperationGUI(){
        setPicture();
        // 设置各按钮信息
        setButton();
        // 设置各标签信息
        setLabel();
        // 设置各文本框信息
        setTextField();
        // 设置各面板信息
        setPanel();
        // 设置布局信息
        setLayout();
        // 设置选项卡信息
        setBase();
        // 设置主窗口信息
        setThis();
        // 连接数据库信息
        //Connect();
        db = new Opengauss();
    }

    private void setPicture(){
        picture = new ImageIcon("G:\\magic1\\4.png");
        picture.setImage(picture.getImage().getScaledInstance(280,250,100));
        Picturelabel1 = new JLabel();
        Picturelabel1.setIcon(picture);

        Picturelabel2 = new JLabel();
        Picturelabel2.setIcon(picture);

        Picturelabel3 = new JLabel();
        Picturelabel3.setIcon(picture);

        Picturelabel4 = new JLabel();
        Picturelabel4.setIcon(picture);

        Picturelabel5 = new JLabel();
        Picturelabel5.setIcon(picture);
    }

    private void setButton(){
        //插入农产品信息的按钮
        Insertinformation = new JButton("添加");
        Insertinformation.setFont(new Font("宋体",1,20));
        Insertinformation.setBounds(150,400,100,45);
        Insertinformation.setMargin(new Insets(0,0,0,0));
        //删除农产品信息的按钮
        DeleteProduct = new JButton("删除");
        DeleteProduct.setFont(new Font("宋体", 1, 20));
        DeleteProduct.setBounds(150, 350, 100, 45);
        DeleteProduct.setMargin(new Insets(0, 0, 0, 0));
        //更新农产品单价和数量的按钮
        UpdateProduct = new JButton("更新");
        UpdateProduct.setFont(new Font("宋体", 1, 20));
        UpdateProduct.setBounds(250, 400, 100, 45);
        //查询订单信息
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
        QueryOrder.setMargin(new Insets(0, 0, 0, 0));
        QueryOrder.setBounds(250, 400, 100, 45);

        Place = new JRadioButton("产地");
        Place.setFont(new Font("宋体", 1, 15));
        Place.setMargin(new Insets(0, 0, 0, 0));
        Place.setBounds(30, 300, 55, 20);

        Type = new JRadioButton("类型");
        Type.setFont(new Font("宋体", 1, 15));
        Type.setMargin(new Insets(0, 0, 0, 0));
        Type.setBounds(30, 330, 55, 20);

        QueryOrder2 = new JButton("查询");
        QueryOrder2.setFont(new Font("宋体", 1, 20));
        QueryOrder2.setMargin(new Insets(0, 0, 0, 0));
        QueryOrder2.setBounds(250, 400, 100, 45);

        //初始化
        initial();

    }

    private void setLabel(){

        //插入农产品信息的标签——第一个标签页
        InsertPname2 = new JLabel("农产品：");
        InsertPname2.setFont(new Font("宋体",1,22));
        InsertPname2.setBackground(Color.GREEN);
        InsertPname2.setBounds(100, 30, 120, 50);

        InsertPlace2 = new JLabel("产地：");
        InsertPlace2.setFont(new Font("宋体", 1, 22));
        InsertPlace2.setBackground(Color.GREEN);
        InsertPlace2.setBounds(100, 80, 120, 50);

        Insertvarieties2 = new JLabel("品种：");
        Insertvarieties2.setFont(new Font("宋体", 1, 22));
        Insertvarieties2.setBackground(Color.GREEN);
        Insertvarieties2.setBounds(100, 130, 120, 50);

        InsertRemaing_number2 = new JLabel("剩余数量：");
        InsertRemaing_number2.setFont(new Font("宋体", 1, 22));
        InsertRemaing_number2.setBackground(Color.GREEN);
        InsertRemaing_number2.setBounds(100, 180, 120, 50);

        InsertUnit_price2 = new JLabel("单价：");
        InsertUnit_price2.setFont(new Font("宋体", 1, 22));
        InsertUnit_price2.setBackground(Color.GREEN);
        InsertUnit_price2.setBounds(100, 230, 120, 50);

        InsertType2 = new JLabel("种类：");
        InsertType2.setFont(new Font("宋体", 1, 22));
        InsertType2.setBackground(Color.GREEN);
        InsertType2.setBounds(100,280 , 120, 50);

        Picturelabel2.setBounds(400,200,280,250);

        //要删除的农产品品种
        DeleteVari2 = new JLabel("品种：");
        DeleteVari2.setBounds(100,100,100,50);
        DeleteVari2.setFont(new Font("宋体",1,22));

        Picturelabel1.setBounds(400,200,280,250);

        //要更新的农产品库存和价格
        UpdateName2 = new JLabel("品种：");
        UpdateName2.setFont(new Font("宋体", 1, 22));
        UpdateName2.setBounds(100, 30, 120, 50);

        UpdateNumber2 = new JLabel("余量：");
        UpdateNumber2.setFont(new Font("宋体", 1, 22));
        UpdateNumber2.setBounds(100, 100, 120, 50);

        UpdatePrice2 = new JLabel("单价：");
        UpdatePrice2.setFont(new Font("宋体", 1, 22));
        UpdatePrice2.setBounds(100, 170, 120, 50);

        Picturelabel3.setBounds(400,200,280,250);
        Picturelabel4.setBounds(400,200,280,250);
        Picturelabel5.setBounds(400,200,280,250);

    }

    private void setTextField(){
        //第一个页面
        InsertPname1 = new JTextField();
        InsertPname1.setFont(new Font("宋体", 1, 23));
        InsertPname1.setBounds(210, 30, 200, 35);

        InsertPlace1 = new JTextField();
        InsertPlace1.setFont(new Font("宋体", 1, 23));
        InsertPlace1.setBounds(210, 80, 200, 35);

        Insertvarieties1 = new JTextField();
        Insertvarieties1.setFont(new Font("宋体", 1, 23));
        Insertvarieties1.setBounds(210, 130, 200, 35);

        InsertRemaing_number1 = new JTextField();
        InsertRemaing_number1.setFont(new Font("宋体", 1, 23));
        InsertRemaing_number1.setBounds(210, 180, 200, 35);

        InsertUnit_price1= new JTextField();
        InsertUnit_price1.setFont(new Font("宋体", 1, 23));
        InsertUnit_price1.setBounds(210, 230, 200, 35);

        InsertType1 = new JTextField();
        InsertType1.setFont(new Font("宋体", 1, 23));
        InsertType1.setBounds(210, 280, 200, 35);

        //删除的输入文本
        DeleteVari1 = new JTextField("是详细品种");
        DeleteVari1.setFont(new Font("宋体", 1, 25));
        DeleteVari1.setBounds(210, 100, 350, 50);

        //要更新的输入文本
        UpdateName1 = new JTextField();
        UpdateName1.setFont(new Font("宋体", 1, 22));
        UpdateName1.setBounds(210, 30, 120, 50);

        UpdateNumber1 = new JTextField();
        UpdateNumber1.setFont(new Font("宋体", 1, 22));
        UpdateNumber1.setBounds(210, 100, 120, 50);

        UpdatePrice1 = new JTextField();
        UpdatePrice1.setFont(new Font("宋体", 1, 22));
        UpdatePrice1.setBounds(210, 170, 120, 50);

        //查询操作
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

        CustomCondition = new JTextField();
        CustomCondition.setFont(new Font("宋体",1,18));
        CustomCondition.setBounds(90,330,100,21);

        OrderCondition.setEditable(true);
        CustomCondition.setEditable(true);

        //Page5的页面

        QueryRusltProduct = new JTextArea("查询结果");
        QueryRusltProduct.setFont(new Font("宋体",1,20));
        QueryRusltProduct.setEditable(false);
        QueryRusltProduct.setLineWrap(true);

        Productscorll = new JScrollPane(QueryRusltProduct);
        Productscorll.setBounds(30,30,400,260);
        Productscorll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);    // 当需要垂直滚动条时显示
        Productscorll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);// 当需要水平滚动条时显示

        PlaceCondition = new JTextField();
        PlaceCondition.setFont(new Font("宋体",1,18));
        PlaceCondition.setBounds(90,300,100,21);

        TypeCondition = new JTextField();
        TypeCondition.setFont(new Font("宋体",1,18));
        TypeCondition.setBounds(90,330,100,21);


        PlaceCondition.setEditable(true);
        TypeCondition.setEditable(true);
    }

    //设置面板信息
    private void setPanel(){
        jpanel1 = new JPanel();
        jpanel2 = new JPanel();
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jpanel5 = new JPanel();
    }

    //设置布局信息
    private void setLayout(){
        //page 1
        jpanel1.setLayout(null);

        //按钮
        jpanel1.add(Insertinformation);

        //JLabel
        jpanel1.add(InsertPname2);
        jpanel1.add(InsertPlace2);
        jpanel1.add(Insertvarieties2);
        jpanel1.add(InsertRemaing_number2);
        jpanel1.add(InsertUnit_price2);
        jpanel1.add(InsertType2);
        jpanel1.add(Picturelabel2);
        
        //JTextField
        jpanel1.add(InsertPname1);
        jpanel1.add(InsertPlace1);
        jpanel1.add(Insertvarieties1);
        jpanel1.add(InsertRemaing_number1);
        jpanel1.add(InsertUnit_price1);
        jpanel1.add(InsertType1);
    



        //第二个页面
        jpanel2.setLayout(null);

        jpanel2.add(DeleteVari2);
        jpanel2.add(DeleteVari1);

        jpanel2.add(DeleteProduct);

        jpanel2.add(Picturelabel1);
        //page3
        jPanel3.setLayout(null);

        jPanel3.add(UpdateProduct);

        jPanel3.add(UpdateName1);
        jPanel3.add(UpdateNumber1);
        jPanel3.add(UpdatePrice1);
        jPanel3.add(UpdateName2);
        jPanel3.add(UpdateNumber2);
        jPanel3.add(UpdatePrice2);
        jPanel3.add(Picturelabel3);

        //page4
        jPanel4.setLayout(null);

        jPanel4.add(scroll);

        jPanel4.add(order);
        jPanel4.add(CustomNumber);

        jPanel4.add(OrderCondition);
        jPanel4.add(CustomCondition);

        jPanel4.add(QueryOrder);
        jPanel4.add(Picturelabel4);

        //page5
        jpanel5.setLayout(null);

        jpanel5.add(Productscorll);

        jpanel5.add(Place);
        jpanel5.add(Type);

        jpanel5.add(PlaceCondition);
        jpanel5.add(TypeCondition);
        //jPanel4.add(ProductConditon);

        jpanel5.add(QueryOrder2);
        jpanel5.add(Picturelabel5);
    }

    //选项卡
    private void setBase(){
        jtabbedpane = new JTabbedPane(JTabbedPane.TOP);
        jtabbedpane.addTab("添加信息",jpanel1);
        jtabbedpane.addTab("删除信息",jpanel2);
        jtabbedpane.addTab("更新信息",jPanel3);
        jtabbedpane.addTab("查询信息",jPanel4);
        jtabbedpane.addTab("农产品",jpanel5);
    }

    private void setThis(){
        this.add(jtabbedpane);
        this.setTitle("农产品销售系统");
        this.setLocation(300,300);
        this.setSize(800,550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

    }

    void initial(){

        Insertinformation.addMouseListener(this);
        DeleteProduct.addMouseListener(this);
        UpdateProduct.addMouseListener(this);
        QueryOrder.addMouseListener(this);
        QueryOrder2.addMouseListener(this);

    }

    @Override
    public void mouseClicked(MouseEvent push){
        //点击了插入按钮的响应

        if(push.getSource().equals(Insertinformation)){
            String Insert_ProductName = InsertPname1.getText();
            String Insert_ProductPlace = InsertPlace1.getText();
            String Insert_Productvari = Insertvarieties1.getText();
            String Insert_ProductRemaing_number = InsertRemaing_number1.getText();
            String Insert_ProductUnit = InsertUnit_price1.getText();
            String Insert_ProductType = InsertType1.getText();
            try {
                try {
                    db.setRs(db.executeQuery(Insert_Productvari));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(!db.getRs().next()){
                        try {
                            db.executeInsert(Insert_ProductName,Insert_ProductPlace,Insert_Productvari,Insert_ProductRemaing_number,Insert_ProductUnit,Insert_ProductType);
                            JOptionPane.showOptionDialog(this,"添加农产品成功","Message",JOptionPane.CLOSED_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        JOptionPane.showOptionDialog(this, "添加农产品失败", "Message",
                                -1, 1, null, null, null);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
            }
            finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        }
        else if(push.getSource().equals(DeleteProduct)) {
            String Deletevariety = DeleteVari1.getText();
            try {
                db.setRs(db.executeQuery(Deletevariety));
                if(db.getRs().next()){
                    db.executeDelete(Deletevariety);
                    JOptionPane.showOptionDialog(this, "删除农产品成功！", "Message",
                            -1, 1, null, null, null);
                }
                else{
                    JOptionPane.showOptionDialog(this, "未找到农产品信息", "Message",
                            -1, 1, null, null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(push.getSource().equals(UpdateProduct)){
            String Updatename = UpdateName1.getText();
            String Updateprice = UpdatePrice1.getText();
            String Updatenumber = UpdateNumber1.getText();
            try {
                db.setRs(db.executeQuery(Updatename));
                if(!db.getRs().next()){
                    JOptionPane.showOptionDialog(this, "未找到农产品",
                            "Message", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE,
                            null, null, null);
                }
                else{
                    db.executeUpdate(Updatename,Updatenumber,Updateprice);
                    JOptionPane.showOptionDialog(this, "数据更新成功！", "Message",
                            -1, 1, null, null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        }
        else if(push.getSource().equals(QueryOrder))
        {
            try{
            String a ="%", b = "%";

            if(order.isSelected() && !OrderCondition.getText().trim().isEmpty()) {
                a = OrderCondition.getText();
            }
            if(CustomNumber.isSelected() && !CustomCondition.getText().trim().isEmpty()){
                b = CustomCondition.getText();
            }

            db.setRs(db.executeQueryByCondition(a,b));

            int i = 0;

            while(db.getRs().next()){
                ++i;
                QueryResult.append("\r\n" + "number  " + i + "  record" + "\r\n"
                + "品种：      " + db.getRs().getString(1) + "\r\n"
                + "订单编号：   " + db.getRs().getString(2) + "\r\n"
                + "顾客编号：   " + db.getRs().getString(3) + "\r\n"
                + "购买数量：   " + db.getRs().getString(4) + "\r\n"
                        +
                        ("\r\n-----------------"));
            }
            QueryResult.setText(QueryResult.getText() +
                    "\r\n" + "共有" + i + "记录"+ "\r\n");

        }
            catch (Exception exception){
                exception.printStackTrace();
            }
            finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
            }
        }
        else if(push.getSource().equals(QueryOrder2)){
            try{
                String a ="%", b = "%";

                if(Place.isSelected() && !PlaceCondition.getText().trim().isEmpty()) {
                    a = PlaceCondition.getText();
                }
                if(Type.isSelected() && !TypeCondition.getText().trim().isEmpty()){
                    b = TypeCondition.getText();
                }

                db.setRs(db.executeQueryByProduct(a,b));

                int i = 0;

                while(db.getRs().next()){
                    ++i;
                    QueryRusltProduct.append("\r\n" + "number  " + i + "  record" + "\r\n"
                            + "名称： " + db.getRs().getString(1) + "\r\n"
                            + "产地： " + db.getRs().getString(2) + "\r\n"
                            + "品种： " + db.getRs().getString(3) + "\r\n"
                            + "余量： " + db.getRs().getString(4) + "\r\n"
                            + "单价： " + db.getRs().getString(5) + "\r\n"
                            + "品种： " + db.getRs().getString(6) + "\r\n"

                            +
                            ("\r\n-----------------"));
                }
                QueryRusltProduct.setText(QueryRusltProduct.getText() +
                        "\r\n" + "共有" + i + "记录");

            }
            catch (Exception exception){
                exception.printStackTrace();
            }
            finally {
                db.CloseRS();
                db.CloseStmt();
                db.CloseConnection();
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
        else if(e.getSource().equals(CustomNumber)){
            CustomCondition.setEditable(CustomNumber.isSelected());
        }
        else if(e.getSource().equals(Productname)){
            ProductConditon.setEditable(Productname.isSelected());
        }
    }
}
