import time
import threading
from threading import *
def square(a):
    for n in a:
        time.sleep(1)
        print(f" Square of {n} =  {n * n}  ")
def cube(a):
    for n in a:
        time.sleep(1)
        print(f" Cube of {n} =  {n * n* n}  ")
arr = [1,2,3,4,5,6,7]
t1 = time.time()
square(arr)
cube(arr)
print("Total time without threading:", time.time() - t1)