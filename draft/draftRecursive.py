import time

# Functions
def copyExc(level, precNum, precStr, newNum, newStr, i, j, op, last):
    ctr = 0
    if (len(precNum) > 2):
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

def createExp(exp1,exp2,op,last): # kalo mo ada versi ga pake kurung tinggal ilangin kurungnya
    exp = exp1 + ' ' + oplist[op] + ' ' + exp2
    if not(last):
        exp = '(' + exp + ')'
    return exp

def createStrArr(numArr):
    strArr = []
    for num in numArr:
        strArr.append(str(num))
    return strArr

def recPerm(level, setStr, precNum, precStr, newNum, newStr):
    if (level == 1):
        if (precNum[0] >= 23.99999999 and precNum[0] <= 24.00000001):
            setStr.add(precStr[0])
    else:
        for i in range(level):
            for j in range(level):
                if (i != j):
                    for op in range(4):
                        res, check = opSwitch(precNum[i],precNum[j],op)
                        if (check):
                            last = False
                            if (level == 2):
                                last = True
                                newerNum = a
                                newerStr = a
                            elif (level == 4):
                                newerNum = cp2
                                newerStr = e2
                            elif (level == 3):
                                newerNum = cp3
                                newerStr = e3
                            copyExc(level, precNum, precStr, newNum, newStr, i, j, op, last)
                            # print("Level:", level)
                            # print('Prec:', precNum[:level],'\t', precStr[:level])
                            # print('New:', newNum[:level-1],'\t', newStr[:level-1])
                            # recPerm(level - 1, setStr, newNum, newStr, precNum, precStr)
                            recPerm(level - 1, setStr, newNum, newStr, newerNum, newerStr)

def printList(list):
    for elmt in list:
        print(elmt)

# Main Algorithm

card = [6,6,6,6]
print('Cards:', card,'\n')
cardStr = createStrArr(card)
oplist = ['+','-','*','/']

a = []
cp1 = [0.0 for i in range(3)]
cp2 = [0.0 for i in range(2)]
cp3 = [0.0 for i in range(1)]
e1 = ['X' for i in range(3)]
e2 = ['X' for i in range(2)]
e3 = ['X' for i in range(1)]

setStr = set([])

start = time.process_time()
recPerm(4, setStr, card, cardStr, cp1, e1)
exec = (time.process_time() - start) * 1000
print(len(setStr), "solution(s) found:")
printList(setStr)
print(f"\nExecution time: %f ms" % exec)