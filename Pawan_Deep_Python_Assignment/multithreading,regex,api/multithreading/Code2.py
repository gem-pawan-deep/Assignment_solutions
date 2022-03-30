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
th1 = threading.Thread(target=square, args=(arr, ))
th2 = threading.Thread(target=cube, args=(arr, ))
th1.start()
th2.start()
th1.join()
th2.join()
print("Total time with threading:",time.time() - t1)