import os
from PIL import Image, ImageDraw, ImageFont

fontPath = "e:/Desktop/shoes/font.ttf"
src_base_pic_dir = "e:/Desktop/shoes/src/"
dst_base_pic_dir = "e:/Desktop/shoes/dst/"

def handle(file):
    print("handle "+file)
    if file.endswith("TTF"):
        return
    if not os.path.isdir(file) and not os.path.isfile(file):
        print(file+"is not dir or file")
        return False
    if os.path.isfile(file):
        relative_path = file.split(src_base_pic_dir)[1]
        outfile = dst_base_pic_dir+relative_path
        addSignNameWaterMark(file,outfile,override=True)
        pass
    elif os.path.isdir(file):
        dirs = os.listdir(file)
        for filename in dirs:
            handle(os.path.join(file,filename))


def addSignNameWaterMark(file,outfile,override = False):
    """
    :param file:
    :param outfile:
    :param override: 如果文件存在是否覆盖
    :return:
    """
    im = Image.open(file).convert("RGBA")
    txt = Image.new("RGBA",im.size,(0,0,0,0))

    fnt = ImageFont.truetype(fontPath,size=int(im.size[0]/10))
    d=ImageDraw.Draw(txt)
    d.text((txt.size[0]-300,txt.size[1]-100), "wx:easygomaixie",font=fnt, fill=(255,255,255,255))
    out=Image.alpha_composite(im, txt)
    safeCreateFile(outfile)
    out.save(outfile,"PNG")


    pass


def safeCreateFile(file):
    path = os.path.dirname(file)
    if not os.path.exists(path):
        os.makedirs(path)

if __name__ == '__main__':

    handle(src_base_pic_dir)
