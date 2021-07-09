package zuoye;

import java.sql.*;
public class Opengauss{

    // 定义数据库的连接
    private Connection conn = null;
    // 定义数据库状态
    private PreparedStatement stmt = null;
    // 定义数据库返回结果集
    private ResultSet rs = null;
    // 返回当前实例结果集
    public ResultSet getRs() {
        return rs;
    }
    // 设置当前实例结果集
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    // 关闭结果集
    public void CloseRS() {
        try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("关闭结果集时发生错误！");
        }
    }

    // 关闭状态的方法
    public void CloseStmt() {
        try {
            stmt.close();
        } catch (SQLException e) {
            System.out.println("关闭状态时发生错误！");
        }
    }

    // 关闭连接
    public void CloseConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("关闭连接时发生错误！");
        }
    }
    //顾客注册
    void executeInsert3(String name, String sex, String age, String address, String password){
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            try {
                Class.forName(driver); //加载驱动
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");
            System.out.println("Opened database successfully!");

            stmt = conn.prepareStatement("insert into public.customer_information values(?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, sex);
            stmt.setString(3, age);
            stmt.setString(4, address);
            stmt.setString(5, password);
            stmt.setString(6, String.valueOf((int)(Math.random()*1000)));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    // 顾客购物
    void executeInsert2(String vari, String custom_name, String number){
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            try {
                Class.forName(driver); //加载驱动
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");

            System.out.println("Opened database successfully!");
            stmt = conn.prepareStatement("insert into public.sale_information values(?,?,?,?)");
            stmt.setString(1, vari);
            stmt.setString(2, String.valueOf((int)(Math.random()*1000)));
            stmt.setString(3,custom_name);
            stmt.setString(4,number);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    //插入农产品信息
    void executeInsert(String Pname, String Place, String variety, String Remaing_number, String Unit_Price, String Type) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动

            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");

            System.out.println("Opened database successfully!");
            stmt = conn.prepareStatement("insert into public.product values(?,?,?,?,?,?)");
            stmt.setString(1, Pname);
            stmt.setString(2, Place);
            stmt.setString(3, variety);
            stmt.setString(4, Remaing_number);
            stmt.setString(5, Unit_Price);
            stmt.setString(6, Type);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    // 删
    void executeDelete(String DeleteProduct) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动

            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");

            System.out.println("Opened database successfully!");
            stmt = conn.prepareStatement("delete from public.product where varieties = ?");
            stmt.setString(1, DeleteProduct);
            stmt.executeUpdate();
            CloseStmt();
            CloseConnection();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    // 查询农产品的主键是否存在
    // 防止不存在主键
    ResultSet executeQuery(String UpdateVari) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动

            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");

            System.out.println("Opened database successfully!");
            String sql = "select * from public.product where varieties = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, UpdateVari);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }
    //用户信息的主键判断
    ResultSet executeQuery2(String a) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动

            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");

            System.out.println("Opened database successfully!");
            String sql = "select * from public.customer_information where cnumber = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, a);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return rs;
    }
    // 更新数据库内容
    void executeUpdate(String UpdateName, String UpdateNumber, String UpdatePrice ) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动
            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");
            System.out.println("Opened database successfully!");

            String sql = "update public.product set unit_price = ? , pnumber =?  where varieties = ?";
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, UpdatePrice);
            stmt.setString(2, UpdateNumber);
            stmt.setString(3,UpdateName);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    // 按条件查询
    ResultSet executeQueryByCondition(String order_number, String cnumber ) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动
            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");
            System.out.println("Opened database successfully!");

            String sql = "select * from public.sale_information where ordernumber like ? AND cnumber like ?";

            stmt = conn.prepareStatement(sql);
            if (order_number.equals("%")) {
                stmt.setString(1, "%");
            } else {
                stmt.setString(1, "%" + order_number + "%");
            }

            if (cnumber.equals("%")) {
                stmt.setString(2, "%");
            } else {
                stmt.setString(2, "%" + cnumber + "%");
            }

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }

    ResultSet executeQueryByProduct(String Place_product, String type_product ) throws Exception {
        try {
            String driver = "org.postgresql.Driver";
            String sourceURL = "jdbc:postgresql://192.168.233.130:15400/FarmProduct";
            Class.forName(driver); //加载驱动
            conn = DriverManager.getConnection(sourceURL, "brw", "hxc123456HXC");
            System.out.println("Opened database successfully!");

            String sql = "select * from public.product where place_of_product like ? AND type like ?";

            stmt = conn.prepareStatement(sql);
            if (Place_product.equals("%")) {
                stmt.setString(1, "%");
            } else {
                stmt.setString(1, "%" + Place_product + "%");
            }

            if (type_product.equals("%")) {
                stmt.setString(2, "%");
            } else {
                stmt.setString(2, "%" + type_product + "%");
            }

            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
}