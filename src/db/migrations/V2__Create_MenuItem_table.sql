CREATE TABLE MenuItem (
    id INT PRIMARY KEY,
    itemname VARCHAR(30) NOT NULL,
    itemdescription VARCHAR(50) NOT NULL,
    price Decimal(10,2) NOT NULL
);

CREATE TABLE Orders (
    orderid INT PRIMARY KEY,
    orderdate TIMESTAMP NOT NULL,
    customername VARCHAR(50)
);

CREATE TABLE OrderItem (
    orderitemid INT PRIMARY KEY,
    orderid INT NOT NULL,
    menuitemid INT NOT NULL,
    quantity INT NOT NULL,
    lineprice DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (orderid) REFERENCES Orders(orderid),
    FOREIGN KEY (menuitemid) REFERENCES MenuItem(id)
);
