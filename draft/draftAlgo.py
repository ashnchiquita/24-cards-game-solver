import time

# Functions
def copyExc(precNum, precStr, newNum, newStr, i, j, op, last):
    if (len(precNum) > 2):
        ctr = 0
        for k in range(len(precNum)):
            if (k != i and k != j):
                newNum[ctr] = precNum[k]
                newStr[ctr] = precStr[k]
                ctr += 1
    newNum[-1], check = opSwitch(precNum[i],precNum[j],op)
    newStr[-1] = createExp(precStr[i],precStr[j],op,last)

def opSwitch(a,b,op):
    status = True
    if (op == 0):
        res = a + b
    elif (op == 1):
        res = a - b
    elif (op == 2):
        res = a * b
    else:
        if (b != 0):
            res = a / b
        else :
            res = 0
            status = False
    return res, status

def createExp(exp1,exp2,op,last):
    exp = exp1 + ' ' + oplist[op] + ' ' + exp2
    if not(last):
        exp = '(' + exp + ')'
    return exp

def createStrArr(numArr):
    strArr = []
    for num in numArr:
        strArr.append(str(num))
    return strArr

def printList(list):
    for elmt in list:
        print(elmt)

# Main Algorithm
card = [6,6,6,6]
print('Cards:', card,'\n')
cardStr = createStrArr(card)
oplist = ['+','-','*','/']
cp1 = [0.0 for i in range(3)]
cp2 = [0.0 for i in range(2)]
cp3 = [0.0 for i in range(1)]
e1 = ['**' for i in range(3)]
e2 = ['**' for i in range(2)]
e3 = ['**' for i in range(1)]

setStr = set([])

start = time.process_time()
for i in range(4):
    for j in range(4):
        if (i != j):
            for op in range(4):
                res, check = opSwitch(card[i],card[j],op)
                if (check):
                    copyExc(card, cardStr, cp1, e1,i,j,op,False)
                    for k in range(3):
                        for l in range(3):
                            if (k != l):
                                for op in range(4):
                                    res, check = opSwitch(cp1[k],cp1[l],op)
                                    if (check):
                                        copyExc(cp1, e1, cp2, e2,k,l,op,False)
                                        for m in range(2):
                                            for n in range(2):
                                                if (m != n):
                                                    for op in range(4):
                                                        res, check = opSwitch(cp2[m],cp2[n],op)
                                                        if (check):
                                                            copyExc(cp2, e2, cp3, e3,m,n,op,True)
                                                            if (cp3[0] <= 24.00000001 and cp3[0] >= 23.99999999): #warning tolerance
                                                                setStr.add(e3[0])
exec = (time.process_time() - start)* 1000
print(len(setStr), "solution(s) found:")
printList(setStr)
print(f"\nExecution time: %f ms" % exec)