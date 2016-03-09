str2='1x^3+2x^2+3x^2+4x^3'
str3='1x^1*2x^3+2x^4*3x^2*1x^2'
def multX(str1):
	exps=dict()
	result=''
	i=0
	while i < len(str1):
		if str1[i]=='*':
			str1=str1[0:prevOp(str1,i)+1]+str(getPrev(str1,getPrevX(str1,i)-1)*getPrev(str1,getNextX(str1,i)-1))+'x^'+str(getPrev(str1,i-1)+getNext(str1,getNextX(str1,i)+2))+str1[nextOp(str1,i):len(str1)]
			i=0
		i+=1
	return str1[1:len(str1)]
def getPrevX(str1,i):
	j=i-1
	while j>0 and str1[j]!='x':
		j-=1
	return j
def getNextX(str1,i):
	j=i+1
	while j<len(str1) and str1[j]!='x':
		j+=1
	return j
def prevOp(str1,i):
	operants=['+','-','*','/']
	j=i-1
	while j>0 and not str1[j] in operants:
		j-=1
	return j			
def nextOp(str1,i):
	operants=['+','-','*','/']
	j=i+1
	while j<len(str1) and not str1[j] in operants:
		j+=1
	return j
def addUpX(str1):
	result=''
	exps=dict()
	for i in xrange(len(str1)):
		if str1[i]=='x':
			if getNext(str1,i+2) in exps:
				exps[getNext(str1,i+2)]+=getPrev(str1,i-1)
			else:
				exps[getNext(str1,i+2)]=getPrev(str1,i-1)
	for i in exps:
		result+=str(exps[i])+'x^'+str(i)+'+'
	return  result
def getPrev(str1,i):
	j=i
	result=''
	while j>=0:
		if isNumeric(str1[j]):
			result=str1[j]+result
		else:
			break
		j-=1
	return int(result)
def getNext(str1,i):
	j=i
	result=''
	while j<len(str1):
		if isNumeric(str1[j]):
			result+=str1[j]
		else:
			break
		j+=1
	return int(result)
def isNumeric(str1):
	numr=['0','1','2','3','4','5','6','7','8','9','.']
	return str1 in numr
print addUpX(str2)
print str(multX(str3))