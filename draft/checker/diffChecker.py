def setFrFile(filename):
    file = open(filename, "r")
    setFile = set([str.strip().replace(" ", "") for str in file.readlines()])
    return setFile

def printList(list):
    for el in list:
        print(el)

# run di directory yg sejajar y
a = input("File A : ")
b = input("File B : ")

setA = setFrFile(a)
setB = setFrFile(b)

AminB = setA.difference(setB)
BminA = setB.difference(setA)

print("------** A - B **------")
print("n(A - B) =", len(AminB))
printList(AminB)

print("\n------** B - A **------")
print("n(B - A) =", len(BminA))
printList(BminA)