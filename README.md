# SUPERMARKET
## Description

This repository is a System of sales (DESKTOP) with JAVA and MySQL.

## Installation
Using JAVA, MySQL preferably.

## DataBase
Using MySQL preferably.

## Usage
```html
$ git clone https://github.com/DanielArturoAlejoAlvarez/SUPERMARKET.git [NAME APP] 

```
Follow the following steps and you're good to go! Important:


![alt text](https://d3nmt5vlzunoa1.cloudfront.net/idea/files/2018/02/image37.gif)


## Coding

### Models

```java
...

public String idOrder() {
    String idv = "";
    String sql = "SELECT max(SALE_Code) FROM sales";
    try {
        conn = cx.connect();
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        
        while (rs.next()) {                
            idv = rs.getString(1);
        }
        
    } catch (Exception e) {
    }
    
    return idv;
}

public int addOrder(Order o) {        
    String sql = "INSERT INTO sales (CLIE_Code,USR_Code,SALE_Number,SALE_Date,SALE_Total,SALE_Flag) VALUES (?,?,?,?,?,?)";
    try {
        conn = cx.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, o.getIdClient());
        ps.setInt(2, o.getIduser());
        ps.setString(3, o.getSerialNumber());
        ps.setString(4, o.getDate());
        ps.setDouble(5, o.getTotal());
        ps.setString(6, o.getFlag());
        r = ps.executeUpdate(); 
        
    } catch (Exception e) {
    }
    
    return r;
}
    
public int addOrderItem(OrderItem det) {
    String sql = "INSERT INTO sale_details (PROD_Code,SALE_Code,DETA_Qty,DETA_Subtotal) VALUES (?,?,?,?)";
    try {
        conn = cx.connect();
        ps = conn.prepareStatement(sql);
        ps.setInt(1, det.getIdProduct());
        ps.setInt(2, det.getIdOrder());
        ps.setInt(3, det.getQty());
        ps.setDouble(4, det.getSubtotal());
        r = ps.executeUpdate(); 
    } catch (Exception e) {
    }
    
    return r;        
}
...

```
### VIEWS

```java
...

 void addProduct() {
    double subtotal;
    int item = 0;
    model = (DefaultTableModel) tbOrderItems.getModel();
    item = item+1;
    
    idp = Integer.parseInt(txtCodProd.getText());
    String name = txtProduct.getText();
    price = Double.parseDouble(txtPrice.getText());
    int stock = Integer.parseInt(txtStock.getText());
    qty = Integer.parseInt(spnQty.getValue().toString());
    
    subtotal = qty*price;
    ArrayList list = new ArrayList();
    if(stock>0) {
        list.add(item);
        list.add(idp);
        list.add(name);
        list.add(qty);
        list.add(price);
        list.add(subtotal);
        
        Object[] obj = new Object[6];
        obj[0] = list.get(0);
        obj[1] = list.get(1);
        obj[2] = list.get(2);
        obj[3] = list.get(3);
        obj[4] = list.get(4);
        obj[5] = list.get(5);
        
        model.addRow(obj);
        tbOrderItems.setModel(model);
        
        calculateTotal();
        
    }else {
        JOptionPane.showMessageDialog(this, "product stock not available");
    }
    
}

void calculateTotal() {
    totalPayment = 0;
    for (int i = 0; i < tbOrderItems.getRowCount(); i++) {
        qty = Integer.parseInt(tbOrderItems.getValueAt(i, 3).toString());
        price = Double.parseDouble(tbOrderItems.getValueAt(i, 4).toString());
        totalPayment = totalPayment + (qty*price);            
    }
    txtTotal.setText(""+totalPayment);
}

void searchProduct() {
    int id = Integer.parseInt(txtCodProd.getText());
    if (txtCodProd.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "You must enter customer code");
    }else {
        Product product = pdao.listID(id);
        if(product.getId() != 0) {
            txtProduct.setText(product.getName());
            txtPrice.setText(""+product.getPrice());
            txtStock.setText(""+product.getStock());
            
        }else {
            JOptionPane.showMessageDialog(this, "Unregistered product, do you want to register?");
            txtCodProd.requestFocus();
        }
    }
}

void searchClient() {
    int r=0;
    String codClient = txtCodClie.getText();
    if(txtCodClie.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "You must enter customer code");
    }else {
        client = cdao.listID(codClient);
        
        if(client.getDni() != null) {
            txtClient.setText(client.getDisplayName());
            txtCodProd.requestFocus();
        }else {
            r = JOptionPane.showConfirmDialog(this, "Unregistered customer, do you want to register?");
            
            if(r==0) {
                ClientForm cf = new ClientForm();
                Principal.winPrincipal.add(cf);
                cf.setVisible(true);
            }
        }
    }
}
    
void addOrder() {
    User usr = udao.userByName(txtUser.getText());
    int idu = usr.getId();        
    int idc = client.getId();
    String serial = txtSerialNumber.getText();
    String date = txtDateOrder.getText();
    double total = totalPayment;
    String status = "ACTIVE";
    
    o.setIduser(idu);
    o.setIdClient(idc);
    o.setSerialNumber(serial);
    o.setDate(date);
    o.setTotal(total);
    o.setFlag(status);
    
    odao.addOrder(o);
}

void addOrderItem() {
    String lastID = odao.idOrder();
    int ido = Integer.parseInt(lastID);
    
    for (int i = 0; i < tbOrderItems.getRowCount(); i++) {
        idp = Integer.parseInt(tbOrderItems.getValueAt(i, 1).toString());
        qty = Integer.parseInt(tbOrderItems.getValueAt(i, 3).toString());
        double subTotal = Double.parseDouble(tbOrderItems.getValueAt(i, 4).toString());
        
        oi.setIdOrder(ido);
        oi.setIdProduct(idp);
        oi.setQty(qty);
        oi.setSubtotal(subTotal);
        
        odao.addOrderItem(oi);
    }
            
}


void updateStockProducts() {
    for (int i = 0; i < model.getRowCount(); i++) {
        idp = Integer.parseInt(tbOrderItems.getValueAt(i, 1).toString());
        qty = Integer.parseInt(tbOrderItems.getValueAt(i, 3).toString());
        p = pdao.listID(idp);
        int currentStock = p.getStock() - qty;
        pdao.updateStock(currentStock, idp);
    }
}
...
```

### Config

```java
...
public void validation() {
    String password = txtPassword.getText();
    String username = txtUsername.getText();
    
    if (txtPassword.getText().equals("") || txtUsername.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "You must enter data in the text boxes");
        txtUsername.requestFocus();
    }else {
        user = udao.validateUser(password, username);
        if(user.getUsername() != null && user.getDni() != null) {
            Principal p = new Principal();
            p.setVisible(true);
            dispose();
            
            p.txtUser2.setText(user.getDisplayName());
            
        }else {
            JOptionPane.showMessageDialog(this, "You must enter valid user");
            txtUsername.requestFocus();
        }
    }
}
...
```

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/DanielArturoAlejoAlvarez/SUPERMARKET. This project is intended to be a safe, welcoming space for collaboration, and contributors are expected to adhere to the [Contributor Covenant](http://contributor-covenant.org) code of conduct.


## License

The gem is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).