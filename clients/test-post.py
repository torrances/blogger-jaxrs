import requests 

# STATUS CODES:
# 	<http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html>
# 	200 = OK
# 	201 = Created

def p5():
	payload = { 'author' : 'p5-author', 'id' : '9995', 'title' : 'p5-title' }
	headers = {u'content-type': u'application/x-www-form-urlencoded'}	
	r = requests.post("http://192.168.1.26:8080/blogger/demo/endpoint/form", params=payload, headers=headers)	
	test(r.status_code)
	return r.text

def p6():
	r = requests.post( 'http://192.168.1.26:8080/blogger/demo/endpoint/{"author":"this is my content","title":"my title","id":9956}' )
	test(r.status_code)
	return r.text

def test(status_code):
	print 'status_code:', status_code
	assert (200 == status_code or 201 == status_code)

p5();
p6()