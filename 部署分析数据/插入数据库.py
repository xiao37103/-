import os

import json
import pymysql

mydb = pymysql.connect(
    host="localhost",
    user="root",
    password="123456",
    database="ge"
)

cursor = mydb.cursor()
data = os.listdir("./json16")
for i in data:
    with open(f"./json16/{i}", 'r', encoding='utf-8') as f:
        code = json.loads(f.read())

    pois = code['pois']

    for i in pois:
        item = {}
        item['name'] = i['name']
        item['type'] = i['type']
        item['address'] = i['address'] if i['address'] else ''  # 如果没有地址信息，则将地址赋为空字符串
        item['location'] = i['location']
        print(item)
        sql = "INSERT INTO 黄山市 (name, type, address, location) VALUES (%s, %s, %s, %s)"
        val = (item['name'], item['type'], item['address'], item['location'])
        cursor.execute(sql, val)

    mydb.commit()
    print(cursor.rowcount, "记录插入成功。")
