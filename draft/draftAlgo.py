card = [6,6,6,6]
oplist = ['+','-','*','/']
# 0 : +, 1 : -, 2 : *, 3 : /

# Functions
def copyExc(precNum, precStr, newNum, newStr,i,j,op, last):
    if (len(precNum) > 2):
        ctr = 0
        for k in range(len(precNum)):
            if (k != i and k != j):
                newNum[ctr] = precNum[k]
                newStr[ctr] = precStr[k]
                ctr += 1
    newNum[-1] = opSwitch(precNum[i],precNum[j],op)
    newStr[-1] = createExp(precStr[i],precStr[j],op,last)

    
def opSwitch(a,b,op):
    if (op == 0):
        return a + b
    elif (op == 1):
        return a - b
    elif (op == 2):
        return a * b
    elif (b != 0): # try catch error dulu
        return a / b

def createExp(exp1,exp2,op,last): # kalo bisa disatuin sama yang atas biar hemat checking
    exp = exp1 + oplist[op] + exp2
    if not(last):
        exp = '(' + exp + ')'
    return exp

print(createExp('1','2',2,False))
cardStr = ['6','6','6','6']
# Main Algorithm
cp1 = [0.0 for i in range(3)]
cp2 = [0.0 for i in range(2)]
cp3 = [0.0 for i in range(1)]
e1 = ['**' for i in range(3)]
e2 = ['**' for i in range(2)]
e3 = ['**' for i in range(1)]
ctSol = 0
print(card, cardStr)
for i in range(4):
    for j in range(4):
        if (i != j):
            for op in range(4):
                copyExc(card, cardStr, cp1, e1,i,j,op,False)
                #print("\t",cp1)
                for k in range(3):
                    for l in range(3):
                        if (k != l):
                            for op in range(4):
                                copyExc(cp1, e1, cp2, e2,k,l,op,False)
                                #print("\t\t",cp2)
                                for m in range(2):
                                    for n in range(2):
                                        if (m != n):
                                            for op in range(4):
                                                copyExc(cp2, e2, cp3, e3,m,n,op,True)
                                                #print("\t\t\t",cp3, end = "")
                                                if (cp3[0] == 24): #warning tolerance
                                                    #print("\t\t\tCHECK, ", end = '')
                                                    print(e3[0])
                                                    ctSol += 1
                                                #else:
                                                    #print(", ")
                                            #print()
                                                # if (cp3[0] == 24): #warning tolerance
                                                #     print(card,'\t',cp1,'\t',cp2,'\t',cp3)                                                
print(ctSol)  
# tes floating point
# x = 10
# y = 120000000
# z = 120000000

# print(x/y*z,(x/y)*z, x*z/y)