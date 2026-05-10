import requests
import json
# 从 data.json1 文件中读取 JSON 数据
with open("data.json1", "r") as f:
    my_properties = json.load(f)
# 设置请求参数
url = "https://api.openstreetmap.org/api/0.6/map?bbox=11.54,48.14,11.543,48.145"
headers = {"Content-type": "application/json1"}
# 发送请求
response = requests.get(url, headers=headers)
# 处理响应
if response.status_code == 200:
    # 解析响应数据
    data = response.text
    # 转换为geojson格式
    geojson_data = {
        "type": "FeatureCollection",
        "features": []
    }
    ways = data.split("<way")
    for way in ways:
        if 'k="highway"' in way:
            coords = []
            nodes = way.split("<nd")
            for node in nodes:
                if 'lat="' in node:
                    lat = float(node.split('lat="')[1].split('"')[0])
                    lon = float(node.split('lon="')[1].split('"')[0])
                    coords.append([lon, lat])
            if len(coords) > 1:
                feature = {
                    "type": "Feature",
                    "geometry": {
                        "type": "LineString",
                        "coordinates": coords
                    },
                    "properties": my_properties
                }
                geojson_data["features"].append(feature)
    # 保存为geojson文件
    with open("output.geojson", "w") as f:
        json.dump(geojson_data, f)
else:
    # 处理错误
    print("请求失败：", response.status_code)