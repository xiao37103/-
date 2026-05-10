import requests
import pymysql

headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36"
}
for i in ['屯溪区', '黄山区', '徽州区']:
    for page in range(1, 3):
        url = "https://restapi.amap.com/v3/place/text"
        params = {
            "platform": "JS",
            "s": "rsv3",
            "logversion": "2.0",
            "key": "83ffb61a50076602733441db0331b179",
            "sdkversion": "2.0.5.16",
            "appname": "http%3A%2F%2Flocalhost%3A8080%2F%23%2FDierselection%2FNavagation3",
            "csid": "525D60F7-451C-4F32-A134-3C401F78A2D1",
            "jscode": "f6751b6456c8c736ed0819aaffd898b2",
            "city": "",
            "page": str(page),
            "offset": "50",
            "extensions": "all",
            "citylimit": "true",
            "language": "zh_cn",
            "children": "",
            "type_": "KEYWORD",
            "antiCrab": "true",
            "keywords": f"安徽省黄山市{i}充电站",
        }
        response = requests.get(url, params=params, headers=headers).text
        with open(f"./json16/{i}+{page}.json", 'a', encoding='utf-8') as f:
            f.write(response)
