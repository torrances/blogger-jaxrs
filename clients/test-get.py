import requests 

def g1():
	r = requests.get('http://192.168.1.26:8080/blogger/demo/endpoint/123')
	test(r.status_code)

def g2():
	payload = { 'id': '123' }
	headers = {'content-type': 'application/json'}
	r = requests.get('http://192.168.1.26:8080/blogger/demo/endpoint/get', params=payload)
	test(r.status_code)

def test(status_code):
	print 'status_code:', status_code
	assert (200 == status_code or 201 == status_code)

g1()
g2()
