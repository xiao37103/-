import json

import requests
from lxml import etree
import pandas as pd

from sqlalchemy import create_engine

with open("new_data.json1", 'r', encoding='utf-8') as f:
    data1 = json.load(f)
list_1 = []
for j in data1.values():
    list_1.append(j)


for i in list_1:
    url = f"https://www.modiauto.com.cn/cdz/c{i}.html"
    conn = create_engine('mysql+pymysql://root:123456@localhost:3306/wjx?charset=utf8')
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36"
    }

    resp = requests.get(url, headers=headers)
    item = {}
    html = etree.HTML(resp.text)
    data = html.xpath("//table//tr[@class='cdzname']")
    list1 = []
    list2 = []
    list3 = []
    for j in data:
        list1.append(j.xpath("./td[1]/text()")[-1])
        list2.append(j.xpath("./td[2]/text()")[-1])
        list3.append(j.xpath("./td[3]/text()")[-1])
    df = pd.DataFrame({
        "名称": list1,
        "运营方": list2,
        "数量": list3,
    })
# 存数据库
    df.to_sql(name=f"table_{i}", con=conn, schema='wjx', index=False, index_label=False, if_exists='append',
              chunksize=1000)
