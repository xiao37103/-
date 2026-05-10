import pymysql

mydb = pymysql.connect(
    host="localhost",
    user="root",
    password="123456",
    database="ge"
)
anhui_cities = ['合肥市', '芜湖市', '蚌埠市', '淮南市', '马鞍山市', '淮北市', '铜陵市', '安庆市', '黄山市', '滁州市', '阜阳市', '宿州市', '六安市', '亳州市', '池州市', '宣城市']
for i in anhui_cities:
    mycursor = mydb.cursor()
    mycursor.execute(
        f"CREATE TABLE {i} (name VARCHAR(255), type VARCHAR(255), address VARCHAR(255), location VARCHAR(255))")

    mydb.commit()  # 提交事务，保存更改
    print(mycursor.rowcount, "记录插入成功。")
