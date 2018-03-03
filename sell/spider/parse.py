from bs4 import BeautifulSoup
from urllib import request
import ssl
from bs4 import BeautifulSoup
web_str ="https://weidian.com/item.html?itemID=2241963448&pc=1&spider_token=9cea&spider=seller.item_classes.content.1"
headers = {"User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36"}
import os
import re
base_save_dir = 'e:\weidian'
def save_img(img_url,file_name,file_path=base_save_dir):
    #保存图片到磁盘文件夹 file_path中，默认为当前脚本运行目录下的 book\img文件夹
    try:
        if not os.path.exists(file_path):
            print('文件夹',file_path,'不存在，重新建立')
            #os.mkdir(file_path)
            os.makedirs(file_path)
        #获得图片后缀
        #拼接图片名（包含路径）
        filename = '{}{}{}'.format(file_path,os.sep,file_name)
        #下载图片，并保存到文件夹中
        img_request = request.Request(img_url);
        with request.urlopen(img_request) as img_data:
            with open(filename,'wb') as f:
                f.write(img_data.read())
                f.close()
            pass

    except IOError as e:
        print('文件操作失败')
    except Exception as e:
        print ('错误 ：')


def go(text):
    images_address = []

    soup = BeautifulSoup(text,"html5lib")
    item_name = soup.title.text
    item_name =  re.sub('[\/:*?"<>|]','-',item_name)
    print(item_name)

    sliderbox = soup.find(id='headerSliderBox')
    imgs =  sliderbox.find_all("img")

    for i  in imgs:
        if len(i.get('class'))>1:
            images_address.append(i.get('data-src'))
            print(i.get('data-src'))
        else:
            images_address.append(i.get('src'))
    for index,img in enumerate(images_address) :
        save_img(img,str(index)+".jpg",'{}{}{}'.format(base_save_dir,os.sep,item_name))

def wrapper():
    class_file = 'E:\\weidian\\aj6.html'
    with open(class_file,encoding='utf-8',mode='r') as f:
        return f.read()
def get_html_text(url):
    req = request.Request(url)
    req.add_header('User-Agent',
                   'Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25')
    with request.urlopen(req) as f:
        data = f.read()
        text = data.decode('utf-8')
    return text

if __name__ == '__main__':
    # req = request.Request(web_str)
    # req.add_header('User-Agent',
    #            'Mozilla/6.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/8.0 Mobile/10A5376e Safari/8536.25')
    # with request.urlopen(req) as f:
    #     data = f.read()
    #     text = data.decode('utf-8')
    class_text = wrapper()
    wrapper_soup = BeautifulSoup(class_text,'html5lib')
    links = wrapper_soup.find_all('a','link for_gaq')


    for i in links:
        abs_url = 'https://weidian.com'+i.get("href")
        html_text = get_html_text(abs_url)
        go(html_text)

    pass

