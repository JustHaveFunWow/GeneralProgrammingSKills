import urllib
import urllib.error, urllib.request, urllib.parse
import http.cookiejar

HOST = 'https://api.diyidan.net'
LOGIN_URL = "https://api.diyidan.net/v0.2/users/login"
GET_CONCERN_USERS = HOST + '/v0.2/main/recommend?callType=102'
values = {'account': '15080182823', 'password': '169048'}
postdata = urllib.parse.urlencode(values).encode()
headers = {
    'User-Agent': 'Mozilla/5.0 (Linux; U; Android 4.3; en-us; SM-N900T Build/JSS15J) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30'}

data = urllib.parse.urlencode(values).encode();

request = urllib.request.Request(LOGIN_URL, data);

cookie_filename = 'cookie.txt'

cookie_aff = http.cookiejar.MozillaCookieJar(cookie_filename)
handler = urllib.request.HTTPCookieProcessor(cookie_aff)
opener = urllib.request.build_opener(handler)

request = urllib.request.Request(LOGIN_URL, postdata, headers)

try:
    response = opener.open(request)
except urllib.error.URLError as e:
    print(e.reason)

cookie_aff.save(ignore_discard=True, ignore_expires=True)

for item in cookie_aff:
    print('Name =' + item.name)
    print('Value =' + item.value)

get_request = urllib.request.Request(GET_CONCERN_USERS,headers=headers)
get_users_response = opener.open(get_request)
print(get_users_response.read().decode())

