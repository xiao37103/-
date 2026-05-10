// 使用XMLHttpRequest发送HTTP请求 
var xhr = new XMLHttpRequest();
xhr.open('GET', 'https://restapi.amap.com/v5/place/text?key=cb019bea12249e2a57eb393c93a69500&keywords=%E5%AE%89%E5%BE%BD%E5%B7%A5%E7%A8%8B%E5%A4%A7%E5%AD%A6', true);
xhr.send();

console.log(xhr);